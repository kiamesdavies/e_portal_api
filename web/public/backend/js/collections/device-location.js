


var app = app || {};

define(["backbone_paginator", "models/device"], function (PageableCollection, Device) {

    return PageableCollection.extend({
        model: Device,
        mode: "client",
        
        // Initial pagination states
        state: {
            firstPage: 1,
            pageSize: 20,
        },
       
        
    });

})