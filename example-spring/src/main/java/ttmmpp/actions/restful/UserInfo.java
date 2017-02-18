package ttmmpp.actions.restful;
import net.demo.hasor.daos.UserDao;
import ttmmpp.forms.UserForm;
import net.hasor.core.Inject;
import net.hasor.web.Invoker;
import net.hasor.web.annotation.*;
/**
 * Created by zhaoyongchun on 16/10/2.
 */
@MappingTo("/restful/{userID}/info.json")
public class UserInfo {
    @Inject
    public UserDao userDao;
    @Get
    public void info(@PathParam("userID") long userID, Invoker renderData) {
    }
    @Post
    public void update(@PathParam("userID") long userID, @Params() UserForm userForm, Invoker renderData) {
        //
    }
}