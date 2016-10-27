package net.demo.hasor.jfinal;
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import net.hasor.core.ApiBinder;
import net.hasor.core.Module;
import net.hasor.plugins.jfinal.HasorHandler;
import net.hasor.plugins.jfinal.HasorInterceptor;
import net.hasor.plugins.jfinal.HasorPlugin;
import net.hasor.web.WebApiBinder;
/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig implements Module {
    /** JFinal 配置常量 */
    public void configConstant(Constants me) {
    }
    /** JFinal 配置路由 */
    public void configRoute(Routes me) {
        me.add("/jfinal", IndexController.class);
    }
    /** JFinal 配置插件 */
    public void configPlugin(Plugins me) {
        me.add(new HasorPlugin(JFinal.me(), this));    // <- （必选）Hasor 容器的启动和销毁
    }
    /** JFinal 配置全局拦截器 */
    public void configInterceptor(Interceptors me) {
        me.add(new HasorInterceptor(JFinal.me()));     // <- （可选）为 JFinal 提供 Controller 的依赖注入
    }
    /** JFinal 配置处理器 */
    public void configHandler(Handlers me) {
        me.add(new HasorHandler(JFinal.me()));         // <- （可选）让 Hasor 的Hasor Web功能 & JFinal 共存
    }
    /** Hasor 集成之后的启动入口 */
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        WebApiBinder webApiBinder = (WebApiBinder) apiBinder;
        //
    }
}
