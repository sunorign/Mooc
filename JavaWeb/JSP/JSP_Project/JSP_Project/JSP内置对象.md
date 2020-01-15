## JSP 内置对象简介
 - 请求与响应模式
 - JSP 内置对象
    1. 内置对象（又叫隐含对象，有9个内置对象）：不需要预先声明就可以在脚本代码和表达式中随意使用
## JSP 作用域介绍
 - 作用域（数据存储的范围）：pageContext、request、session、application
### request 内置对象（作用域）及out、response 对象应用
- request：封装了由 WEB 浏览器或其他客户端生成 HTTP 请求的细节（参数，属性，头标和数据）
- out：代表输出流的对象
- response：封装了返回到 HTTP 客户端的输出，向页面作者提供设置响应头和状态码
- request 作用域：用户的请求周期
---
发一次请求，创建一个 request 对象。第二次请求获取第一次请求的值，需要请求转发
`request.getRequestDispatcher("第二次请求的页面.jsp").forward(request,response)`
### pageContext 内置对象（作用域）及page 对象应用
- pageContext（上下文）：提供了转发请求到其他资源和包含其他资源的方法，提供获取其他内置对象的方法
- page 对象（this）：代表了正在运行的由 JSP 文件产生的类对象
- pageContext 作用域：当前执行页面
### session 内置对象（作用域）及config、exception 对象应用
- session：主要用于跟踪会话
> 会话是代表用户第一次进入当前系统直到退出系统或关闭浏览器，在此期间服务器的一系列交互
- session 作用域：会话期间
---
设置 session 的有效时间为 5 分钟 
`session.setMaxInactiveInterval(5*60)`
- config 对象：获取配置信息
- exception 对象：异常信息
1. exception 对象只能在错误页面中使用，page 加入一个属性 isErrorPage = "true"
2. 有一个页面出现了异常，在页面中指定一个错误的页面，page 指令中通过 errorPage 来指定：`<%@ errorPage="需要处理异常对象的JSP文件.jsp""%>`
### application 内置对象（作用域）
- application：提供了关于服务器版本，应用级初始化参数和应用内资源绝对路径方式
