package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by Nicsi on 5/18/2018.
 */

public class EntryDetailDistAdm implements KvmSerializable
{
    public static Class<EntryDetailDistAdm> EntryDatadetailDistAdm = EntryDetailDistAdm.class;

    int id;
    private String DeptId;
    private String DistCode;
    private String HostCode;
    private String PresentStd;
    private String MeshStatus;
    private String StoreRoomStatus;
    private String ChildGrivance;
    private String EntryDate;
    private String HostelName;
    private String EntryBy;




    @Override
    public Object getProperty(int index) {
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 0;
    }

    @Override
    public void setProperty(int index, Object value) {

    }

    @Override
    public void getPropertyInfo(int index, Hashtable properties, PropertyInfo info) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getHostCode() {
        return HostCode;
    }

    public void setHostCode(String hostCode) {
        HostCode = hostCode;
    }

    public String getPresentStd() {
        return PresentStd;
    }

    public void setPresentStd(String presentStd) {
        PresentStd = presentStd;
    }

    public String getMeshStatus() {
        return MeshStatus;
    }

    public void setMeshStatus(String meshStatus) {
        MeshStatus = meshStatus;
    }

    public String getStoreRoomStatus() {
        return StoreRoomStatus;
    }

    public void setStoreRoomStatus(String storeRoomStatus) {
        StoreRoomStatus = storeRoomStatus;
    }

    public String getChildGrivance() {
        return ChildGrivance;
    }

    public void setChildGrivance(String childGrivance) {
        ChildGrivance = childGrivance;
    }

    public String getEntryDate() {
        return EntryDate;
    }

    public void setEntryDate(String entryDate) {
        EntryDate = entryDate;
    }

    public String getHostelName() {
        return HostelName;
    }

    public void setHostelName(String hostelName) {
        HostelName = hostelName;
    }

    public String getEntryBy() {
        return EntryBy;
    }

    public void setEntryBy(String entryBy) {
        EntryBy = entryBy;
    }
}
