package com.traccar.positions;

import com.traccar.entities.JpaDevicePositionsView;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2018-01-27T09:25:36+0100",

    comments = "version: 1.2.0.Beta2, compiler: javac, environment: Java 1.8.0_151 (Oracle Corporation)"

)

public class DevicePositionsMapperImpl implements DevicePositionsMapper {

    @Override

    public DevicePositions jpaDevicePositionToModel(JpaDevicePositionsView appUser) {

        if ( appUser == null ) {

            return null;
        }

        DevicePositions devicePositions = new DevicePositions();

        devicePositions.setId( appUser.getId() );

        devicePositions.setAddress( appUser.getAddress() );

        devicePositions.setLatitude( appUser.getLatitude() );

        devicePositions.setLongitude( appUser.getLongitude() );

        devicePositions.setTime( appUser.getTime() );

        devicePositions.setCourse( appUser.getCourse() );

        devicePositions.setDescription( appUser.getDescription() );

        devicePositions.setIconArrowMovingColor( appUser.getIconArrowMovingColor() );

        devicePositions.setIconArrowOfflineColor( appUser.getIconArrowOfflineColor() );

        devicePositions.setIconArrowPausedColor( appUser.getIconArrowPausedColor() );

        devicePositions.setIconArrowRadius( appUser.getIconArrowRadius() );

        devicePositions.setIconArrowStoppedColor( appUser.getIconArrowStoppedColor() );

        devicePositions.setName( appUser.getName() );

        devicePositions.setDeviceId( appUser.getDeviceId() );

        return devicePositions;
    }
}

