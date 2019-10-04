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
 * Created on Aug 30, 2019
 *******************************************************************************/

package com.primeton.eos.dap.demo.order.impl.model;

import java.io.Serializable;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public class OrderCriteria implements Serializable {
    private static final long serialVersionUID = 2594493326754690159L;

    private String userId;

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