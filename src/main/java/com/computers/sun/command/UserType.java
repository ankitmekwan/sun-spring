package com.computers.sun.command;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.computers.sun.persistence.BaseEntity;

@Entity
@Table(name = "user_types")
public class UserType extends BaseEntity implements Serializable {
    public UserType() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private Short status;

    @Column(name = "is_visible")
    private Short iVisible;

    @Column(name = "is_private")
    private Short isPrivate;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getiVisible() {
        return iVisible;
    }

    public void setiVisible(Short iVisible) {
        this.iVisible = iVisible;
    }

    public Short getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(Short isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
