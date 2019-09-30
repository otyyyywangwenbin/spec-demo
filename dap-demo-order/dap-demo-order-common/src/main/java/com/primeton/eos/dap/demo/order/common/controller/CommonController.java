/**
 * 
 */
package com.primeton.eos.dap.demo.order.common.controller;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primeton.eos.dap.demo.order.common.model.IdentifiablePersistentModel;
import com.primeton.eos.dap.demo.order.common.service.IdentifiablePersistentModelService;
import com.primeton.eos.dap.demo.order.common.util.ValidationGroups;

import io.swagger.annotations.ApiOperation;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public abstract class CommonController<VO, T extends IdentifiablePersistentModel> {

    protected abstract IdentifiablePersistentModelService<T> getSvc();

    protected abstract T toPO(VO vo);

    protected abstract VO toVO(T po);

    @ApiOperation("新增")
    @RequestMapping(method = POST, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public VO create(@Validated({ ValidationGroups.Create.class }) @RequestBody VO vo) {
        T po = toPO(vo);
        po = getSvc().create(po);
        return toVO(po);
    }

    @ApiOperation("更新")
    @RequestMapping(method = PUT, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public VO update(@Validated({ ValidationGroups.Update.class }) @RequestBody VO vo) {
        T po = toPO(vo);
        po = getSvc().update(po);
        return toVO(po);
    }

    @ApiOperation("按主键删除")
    @RequestMapping(value = "/{id}", method = DELETE, consumes = ALL_VALUE)
    public void deleteById(@PathVariable(name = "id") String id) {
        getSvc().deleteById(id);
    }

    @ApiOperation("按主键查询")
    @RequestMapping(value = "/{id}", method = GET, consumes = ALL_VALUE)
    public VO findById(@PathVariable(name = "id") String id) {
        T po = getSvc().findById(id);
        return toVO(po);
    }

}
