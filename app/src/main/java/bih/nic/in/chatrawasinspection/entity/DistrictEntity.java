package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class DistrictEntity implements KvmSerializable {

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
