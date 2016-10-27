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
package net.demo.hasor.domain;
import net.hasor.plugins.jfinal.AbstractModel;

import java.util.Date;
/**
 * 用户数据
 * @version : 2016年08月11日
 * @author 赵永春(zyc@hasor.net)
 */
public class UserDO extends AbstractModel<UserDO> {
    public long   id          = 0;    // UserID（PK，自增）
    public String account     = null; // 帐号（唯一）
    public String email       = null; // email
    public String password    = null; // 密码(非明文)
    public String nick        = null; // 昵称
    public Date   create_time = null; // 创建时间
    public Date   modify_time = null; // 修噶改时间
}