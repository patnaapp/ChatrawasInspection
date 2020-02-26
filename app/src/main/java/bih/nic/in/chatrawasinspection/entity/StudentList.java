package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class StudentList implements KvmSerializable {
    public static Class<StudentList> SCHEMELIST_CLASS = StudentList.class;


    private String BeneficiaryId = "";
    private String SchemeId = "";
    private String BlockName = "";

    private String Ben_Name = "";
    private String Ben_NameHN = "";
    private String BenFH_Name = "";
    private String BenFH_NameHn = "";
    private String BlockCode = "";

    public StudentList() {
    }


    @SuppressWarnings("deprecation")
    public StudentList(SoapObject object){

        this.BeneficiaryId=object.getProperty("BeneficiaryId").toString();
        this.SchemeId=object.getProperty("SchemeId").toString();
        this.BlockName=object.getProperty("BlockName").toString();
        this.Ben_Name=object.getProperty("Ben_Name").toString();
        this.Ben_NameHN=object.getProperty("Ben_NameHN").toString();
        this.BenFH_Name=object.getProperty("BenFH_Name").toString();
        this.BenFH_NameHn=object.getProperty("BenFH_NameHN").toString();
        this.BlockCode=object.getProperty("BlockCode").toString();
        //Log.e("DEPTID","x"+object.getProperty("Dept_Id").toString());
       // Log.e("Dept_RefNameHN","X"+object.getProperty("Dept_RefNameHN").toString());

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

    public String getBeneficiaryId() {
        return BeneficiaryId;
    }

    public void setBeneficiaryId(String beneficiaryId) {
        BeneficiaryId = beneficiaryId;
    }

    public String getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(String schemeId) {
        SchemeId = schemeId;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
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

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getBenFH_NameHn() {
        return BenFH_NameHn;
    }

    public void setBenFH_NameHn(String benFH_NameHn) {
        BenFH_NameHn = benFH_NameHn;
    }
}
