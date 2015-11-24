package com.computers.sun.command;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.computers.sun.persistence.BaseEntity;

@Entity
@NamedQueries({
        @NamedQuery(name = "get.cities", query = "SELECT city.id, city.name FROM City city"),
        @NamedQuery(name = "get.city.name.by.id", query = "SELECT city.name FROM City city WHERE city.id = :cityId"),
        @NamedQuery(name = "get.city.by.state.id", query = "SELECT city.id, city.name FROM City city where city.state.id = :stateId order by city.name") })
@Table(name = "cities")
public class City extends BaseEntity implements Serializable {

    public City() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "state_id")
    public State state;

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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

}
