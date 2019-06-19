## 基本环境搭建

### 四项确认

1. 确认系统网络
2. 确认yum可用
3. 确认关闭 `iptables` 规则

```shell
#查看iptables规则
iptables -L
iptables -t nat -L
#关闭
iptables -F
iptbles -t nat -F
```

4. 确认停用 `selinux`

```shell
#查看状态,显示 disable 即为关闭状态
getenforce
#关闭
setenforce 0
```

### 环境调试

#### 两项安装

```shell
#1
yum -y install gcc gcc-c++ autoconf pcre pcre-devel make automake
#2
yum -y install wget http-tools vim
```

#### 一次初始化

```shell
cd/opt
#app存放代码；download网上的源码包；logs自定义日志；work shell脚本；backup 备份
mkdir app download logs work backup
```

## Nginx 介绍

### 中间件

> 层次性更高、负载均衡、内容缓存、安全防护、数据处理等等
>
> nginx 是开源、高性能、可靠的http中间件、代理服务

![1560691548003](.\img\1560691548003.png)

### Nginx 优势：多路 IO 复用

> 1. Nginx 具备的优势，很多其他的服务也有采用（如 Apache 的 event）
>2. Nginx 在功能全面上并不占优势
> 3. Nginx 具备的功能是企业最常使用且需要的
> 4. Nginx 具备的轻量级、性能优势

#### 一、采用IO多路复用Epoll模型

**SELECT 模型**

```
while true{
	select(streams[]){
		for i in streams[]{
			if i has data
			read until unavaiable
		}
	}
}
```

**Epoll 模型**

1. 解决 SELECT 模型对文件句柄FD打开限制
2. 采用callback函数回调机制优化模型效率

#### 二、cpu 亲和（affinity）

> 把cpu核心和Nginx工作进场绑定，把每个worker进场固定在一个cpu上执行，减少切换cpu的cache miss，获得更好的性能

#### 三、sendfile

文件直接通过内核空间传输，不用经过用户空间

## Nginx 基础

### 一、Nginx 快速搭建与基本参数使用

- Mainline version - 开发版

  1. CHANGES - 版本变动

  2. pgp 安全校验

- Stable vsersion - 稳定版

- Legacy version - 历史版本

- Pre-Built Packages - 系统安装方式

### 二、基本参数使用

#### 安装目录

`rpm -ql nginx`

![Nginx 安装目录](.\img\Nginx 安装目录.png)

#### 编译参数

`nginx -V`

```shell
nginx version: nginx/1.16.0
built by gcc 4.8.5 20150623 (Red Hat 4.8.5-36) (GCC) 
built with OpenSSL 1.0.2k-fips  26 Jan 2017
TLS SNI support enabled
configure arguments: 
#安装目的的目录或路径
--prefix=/etc/nginx 
--sbin-path=/usr/sbin/nginx 
--modules-path=/usr/lib64/nginx/modules 
--conf-path=/etc/nginx/nginx.conf 
--error-log-path=/var/log/nginx/error.log 
--http-log-path=/var/log/nginx/access.log 
--pid-path=/var/run/nginx.pid 
--lock-path=/var/run/nginx.lock
#执行对应模块时，Nginx所保留的临时性文件
--http-client-body-temp-path=/var/cache/nginx/client_temp 
--http-proxy-temp-path=/var/cache/nginx/proxy_temp 
--http-fastcgi-temp-path=/var/cache/nginx/fastcgi_temp 
--http-uwsgi-temp-path=/var/cache/nginx/uwsgi_temp
--http-scgi-temp-path=/var/cache/nginx/scgi_temp
#设定Nginx进程启动的用户和组用户
--user=nginx 
--group=nginx
#Nginx启用的一些模块
--with-compat 
--with-file-aio 
--with-threads 
--with-http_addition_module 
--with-http_auth_request_module 
--with-http_dav_module 
--with-http_flv_module
--with-http_gunzip_module 
--with-http_gzip_static_module 
--with-http_mp4_module 
--with-http_random_index_module
--with-http_realip_module 
--with-http_secure_link_module
--with-http_slice_module 
--with-http_ssl_module 
--with-http_stub_status_module
--with-http_sub_module 
--with-http_v2_module 
--with-mail 
--with-mail_ssl_module
--with-stream 
--with-stream_realip_module
--with-stream_ssl_module 
--with-stream_ssl_preread_module
#设置额外的参数将被添加到CFLAGS变量
--with-cc-opt='-O2 -g -pipe -Wall -Wp,-D_FORTIFY_SOURCE=2 -fexceptions -fstack-protector-strong --param=ssp-buffer-size=4 -grecord-gcc-switches -m64 -mtune=generic -fPIC' 
#设置附加参数，链接系统库
--with-ld-opt='-Wl,-z,relro -Wl,-z,now -pie'
```

#### Nginx 基本配置语法

`/etc/nginx/nginx.conf`

```shell
#设置nginx服务的系统使用用户
user  nginx;
#工作进程数
worker_processes  1;
#nginx的错误日志
error_log  /var/log/nginx/error.log warn;
#nginx服务启动时候的pid
pid        /var/run/nginx.pid;

events {
	#每个进程允许最大连接数，企业需要优化
    worker_connections  1024;
    #工作进程数
	#use
}

#http可以包含多个server
http {

    include       /etc/nginx/mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;

    sendfile        on;
    #tcp_nopush     on;

	#超时时间，$秒
    keepalive_timeout  65;

    #gzip  on;

	#另外加载 /etc/nginx/conf.d/ 目录下的 *.conf 文件
    include /etc/nginx/conf.d/*.conf;
}
```

`/etc/nginx/conf.d/default.conf`

```shell

#可以包含多个location
server {
	#监听的端口
    listen       80;
    #服务名（域名地址）
    server_name  localhost;
    #access_log  /var/log/nginx/host.access.log  main;

	# / 首页
    location / {
    	#首页路径
        root   /usr/share/nginx/html;
        #默认访问的页面
        index  index.html index.htm;
    }

    #error_page  404              /404.html;

    # redirect server error pages to the static page /50x.html
    #
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   /usr/share/nginx/html;
    }

    # proxy the PHP scripts to Apache listening on 127.0.0.1:80
    #
    #location ~ \.php$ {
    #    proxy_pass   http://127.0.0.1;
    #}

    # pass the PHP scripts to FastCGI server listening on 127.0.0.1:9000
    #
    #location ~ \.php$ {
    #    root           html;
    #    fastcgi_pass   127.0.0.1:9000;
    #    fastcgi_index  index.php;
    #    fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
    #    include        fastcgi_params;
    #}

    # deny access to .htaccess files, if Apache's document root
    # concurs with nginx's one
    #
    #location ~ /\.ht {
    #    deny  all;
    #}
}
```

### 三、Http 请求

客户端 request 包含：请求行、请求头部、请求数据

服务端 response 包含：状态行、消息报头、响应正文

`curl -v http://www.baidu.com >/dev/null`

```powershell
> GET / HTTP/1.1
> User-Agent: curl/7.29.0
> Host: www.baidu.com
> Accept: */*
> 
< HTTP/1.1 200 OK
< Accept-Ranges: bytes
< Cache-Control: private, no-cache, no-store, proxy-revalidate, no-transform
< Connection: Keep-Alive
< Content-Length: 2381
< Content-Type: text/html
< Date: Mon, 17 Jun 2019 03:27:26 GMT
< Etag: "588604dd-94d"
< Last-Modified: Mon, 23 Jan 2017 13:27:57 GMT
< Pragma: no-cache
< Server: bfe/1.0.8.18
< Set-Cookie: BDORZ=27315; max-age=86400; domain=.baidu.com; path=/
```

### 四、多套业务服务配置

一个Nginx启用多个虚拟主机服务配置多个业务服务，**虚拟主机配置方式**

#### 基于主机多IP方式

方式一、多网卡多IP方式，设备要求比较高

方式二、单网卡多IP方式，用网卡别名来对应多个IP

1. 查看网卡和IP：`ip a`

```powershell
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN group default qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
    inet6 ::1/128 scope host 
       valid_lft forever preferred_lft forever
2: enp2s0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc pfifo_fast state DOWN group default qlen 1000
    link/ether 68:f7:28:3f:a2:23 brd ff:ff:ff:ff:ff:ff
#真正的网卡
3: wlp3s0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default qlen 1000
    link/ether ac:b5:7d:74:22:8f brd ff:ff:ff:ff:ff:ff
    inet 192.168.1.80/24 brd 192.168.1.255 scope global noprefixroute dynamic wlp3s0
       valid_lft 78515sec preferred_lft 78515sec
    inet6 fe80::fc69:b464:5bf4:7538/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever
4: virbr0: <NO-CARRIER,BROADCAST,MULTICAST,UP> mtu 1500 qdisc noqueue state DOWN group default qlen 1000
    link/ether 52:54:00:1f:9d:85 brd ff:ff:ff:ff:ff:ff
    inet 192.168.122.1/24 brd 192.168.122.255 scope global virbr0
       valid_lft forever preferred_lft forever
5: virbr0-nic: <BROADCAST,MULTICAST> mtu 1500 qdisc pfifo_fast master virbr0 state DOWN group default qlen 1000
    link/ether 52:54:00:1f:9d:85 brd ff:ff:ff:ff:ff:ff
```

2.  添加网卡前查看准备的ip是否在使用

```powershell
ping 192.168.1.166
#显示不可达即可使用
From 192.168.1.80 icmp_seq=1 Destination Host Unreachable
From 192.168.1.80 icmp_seq=2 Destination Host Unreachable
From 192.168.1.80 icmp_seq=3 Destination Host Unreachable
From 192.168.1.80 icmp_seq=4 Destination Host Unreachable
```

3. 添加 ip

`ip a add 192.168.1.166/24 dev wlp3s0`

4. 查看添加情况

```powershell
ip a
3: wlp3s0: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc noqueue state UP group default qlen 1000
    link/ether ac:b5:7d:74:22:8f brd ff:ff:ff:ff:ff:ff
    #原ip地址
    inet 192.168.1.80/24 brd 192.168.1.255 scope global noprefixroute dynamic wlp3s0
       valid_lft 76717sec preferred_lft 76717sec
    #多了一个ip地址
    inet 192.168.1.166/24 scope global secondary wlp3s0
       valid_lft forever preferred_lft forever
    inet6 fe80::fc69:b464:5bf4:7538/64 scope link noprefixroute 
       valid_lft forever preferred_lft forever

ping 192.168.1.166
#可以ping通了
64 bytes from 192.168.1.166: icmp_seq=1 ttl=64 time=0.044 ms
64 bytes from 192.168.1.166: icmp_seq=2 ttl=64 time=0.079 ms
64 bytes from 192.168.1.166: icmp_seq=3 ttl=64 time=0.080 ms
64 bytes from 192.168.1.166: icmp_seq=4 ttl=64 time=0.035 ms
```

5. 新增配置

`/etc/nginx/conf.d/vserver1.conf`

```powershell
server {
	#vserver1 ip地址
    listen       192.168.1.80:80;
    server_name  localhost;
    location / {
    	#vserver1 路径
        root   /opt/app/code;
        index  index.html index.htm;
    }
}
```

`/etc/nginx/conf.d/vserver2.conf`

```powershell
server {
	#vserver2 ip地址
    listen       192.168.1.166:80;
    server_name  localhost;
    location / {
    	#vserver2 路径
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }
}
```

#### 基于端口的配置方式

```powershell
#查看本机端口是否在使用
ss -luntp
#更改配置端口为81
server {
    #listen      80;
    listen 		 81;
    server_name  localhost;
    location / {
        root   /opt/app/code;
        index  index.html index.htm;
    }
}
```

#### 基于多hosts名称方式（多域名方式）

1. 服务端

```powershell
server {
    listen 		 80;
    #配置虚拟主机2
    server_name  vserver2.com;
    location / {
        root   /opt/app/code;
        index  index.html index.htm;
    }
}
```

2. 客户端更改 hosts

### 五、Nginx 日志

Nginx 日志类型包括 ：error.log、 acess_log（访问日志）

```powershell
#log 配置
http {
# log_format 别名 '"变量1" "变量2"'
    log_format  main  '$http_user_agent' 
    				  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  /var/log/nginx/access.log  main;
}

#配置语法检查
[root@localhost nginx]# nginx -t -c /etc/nginx/nginx.conf
nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
nginx: configuration file /etc/nginx/nginx.conf test is successful
#重新加载配置
[root@localhost nginx]# nginx -s reload  -c /etc/nginx/nginx.conf
[root@localhost nginx]# ps -aux|grep nginx
nginx    19988  0.0  0.0  48992  2084 ?        S    15:34   0:00 nginx: worker process
root     22487  0.0  0.0 112728   988 pts/1    S+   15:34   0:00 grep --color=auto nginx
root     29728  0.0  0.0  46480  1948 ?        Ss   14:25   0:00 nginx: master process /usr/sbin/nginx -c /etc/nginx/nginx.conf

```

### 六、Nginx 模块

> 分为官方模块、第三方模块

#### http_stub_status_module

> Nginx 的客户端状态
>
> 配置语法：
>
> ```powershell
> Syntax：stub_status;
> Default:-
> Context:server,location
> ```

```powershell
[root@localhost]# vim /etc/nginx/conf.d/default.conf
server {
    location /mystatus{
    	stub_status;
    }
}
```

```html
<!--当前的链接数-->
Active connections: 2 
<!--握手总次数、总的链接数、处理的总次数-->
server accepts handled requests
 14 14 9 
<!--读的个数、写的个数、等待个数-->
Reading: 0 Writing: 1 Waiting: 1 
```

#### http_random_index_module

>目录中选择一个随机主页，不包括隐藏文件
>
>配置语法
>
>```powershell
>Syntax：random_index on|off;
>Default:random_index off;
>Context:location
>```

```powershell
[root@localhost]# vim /etc/nginx/conf.d/default.conf
server {
    location /{
    	root /usr/share/nginx/html;
    	random_index on;
    	#index index.html index.htm
    }
}
```

#### http_sub_module

> http 内容替换
>
> 配置语法
>
> ```powershell
> Syntax:sub_filter string replacement;
> Default:-;
> Context:http,server,location
> 
> Syntax:sub_filter_last_modified on|off;
> Default:sub_filter_last_modified off
> Context:http,server,location
> 
> Syntax:sub_filter_once on|off;
> Default:sub_filter_once on;
> Context:http,server,location
> ```

```powershell
[root@localhost]# vim /etc/nginx/conf.d/default.conf
server {
    location /{
    	sub_filter 'old内容' 'new内容';
    	#替换全局
    	sub_filter_once off;
    }
}
#记得清理浏览器缓存
```

### 七、Nginx 请求限制

| HTTP   协议版本 | 连接关系        |
| --------------- | --------------- |
| HTTP 1.0        | TCP 不能复用    |
| HTTP 1.1        | 顺序性TCP复用   |
| HTTP 2.0        | 多路复用TCP复用 |

HTTP 多次的请求可以建立在一次的连接之上，对请求的限制在精度上比连接的限制更有效

#### 请求频率的限制

> 请求限制 -limit_req_module
>
> 配置语法
>
> ```powershell
>Syntax:limit_req_zone key zone=name:size  rate=rate;
> Default:-
>Context:http
> 
>Syntax:limit_req zone=name [burst=number] [nodelay];
> Default:-
>Context:http,server,location
> ```

```powershell
[root@localhost]# vim /etc/nginx/conf.d/default.conf
#只允许在 1s 的时间内发起一个
limit_req_zone $binary_remote_addr zone=req_zone:1m rate=1r/s
server {
    location /{
    	#遗留3个延迟响应
    	#limit_req zone=req_zone burst=3 nodelay;
    	#limit_req zone=req_zone burst=3;
    	limit_req zone=req_zone；
    }
}
```

客户端压力请求

```shell
#压力测试工具
#发起50个请求，并发20个
ab -n 50 -c 20 url
```

#### 连接频率的限制

> 连接限制 -limit_conn_module
>
> 配置语法
>
> ```powershell
> Syntax: limit_conn_zone key zone=name:size;
> Default:-
> Context:http
> 
> 
> Syntax:limit_conn_zone number;
> Default:-
> Context:http,server,location
> ```

```powershell
[root@localhost]# vim /etc/nginx/conf.d/default.conf
server {
    location /{
    	#限制服务端同一时刻只允许一个ip来连接
    	limit_conn conn_zone 1;
    }
}
```

### 八、 Nginx 访问控制

#### 基于IP的访问控制

> **http_access_module**
>
> 配置语法：
>
> ```powershell
> #允许：ip地址，CIDR网段，socket方式,所有
> Syntax:allow address|CIDR|unix:|all;
> Default:-
> Context:http,server,location,limit_except
> #禁止
> Syntax:deny address|CIDR|unix:|all;
> Default:-
> Context:http,server,location,limit_except
> ```

```powershell
server {
    listen       80;
    server_name  localhost;
    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }
    #~ 访问路径模式匹配，针对 admin.html
    location ~ ^/admin.html{
     	root   /usr/share/nginx/html;
        #限制客户端ip
        deny  192.168.1.241;
        #其他的ip可以访问
        allow all;
        
        index  index.html index.htm;
    }
}
```

**局限性**

如果中间通过中间件转发，那么就不能精准屏蔽 IP1

![1560947320(1)](.\img\1560947320(1).png)

**补漏方案**

1. 采用 http 头信息控制，如 http_x_forwarded_for；容易被客户端修改

http_x_forwarded_for，在服务端可以查看到所有的 IP

![1560947527(1)](.\img\1560947527(1).png)

http_x_forwarded_for = Client IP, Proxy(1) IP, Proxy(2) IP, ...

2. 结合 geo 模块
3. 通过 HTTP 自定义变量传递

#### 基于用户的信任登陆

> **http_auth_basic_module**
>
> [配置语法](http://nginx.org/en/docs/http/ngx_http_auth_basic_module.html)：
>
> ```powershell
> Syntax:auth_basic string|off;
> Default:auth_basic off;
> Context:http,server,location,limit_except
> 
> Syntax:auth_basic_user_file filePath;
> Default:-
> Context:http,server,location,limit_except
> ```

```powershell
#创建密码文件
[root@localhost nginx]# htpasswd -c ./auth_conf czx

server {
    listen       80;
    server_name  localhost;
    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
    }
    location ~ ^/admin.html{
     	root   /usr/share/nginx/html;
      	auth_basic "Auth access Test!input your password!";
      	auth_basic_user_file /etc/nginx/auth_conf;
        index  index.html index.htm;
    }
}
```

**局限性**

1. 用户信息依赖文件方式
2. 操作管理机械，效率低下

**补漏方案**

1. Nginx 结合 LUA 实现高效验证
2. Nginx 和 LDAP 打通，利用 nginx-auth-ldap模块

