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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.primeton.eos.dap.demo.order.common.model.BasePersistentModel;
import com.primeton.eos.dap.demo.order.common.util.ValidationGroups;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Entity
@Table(name = TABLE_NAME_PREFIX + "PRODUCT")
public class Product extends BasePersistentModel {

    private static final long serialVersionUID = -7195802315142091400L;

    @NotBlank(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private String name;

    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @Min(value = 1)
    private Integer quantity;

    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @Min(value = 1)
    private Integer price;

    public Product() {
        super();
    }

    public Product(String id) {
        super(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}

/*
 * 修改历史
 * $Log$ 
 */