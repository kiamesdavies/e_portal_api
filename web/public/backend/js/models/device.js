
var app = app || {};

define(["backbone"], function (Backbone) {


    return Backbone.Model.extend({
        idAttribute: "deviceId",
        urlRoot: andromeda_server_url + "devices",

    });

})
