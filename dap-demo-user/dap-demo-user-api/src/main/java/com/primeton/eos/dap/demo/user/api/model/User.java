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

package com.primeton.eos.dap.demo.user.api.model;

import static com.primeton.eos.dap.demo.user.api.util.UserApiConstants.DATE_TIME_FORMAT;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.primeton.eos.dap.demo.user.api.util.ValidationGroups;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */
public class User implements Serializable {

    private static final long serialVersionUID = -7195802315142091400L;

    @NotBlank(groups = { ValidationGroups.Update.class, ValidationGroups.Association.class })
    private String id;

    @NotBlank(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    private String name;

    @NotNull(groups = { ValidationGroups.Create.class, ValidationGroups.Update.class })
    @Min(value = 10)
    @Max(value = 200)
    private Integer age;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdDate;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @JsonProperty(access = Access.READ_ONLY)
    private Date lastModifiedDate;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

}

/*
 * 修改历史
 * $Log$ 
 */