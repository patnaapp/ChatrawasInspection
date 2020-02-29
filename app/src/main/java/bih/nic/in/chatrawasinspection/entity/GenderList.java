package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class GenderList implements KvmSerializable {

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
