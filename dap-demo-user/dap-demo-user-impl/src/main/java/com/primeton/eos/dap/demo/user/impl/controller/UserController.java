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
 * Created on Sep 29, 2019
 *******************************************************************************/

package com.primeton.eos.dap.demo.user.impl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.primeton.eos.dap.demo.user.api.UserApi;
import com.primeton.eos.dap.demo.user.api.model.User;
import com.primeton.eos.dap.demo.user.api.model.UserCriteria;
import com.primeton.eos.dap.demo.user.api.util.ValidationGroups;
import com.primeton.eos.dap.demo.user.impl.service.UserService;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@RestController
public class UserController implements UserApi {
    @Autowired
    private UserService userSvc;

    protected UserService getSvc() {
        return userSvc;
    }

    protected com.primeton.eos.dap.demo.user.impl.model.User toPO(User vo) {
        com.primeton.eos.dap.demo.user.impl.model.User po = new com.primeton.eos.dap.demo.user.impl.model.User();
        BeanUtils.copyProperties(vo, po);
        return po;
    }

    protected User toVO(com.primeton.eos.dap.demo.user.impl.model.User po) {
        User vo = new User();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public User create(@Validated({ ValidationGroups.Create.class }) @RequestBody User user) {
        com.primeton.eos.dap.demo.user.impl.model.User po = toPO(user);
        po = getSvc().create(po);
        return toVO(po);
    }

    public User update(@Validated({ ValidationGroups.Update.class }) @RequestBody User user) {
        com.primeton.eos.dap.demo.user.impl.model.User po = toPO(user);
        po = getSvc().update(po);
        return toVO(po);
    }

    public void deleteById(@PathVariable(name = "id") String id) {
        getSvc().deleteById(id);
    }

    public User findById(@PathVariable(name = "id") String id) {
        com.primeton.eos.dap.demo.user.impl.model.User po = getSvc().findById(id);
        return toVO(po);
    }

    public List<User> findAll(UserCriteria criteria, Sort sort) {
        List<User> vos = new ArrayList<User>();
        List<com.primeton.eos.dap.demo.user.impl.model.User> pos = getSvc().findAll(criteria, sort);
        for (com.primeton.eos.dap.demo.user.impl.model.User po : pos) {
            vos.add(toVO(po));
        }
        return vos;
    }

    public Page<User> pagingAll(UserCriteria criteria, Pageable pageable) {
        List<User> vos = new ArrayList<User>();
        Page<com.primeton.eos.dap.demo.user.impl.model.User> page = getSvc().findAll(criteria, pageable);
        for (com.primeton.eos.dap.demo.user.impl.model.User po : page.getContent()) {
            vos.add(toVO(po));
        }
        return new PageImpl<User>(vos, pageable, page.getTotalElements());
    }

}

/*
 * 修改历史
 * $Log$ 
 */