/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class FormDataImportEvent {

    public final Map<String, Object> mapping;
    public final List< Map<String, Object>> data;
    public final String capturedBy;
    public final String formVersionId;

    public FormDataImportEvent(Map<String, Object> mapping, List<Map<String, Object>> data, String capturedBy, String formVersionId) {
        this.mapping = mapping;
        this.data = data;
        this.capturedBy = capturedBy;
        this.formVersionId = formVersionId;
    }

   public static class Entry {

        public final Map<String, Object> mapping;
        public final Map<String, Object> data;
        public final String capturedBy;
        public final String formVersionId;

        public Entry(Map<String, Object> mapping, Map<String, Object> data, String capturedBy, String formVersionId) {
            this.mapping = mapping;
            this.data = data;
            this.capturedBy = capturedBy;
            this.formVersionId = formVersionId;
        }
    }

}
