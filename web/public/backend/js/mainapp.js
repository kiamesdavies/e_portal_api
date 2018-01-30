var andromeda_server_url = "http://localhost:9000/";
//server base url
var andromeda_base_url = "/backend/js/";
var appUserStorageName = "appuser";
var app = {};

require.config({
    //client base url
    baseUrl: andromeda_base_url,
    waitSeconds: 200,
    paths: {
        jquery: 'plugins/jquery-2.1.1',
        underscore: 'plugins/underscore-min',
        backbone: 'plugins/backbone',
        backbone_paginator: 'plugins/backbone.paginator.min',
        parsley: 'plugins/parsleyjs/dist/parsley.min',
        tether: 'https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min',
        material: 'material-kit/material.min',
        material_dashboard: 'material-kit/material-dashboard',
        nouislider: 'material-kit/nouislider.min',
        bootstrap_datepicker: 'material-kit/bootstrap-datepicker',
        material_kit: 'material-kit/material-kit',
        bootstrap: 'material-kit/bootstrap.min',
        responsive_table: '../bower_components/responsive-tables/responsive-tables',
        datatable: 'jquery.dataTables.min',
        chosen: '../bower_components/chosen/chosen.jquery',
        cookie: 'jquery.cookie',
        notification: 'plugins/notify/bootstrap-notify',
        charisma: 'charisma',
        history: 'jquery.history',
        store: 'plugins/storageapi/jquery.storageapi.min',
        raty: 'jquery.raty.min',
        utility: 'custom/utility',



        bootstrap_wizard: 'plugins/bootstrap-wizard/jquery.bootstrap-wizard',
        select_bootstrap: 'plugins/select-bootstrap/jquery.select-bootstrap',

        multi_bootstrap_datepicker: 'https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.4/js/bootstrap-datepicker',
        uppy: 'https://unpkg.com/uppy@0.21.0/dist/uppy.min',
        locate: 'https://domoritz.github.io/leaflet-locatecontrol/dist/L.Control.Locate.min',
        leaflet:'https://unpkg.com/leaflet@1.2.0/dist/leaflet',
        googleMutant:'https://unpkg.com/leaflet.gridlayer.googlemutant@latest/Leaflet.GoogleMutant',
        markercluster:'https://unpkg.com/leaflet.markercluster@1.1.0/dist/leaflet.markercluster',
        routing:'https://unpkg.com/leaflet-routing-machine@latest/dist/leaflet-routing-machine',
        polyline_decorator:'plugins/leaflet/js/polylineDecorator',
        snakeanim:'plugins/leaflet/js/SnakeAnim',
        makimarkers:'plugins/leaflet/js/MakiMarkers'
    },
    shim: {
        "routing": {
            deps: ["leaflet"]
        },
        "markercluster": {
            deps: ["leaflet"]
        },
        "googleMutant": {
            deps: ["leaflet"]
        },
        "locate": {
            deps: ["leaflet"]
        },
        "polyline_decorator": {
            deps: ["leaflet"]
        },
        "snakeanim": {
            deps: ["leaflet"]
        },
        "makimarkers": {
            deps: ["leaflet"]
        },
        
        "material_dashboard": {
            deps: ["material"]
        },
        "bootstrap_datepicker": {
            deps: ["nouislider"]
        },
        "nouislider": {
            deps: ["material"]
        },
        "multi_bootstrap_datepicker": {
            deps: ["bootstrap"]
        },
        "material": {
            deps: ["bootstrap"]
        },

        "bootstrap": {
            deps: ["jquery"]
        },
        "uppy": {
            deps: ["jquery"]
        },
        "notification": {
            deps: ["jquery"]
        },
        "dropzone": {
            deps: ["jquery"]
        },
        "bootstrap_wizard": {
            deps: ["jquery"]
        },
        "charisma": {
            deps: ["jquery", "bootstrap", "material", "nouislider", "material_dashboard", "parsley", "notification", "select_bootstrap", "multi_bootstrap_datepicker"],
            export: "charisma"
        },
        "evaporate": {
            deps: ["jquery"]
        },

        backbone_paginator: {
            deps: ["backbone"]
        },

        "utility": {
            deps: ["jquery", "store"],
            export: "utility"
        }

    }
});




require(["jquery", "underscore", "backbone", "charisma", "utility", "models/appuser", "text!templates/mainview.html", "route/mainroute"],
    function ($, _, Backbone, charisma, utility, AppUserModel, mainView, mainRoute) {
        var MainView = Backbone.View.extend({
            el: "#body",
            maintemplate: _.template(mainView),
            events: {
                "click #login-form-button": "login",
                "click #signup-form-button": "signup",
                "click #logout": "logOut",

            },
            render: function (signup, returnUrl) {
                var appUser = this.model.toJSON();
                appUser.login = !appUser.appUserId;
                appUser.signup = signup;
                var self = this;
                this.$el.html(this.maintemplate(appUser));

                //reload the panel to the loader icon
                this.$el.find("#main-panel-container").html('');
                this.$el.find("#loader-placeholder").clone().show().appendTo(this.$el.find("#main-panel-container"));

                this.returnUrl = returnUrl || "home";
                return this;
            },
            initialize: function () {
                this.model = new AppUserModel(getHtmlStorage(appUserStorageName) || {});
                $.notifyDefaults({

                    timeout: 6000, // delay for closing event. Set false for sticky notifications

                });
                
                return this;
            },



            
        })


        app.mainRoute = new mainRoute(MainView);
        Backbone.history.start();


    })


/*
 facking sessions
 setHtmlStorage(appUserStorageName, {"email":"jamesniranye@gmail.com","username":"kiamesdavies","firstName":"James","lastName":"Akinniranye","dateCreated":1436648900000,"createdBy":"SYSTEM","dateModified":null,"modifiedBy":null,"Authorization":"nbp9b42500l4paf2pc0m3rq6vj","allDevices":0,"balance":0,"deviceShowingVideos":0,"timeout":2400,"allVideos":0,"videosShowing":0}, 40*60)*/
