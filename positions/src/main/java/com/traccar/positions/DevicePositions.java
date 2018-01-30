/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.traccar.positions;

import java.util.Date;
import javax.persistence.Column;

/**
 *
 * @author poseidon
 */
public class DevicePositions {

    private long id;

    private String address;
    private Double latitude;

    private Double longitude;

    private Date time;

    private Double course;

    private String description;

    private String iconArrowMovingColor;

    private String iconArrowOfflineColor;

    private String iconArrowPausedColor;

    private Double iconArrowRadius;

    private String iconArrowStoppedColor;

    private String name;
  
    private String deviceId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getCourse() {
        return course;
    }

    public void setCourse(Double course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconArrowMovingColor() {
        return iconArrowMovingColor;
    }

    public void setIconArrowMovingColor(String iconArrowMovingColor) {
        this.iconArrowMovingColor = iconArrowMovingColor;
    }

    public String getIconArrowOfflineColor() {
        return iconArrowOfflineColor;
    }

    public void setIconArrowOfflineColor(String iconArrowOfflineColor) {
        this.iconArrowOfflineColor = iconArrowOfflineColor;
    }

    public String getIconArrowPausedColor() {
        return iconArrowPausedColor;
    }

    public void setIconArrowPausedColor(String iconArrowPausedColor) {
        this.iconArrowPausedColor = iconArrowPausedColor;
    }

    public Double getIconArrowRadius() {
        return iconArrowRadius;
    }

    public void setIconArrowRadius(Double iconArrowRadius) {
        this.iconArrowRadius = iconArrowRadius;
    }

    public String getIconArrowStoppedColor() {
        return iconArrowStoppedColor;
    }

    public void setIconArrowStoppedColor(String iconArrowStoppedColor) {
        this.iconArrowStoppedColor = iconArrowStoppedColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    
}
