## 一、静态资源WEB服务

**静态资源**

非服务器动态运行（计算）生成的文件

![1560949419(1)](./img/1560949419(1).png)

### 静态资源服务场景 CDN

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

### 浏览器缓存

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

## 二、代理服务



## 三、负载均衡调度器



## 四、动态缓存