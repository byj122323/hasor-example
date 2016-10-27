package net.demo.hasor.jfinal;
import com.jfinal.core.Controller;
import net.demo.hasor.manager.EnvironmentConfig;
import net.hasor.core.Inject;
/**
 * IndexController
 */
public class IndexController extends Controller {
    @Inject
    private EnvironmentConfig config;
    public void index() {
        render("index.htm");
    }
}