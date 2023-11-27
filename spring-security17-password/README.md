跨域前提是在浏览器下执行的:不同域名之间会有限制
server:http://a.test.com/login

client:http://b.test.com

SpringBoot+SpringSecurity解决跨域:
1、SpringBoot中需要配置
2、SpringSecurity中也需要配置

Vue3 vite:中需要配置

如果我们使用了nginx

csrf:跨域请求伪造
公共场合登录了系统:cookie、session
有一个黑客把我的cookie、session获取到了 在另外一个地方【系统】中用这个cookie登录系统

我们系统需要去防护:
1、value=对称加密+本机ip+。。。。。---->服务器解密

