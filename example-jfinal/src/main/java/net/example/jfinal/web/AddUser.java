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
package net.example.jfinal.web;
import net.example.jfinal.domain.UserDO;
import net.example.jfinal.services.UserManager;
import net.hasor.core.Inject;
import net.hasor.restful.RenderData;
import net.hasor.restful.api.MappingTo;
import net.hasor.restful.api.Params;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
/**
 * Hasor 方式新增用户
 * @version : 2016年11月07日
 * @author 赵永春(zyc@hasor.net)
 */
@MappingTo("/addUser.do")
public class AddUser {
    @Inject
    private UserManager userManager;
    //
    public void execute(@Params() UserDO userParam, RenderData data) throws SQLException, IOException {
        //
        userParam.setModify_time(new Date());
        userParam.setCreate_time(new Date());
        userManager.addUser(userParam);
        //
        data.getHttpResponse().sendRedirect("/list");
    }
}