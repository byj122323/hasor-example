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
package net.example.jfinal.domain;
import net.example.jfinal.core.AbstractModel;

import java.util.Date;
/**
 * 用户数据
 * @version : 2016年08月11日
 * @author 赵永春(zyc@hasor.net)
 */
public class UserDO extends AbstractModel<UserDO> {
    private long   id          = 0;    // UserID（PK，自增）
    private String account     = null; // 帐号（唯一）
    private String email       = null; // email
    private String password    = null; // 密码(非明文)
    private String nick        = null; // 昵称
    private Date   create_time = null; // 创建时间
    private Date   modify_time = null; // 修噶改时间
    //
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String nick) {
        this.nick = nick;
    }
    public Date getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
    public Date getModify_time() {
        return modify_time;
    }
    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }
}