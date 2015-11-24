package com.computers.sun.command;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.computers.sun.persistence.BaseEntity;

@Entity
@NamedQueries({
        @NamedQuery(name = "get.countries", query = "SELECT c.id, c.countryName FROM Country c ORDER BY c.countryName"),
        @NamedQuery(name = "get.country.name.by.id", query = "SELECT c.countryName FROM Country c WHERE c.id = :countryId") })
@Table(name = "countries")
public class Country extends BaseEntity implements Serializable {

    public Country() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "status")
    private Short status;

    @Column(name = "country_flag")
    private byte[] countryFlag;

    @Column(name = "iso2_code")
    private String iso2Code;

    @Column(name = "iso3_code")
    private String iso3Code;

    @Column(name = "dial_code")
    private String dialCode;

    @Column(name = "Currency_code")
    private String currencyCode;

    @Column(name = "Currency_desc")
    private String currencyDesc;

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public byte[] getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(byte[] countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getIso2Code() {
        return iso2Code;
    }

    public void setIso2Code(String iso2Code) {
        this.iso2Code = iso2Code;
    }

    public String getIso3Code() {
        return iso3Code;
    }

    public void setIso3Code(String iso3Code) {
        this.iso3Code = iso3Code;
    }

    public String getDialCode() {
        return dialCode;
    }

    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

}
