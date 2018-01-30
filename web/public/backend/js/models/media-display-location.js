
var app = app || {};

define(["backbone"],function(Backbone){


    var MediaDisplayLocation = Backbone.Model.extend({
        //idAttribute: "mediaId",
        urlRoot: andromeda_server_url+"media_display_locations",
        parse:function(response){
              this.id = response.mediaDisplayHistoryId;
              return response;
        }
    });

    return MediaDisplayLocation;
})
