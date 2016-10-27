package net.demo.hasor.web;
import com.jfinal.core.Controller;
import net.demo.hasor.domain.UserDO;
import net.demo.hasor.manager.EnvironmentConfig;
import net.hasor.core.Inject;
/**
 * IndexController
 */
public class IndexController extends Controller {
    @Inject
    private EnvironmentConfig config;
    //
    public void index() {
        //
        UserDO userDO = new UserDO();
        //
        render("index.htm");
    }
}