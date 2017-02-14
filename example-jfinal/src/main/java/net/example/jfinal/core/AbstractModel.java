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
package net.example.jfinal.core;
import com.jfinal.plugin.activerecord.IBean;
import com.jfinal.plugin.activerecord.Model;
import net.hasor.rsf.utils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
/**
 *
 * @version : 2016年11月07日
 * @author 赵永春(zyc@hasor.net)
 */
public abstract class AbstractModel<M extends AbstractModel<M>> extends Model<M> implements IBean {
    public static interface PersistentFilter {
        boolean doPersistent(Field field, Object oldValue, Object newValue);
    }
    //
    public Model<M> recover() {
        Class<?> oriType = getSuperClassGenricType(this.getClass(), 0);
        oriType = (oriType == null) ? this.getClass() : oriType;
        List<Field> allFields = BeanUtils.findALLFields(oriType);
        for (Field field : allFields) {
            if (field.getDeclaringClass().isAssignableFrom(Model.class))
                continue;
            if (field.getDeclaringClass().isAssignableFrom(AbstractModel.class))
                continue;
            //
            String name = field.getName();
            Object val = this.get(name);
            if (field.getType().isPrimitive() && val == null) {
                continue;
            }
            BeanUtils.writePropertyOrField(this, name, val);
        }
        return this;
    }
    //
    protected Model<M> setup(PersistentFilter filter) {
        Class<?> oriType = getSuperClassGenricType(this.getClass(), 0);
        oriType = (oriType == null) ? this.getClass() : oriType;
        List<Field> allFields = BeanUtils.findALLFields(oriType);
        for (Field field : allFields) {
            if (field.getDeclaringClass().isAssignableFrom(Model.class))
                continue;
            if (field.getDeclaringClass().isAssignableFrom(AbstractModel.class))
                continue;
            //
            String name = field.getName();
            Object newValue = BeanUtils.readPropertyOrField(this, name);
            if (filter.doPersistent(field, this.get(name), newValue)) {
                this.set(name, newValue);
            }
        }
        return this;
    }
    /** 持久化所有值 */
    public Model<M> setupAll() {
        return this.setup(new PersistentFilter() {
            public boolean doPersistent(Field field, Object oldValue, Object newValue) {
                return true;
            }
        });
    }
    /** 持久化非Blank的值 */
    public Model<M> setupIgnoreEmpty() {
        return this.setup(new PersistentFilter() {
            public boolean doPersistent(Field field, Object oldValue, Object newValue) {
                return !(newValue == null || "".equals(newValue));
            }
        });
    }
    /** 持久化非空的值，包括持久化空字符串。 */
    public Model<M> setupIgnoreNull() {
        return this.setup(new PersistentFilter() {
            public boolean doPersistent(Field field, Object oldValue, Object newValue) {
                return !(newValue == null);
            }
        });
    }
    /** 持久化填充oldValue为空的值。 */
    public Model<M> setupFillEmpty() {
        return this.setup(new PersistentFilter() {
            public boolean doPersistent(Field field, Object oldValue, Object newValue) {
                return !(oldValue == null || "".equals(oldValue));
            }
        });
    }
    /**获取泛型类型。*/
    public static Class<?> getSuperClassGenricType(final Class<?> clazz, final int index) {
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type。
        Type genType = clazz.getGenericSuperclass();
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        //返回表示此类型实际类型参数的 Type 对象的数组。
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }
}