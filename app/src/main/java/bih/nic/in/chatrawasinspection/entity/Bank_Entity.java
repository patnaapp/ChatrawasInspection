package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Bank_Entity {

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
}
