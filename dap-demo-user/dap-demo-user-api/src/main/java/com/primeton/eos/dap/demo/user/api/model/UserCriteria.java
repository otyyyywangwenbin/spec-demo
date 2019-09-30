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

package com.primeton.eos.dap.demo.user.api.model;

import java.io.Serializable;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public class UserCriteria implements Serializable {

    private static final long serialVersionUID = -8882905863412907837L;

    private String name;

    private Integer age;

    public UserCriteria() {
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