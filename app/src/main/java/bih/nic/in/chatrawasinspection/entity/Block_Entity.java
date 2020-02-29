package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Block_Entity implements KvmSerializable {

    public static Class<Block_Entity> BLKCLASS = Block_Entity.class;
    private String BlockCode;
    private String BlockName;

    public Block_Entity(SoapObject final_object) {
        this.setBlockCode(final_object.getProperty("BlockCode").toString());
        this.setBlockName(final_object.getProperty("BlockName").toString());
    }

    public Block_Entity(){

    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
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
