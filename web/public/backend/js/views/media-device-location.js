

var app = app || {};
define(["backbone", "collections/device-location", "models/device", "text!templates/device-location.html", "/backend/js/randomColor.js","snakeanim"], function (Backbone, DeviceLocationCollection,DeviceModel, DeviceLocationTemplate, RandomColor,SnakeAnim) {

    var SingleDeviceLocation = Backbone.View.extend({
        tagName: "div",
        template: _.template(DeviceLocationTemplate),
        events: {
            "click .view": "playMotion",
            "click .stop": "stopMotion",
        },
        model: DeviceModel,
        
        render: function (map,polylineGroup) {
            this.map = map;
            this.polylineGroup = polylineGroup;
            this.$el.html(this.template(this.model.attributes));
            return this;
        },
        playMotion: function () {
            var _this = this;
            this.$el.find(".motion").toggle();
            
            var line = L.polyline(this.model.get("locations"), {snakingSpeed: 200,color: this.model.get("color")});
            this.snakeLine = line.addTo(this.polylineGroup).on("snakeend", function(){
                noty({
                    "text": _this.model.get("deviceId")+" is done animating",
                    "layout": "topCenter",
                    "type": "success"
                });
            })
            this.snakeLine.snakeIn();

        },
        stopMotion: function () {
            this.$el.find(".motion").toggle();
            this.polylineGroup.removeLayer(this.snakeLine);
            
        }

    });


    return Backbone.View.extend({
        el: "#devices",
        collection: DeviceLocationCollection,
        fetchSheets: function () {
            this.collection.getNextPage();
        },
        initialize: function () {
            this.listenTo(this.collection.fullCollection, "add", this.addDeviceLocation);
            this.listenTo(this.collection, "reset", this.resetCollection);
        },
        
        addDeviceLocation:function(element){
            element.set("color",RandomColor({luminosity:"dark"}))
            var device = new SingleDeviceLocation({ model: element });
            this.$el.append(device.render(this.map,this.polylineGroup).el);
        },
        resetCollection:function(){
            this.$el.html("");
        },
        render: function (map,polylineGroup) {
            this.map = map;
            this.polylineGroup = polylineGroup;
        }
    });

})
