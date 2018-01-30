/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

import java.io.File;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class AmazonS3FileSave implements GenericEvents {

    public final String key;
    public final File file;

    //form media
    public final String formId;
    public final String createdBy;
    public final String formMediaId;
    public final String fileName;

    public AmazonS3FileSave(String key, File file) {
	this.key = key;
	this.file = file;
	this.formId = null;
	this.createdBy = null;
	this.formMediaId = null;
	this.fileName = null;
    }

    public AmazonS3FileSave(String key, File file, String fileName, String formId, String createdBy, String formMediaId) {
	this.key = key;
	this.file = file;
	this.formId = formId;
	this.createdBy = createdBy;
	this.formMediaId = formMediaId;
	this.fileName = fileName;
    }

}
