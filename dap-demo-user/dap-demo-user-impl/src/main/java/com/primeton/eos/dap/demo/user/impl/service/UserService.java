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

package com.primeton.eos.dap.demo.user.impl.service;

import static com.primeton.eos.dap.demo.user.common.util.JpaPredicateUtils.like;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.primeton.eos.dap.demo.user.api.model.UserCriteria;
import com.primeton.eos.dap.demo.user.common.service.BasePersistentModelService;
import com.primeton.eos.dap.demo.user.impl.dao.UserJpaRepository;
import com.primeton.eos.dap.demo.user.impl.model.User;
import com.primeton.eos.dap.demo.user.impl.model.User_;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Service
public class UserService extends BasePersistentModelService<User> {

    @Autowired
    private UserJpaRepository userRepo;

    protected UserJpaRepository getRepo() {
        return userRepo;
    }

    public List<User> findAll(UserCriteria criteria, Sort sort) {
        return getRepo().findAll(toSpec(criteria), sort);
    }

    public Page<User> findAll(UserCriteria criteria, Pageable pageable) {
        return getRepo().findAll(toSpec(criteria), pageable);
    }

    private Specification<User> toSpec(UserCriteria criteria) {
        return (root, query, builder) -> {
            if (criteria == null) {
                return builder.and(new Predicate[] {});
            }
            List<Predicate> predicates = new ArrayList<Predicate>();
            Optional.ofNullable(criteria.getName()).ifPresent((value) -> predicates.add(like(builder, root.get(User_.name), value)));
            Optional.ofNullable(criteria.getAge()).ifPresent((value) -> predicates.add(builder.ge(root.get(User_.age), value)));
            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}

/*
 * 修改历史
 * $Log$ 
 */