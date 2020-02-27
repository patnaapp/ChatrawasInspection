package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Ward_Entity {

    public static Class<Ward_Entity> Ward_Class = Ward_Entity.class;
    private String PanchayatCode;
    private String WARDCODE;
    private String WARDNAME;
    private String BlockCode;
    private String AreaCode;

    public Ward_Entity(SoapObject final_object) {
        this.setPanchayatCode(final_object.getProperty("PanchayatCode").toString());
        this.setWARDCODE(final_object.getProperty("WARDCODE").toString());
        this.setWARDNAME(final_object.getProperty("WARDNAME").toString());
        this.setBlockCode(final_object.getProperty("BlockCode").toString());
        this.setAreaCode(final_object.getProperty("AreaCode").toString());
    }

    public Ward_Entity(){

    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getWARDCODE() {
        return WARDCODE;
    }

    public void setWARDCODE(String WARDCODE) {
        this.WARDCODE = WARDCODE;
    }

    public String getWARDNAME() {
        return WARDNAME;
    }

    public void setWARDNAME(String WARDNAME) {
        this.WARDNAME = WARDNAME;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getAreaCode() {
        return AreaCode;
    }

    public void setAreaCode(String areaCode) {
        AreaCode = areaCode;
    }
}
