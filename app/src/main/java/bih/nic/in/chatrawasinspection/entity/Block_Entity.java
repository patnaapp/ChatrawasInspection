package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.SoapObject;

public class Block_Entity {

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
}
