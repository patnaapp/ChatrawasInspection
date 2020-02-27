package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Village_Entity {

    public static Class<Village_Entity> Village_Class = Village_Entity.class;
    private String Vill_code;
    private String Vill_Name;


    public Village_Entity(SoapObject final_object) {
        this.setVill_code(final_object.getProperty("VillageCode").toString());
        this.setVill_Name(final_object.getProperty("VillageName").toString());

    }

    public Village_Entity(){

    }

    public String getVill_code() {
        return Vill_code;
    }

    public void setVill_code(String vill_code) {
        Vill_code = vill_code;
    }

    public String getVill_Name() {
        return Vill_Name;
    }

    public void setVill_Name(String vill_Name) {
        Vill_Name = vill_Name;
    }
}
