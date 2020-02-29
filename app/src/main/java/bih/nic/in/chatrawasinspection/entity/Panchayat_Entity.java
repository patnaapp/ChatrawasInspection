package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Panchayat_Entity implements KvmSerializable {

    public static Class<Panchayat_Entity> Panchayat_Class = Panchayat_Entity.class;
    private String Pan_code;
    private String Pan_Name;
    private String block_code;
    private String area_type;

    public Panchayat_Entity(SoapObject final_object) {
        this.setPan_code(final_object.getProperty("PanchayatCode").toString());
        this.setPan_Name(final_object.getProperty("PanchayatName").toString());
        this.setBlock_code(final_object.getProperty("BlockCode").toString());
        this.setArea_type(final_object.getProperty("AreaCode").toString());
    }

    public Panchayat_Entity(){

    }

    public String getPan_code() {
        return Pan_code;
    }

    public void setPan_code(String pan_code) {
        Pan_code = pan_code;
    }

    public String getPan_Name() {
        return Pan_Name;
    }

    public void setPan_Name(String pan_Name) {
        Pan_Name = pan_Name;
    }

    public String getBlock_code() {
        return block_code;
    }

    public void setBlock_code(String block_code) {
        this.block_code = block_code;
    }

    public String getArea_type() {
        return area_type;
    }

    public void setArea_type(String area_type) {
        this.area_type = area_type;
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
