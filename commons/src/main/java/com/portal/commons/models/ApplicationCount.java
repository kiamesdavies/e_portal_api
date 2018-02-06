/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.models;

import java.math.BigInteger;

/**
 *
 * @author poseidon
 */
public class ApplicationCount {

    private BigInteger count;

    private BigInteger allPaid;

    private Double totalAmountPaid;

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
