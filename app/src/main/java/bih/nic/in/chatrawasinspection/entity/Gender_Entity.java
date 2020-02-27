package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Gender_Entity {

    public static Class<Gender_Entity> Gender_Class = Gender_Entity.class;
    private String gender_Id;
    private String gender_Nm;

    public Gender_Entity(SoapObject final_object) {
        this.setGender_Id(final_object.getProperty("SValue").toString());
        this.setGender_Nm(final_object.getProperty("Sex").toString());
    }

    public Gender_Entity(){

    }

    public String getGender_Id() {
        return gender_Id;
    }

    public void setGender_Id(String gender_Id) {
        this.gender_Id = gender_Id;
    }

    public String getGender_Nm() {
        return gender_Nm;
    }

    public void setGender_Nm(String gender_Nm) {
        this.gender_Nm = gender_Nm;
    }
}
