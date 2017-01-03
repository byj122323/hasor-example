package net.demo.hasor.web.actions.restful;
import net.demo.hasor.web.forms.UserForm;
import net.hasor.web.annotation.MappingTo;
import net.hasor.web.annotation.Params;
import net.hasor.web.annotation.Post;
/**
 * Created by zhaoyongchun on 16/10/2.
 */
@MappingTo("/restful/users")
public class Users {
    @Post
    public void execute(@Params() UserForm userForm) {
        //
    }
}