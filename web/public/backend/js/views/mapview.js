/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Created by encentral_lap_1 on 8/30/2015.
 */


var app = app || {};
app.dateFormat = "YYYY-MM-DDTHH:mm:ss.000";
define(["backbone", "text!templates/pages/mapview.html", "//cdn.jsdelivr.net/momentjs/latest/moment.min.js", "//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js", "text!../../css/daterangepicker.css", "collections/media-display-location", "views/media-display-location"], function (Backbone, mapViewTemplate, moment, dateRangerPicker, dateRangerPickerCss, MediaDisplayCollection, MediaDisplayView) {
    return Backbone.View.extend({
        el: "#main-panel-container",
        template: _.template(mapViewTemplate),
        events: {
            //"click .apply-filter": "process"
        },

        initialize: function () {
            //if the css has not been loaded before
            if (!$("#general-style").data("dateRangerPickerCss")) {
                $("#general-style").append(dateRangerPickerCss);
                $("#general-style").data("dateRangerPickerCss", true);
            }

        },
        render: function () {
            //2018-01-03T13:51:23.268+0000
            this.$el.html(this.template({}));
            var start = moment().subtract(1, 'days');
            var end = moment();
            app.start = start.format(app.dateFormat);
            app.end = end.format(app.dateFormat)

            this.$dateRange = this.$("#reportrange");


            this.dataTable = this.$dateRange.daterangepicker({
                startDate: start,
                endDate: end,
                timePicker: true,
                timePickerIncrement: 30,
                applyClass: "apply-filter",
                locale: {
                    format: app.dateFormat
                },
                ranges: {
                    'Today': [moment().startOf('day'), moment().endOf('day')],
                    'Yesterday': [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')],
                    'Last 7 Days': [moment().subtract(6, 'days').startOf('day'), moment().endOf('day')],
                    'Last 30 Days': [moment().subtract(29, 'days').startOf('day'), moment().endOf('day')],
                    'This Month': [moment().startOf('month').startOf('day'), moment().endOf('month').endOf('day')],
                    'Last Month': [moment().subtract(1, 'month').startOf('month').startOf('day'), moment().subtract(1, 'month').endOf('month').endOf('day')]
                }

            }, this.cb);

            this.cb(start, end);
            var _this = this;
            this.$dateRange.on('apply.daterangepicker', function (ev, picker) {
                //do something, like clearing an input
                var start = picker.startDate.format(app.dateFormat);
                var end = picker.endDate.format(app.dateFormat);
                app.start = start;
                app.end = end;
                _this.mediaDisplayCollection.reset();

                _this.mediaDisplayCollection.getFirstPage({ fetch: true })
                .done(function(){
                    _this.mediaDisplayView.loadNextPage();
                })
                .fail(function (xhr, model, options) {
                    noty({
                        "text": "Can not fetch map data" + xhr.responseText,
                        "layout": "topCenter",
                        "type": "error"
                    });
                });
            });

            var mediaDisplayCollection = new MediaDisplayCollection
                ([],
                {
                    queryParams: {
                        start: function () {
                            return app.start;
                        },
                        end: function () {
                            return app.end;
                        },
                        _search: false
                    }
                });
            this.mediaDisplayCollection = mediaDisplayCollection;
            var mediaDisplayView = new MediaDisplayView({ collection: this.mediaDisplayCollection });
            this.mediaDisplayView = mediaDisplayView;
            mediaDisplayView.render();
            this.mediaDisplayCollection.fetch().done(function(){
                mediaDisplayView.loadNextPage();
            }).fail(function (xhr, model, options) {
                noty({
                    "text": "Can not fetch map data" + xhr.responseText,
                    "layout": "topCenter",
                    "type": "error"
                });

            });


        },
        cb: function (start, end) {
            $("#reportrange").html(start.format('MMMM D, YYYY h:mm A') + ' - ' + end.format('MMMM D, YYYY h:mm A'));
        }

    });
})








