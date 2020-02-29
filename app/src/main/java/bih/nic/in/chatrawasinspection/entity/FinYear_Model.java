package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class FinYear_Model implements KvmSerializable {

    public static Class<FinYear_Model> FYEARCLASS = FinYear_Model.class;
    private String FYearCode;
    private String FYearName;

    public FinYear_Model(SoapObject final_object) {
        this.setFYearCode(final_object.getProperty("FYearID").toString());
        this.setFYearName(final_object.getProperty("FinYear").toString());
    }

    public FinYear_Model(){

    }

    public String getFYearCode() {
        return FYearCode;
    }

    public void setFYearCode(String FYearCode) {
        this.FYearCode = FYearCode;
    }

    public String getFYearName() {
        return FYearName;
    }

    public void setFYearName(String FYearName) {
        this.FYearName = FYearName;
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
