
var app = app || {};

define(["backbone"],function(Backbone){


    return Backbone.Model.extend({
		 getCustomUrl: function (method, model) {
			return andromeda_server_url+"media/media_display_history/"+encodeURIComponent(this.get("accountId"))+"/stats" ;
         },
        // Now lets override the sync function to use our custom URLs
        sync: function (method, model, options) {
            options || (options = {});
            options.url = this.getCustomUrl(method.toLowerCase(), model);

            // Lets notify backbone to use our URLs and do follow default course
            return Backbone.sync.apply(this, arguments);
        },
        parse:function(response){
              
              return response.data;
        }
    });
})
