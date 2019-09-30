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
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.primeton.eos.dap.demo.order.common.model.IdentifiablePersistentModel;
import com.primeton.eos.dap.demo.order.common.util.ValidationGroups;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "ORDER_ITEM")
public class OrderItem extends IdentifiablePersistentModel {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5012464519940921689L;

    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @ConvertGroup(from = ValidationGroups.Create.class, to = ValidationGroups.Association.class)
    @ConvertGroup(from = ValidationGroups.Update.class, to = ValidationGroups.Association.class)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @Valid
    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @ConvertGroup(from = ValidationGroups.Create.class, to = ValidationGroups.Association.class)
    @ConvertGroup(from = ValidationGroups.Update.class, to = ValidationGroups.Association.class)
    @JsonIgnore // 不然双向循环引用
    private Order order;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

/*
 * 修改历史
 * $Log$ 
 */