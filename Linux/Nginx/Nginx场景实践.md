## 一、静态资源WEB服务

**静态资源**

非服务器动态运行（计算）生成的文件

![1560949419(1)](./img/1560949419(1).png)

### 1、静态资源服务场景 CDN

> 传输延时的最小化

![1560949801(1)](./img/1560949801(1).png)

#### 文件读取

 ```nginx
Syntax:sendfile on|off;
 Default:sendfile off;
 Context:http,server,location,if in location
 
 #引读：-with-file-aio 异步文件读取
 ```

#### tcp_nopush

> 多包整合发送

 ```nginx
 Syntax:tcp_nopush on|off;
 Default:tcp_nopush off;
 Context:http,server,location
 #作用：sendfile 开启的情况下，提高网络包的传输效率
 ```

#### tcp_nodelay

> 数据包不等待

```nginx
 Syntax:tcp_nodelay on|off;
 Default:tcp_nodelay on;
 Context:http,server,location
 #作用：keepalive 连接下，提高网络包的传输实时性
```

#### 压缩

```nginx
 Syntax:gzip on|off;
 Default:gzip off;
 Context:http,server,location,if in location
 
 #压缩比
 Syntax:gzip_comp_level level;
 Default:gzip_comp_level 1;
 Context:http,server,location

 #版本控制
 Syntax:gzip_http_version 1.0|1.1;
 Default:gzip_http_version 1.1;
 Context:http,server,location
```

 ![1560955294(1)](./img/1560955294(1).png)



**压缩模块扩展**

1. `http_gzip_static_module` - 预读 gzip 功能

2. `http_gunzip_module` - 应用支持 gunzip 的压缩方式

   对于极少浏览器不支持gzip解压而提供

```nginx
server{
	listen 80;
	server_name 192.168.1.80
	
	sendfile on;
	#charset koi8-r;
	access_log /var/log/nginx/log/static_access.log main
	
    #以 jpg、git、png 结尾的
	location ~ .*\.(jpg|gif|png)${
		gizp on;
		gizp_http_version 1.1;
        gizp_comp_level 2;
        #gzip 压缩的类型
        gzip_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png;
        root /opt/app/code/images;
	}
    
    #以 txt、xml 结尾的 
    location ~ .*\.(txt|xml)${
        #gizp on;
        #gzip_http_version 1.1;
        #gzip_comp_level 1;
        #gzip_types text/plain application/javascript application/x-javascript text/css application/xml text/javascript application/x-httpd-php image/jpeg image/gif image/png;
        root /opt/app/code/doc;
    }
    
    #以 download 开头的
    location ~ ^/download{
        #可以读取 .gzip 的文件
        gzip_static on;
        tcp_nopush on;
        root /opt/app/code;
    }
}
```

### 2、浏览器缓存

#### 校验过期机制

| 作用                    | http 头信息                              |
| ----------------------- | :--------------------------------------- |
| 校验是否过期            | Expires、Cache-Control(max-age) #http1.1 |
| 协议中Etag头信息校验    | Etag                                     |
| Last-Modified头信息校验 | Last-Modified yyyyMMddHHmmss             |

![有缓存的流程图](./img/1561082116(1).png)

#### expires

> 对客户端 Response 的信息里添加Cache-Control、Expires 头

```nginx
Syntax:expires [modified] time;
	   expires epoch |max|off;
Default:expires off;
Context:http,server,location,if in location
```

```nginx
location ~ .*\.(htm|html)${
 	#过期时间 24H
    expires 24h;
    root /opt/app/code;
}
```

### 3、跨域访问

#### 浏览器禁止跨域访问

> 不安全，容易出现CSRF攻击

#### Nginx 打开跨域访问

> 添加头信息

``` nginx
Syntax:add_header name value [always];
Default:-
Context:http,server,location,if in location
```

客户端识别` Access-Control-Allow-Origin` 头信息，如果服务端允许跨域访问，客户端就打开（默认阻止）

```nginx
location ~ .*\.(htm|html)${
    #允许跨域访问的域名
    add_header Access-Control-Allow-Origin http:www.czx.com;
    #允许请求的方法
    add_header Access-Control-Allow-Methods GET,POST,DELETE,OPTIONS;
    root /opt/app/code;
}
```

### 4、防盗链

> 防止网站资源被盗用
>
> 设置思路：区别哪些请求是非正常的用户请求

#### http_refer

> 浏览器上一次请求的地址

```nginx
Syntax：valid_referers none|blocked|server_names|string..;
Default:-
Context:server,location
```

```nginx
location ~ .*\.(jpg|gif|png)${
    #valid_referers 允许哪些 referers 信息来访问
    #none 表示没有带 regerers 信息的
    #blocked 表示没有带协议信息的
    #192.168.1.80
    valid_referers none blocked 192.168.1.80 ~/google\./;
    #请求信息不包含在 valid_referers，$invalid_referer 为1（true）
    if($invalid_referer){
        return 403;
    }
    root /opt/app/code/images;
}
```

## 二、代理服务

### 1、正向代理

> 代理的对象是客户端，为客户端服务

![1561088180(1)](img/1561088180(1).png)

### 2、反向代理

> 代理的对象是服务端，为服务端服务

![1561088387(1)](/img/1561088387(1).png)

### 3、代理配置

| 协议                      | Nginx | Server        |
| :------------------------ | :---: | ------------- |
| **HTTP**                  |   -   | *server       |
| **Websocket**（长链接）   |   -   | Socket server |
| **GRPC**（Go语言）        |   -   | Grpc server   |
| ICMP\POP\IMAP（邮件分发） |   -   | Mail server   |
| **HTTPS**                 |   -   | Http server   |
| RTMP（流媒体）            |   -   | media server  |

| 反向代理模式           | Nginx配置模块           |
| ---------------------- | ----------------------- |
| http、websocket、https | ngx_http_proxy_module   |
| fastcgi                | ngx_http_fastcgi_module |
| uwsgi                  | ngx_http_uwsgi_module   |
| grpc                   | ngx_http_v2_module      |

| 正向代理 | Nginx配置模块         |
| -------- | --------------------- |
| http     | ngx_http_proxy_module |

#### http_proxy

```nginx
#URL:
#http://localhost:8000/uri/
#https://192.168.1.1:8000/uri/
#http://unix:/tmp/backend.socket:/uri/;
Syntax:proxy_pass URL
Default:-
Context:location,if in location,limit_except
```

```nginx
location ~/test_proxy.html${
    proxy_pass http://127.0.0.1:8080
}
```

**代理配置语法补充**

```nginx
#缓冲区
Syntax:proxy_buffering on|off;
Default:proxy_buffering on;
Context：http,server,location

#跳转重定向
Syntax: proxy_redirect default;
		proxy_redirect off;
		proxy_redirect redirect replacement;
Default:proxy_redirect default;
Context:http,server,location

#头信息
Syntax:proxy_set_header field value;
Default:proxy_set_header Host $proxy_host;
		proxy_set_header Connection close;
Context:http,server,location
#扩展：proxy_hide_header、proxy_set_body

#超时
Syntax:proxy_connect_timeout time;
Default:proxy_connect_timeout 60s;
Context:http,server,location
#扩展：proxy_read_timeout、proxy_send_timeout
```

**补充配置和规范**

```nginx
location /{
    proxy_pass http://127.0.0.1:8080;
    include proxy_params;
}
```

`proxy_params` file

```nginx
proxy_redirect default;

proxy_set_header Host $http_host;
proxy_set_header X-Real-IP $remote_addr;

proxy_connect_timeout 30;
proxy_send_timeout 60;
proxy_read_timeout 60;

proxy_buffer_size 32k;
proxy_buffering on;
proxy_buffers 4 128k;
proxy_busy_buffers_size 256k;
proxy_max_temp_file_size 256k;
```

## 三、动态缓存

> 缓存分为：客户端缓存、代理缓存、服务端缓存

### 1、proxy_cache

```nginx
#配置语法
Syntax:proxy_cache_path path [levels=levels][use_temp_path=on|off] key_zone=name:size [inactive=time]
[max_size=size]...
Default:-
Context:http

Syntax:proxy_cache zone|off;
Default:proxy_cache off;
Context:http,server,location

#缓存过期周期
Syntax:proxy_cache_valid [code...] time;
Default:-
Context:http,server,location

#缓存的维度
Syntax:proxy_cache_key string;
Default:proxy_cache_key $scheme$proxy_host$request_uri;
Context:http,server,location
```

```nginx
http{
    #levels 2层目录分级；keys_zone=开辟空间的名称：大小；
    #max_size 最大大小 inactive=不活跃的时间，超过时间清除
    #use_temp_path=off
    proxy_cache_path /opt/app/cache levels=1:2 keys_zone=czx_cache:10m max_size=10g inactive=60m use_temp_path=off;
    server{
        location/{
            proxy_cache czx_cache;
            proxy_pass http://www.baidu.com;
            #返回 200 304的信息，12小时过期
            proxy_cache_valid 200 304 12h
            #返回其他的10分钟过期
            proxy_cache_valid any 10m;
            #缓存的key
            proxy_cache_key $host$uri$is_args$args;
            #缓存头信息
            add_header Nginx-Cache "$upstream_cache_status";
            #出现以下参数跳过，访问下一台服务器
            proxy_next_upstream error timeout invalid_header http_500 http502 http503 http504;
            include proxy_params;
        }
    }
}
```



## 四、