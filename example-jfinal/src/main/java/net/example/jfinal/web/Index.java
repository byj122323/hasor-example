package net.example.jfinal.web;
import com.jfinal.core.Controller;
import net.example.jfinal.domain.UserDO;
import net.example.jfinal.services.EnvironmentConfig;
import net.example.jfinal.services.UserManager;
import net.hasor.core.Inject;

import java.util.List;
/**
 * 使用 JFinal 方式查询用户列表。
 */
public class Index extends Controller {
    @Inject
    private EnvironmentConfig environmentConfig;
    @Inject
    private UserManager       userManager;
    //
    public void index() {
        //
        this.setAttr("env", environmentConfig);
        render("index.htm");
    }
    //
    public void list() {
        List<UserDO> userDOs = userManager.queryList();
        this.setAttr("userList", userDOs);
        render("user_list.htm");
    }
}