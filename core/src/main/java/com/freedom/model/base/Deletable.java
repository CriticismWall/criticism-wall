package com.freedom.model.base;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"hasDelete", "deleteAt"}, allowGetters = true)
public class Deletable extends Base {

    protected ZonedDateTime deleteAt;

    @Column(columnDefinition = "bool default false", nullable = false)
    protected boolean hasDelete;

    public void delete() {
        hasDelete = true;
        deleteAt = ZonedDateTime.now();
    }

    public ZonedDateTime getDeleteAt() {
        return deleteAt;
    }

    public Deletable setDeleteAt(ZonedDateTime deleteAt) {
        this.deleteAt = deleteAt;
        return this;
    }

    public boolean isHasDelete() {
        return hasDelete;
    }

    public Deletable setHasDelete(boolean hasDelete) {
        this.hasDelete = hasDelete;
        return this;
    }
}
