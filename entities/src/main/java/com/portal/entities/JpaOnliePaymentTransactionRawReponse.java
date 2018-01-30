/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author poseidon
 */
@Entity
@Table(name = "onlie_payment_transaction_raw_reponse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JpaOnliePaymentTransactionRawReponse.findAll", query = "SELECT j FROM JpaOnliePaymentTransactionRawReponse j")})
public class JpaOnliePaymentTransactionRawReponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "raw_response_id")
    private Integer rawResponseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "raw_response", columnDefinition = "json")
    @Convert(converter = JsonStringConverter.class)
    private String rawResponse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "url")
    private String url;
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    public JpaOnliePaymentTransactionRawReponse() {
    }

    public JpaOnliePaymentTransactionRawReponse(Integer rawResponseId) {
        this.rawResponseId = rawResponseId;
    }

    public JpaOnliePaymentTransactionRawReponse(Integer rawResponseId, String rawRespomse, String url) {
        this.rawResponseId = rawResponseId;
        this.rawResponse = rawRespomse;
        this.url = url;
    }

    public Integer getRawResponseId() {
        return rawResponseId;
    }

    public void setRawResponseId(Integer rawResponseId) {
        this.rawResponseId = rawResponseId;
    }

    public Object getRawRespomse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rawResponseId != null ? rawResponseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JpaOnliePaymentTransactionRawReponse)) {
            return false;
        }
        JpaOnliePaymentTransactionRawReponse other = (JpaOnliePaymentTransactionRawReponse) object;
        if ((this.rawResponseId == null && other.rawResponseId != null) || (this.rawResponseId != null && !this.rawResponseId.equals(other.rawResponseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.portal.entities.JpaOnliePaymentTransactionRawReponse[ rawResponseId=" + rawResponseId + " ]";
    }
    
}
