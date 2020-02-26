package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class StudentPhoto implements KvmSerializable {

    //private String id;
    int id;
    private String BenficiaryName;
    private String BenficiaryHName;
    private String BenficiaryId;
    private String photo1;
    private String photo2;
    private String schemeid;
    private String DepartId;
    private String latitude;
    private String longitude;
    private String entryDate;
    private String entryBy;
    private String DistCode;
    private String BenFName;
    private String BenHFName;
    private String Hostelcode;
    private String AppVersion;
    private String DeviceType;
    private byte[] photoByte1;
    private byte[] photoByte2;

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

//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBenficiaryName() {
        return BenficiaryName;
    }

    public void setBenficiaryName(String benficiaryName) {
        BenficiaryName = benficiaryName;
    }

    public String getBenficiaryId() {
        return BenficiaryId;
    }

    public void setBenficiaryId(String benficiaryId) {
        BenficiaryId = benficiaryId;
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

    public String getSchemeid() {
        return schemeid;
    }

    public void setSchemeid(String schemeid) {
        this.schemeid = schemeid;
    }

    public String getDepartId() {
        return DepartId;
    }

    public void setDepartId(String departId) {
        DepartId = departId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getBenFName() {
        return BenFName;
    }

    public void setBenFName(String benFName) {
        BenFName = benFName;
    }

    public String getHostelcode() {
        return Hostelcode;
    }

    public void setHostelcode(String hostelcode) {
        Hostelcode = hostelcode;
    }

    public String getAppVersion() {
        return AppVersion;
    }

    public void setAppVersion(String appVersion) {
        AppVersion = appVersion;
    }

    public String getDeviceType() {
        return DeviceType;
    }

    public void setDeviceType(String deviceType) {
        DeviceType = deviceType;
    }

    public byte[] getPhotoByte1() {
        return photoByte1;
    }

    public void setPhotoByte1(byte[] photoByte1) {
        this.photoByte1 = photoByte1;
    }

    public byte[] getPhotoByte2() {
        return photoByte2;
    }

    public void setPhotoByte2(byte[] photoByte2) {
        this.photoByte2 = photoByte2;
    }

    public String getBenficiaryHName() {
        return BenficiaryHName;
    }

    public void setBenficiaryHName(String benficiaryHName) {
        BenficiaryHName = benficiaryHName;
    }

    public String getBenHFName() {
        return BenHFName;
    }

    public void setBenHFName(String benHFName) {
        BenHFName = benHFName;
    }
}
