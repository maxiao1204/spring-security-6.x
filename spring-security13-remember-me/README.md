remember-me:记住cookie

持久化令牌:将登录信息记录到数据库中【token令牌:和username关联起来的一个值】

SpringSecurity默认自带了一个令牌管理的Dao 但是是使用的spring-jdbc:JdbcTokenRepositoryImpl
    改造成mybatis-plus的:正好给大家演示一下如何自定义

我们只需要实现:PersistentTokenRepository SpringSecurity定义的接口 会自动帮我们填充数据、调用方法

并发控制:后登录的人可以把前面登录的人剔除下线
