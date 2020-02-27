package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Category_Entity {

    public static Class<Category_Entity> Category_Class = Category_Entity.class;
    private String Cat_Id;
    private String Cat_Nm;

    public Category_Entity(SoapObject final_object) {
        this.setCat_Id(final_object.getProperty("a_id").toString());
        this.setCat_Nm(final_object.getProperty("Cat_Name").toString());
    }

    public Category_Entity(){

    }

    public String getCat_Id() {
        return Cat_Id;
    }

    public void setCat_Id(String cat_Id) {
        Cat_Id = cat_Id;
    }

    public String getCat_Nm() {
        return Cat_Nm;
    }

    public void setCat_Nm(String cat_Nm) {
        Cat_Nm = cat_Nm;
    }
}
