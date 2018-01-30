/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;


import com.portal.commons.util.AccountType;



/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class FileEvent implements GenericEvents {

    public final AccountType accountType;
    public final String type;
    public final String subType;
    public final String key;
    public final long byteSize;

    //form media
    public final String formId;
    public final String createdBy;
    public final String formMediaId;
    public final String mediaName;
    

    public FileEvent(AccountType accountType, String type, String subType, String key, long byteSize) {
	this.accountType = accountType;
	this.type = type;
	this.subType = subType;
	this.key = key;
	this.byteSize = byteSize;

	this.formId = null;
	this.createdBy = null;
	this.formMediaId = null;
	this.mediaName = null;
    }

    public FileEvent(AccountType accountType, String type, String subType, String key, long byteSize, String formId, String createdBy, String formMediaId, String mediaName) {
	this.accountType = accountType;
	this.type = type;
	this.subType = subType;
	this.key = key;
	this.byteSize = byteSize;
	this.formId = formId;
	this.createdBy = createdBy;
	this.formMediaId = formMediaId;
	this.mediaName = mediaName;
    }

}
