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
        @NamedQuery(name = "get.states", query = "SELECT state.id, state.name FROM State state"),
        @NamedQuery(name = "get.state.name.by.id", query = "SELECT state.name FROM State state WHERE state.id = :stateId"),
        @NamedQuery(name = "get.states.by.country.id", query = "SELECT state.id, state.name FROM State state where state.country.id = :countryId order by state.name") })
@Table(name = "states")
public class State extends BaseEntity implements Serializable {

    public State() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private Long id;

    @Column(name = "name")
    public String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "country_id")
    public Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
