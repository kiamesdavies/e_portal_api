/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traccar.positions;

import com.google.inject.Singleton;
import com.github.markserrano.jsonquery.jpa.enumeration.OrderEnum;
import com.github.markserrano.jsonquery.jpa.filter.JsonFilter;
import com.github.markserrano.jsonquery.jpa.response.JqgridResponse;
import com.github.markserrano.jsonquery.jpa.service.FilterService;
import com.github.markserrano.jsonquery.jpa.specifier.Order;
import com.github.markserrano.jsonquery.jpa.util.DateTimeUtil;
import com.github.markserrano.jsonquery.jpa.util.QueryUtil;
import com.google.inject.Inject;
import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.OrderSpecifier;
import com.traccar.entities.JpaDevicePositionsView;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;
import mock.springframework.data.domain.Page;
import mock.springframework.data.domain.PageRequest;
import mock.springframework.data.domain.Pageable;
import play.db.jpa.JPAApi;

/**
 *
 * @author poseidon
 */
@Singleton
public class DevicePositionsResource {

    @Inject
    JPAApi jPAApi;

    @Inject
    private FilterService filterService;

    public JqgridResponse<DevicePositions> getDevicePositions(Date start, Date end, Boolean search, String filters, Integer page, Integer rows, String sidx, String sord) {
        filters = filters == null ? QueryUtil.INIT_FILTER : filters;

        Order order = new Order(JpaDevicePositionsView.class);

        OrderSpecifier<?> orderSpecifier = order.by(sidx, OrderEnum.getEnum(sord));
        JsonFilter jsonFilter = new JsonFilter(filters);

        BooleanBuilder build = filterService.getJsonBooleanBuilder(JpaDevicePositionsView.class).buildAndBlock(Arrays.asList(new JsonFilter.Rule("AND", "time", "ge", DateTimeUtil.SIMPLE_DATE_WITH_TIME_FORMAT.format(start)), new JsonFilter.Rule("AND", "time", "le", DateTimeUtil.SIMPLE_DATE_WITH_TIME_FORMAT.format(end))), jsonFilter);

        if (rows == null) {
            rows = filterService.count(build, JpaDevicePositionsView.class, orderSpecifier).intValue();
        }

        // Prepare arguments before reading from service
        Pageable pageable = new PageRequest(page >= 1 ? page - 1 : 0, rows > 0 ? rows : 1);

        // Read from service
        Page<JpaDevicePositionsView> results = filterService.readAndCount(build, pageable, JpaDevicePositionsView.class, orderSpecifier);

        // Map response
        JqgridResponse<DevicePositions> response = new JqgridResponse<>();
        response.setRows(results.getContent().stream().map(DevicePositionsMapper.INSTANCE::jpaDevicePositionToModel).filter(s -> s != null).collect(Collectors.toList()));
        response.setRecords(Long.toString(results.getTotalElements()));
        response.setTotal(Integer.toString(results.getTotalPages()));
        response.setPage(Integer.toString(results.getNumber() + 1));
        return response;
    }

}
