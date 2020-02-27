package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class DistrictEntity {

    public static Class<DistrictEntity> DistClass = DistrictEntity.class;
    private String DistCode;
    private String DistName;

    public DistrictEntity(SoapObject final_object) {
        this.setDistCode(final_object.getProperty("DistCode").toString());
        this.setDistName(final_object.getProperty("DistName").toString());
    }

    public DistrictEntity(){

    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getDistName() {
        return DistName;
    }

    public void setDistName(String distName) {
        DistName = distName;
    }
}
