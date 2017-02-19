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
package net.example.hasor.core;
import net.hasor.web.WebApiBinder;
import net.hasor.web.WebModule;
/**
 * Hasor API 引导式配置
 * @version : 2015年12月25日
 * @author 赵永春(zyc@hasor.net)
 */
public class StartModule extends WebModule {
    @Override
    public void loadModule(WebApiBinder apiBinder) throws Throwable {
        //
        apiBinder.setEncodingCharacter("utf-8", "utf-8");   //设置请求响应编码
        apiBinder.scanAnnoRender();                         //扫描并注册页面渲染器，所有 @Render 注解
        apiBinder.scanMappingTo();                          //扫描所有 @MappingTo 注解
        //
        apiBinder.installModule(new DataSourceModule());    //连接数据库
        apiBinder.installModule(new RpcModule());           //发布分布式服务
    }
}