package com.portal.commons.models;

import java.lang.Integer;
import java.lang.String;
import java.lang.Void;
import java.util.Date;
import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Generated(
        date = "2018-01-30T11:51:55.294+0100",
        comments = "By Akinniranye James Ayodele",
        value = "Generated from com.portal.entities.JpaOnliePaymentTransactionRawReponse"
)
public class OnliePaymentTransactionRawReponse {

    private Integer rawResponseId;
    private String rawResponse;
    @Size(
            max = 2147483647,
            min = 1
    )
    @NotNull
    private String url;

    private Date dateCreated;

    public Integer getRawResponseId() {
        return this.rawResponseId;
    }

    public Void setRawResponseId(Integer rawResponseId) {
        this.rawResponseId = rawResponseId;
        return null;
    }

    public String getUrl() {
        return this.url;
    }

    public Void setUrl(String url) {
        this.url = url;
        return null;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public Void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
        return null;
    }
}
