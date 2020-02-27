package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Area_Entity {

    public static Class<Area_Entity> Area_CLASS = Area_Entity.class;
    private String AreaCode;
    private String AreaName;

    public Area_Entity(SoapObject final_object) {
        this.setAreaCode(final_object.getProperty("AreaCode").toString());
        this.setAreaName(final_object.getProperty("AreaName").toString());
    }

    public Area_Entity(){

    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }
}
