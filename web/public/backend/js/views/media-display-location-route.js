

var app = app || {};
define(["backbone", "models/media-display-location", "leaflet", "text!templates/location-popup.html", "models/advert-media", "snakeanim", "makimarkers"], function (Backbone, MediaDisplayModel, L, LocationPopupTemplate, AdvertModel, snakeanim, makimarkers) {

    var PopUp = Backbone.View.extend({
        tagName: "div",
        className: "row",
        template: _.template(LocationPopupTemplate),
        model: MediaDisplayModel,
        render: function () {

            if(this.model.get("time")){
                this.model.set("time", new Date(this.model.get("time")));
            }
            this.$el.html(this.template(this.model.attributes));
            return this;
        },
        showMediaName: function () {
            var _this = this;
            if (!this.mediaName) {
                var advert = new AdvertModel({mediaId: this.model.get("mediaId")})
                advert.fetch().done(function (data, respose, options) {
                    _this.mediaName = advert.get("name");
                    _this.$el.find(".mediaName").html(_this.mediaName)
                });

            }

        },

    });
    return Backbone.View.extend({

        model: MediaDisplayModel,

        initialize: function () {
            L.MakiMarkers.accessToken = "pk.eyJ1Ijoia2lhbWVzZGF2aWVzIiwiYSI6Im1DY29wUjQifQ.EK0tN92ikkDZIxXTF4FKBQ";
        },
        render: function (deviceLocationCollection, map, markerClusters, polylineGroup) {
            //no-op if device previously exist
            deviceLocationCollection.fullCollection.add({deviceId: this.model.get("deviceId"),name: this.model.get("name"), locations: []});



            var someGeojsonFeature = [];
            var geopoint = [this.model.get("latitude"),this.model.get("longitude")];
            someGeojsonFeature.push({
                "type": "Feature",
                "properties": this.model,
                "geometry": {
                    "type": "Point",
                    "coordinates": [parseFloat(geopoint[1]), parseFloat(geopoint[0])]
                }
            })

            var icon = L.MakiMarkers.icon({icon:  this.model.get("course") && parseInt(this.model.get("course")) ? "car":"square", color: deviceLocationCollection.fullCollection.get(this.model.get("deviceId")).get("color"), size: "l"});

            L.geoJSON(someGeojsonFeature, {
                onEachFeature: this.onEachFeature,
                pointToLayer: function (feature, latlng) {
                    return L.marker(latlng, {icon: icon});
                }
            }).addTo(markerClusters);
            map.fitBounds(markerClusters.getBounds());



            var previousLocations = deviceLocationCollection.fullCollection.get(this.model.get("deviceId")).get("locations");
            previousLocations.push(geopoint);
            deviceLocationCollection.fullCollection.get(this.model.get("deviceId")).set("locations", previousLocations)



        },
        onEachFeature: function (feature, layer) {

            if (feature.properties) {
                var model = feature.properties;
                var pop = new PopUp({model: model});


                layer.bindPopup(pop.render().el).on('click', function () {
                    

                })
            }

        },

    });

})
