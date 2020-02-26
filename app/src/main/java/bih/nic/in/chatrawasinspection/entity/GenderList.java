package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class GenderList {

    private String GenderName;
    private String GenderValue;

    public GenderList(SoapObject final_object) {
        this.GenderName=final_object.getProperty("").toString();
        this.GenderValue=final_object.getProperty("").toString();
    }

    public String getGenderName() {
        return GenderName;
    }

    public void setGenderName(String genderName) {
        GenderName = genderName;
    }

    public String getGenderValue() {
        return GenderValue;
    }

    public void setGenderValue(String genderValue) {
        GenderValue = genderValue;
    }
}
