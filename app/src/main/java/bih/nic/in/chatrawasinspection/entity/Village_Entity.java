package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Village_Entity  implements KvmSerializable {

    public static Class<Village_Entity> Village_Class = Village_Entity.class;
    private String Vill_code;
    private String Vill_Name;


    public Village_Entity(SoapObject final_object) {
        this.setVill_code(final_object.getProperty("VillageCode").toString());
        this.setVill_Name(final_object.getProperty("VillageName").toString());

    }

    public Village_Entity(){

    }

    public String getVill_code() {
        return Vill_code;
    }

    public void setVill_code(String vill_code) {
        Vill_code = vill_code;
    }

    public String getVill_Name() {
        return Vill_Name;
    }

    public void setVill_Name(String vill_Name) {
        Vill_Name = vill_Name;
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
