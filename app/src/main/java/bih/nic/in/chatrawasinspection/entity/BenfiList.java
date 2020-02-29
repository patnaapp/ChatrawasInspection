package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class BenfiList implements KvmSerializable {
    private String SchemeId="";
    private String DistrictCode="";
    private String DistrictName="";
    private String BlockCode;
    private String BlockName;
    private String depId;
    private String BeneficiaryId;
    private String Ben_Name;
    private String Ben_NameHN;
    private String BenFH_Name;
    private String BenFH_NameHN;
    private String photo1;
    private String photo2;
    private String docLat;
    private String doclong;
    private String photoLat;
    private String photoLong;
    private String entryDate;
    private String EntryBy;


    public static Class<BenfiList> HOSTEL_CLASS = BenfiList.class;
    public BenfiList(SoapObject final_object) {
        this.SchemeId=final_object.getProperty("SchemeId").toString();
        this.DistrictCode=final_object.getProperty("DistrictCode").toString();
        this.DistrictName=final_object.getProperty("DistrictName").toString();
        this.BlockCode=final_object.getProperty("BlockCode").toString();
        this.BlockName=final_object.getProperty("BlockName").toString();
        this.BeneficiaryId=final_object.getProperty("BeneficiaryId").toString();
        this.Ben_Name=final_object.getProperty("Ben_Name").toString();
        this.Ben_NameHN=final_object.getProperty("Ben_NameHN").toString();
        this.BenFH_Name=final_object.getProperty("BenFH_Name").toString();
        this.BenFH_NameHN=final_object.getProperty("BenFH_NameHN").toString();
    }

    public BenfiList() {

    }


    public String getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(String schemeId) {
        SchemeId = schemeId;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
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

    public String getBeneficiaryId() {
        return BeneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        BeneficiaryId = beneficiaryId;
    }

    public String getBen_Name() {
        return Ben_Name;
    }

    public void setBen_Name(String ben_Name) {
        Ben_Name = ben_Name;
    }

    public String getBen_NameHN() {
        return Ben_NameHN;
    }

    public void setBen_NameHN(String ben_NameHN) {
        Ben_NameHN = ben_NameHN;
    }

    public String getBenFH_Name() {
        return BenFH_Name;
    }

    public void setBenFH_Name(String benFH_Name) {
        BenFH_Name = benFH_Name;
    }

    public String getBenFH_NameHN() {
        return BenFH_NameHN;
    }

    public void setBenFH_NameHN(String benFH_NameHN) {
        BenFH_NameHN = benFH_NameHN;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getPhoto2() {
        return photo2;
    }

    public void setPhoto2(String photo2) {
        this.photo2 = photo2;
    }

    public String getDocLat() {
        return docLat;
    }

    public void setDocLat(String docLat) {
        this.docLat = docLat;
    }

    public String getDoclong() {
        return doclong;
    }

    public void setDoclong(String doclong) {
        this.doclong = doclong;
    }

    public String getPhotoLat() {
        return photoLat;
    }

    public void setPhotoLat(String photoLat) {
        this.photoLat = photoLat;
    }

    public String getPhotoLong() {
        return photoLong;
    }

    public void setPhotoLong(String photoLong) {
        this.photoLong = photoLong;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
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
