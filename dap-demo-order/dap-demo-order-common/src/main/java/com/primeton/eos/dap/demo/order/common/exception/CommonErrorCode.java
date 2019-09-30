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

package com.primeton.eos.dap.demo.order.common.exception;

import com.primeton.eos.dap.sdk.api.app.exception.SDKAppErrorCode;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

public enum CommonErrorCode implements SDKAppErrorCode {
    MODEL_CLONE_NOT_SUPPORTED//
    , NOT_FOUND_MODEL_BY_ID //
    , DUPLICATED_MODEL_WITH_SAME_CODE//
    , NOT_ALLOWED_CHANGE_MODEL_CODE//
    ;

    public String getCode() {
        return this.name();
    }

}

/*
 * 修改历史
 * $Log$ 
 */