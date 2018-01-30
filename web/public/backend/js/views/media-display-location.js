

var app = app || {};
define(["backbone", "collections/media-display-location", "leaflet", "markercluster", "googleMutant", "locate", "views/media-display-location-route", "collections/device-location", "views/media-device-location"], function (Backbone, MediaDisplayCollection, leaflet, Markercluster, GoogleMutant, Locate, RouteView, DeviceLocationCollection, DeviceLocationView) {


    return Backbone.View.extend({
        el: "#map",

        collection: MediaDisplayCollection,
        events: {
            "scroll": "fetchSheets"
        },
        fetchSheets: function () {
            this.collection.getNextPage();
        },
        initialize: function () {
            this.listenTo(this.collection, "add", this.addDisplayLocation);
            this.listenTo(this.collection, "reset", this.resetCollection);
            this.geojsonMarkerOptions = {
                radius: 8,
                icon: L.icon({
                    iconUrl: '/backend/img/map/marker-icon.png',
                    shadowUrl: '/backend/img/map/marker-shadow.png'
                }),
                weight: 1,
                opacity: 1,
                fillOpacity: 0.8
            };

        },

        addDisplayLocation: function (displayLocation) {
            // for (var i = 0; i < this.collection.length; i++) {
            //     var routeView = new RouteView({ model: this.collection.at(i) });
            //     routeView.render(this.map, this.markerClusters, this.geojsonMarkerOptions);
            // }
            // return this;

            var routeView = new RouteView({model: displayLocation});
            routeView.render(this.deviceLocationCollection, this.map, this.markerClusters, this.polylineGroup, this.geojsonMarkerOptions);
        },
        resetCollection: function () {

            this.markerClusters.clearLayers();
            this.polylineGroup.clearLayers();
            this.deviceLocationCollection.fullCollection.reset();
        },
        loadNextPage: function () {
            var _this = this;
            if (this.collection.hasNextPage()) {
                setTimeout(function () {
                    _this.collection.getNextPage().done(function () {
                        _this.loadNextPage()
                    })
                }, 1000)
            }
        },
        render: function () {
            var map = L.map(this.el, {
                zoom: 2,
                center: [9.0820, 8.6753],
                zoomControl: false,
                attributionControl: false
            });


            this.geoJsonLayerGroup = new L.LayerGroup();
            var markerClusters = new L.MarkerClusterGroup({
                spiderfyOnMaxZoom: true,
                showCoverageOnHover: false,
                zoomToBoundsOnClick: true,
                disableClusteringAtZoom: 16
            });

            var googleMap = L.gridLayer.googleMutant({
                type: 'roadmap' // valid values are 'roadmap', 'satellite', 'terrain' and 'hybrid'
            });
            var mapboxSat = L.gridLayer.googleMutant({
                type: 'satellite' // valid values are 'roadmap', 'satellite', 'terrain' and 'hybrid'
            });
            var mapboxOSM = L.gridLayer.googleMutant({
                type: 'terrain' // valid values are 'roadmap', 'satellite', 'terrain' and 'hybrid'
            });
            var usgsImagery = L.gridLayer.googleMutant({
                type: 'hybrid' //mapboxOSM valid values are 'roadmap', 'satellite', 'terrain' and 'hybrid'
            });

            //add the generated list to cluster
            markerClusters.addLayer(this.geoJsonLayerGroup);

            //add layers to map
            map.addLayer(googleMap);
            map.addLayer(markerClusters);


            /* Attribution control */
            function updateAttribution(e) {
                $.each(map._layers, function (index, layer) {
                    if (layer.getAttribution) {
                        $('#attribution').html((layer.getAttribution()));
                    }
                });
            }
            map.on('layeradd', updateAttribution);
            map.on('layerremove', updateAttribution);

            let attributionControl = L.control({
                position: 'bottomright'
            });
            attributionControl.onAdd = function (map) {
                let div = L.DomUtil.create('div', 'leaflet-control-attribution');
                div.innerHTML = "<span class='hidden-xs'>Developed by <a href='http://io-technologies.net'>IO-Technologies</a> | </span><a href='#' onclick='$(\"#attributionModal\").modal(\"show\"); return false;'>Attribution</a>";
                return div;
            };
            map.addControl(attributionControl);

            var zoomControl = L.control.zoom({
                position: 'bottomright'
            }).addTo(map);

            /* GPS enabled geolocation control set to follow the user's location */
            var locateControl = L.control.locate({
                position: 'bottomright',
                drawCircle: true,
                follow: true,
                setView: true,
                keepCurrentZoomLevel: true,
                markerStyle: {
                    weight: 1,
                    opacity: 0.8,
                    fillOpacity: 0.8
                },
                circleStyle: {
                    weight: 1,
                    clickable: false
                },
                icon: 'fa fa-location-arrow',
                metric: false,
                strings: {
                    title: 'My location',
                    popup: 'You are within {distance} {unit} from this point',
                    outsideMapBoundsMsg: 'You seem located outside the boundaries of the map'
                },
                locateOptions: {
                    maxZoom: 18,
                    watch: true,
                    enableHighAccuracy: true,
                    maximumAge: 10000,
                    timeout: 10000
                }
            }).addTo(map);




            var baseLayers = {
                'Road Map': googleMap,
                'Satellite Map': mapboxSat,
                'Terrain': mapboxOSM,
                'Hybrid': usgsImagery
            };

            var polylineGroup = L.layerGroup().addTo(map);
            ;

            var groupedOverlays = {
                'Markers': markerClusters,
                'Line': polylineGroup
            };



            L.control.layers(baseLayers, groupedOverlays).addTo(map);


            this.map = map;
            this.markerClusters = markerClusters;
            this.polylineGroup = polylineGroup;

            var deviceLocationCollection = new DeviceLocationCollection();
            this.deviceLocationCollection = deviceLocationCollection;

            var deviceLocationView = new DeviceLocationView({collection: this.deviceLocationCollection});
            deviceLocationView.render(this.map, this.polylineGroup);
            setTimeout(function () {
                $(window).on("resize", function () {

                    $(".map_container").height($(window).height()-80);
                    $("#map").height($(window).height() - 85);
                    map.invalidateSize();
                }).trigger("resize");
            }, 2000)
        }
    });

})
