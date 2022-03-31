1. auth-server 登录授权返回jwt
2. gateway全局过滤器，解析header中jwt鉴权
3. 业务服务过滤器解析header中jwt获取用户id，放入当前threadlocal中。需要用户信息时根据用户id查询auth-server获取