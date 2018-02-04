/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portal.binder;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;

/**
 *
 * @author Akinniranye James Ayodele <kaimedavies@sycliff.com>
 */

public class MyVelocityEngine extends  VelocityEngine{

    public MyVelocityEngine() {
        this.setProperty(Velocity.RESOURCE_LOADER, "string");
        this.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
        this.addProperty("string.resource.loader.repository.static", "false");
//        this.addProperty("string.runtime.references.strict", "true");
//        this.addProperty("runtime.references.strict", "true");
        this.init();
    }
    
}
