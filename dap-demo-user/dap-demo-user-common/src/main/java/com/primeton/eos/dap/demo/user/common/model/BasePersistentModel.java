/**
 * 
 */
package com.primeton.eos.dap.demo.user.common.model;

import static com.primeton.eos.dap.demo.user.common.util.CommonConstants.DATE_TIME_FORMAT;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/**
 * TODO 此处填写 class 信息
 *
 * @author wangwb (mailto:wangwb@primeton.com)
 */

@MappedSuperclass
public abstract class BasePersistentModel extends IdentifiablePersistentModel {

    private static final long serialVersionUID = 1700205987073171353L;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @JsonProperty(access = Access.READ_ONLY)
    private Date createdDate;

    @DateTimeFormat(pattern = DATE_TIME_FORMAT)
    @JsonFormat(pattern = DATE_TIME_FORMAT)
    @JsonProperty(access = Access.READ_ONLY)
    private Date lastModifiedDate;

    public BasePersistentModel() {
    }

    public BasePersistentModel(String id) {
        super(id);
    }

    @PrePersist
    protected void prePersist() {
        super.prePersist();
        if (this.createdDate == null) {
            this.createdDate = new Date();
        }
        if (this.lastModifiedDate == null) {
            this.lastModifiedDate = new Date();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastModifiedDate = new Date();
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
