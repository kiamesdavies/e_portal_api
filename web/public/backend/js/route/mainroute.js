/**
 * Created by encentral_lap_1 on 8/29/2015.
 */

var app = app || {};

define(["backbone"], function (Backbone) {
    var mainRoute = Backbone.Router.extend({
        initialize: function (MainView) {
            app.mainView = new MainView();
            $('#pace-css').attr('href', andromeda_base_url + 'plugins/pace/minimal.css');
            $(document).ajaxStart(function () {
                Pace.restart();
            });
            $(document).ajaxComplete(function () {
                Pace.stop();
            });
            $(document).ajaxSend(function (xhr, jqxhr, settings) {
                if (getAuthorizationToken()) {
                    jqxhr.setRequestHeader("Authorization", getAuthorizationToken());
                }
                //jqxhr.setRequestHeader("X-Requested-With","XMLHttpRequest");
                jqxhr.setRequestHeader("Accept", "application/json, text/javascript, */*; q=0.01");
            });

        },

        routes: {
            "": "home",
            "home": "home",
            
        },

        home: function () {
            app.mainView.initialize();
            app.mainView.initialize();
            app.mainView.render();
            require(["views/mapview"], function (MapView) {
                var map = new MapView();
                map.render();
                app.mainView.currentView = map;
            })


        },
        

    });
    return mainRoute;
})
