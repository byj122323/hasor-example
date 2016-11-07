package net.demo.hasor.daos;
import net.demo.hasor.domain.UserDTO;
import net.hasor.core.Inject;
import net.hasor.db.jdbc.core.JdbcTemplate;
import org.more.util.StringUtils;

import java.sql.SQLException;
import java.util.List;
/**
 *
 * @version : 2016年11月07日
 * @author 赵永春(zyc@hasor.net)
 */
public class UserDao {
    @Inject
    private JdbcTemplate jdbcTemplate;
    //
    public List<UserDTO> queryList() throws SQLException {
        List<UserDTO> userDTOs = this.jdbcTemplate.queryForList("select * from TEST_USER_INFO", UserDTO.class);
        return userDTOs;
    }
    //
    //
    //
    public UserDTO queryUserInfoByAccount(long userID) {
        if (userID > 1000) {
            return new UserDTO();
        }
        return null;
    }
    public UserDTO queryUserInfoByAccount(String account) {
        if (StringUtils.equalsIgnoreCase(account, "zyc")) {
            return new UserDTO();
        }
        return null;
    }
}
