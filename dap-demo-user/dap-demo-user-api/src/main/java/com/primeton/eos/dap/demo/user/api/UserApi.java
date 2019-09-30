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

package com.primeton.eos.dap.demo.user.api;

import static com.primeton.eos.dap.demo.user.api.util.UserApiConstants.API_PATH_PREFIX;
import static com.primeton.eos.dap.demo.user.api.util.UserApiConstants.PATH_PAGE_QUERY_BY_CRITERIA;
import static com.primeton.eos.dap.demo.user.api.util.UserApiConstants.PATH_QUERY_BY_CRITERIA;
import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primeton.eos.dap.demo.user.api.model.User;
import com.primeton.eos.dap.demo.user.api.model.UserCriteria;
import com.primeton.eos.dap.demo.user.api.util.ValidationGroups;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
@Api("用户API")
@FeignClient(name = "${user-api-provider:DEMO-APP-USER}")
public interface UserApi {

    @ApiOperation("新增")
    @RequestMapping(method = POST, value = API_PATH_PREFIX + "/users", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public User create(@Validated({ ValidationGroups.Create.class }) @RequestBody User user);

    @ApiOperation("更新")
    @RequestMapping(method = PUT, value = API_PATH_PREFIX + "/users", consumes = { APPLICATION_JSON_UTF8_VALUE }, produces = { APPLICATION_JSON_UTF8_VALUE })
    public User update(@Validated({ ValidationGroups.Update.class }) @RequestBody User user);

    @ApiOperation("按主键删除")
    @RequestMapping(method = DELETE, value = API_PATH_PREFIX + "/users/{userId}", consumes = ALL_VALUE)
    public void deleteById(@PathVariable(name = "userId") String userId);

    @ApiOperation("按主键查询")
    @RequestMapping(method = GET, value = API_PATH_PREFIX + "/users/{userId}", consumes = ALL_VALUE)
    public User findById(@PathVariable(name = "userId") String userId);

    @ApiOperation("按条件全部查询")
    @RequestMapping(method = GET, value = API_PATH_PREFIX + "/users/" + PATH_QUERY_BY_CRITERIA, consumes = ALL_VALUE)
    public List<User> findAll(UserCriteria criteria, @SortDefault Sort sort);

    @ApiOperation("按条件分页查询")
    @RequestMapping(method = GET, value = API_PATH_PREFIX + "/users/" + PATH_PAGE_QUERY_BY_CRITERIA, consumes = ALL_VALUE)
    public Page<User> pagingAll(UserCriteria criteria, @PageableDefault Pageable pageable);

}

/*
 * 修改历史
 * $Log$ 
 */