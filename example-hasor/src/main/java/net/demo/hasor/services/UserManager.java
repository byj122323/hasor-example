/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.demo.hasor.services;
import net.demo.client.domain.UserDO;
import net.demo.hasor.daos.UserDao;
import net.demo.hasor.domain.UserDTO;
import net.hasor.core.Inject;
import net.hasor.core.Singleton;
import net.hasor.db.Transactional;
import org.more.util.BeanUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static net.hasor.db.transaction.Propagation.REQUIRED;
/**
 *
 * @version : 2016年11月07日
 * @author 赵永春(zyc@hasor.net)
 */
@Singleton
public class UserManager {
    @Inject
    private UserDao userDao;
    //
    /** 查询列表 */
    public List<UserDO> queryList() throws SQLException {
        List<UserDTO> userDOs = userDao.queryList();
        List<UserDO> userList = new ArrayList<UserDO>();
        for (UserDTO dto : userDOs) {
            UserDO userDO = new UserDO();
            BeanUtils.copyProperties(userDO, dto);
            userDO.setCreateTime(dto.getCreate_time());
            userDO.setModifyTime(dto.getModify_time());
            userList.add(userDO);
        }
        return userList;
    }
    //
    /** 添加用户 */
    @Transactional(propagation = REQUIRED)
    public void addUser(UserDTO userDO) throws SQLException {
        UserDO dataUser = new UserDO();
        BeanUtils.copyProperties(dataUser, userDO);
        boolean save = dataUser.setupAll().save();
        if (!save) {
            throw new SQLException("保存失败。");
        }
    }
}