/**
 * 
 */
package com.primeton.eos.dap.demo.user.common.service;

import static com.primeton.eos.dap.demo.user.common.exception.CommonErrorCode.NOT_FOUND_MODEL_BY_ID;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.primeton.eos.dap.demo.user.common.dao.CommonJpaRepository;
import com.primeton.eos.dap.demo.user.common.model.IdentifiablePersistentModel;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public abstract class IdentifiablePersistentModelService<T extends IdentifiablePersistentModel> {

    protected abstract CommonJpaRepository<T, String> getRepo();

    protected abstract Sort getDefaultSort();

    @Transactional(rollbackOn = Throwable.class)
    public T create(T model) {
        return getRepo().save(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public T update(T model) {
        return getRepo().save(model);
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        getRepo().deleteById(id);
    }

    public T findById(String id) {
        return findById(id, true);
    }

    public T findById(String id, boolean errorIfNotFound) {
        Optional<T> optional = getRepo().findById(id);
        return errorIfNotFound ? optional.orElseThrow(() -> NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id)) : optional.orElse(null);
    }

    public void errorIfNotFoundById(String id) {
        if (!getRepo().existsById(id)) {
            throw NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id);
        }
    }

    public List<T> findAll() {
        return getRepo().findAll(getDefaultSort());
    }

    public Page<T> findAll(Pageable page) {
        if (!page.getSort().isSorted()) {
            page = PageRequest.of(page.getPageNumber(), page.getPageSize(), getDefaultSort());
        }
        return getRepo().findAll(page);
    }

    @SuppressWarnings("unused")
    protected String getRepoClassName() {
        Class<?> clazz = getRepo().getClass().getInterfaces()[0];
        Type type = clazz.getGenericSuperclass();
        return clazz.getSimpleName();
    }

}
