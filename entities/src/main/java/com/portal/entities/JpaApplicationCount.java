/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "application_count")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaApplicationCount.findAll", query = "SELECT j FROM JpaApplicationCount j")})
public class JpaApplicationCount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "count")
    @Id
    private BigInteger count;
    @Column(name = "all_paid")
    private BigInteger allPaid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_amount_paid")
    private Double totalAmountPaid;

    public JpaApplicationCount() {
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

    public BigInteger getAllPaid() {
        return allPaid;
    }

    public void setAllPaid(BigInteger allPaid) {
        this.allPaid = allPaid;
    }

    public Double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(Double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }
    
}
