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

import static com.primeton.eos.dap.demo.order.common.exception.CommonErrorCode.NOT_FOUND_MODEL_BY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.primeton.eos.dap.demo.order.common.service.BasePersistentModelService;
import com.primeton.eos.dap.demo.order.impl.dao.OrderItemJpaRepository;
import com.primeton.eos.dap.demo.order.impl.dao.OrderJpaRepository;
import com.primeton.eos.dap.demo.order.impl.model.Order;
import com.primeton.eos.dap.demo.order.impl.model.OrderCriteria;
import com.primeton.eos.dap.demo.order.impl.model.OrderItem;
import com.primeton.eos.dap.demo.order.impl.model.OrderItem_;
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
    private OrderItemJpaRepository orderItemRepo;

    @Autowired
    private UserApi userApi;

    protected OrderJpaRepository getRepo() {
        return orderRepo;
    }

    @Transactional(rollbackOn = Throwable.class)
    public Order create(Order order) {
        order = super.create(order);
        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
            orderItemRepo.save(item);
        }
        return order;
    }

    @Transactional(rollbackOn = Throwable.class)
    public Order update(Order order) {
        order = super.update(order);
        for (OrderItem item : order.getItems()) {
            item.setOrder(order);
            orderItemRepo.save(item);
        }
        return order;
    }

    @Transactional(rollbackOn = Throwable.class)
    public void deleteById(String id) {
        List<OrderItem> items = orderItemRepo.findAll((root, query, builder) -> {
            return builder.equal(root.get(OrderItem_.order).get(Order_.id), id);
        });
        orderItemRepo.deleteAll(items);
        super.deleteById(id);
    }

    public Order findById(String id) {
        Order order = orderRepo.findOne((root, query, builder) -> {
            root.fetch(Order_.items).fetch(OrderItem_.product);
            return builder.equal(root.get(Order_.id), id);
        }).orElseThrow(() -> NOT_FOUND_MODEL_BY_ID.runtimeException(getRepoClassName(), id));
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

    private Specification<Order> toSpecification(OrderCriteria criteria) {
        return (root, query, builder) -> {
            if (Long.class != query.getResultType()) {
                // 分页查询时, 会有查询总条数, 查询总条数的resultType == Long.class
                root.fetch(Order_.items).fetch(OrderItem_.product);
            }
            if (criteria == null) {
                return builder.and(new Predicate[] {});
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            Optional.ofNullable(criteria.getOrderNo()).ifPresent((value) -> predicates.add(builder.equal(root.get(Order_.orderNo), value)));
            Optional.ofNullable(criteria.getUserId()).ifPresent((value) -> predicates.add(builder.equal(root.get(Order_.userId), value)));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}

/*
 * 修改历史
 * $Log$ 
 */