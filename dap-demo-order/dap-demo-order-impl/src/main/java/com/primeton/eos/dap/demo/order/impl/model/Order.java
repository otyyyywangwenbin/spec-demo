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

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

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
    private String orderNo;

    @JsonIgnore
    private String userId;

    @Transient
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @NotEmpty(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private List<OrderItem> items;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        this.userId = user.getId();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

/*
 * 修改历史
 * $Log$ 
 */