
var app = app || {};

define(["backbone"], function (Backbone) {


    return Backbone.Model.extend({
        idAttribute: "mediaId",
        urlRoot: andromeda_server_url + "media/",
        
        getVideoUrl: function (doneCallback, failureCallback) {
            if(this.object_url){
                doneCallback({url:this.object_url})
            }
            else{
                var self = this;
                $.get(this.url() + "/object_url").done(function (data) {
                    self.object_url = data.url;
                    doneCallback({url:self.object_url})
                })
            }
             
        },
        stop:function(){
            var self = this;
            return $.put(this.url()+"/status/STOPPED", function(data){
                self.set({status:"STOPPED"});
            })
        },
        start:function(){
            var self = this;
            return $.put(this.url()+"/status/STARTED", function(data){
                self.set({status:"STARTED"});
            })
        }
    });


})
