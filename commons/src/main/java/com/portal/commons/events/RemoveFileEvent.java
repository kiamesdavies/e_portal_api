/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.commons.events;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */
public class RemoveFileEvent implements GenericEvents{

    public final String key;

    public RemoveFileEvent(String key) {
        this.key = key;
    }

}
