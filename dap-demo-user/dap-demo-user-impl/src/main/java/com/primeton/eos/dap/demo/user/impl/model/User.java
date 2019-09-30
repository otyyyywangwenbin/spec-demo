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

package com.primeton.eos.dap.demo.user.impl.model;

import static com.primeton.eos.dap.demo.user.common.util.CommonConstants.TABLE_NAME_PREFIX;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.primeton.eos.dap.demo.user.common.model.BasePersistentModel;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "USER")
public class User extends BasePersistentModel {
    private static final long serialVersionUID = 3223897206987536034L;

    private String name;

    private Integer age;

    public User() {
        super();
    }

    public User(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

/*
 * 修改历史
 * $Log$ 
 */