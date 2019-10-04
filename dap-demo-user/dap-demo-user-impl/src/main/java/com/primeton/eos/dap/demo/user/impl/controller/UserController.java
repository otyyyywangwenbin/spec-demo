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
import org.springframework.web.bind.annotation.RestController;

import com.primeton.eos.dap.demo.user.api.UserApi;
import com.primeton.eos.dap.demo.user.api.model.User;
import com.primeton.eos.dap.demo.user.api.model.UserCriteria;
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

    protected com.primeton.eos.dap.demo.user.impl.model.User toBO(User vo) {
        com.primeton.eos.dap.demo.user.impl.model.User bo = new com.primeton.eos.dap.demo.user.impl.model.User();
        BeanUtils.copyProperties(vo, bo);
        return bo;
    }

    protected User toVO(com.primeton.eos.dap.demo.user.impl.model.User bo) {
        User vo = new User();
        BeanUtils.copyProperties(bo, vo);
        return vo;
    }

    public User create(User user) {
        com.primeton.eos.dap.demo.user.impl.model.User bo = toBO(user);
        bo = userSvc.create(bo);
        return toVO(bo);
    }

    public User update(User user) {
        com.primeton.eos.dap.demo.user.impl.model.User bo = toBO(user);
        bo = userSvc.update(bo);
        return toVO(bo);
    }

    public void deleteById(String userId) {
        userSvc.deleteById(userId);
    }

    public User findById(String userId) {
        com.primeton.eos.dap.demo.user.impl.model.User bo = userSvc.findById(userId);
        return toVO(bo);
    }

    public List<User> findAll(UserCriteria criteria, Sort sort) {
        List<User> vos = new ArrayList<User>();
        List<com.primeton.eos.dap.demo.user.impl.model.User> bos = userSvc.findAll(criteria, sort);
        for (com.primeton.eos.dap.demo.user.impl.model.User bo : bos) {
            vos.add(toVO(bo));
        }
        return vos;
    }

    public Page<User> pagingAll(UserCriteria criteria, Pageable pageable) {
        List<User> vos = new ArrayList<User>();
        Page<com.primeton.eos.dap.demo.user.impl.model.User> page = userSvc.findAll(criteria, pageable);
        for (com.primeton.eos.dap.demo.user.impl.model.User bo : page.getContent()) {
            vos.add(toVO(bo));
        }
        return new PageImpl<User>(vos, page.getPageable(), page.getTotalElements());
    }

}

/*
 * 修改历史
 * $Log$ 
 */