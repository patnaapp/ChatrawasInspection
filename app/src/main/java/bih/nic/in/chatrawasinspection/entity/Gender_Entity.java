package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Gender_Entity implements KvmSerializable {

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
