package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class HostalDeatils implements KvmSerializable {

    int id;
    private String DistCode;
    private String SchemeCode;
    private String HostalId;
    private String HostalName;
    private String latitude;
    private String longitude;
    private String entryDate;
    private String entryBy;
    private String DeptId;
    private byte[] photoByte1;
    private byte[] photoByte2;
    private byte[] photoByte3;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHostalId() {
        return HostalId;
    }

    public void setHostalId(String hostalId) {
        HostalId = hostalId;
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

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getSchemeCode() {
        return SchemeCode;
    }

    public void setSchemeCode(String schemeCode) {
        SchemeCode = schemeCode;
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

    public byte[] getPhotoByte3() {
        return photoByte3;
    }

    public void setPhotoByte3(byte[] photoByte3) {
        this.photoByte3 = photoByte3;
    }

    public String getHostalName() {
        return HostalName;
    }

    public void setHostalName(String hostalName) {
        HostalName = hostalName;
    }
}
