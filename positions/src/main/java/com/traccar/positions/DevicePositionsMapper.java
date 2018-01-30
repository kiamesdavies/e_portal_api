/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traccar.positions;

import com.traccar.entities.JpaDevicePositionsView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author poseidon
 */
@Mapper
public interface DevicePositionsMapper {

    DevicePositionsMapper INSTANCE = Mappers.getMapper(DevicePositionsMapper.class);

    DevicePositions jpaDevicePositionToModel(JpaDevicePositionsView appUser);
}
