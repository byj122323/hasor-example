package net.example.jfinal.core;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import net.demo.client.consumer.EchoService;
import net.demo.client.consumer.MessageService;
import net.example.jfinal.domain.UserDO;
import net.example.jfinal.provider.EchoServiceImpl;
import net.example.jfinal.provider.MessageServiceImpl;
import net.example.jfinal.web.Index;
import net.hasor.core.*;
import net.hasor.db.jdbc.core.JdbcTemplate;
import net.hasor.plugins.jfinal.HasorDataSourceProxy;
import net.hasor.plugins.jfinal.HasorHandler;
import net.hasor.plugins.jfinal.HasorInterceptor;
import net.hasor.plugins.jfinal.HasorPlugin;
import net.hasor.rsf.RsfApiBinder;
import net.hasor.rsf.RsfModule;
import net.hasor.rsf.RsfPlugin;
import net.hasor.web.WebApiBinder;

import java.util.Arrays;
import java.util.List;
/**
 * JFinal API 引导式配置（Hasor系列框架全面深度整合）
 */
public class DemoConfig extends JFinalConfig implements LifeModule, RsfPlugin {
    private C3p0Plugin getC3p0Plugin() {
        String jdbcUrl = PropKit.get("jdbc.url").trim();
        String user = PropKit.get("jdbc.user").trim();
        String password = PropKit.get("jdbc.password").trim();
        String driver = PropKit.get("jdbc.driver").trim();
        return new C3p0Plugin(jdbcUrl, user, password, driver);
    }
    /** JFinal 配置常量 */
    public void configConstant(Constants me) {
        //
        PropKit.use("env.config", "utf-8");                             // <- （可选）加载 JFinal 配置文件，同时作为 Hasor 的环境变量
    }
    /** JFinal 配置路由 */
    public void configRoute(Routes me) {
        //
        me.add("/", Index.class);
    }
    /** JFinal 配置插件 */
    public void configPlugin(Plugins me) {
        //
        // .JFinal 数据库 DataSource
        C3p0Plugin c3p0 = getC3p0Plugin();
        HasorDataSourceProxy dbProxy = new HasorDataSourceProxy(c3p0);  // <- （可选）如使用 Hasor 管理数据库事务，需要配置 JFinal 代理数据源
        me.add(c3p0);
        //
        // .Hasor框架集成
        List<Module> hasorPlugins = Arrays.asList(//
                dbProxy,// .配置代理数据源到 Hasor
                this,   // .Module 或 LifeModule接口
                RsfModule.toModule(this) // .RsfPlugin 转换为 Module
        );
        me.add(new HasorPlugin(JFinal.me(), hasorPlugins));            // <- （必选）Hasor 框架的启动和销毁
        //
        // .JFinal 表映射
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dbProxy);
        me.add(arp);
        arp.addMapping("TEST_USER_INFO", "id", UserDO.class);
    }
    /** JFinal 配置全局拦截器 */
    public void configInterceptor(Interceptors me) {
        //
        me.add(new HasorInterceptor(JFinal.me()));                      // <- （可选）为 JFinal 提供 Controller 的依赖注入
    }
    /** JFinal 配置处理器 */
    public void configHandler(Handlers me) {
        //
        me.add(new HasorHandler(JFinal.me()));                          // <- （可选）将 Hasor 的 Web 功能集成到 JFinal 中
    }
    /** Hasor 集成之后的启动入口 */
    public void loadModule(ApiBinder apiBinder) throws Throwable {      // <- （可选）Hasor 的启动初始化。
        WebApiBinder webApiBinder = (WebApiBinder) apiBinder;
    }
    @Override
    public void loadModule(RsfApiBinder apiBinder) throws Throwable {   // <- （可选）RSF 分布式服务框架的启动过程。
        // - 分布式服务（服务提供者）
        //
        //  EchoService 接口
        apiBinder.rsfService(EchoService.class)//
                .toInfo(apiBinder.bindType(EchoService.class).to(EchoServiceImpl.class).toInfo())//
                .register();
        //  MessageService 接口
        BindInfo<MessageService> bindInfo = apiBinder.bindType(MessageService.class).to(MessageServiceImpl.class).toInfo();
        apiBinder.rsfService(MessageService.class).toInfo(bindInfo).register();
    }
    @Override
    public void onStart(AppContext appContext) throws Throwable {       // <- （可选）Hasor 的启动启动过程。
        //
        // 例子：Hasor 去初始化 JFinal 映射中 数据库不存在的表。
        //
        // .Hasor，查询某个表是否存在
        boolean needRunSQL = true;
        JdbcTemplate jdbcTemplate = appContext.getInstance(JdbcTemplate.class);
        List<String> records = jdbcTemplate.queryForList("SHOW TABLES LIKE '%';", String.class);
        for (String record : records) {
            if ("TEST_USER_INFO".equalsIgnoreCase(record)) {
                needRunSQL = false;
                break;
            }
        }
        // .如果不存在 TEST_USER_INFO 表，那么使用 sql 脚本初始化它
        if (needRunSQL) {
            jdbcTemplate.loadSQL("utf-8", "ddl_sql_user_info.sql");
        }
    }
    @Override
    public void onStop(AppContext appContext) throws Throwable {        // <- （可选）Hasor 的启动启动过程。LifeModule 接口实现。
        // appContext.getInstance(xxx);
    }
}