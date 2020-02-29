package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Category_Entity implements KvmSerializable {

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

    @Override
    public Object getProperty(int i) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int i, Object o) {

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {

    }
}
