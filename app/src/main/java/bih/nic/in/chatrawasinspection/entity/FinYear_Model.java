package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class FinYear_Model {

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
}
