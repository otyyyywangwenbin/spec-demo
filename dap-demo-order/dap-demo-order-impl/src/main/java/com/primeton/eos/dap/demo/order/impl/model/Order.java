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

package com.primeton.eos.dap.demo.order.impl.model;

import static com.primeton.eos.dap.demo.order.common.util.CommonConstants.TABLE_NAME_PREFIX;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.primeton.eos.dap.demo.order.common.model.BasePersistentModel;
import com.primeton.eos.dap.demo.order.common.util.ValidationGroups;
import com.primeton.eos.dap.demo.user.api.model.User;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "ORDER")
public class Order extends BasePersistentModel {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5012464519940921689L;

    @NotBlank(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private String orderDetail;

    @JsonIgnore
    private String userId;

    @Transient
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(String orderDetail) {
        this.orderDetail = orderDetail;
    }

}

/*
 * 修改历史
 * $Log$ 
 */