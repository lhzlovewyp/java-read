

#说明
访问请求为/backend/*的请求均需要登录除了如下几个请求
/backend/doLogin

调用前台相关的请求/user/*,均需要登录.
前后端用户登录信息进行拆分,后端登录信息放到backend.xxx.com下
web服务，登录信息放到m.xxx.com下，登录生成的token信息需要在调用接口时传入到服务端.