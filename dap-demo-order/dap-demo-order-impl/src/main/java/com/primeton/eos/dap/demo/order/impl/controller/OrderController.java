/*******************************************************************************
 * $Header$
 * $Revision$
 * $Date$
 *
 *==============================================================================
 *
 * Copyright (c) 2001-2017 Primeton Technologies, Ltd.
 * All rights reserved.
 * 
 * Created on Aug 28, 2019
 *******************************************************************************/

package com.primeton.eos.dap.demo.order.impl.controller;

import static com.primeton.eos.dap.demo.order.common.util.CommonConstants.API_PATH_PREFIX;
import static com.primeton.eos.dap.demo.order.common.util.CommonConstants.PATH_QUERY_BY_CRITERIA;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.eos.dap.demo.order.common.util.ValidationGroups;
import com.primeton.eos.dap.demo.order.impl.model.Order;
import com.primeton.eos.dap.demo.order.impl.model.OrderCriteria;
import com.primeton.eos.dap.demo.order.impl.service.OrderService;
import com.primeton.eos.dap.demo.user.api.UserApi;
import com.primeton.eos.dap.demo.user.api.model.User;
import com.primeton.eos.dap.demo.user.api.model.UserCriteria;

import io.swagger.annotations.ApiOperation;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@RestController
@RequestMapping(value = API_PATH_PREFIX + "/orders", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
public class OrderController {
    @Autowired
    private OrderService orderSvc;

    @Autowired
    private UserApi userApi;

    @RequestMapping(method = GET, value = "/test", consumes = ALL_VALUE)
    public Page<User> test() {
        UserCriteria criteria = new UserCriteria();
        criteria.setAge(26);
        return userApi.pagingAll(criteria, PageRequest.of(0, 2, Direction.DESC, "createdDate"));
    }

    @ApiOperation("新增")
    @RequestMapping(method = POST, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public Order create(@Validated({ ValidationGroups.Create.class }) @RequestBody Order order) {
        return orderSvc.create(order);
    }

    @ApiOperation("更新")
    @RequestMapping(method = PUT, consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public Order update(@Validated({ ValidationGroups.Update.class }) @RequestBody Order order) {
        return orderSvc.update(order);
    }

    @ApiOperation("按主键删除")
    @RequestMapping(method = DELETE, value = "/{orderId}", consumes = ALL_VALUE)
    public void deleteById(@PathVariable(name = "orderId") String orderId) {
        orderSvc.deleteById(orderId);
    }

    @ApiOperation("按主键查询")
    @RequestMapping(method = GET, value = "/{orderId}", consumes = ALL_VALUE)
    public Order findById(@PathVariable(name = "orderId") String orderId) {
        return orderSvc.findById(orderId);
    }

    @ApiOperation("按条件查询")
    @RequestMapping(method = GET, value = "/" + PATH_QUERY_BY_CRITERIA, consumes = ALL_VALUE)
    public List<Order> findByCriteria(@Validated OrderCriteria criteria) {
        return orderSvc.findByCriteria(criteria);
    }
}

/*
 * 修改历史
 * $Log$ 
 */