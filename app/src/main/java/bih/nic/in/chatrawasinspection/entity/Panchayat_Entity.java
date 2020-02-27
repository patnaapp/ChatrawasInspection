package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Panchayat_Entity {

    public static Class<Panchayat_Entity> Panchayat_Class = Panchayat_Entity.class;
    private String Pan_code;
    private String Pan_Name;

    public Panchayat_Entity(SoapObject final_object) {
        this.setPan_code(final_object.getProperty("PanchayatCode").toString());
        this.setPan_Name(final_object.getProperty("PanchayatName").toString());
    }

    public Panchayat_Entity(){

    }

    public String getPan_code() {
        return Pan_code;
    }

    public void setPan_code(String pan_code) {
        Pan_code = pan_code;
    }

    public String getPan_Name() {
        return Pan_Name;
    }

    public void setPan_Name(String pan_Name) {
        Pan_Name = pan_Name;
    }
}
