/**
 * 
 */
package com.primeton.eos.dap.demo.order.common.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.primeton.eos.dap.demo.order.common.model.BasePersistentModel;
import com.primeton.eos.dap.demo.user.common.model.BasePersistentModel_;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public abstract class BasePersistentModelService<T extends BasePersistentModel> extends IdentifiablePersistentModelService<T> {

    protected Sort defaultSort = Sort.by(Order.asc(BasePersistentModel_.createdDate.getName()));

    protected Sort getDefaultSort() {
        return defaultSort;
    }

    @Transactional(rollbackOn = Throwable.class)
    public T create(T model) {
        preCreate(model);
        return super.create(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public T update(T model) {
        preUpdate(model);
        return super.create(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        preDelete(id);
        super.deleteById(id);
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preCreate(T model) {
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preUpdate(final T model) {
    }

    // 注意: 用于校验数据, 不要做其他事情, 子类也要注意
    protected void preDelete(String id) {
    }

}
