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

package com.primeton.eos.dap.demo.order.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.primeton.eos.dap.demo.order.common.service.BasePersistentModelService;
import com.primeton.eos.dap.demo.order.impl.dao.ProductJpaRepository;
import com.primeton.eos.dap.demo.order.impl.model.Product;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class ProductService extends BasePersistentModelService<Product> {
    @Autowired
    private ProductJpaRepository prodRepo;

    protected ProductJpaRepository getRepo() {
        return prodRepo;
    }

}

/*
 * 修改历史
 * $Log$ 
 */