package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class HostelList implements KvmSerializable {
    public static Class<HostelList> HOSTEL_CLASS = HostelList.class;



    private String BlockCode= "";
    private String DistCode = "";
    private String BlockNameHN = "";

    private String DistNameHN = "";
    private String HostalNameHN = "";
    private String HostalName = "";
    private String Deptid = "";
    private String HostelCode = "";


    public HostelList() {
    }


    @SuppressWarnings("deprecation")
    public HostelList(SoapObject object){

        this.BlockCode=object.getProperty("BlockCode").toString();
        this.DistCode=object.getProperty("DistCode").toString();
        this.HostalName=object.getProperty("HostalName").toString();
        this.HostelCode=object.getProperty("HostelCode").toString();

        this.DistNameHN=object.getProperty("DistNameHN").toString();
        this.HostalNameHN=object.getProperty("HostalNameHN").toString();


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

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getDistCode() {
        return DistCode;
    }

    public void setDistCode(String distCode) {
        DistCode = distCode;
    }

    public String getBlockNameHN() {
        return BlockNameHN;
    }

    public void setBlockNameHN(String blockNameHN) {
        BlockNameHN = blockNameHN;
    }

    public String getDistNameHN() {
        return DistNameHN;
    }

    public void setDistNameHN(String distNameHN) {
        DistNameHN = distNameHN;
    }

    public String getHostalNameHN() {
        return HostalNameHN;
    }

    public void setHostalNameHN(String hostalNameHN) {
        HostalNameHN = hostalNameHN;
    }

    public String getHostalName() {
        return HostalName;
    }

    public void setHostalName(String hostalName) {
        HostalName = hostalName;
    }

    public String getDeptid() {
        return Deptid;
    }

    public void setDeptid(String deptid) {
        Deptid = deptid;
    }

    public String getHostelCode() {
        return HostelCode;
    }

    public void setHostelCode(String hostelCode) {
        HostelCode = hostelCode;
    }
}
