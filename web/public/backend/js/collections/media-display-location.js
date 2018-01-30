


var app = app || {};

define(["backbone_paginator", "models/media-display-location"], function (PageableCollection, MediaDisplayLocation) {

    return PageableCollection.extend({
        model: MediaDisplayLocation,
        mode: "server",
        url: application_server_url + "positions/",

        // Initial pagination states
        state: {
            firstPage: 1,
            pageSize: 100,
            sortKey: "time",
            order: -1,
            totalRecords: 10
        },
        parseState: function (resp, queryParams, state, options) {
            return { totalRecords: parseInt(resp.records) ,totalPages: parseInt(resp.total) };
        },
        // parseLinks: function (resp, options) {
        //     if (resp.length == 0) {
        //         return { "next": "" };
        //     }
        //     return { "next": this.url + "?page=" + this.state.currentPage + "&rows=" + this.state.pageSize };
        // },
        parseRecords: function (resp) {
            return resp.rows;
        },
        // You can remap the query parameters from ``state`` keys from the default
        // to those your server supports. Setting ``null`` on queryParams removed them
        // from being appended to the request URLs.
        queryParams: {
            totalPages: null,
            totalRecords: null,
            currentPage: "page",
            pageSize: "rows",
            sortKey: "sidx",
            order: "sord",
            directions: {
                "-1": "asc",
                "1": "desc"
            }
        }
    });

})