/**
 * Created by encentral_lap_1 on 8/25/2015.
 */
var app = app || {};

define(["backbone"],function(Backbone){


    var VideoModel = Backbone.Model.extend({
        idAttribute: "videoId",
        urlRoot: andromeda_server_url+"video",
        // Lets create function which will return the custom URL based on the method type
        getCustomUrl: function (method, model) {
            switch (method) {
                case 'read':
                    return andromeda_server_url+"video/" + this.id;
                    break;
                case 'create':
                    return andromeda_server_url+"video/";
                    break;
                case 'update':
                    if(model.get("start")){
                        return andromeda_server_url+"video/" + this.id+"/start";
                    }
                    else{
                        return andromeda_server_url+"video/" + this.id;
                    }
                    break;
                case 'delete':
                    return  andromeda_server_url+"video/" + this.id;
                    break;
            }
        },
        // Now lets override the sync function to use our custom URLs
        sync: function (method, model, options) {
            options || (options = {});
            options.url = this.getCustomUrl(method.toLowerCase(), model);

            // Lets notify backbone to use our URLs and do follow default course
            return Backbone.sync.apply(this, arguments);
        }

    });

    return VideoModel;
})