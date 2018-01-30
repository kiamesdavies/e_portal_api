/**
 * Created by encentral_lap_1 on 8/20/2015.
 */

var app = app || {};

define(["backbone"], function (Backbone) {


    var AppUserModel = Backbone.Model.extend({
        //idAttribute: "appUserId",
        //urlRoot: andromeda_server_url+"users",
        initialize: function () {
            this.on("change", function () {
                setHtmlStorage(appUserStorageName, this.toJSON(), app.mainView.model.get("timeout"))
            }, this)
        },
        getPrivilege(activityId) {
            return _.find(this.get("activities"), function (obj) {
                return (obj["activityId"]).toUpperCase() == activityId.toUpperCase();
            });
        },
        getCustomUrl: function (method, model) {
            switch (method) {
                case 'read':
                    return andromeda_server_url + "auth/login";
                    break;
                case 'create':
                    return andromeda_server_url + "auth/signup";
                    break;
                case 'update':
                    return andromeda_server_url + "users/" + this.id;
                    break;
                case 'delete':
                    return  andromeda_server_url + "users/" + this.id;
                    break;
            }
        },
        sync: function (method, model, options) {
            options || (options = {});


            options.url = this.getCustomUrl(method.toLowerCase(), model);

            // Lets notify backbone to use our URLs and do follow default course
            return Backbone.sync.apply(this, arguments);
        },
        parse: function (response) {
            this.id = response.appUserId;
            return response;
        }
    });

    return AppUserModel;
})
