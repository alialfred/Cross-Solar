/*
 */
package com.crossover.techtrial.dto;

import com.crossover.techtrial.model.Panel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Ali Imran
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanelDto extends AbstractDto<Panel>{
    private Panel panel;

    public PanelDto() {
        this(new Panel());
    }

    public PanelDto(Panel panel) {
        this.panel = panel;
    }

    public Long getId() {
        return panel.getId();
    }

    public void setId(Long id) {
        this.panel.setId(id);
    }

    public String getSerial() {
        return panel.getSerial();
    }

    public void setSerial(String serial) {
        this.panel.setSerial(serial);
    }

    public Double getLongitude() {
        return panel.getLongitude();
    }

    public void setLongitude(Double longitude) {
        this.panel.setLongitude(longitude);
    }

    public Double getLatitude() {
        return this.panel.getLatitude();
    }

    public void setLatitude(Double latitude) {
        this.panel.setLatitude(latitude);
    }

    public String getBrand() {
        return this.panel.getBrand();
    }

    public void setBrand(String brand) {
        this.panel.getBrand();
    }
    
    @Override
    public String validate(){
        if(this.panel.getSerial().length() != 16){
            return "Serial number must be 16 character long.";
        }
        if(String.valueOf(panel.getLatitude()).split("[.]")[1].length() < 6){
            return "Latitude must contains 6 decimal places.";
        }
        if(String.valueOf(panel.getLongitude()).split("[.]")[1].length() < 6){
            return "Longitude must contains 6 decimal places.";
        }
        return null;
    }

    @Override
    public Panel getEntity() {
        return this.panel;
    }
    
}
