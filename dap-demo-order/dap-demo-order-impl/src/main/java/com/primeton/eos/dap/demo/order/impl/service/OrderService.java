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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.primeton.eos.dap.demo.order.common.service.BasePersistentModelService;
import com.primeton.eos.dap.demo.order.impl.dao.OrderJpaRepository;
import com.primeton.eos.dap.demo.order.impl.model.Order;
import com.primeton.eos.dap.demo.order.impl.model.OrderCriteria;
import com.primeton.eos.dap.demo.order.impl.model.Order_;
import com.primeton.eos.dap.demo.user.api.UserApi;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class OrderService extends BasePersistentModelService<Order> {
    @Autowired
    private OrderJpaRepository orderRepo;

    @Autowired
    private UserApi userApi;

    protected OrderJpaRepository getRepo() {
        return orderRepo;
    }

    public Order findById(String id) {
        Order order = super.findById(id);
        order.setUser(userApi.findById(order.getUserId()));
        return order;
    }

    public List<Order> findByCriteria(OrderCriteria criteria) {
        List<Order> orders = orderRepo.findAll(toSpecification(criteria));
        for (Order order : orders) {
            order.setUser(userApi.findById(order.getUserId()));
        }
        return orders;
    }

    public List<Order> findAll() {
        return findByCriteria(null);
    }

    private Specification<Order> toSpecification(OrderCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria == null) {
                return builder.and(new Predicate[] {});
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            Optional.ofNullable(criteria.getUserId()).ifPresent((value) -> predicates.add(builder.equal(root.get(Order_.userId), value)));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}

/*
 * 修改历史
 * $Log$ 
 */