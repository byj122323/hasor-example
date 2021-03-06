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
package net.example.nutz.customer;
import net.example.domain.consumer.EchoService;
import net.example.domain.consumer.MessageService;
import net.hasor.core.ApiBinder;
import net.hasor.rsf.RsfApiBinder;
import org.nutz.integration.hasor.NutzModule;
import org.nutz.integration.hasor.annotation.HasorConfiguration;
//
//
@HasorConfiguration
public class RpcModule extends NutzModule {
    @Override
    public void loadModule(ApiBinder apiBinder) throws Throwable {
        // .切换成 RSF RsfApiBinder
        RsfApiBinder rsfApiBinder = apiBinder.tryCast(RsfApiBinder.class);
        //
        // .服务订阅（发布到 Hasor 容器中的 Bean 会自动导出到 Nutz）
        rsfApiBinder.bindType(EchoService.class).toProvider(rsfApiBinder.converToProvider(      // 发布服务到 Hasor 容器中
                rsfApiBinder.rsfService(EchoService.class).register()                           // 注册消费者
        ));
        rsfApiBinder.bindType(MessageService.class).toProvider(rsfApiBinder.converToProvider(   // 发布服务到 Hasor 容器中
                rsfApiBinder.rsfService(MessageService.class).register()                        // 注册消费者
        ));
    }
}