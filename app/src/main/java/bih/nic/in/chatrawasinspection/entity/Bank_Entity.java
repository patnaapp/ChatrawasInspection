package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Bank_Entity implements KvmSerializable {

    public static Class<Bank_Entity> Bank_Class = Bank_Entity.class;
    private String Bank_Code;
    private String Bank_Nm;

    public Bank_Entity(SoapObject final_object) {
        this.setBank_Code(final_object.getProperty("code").toString());
        this.setBank_Nm(final_object.getProperty("Bankname").toString());
    }

    public Bank_Entity(){

    }

    public String getBank_Code() {
        return Bank_Code;
    }

    public void setBank_Code(String bank_Code) {
        Bank_Code = bank_Code;
    }

    public String getBank_Nm() {
        return Bank_Nm;
    }

    public void setBank_Nm(String bank_Nm) {
        Bank_Nm = bank_Nm;
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
