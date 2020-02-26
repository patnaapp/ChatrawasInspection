package bih.nic.in.chatrawasinspection.entity;

import android.util.Log;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

public class Schemelist implements KvmSerializable {
    public static Class<Schemelist> SCHEMELIST_CLASS = Schemelist.class;

    private String DeptId = "";
    private String DeptName="";
    private String SchemeId = "";
    private String SchemeName = "";
    private String SchemeNameHN = "";


    public Schemelist() {
    }


    @SuppressWarnings("deprecation")
    public Schemelist(SoapObject object){

        this.DeptId=object.getProperty("DeptId").toString();
        this.SchemeId=object.getProperty("SchemeId").toString();
        this.SchemeName=object.getProperty("SchemeName").toString();
        this.SchemeNameHN=object.getProperty("SchemeNameHN").toString();
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

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getSchemeId() {
        return SchemeId;
    }

    public void setSchemeId(String schemeId) {
        SchemeId = schemeId;
    }

    public String getSchemeName() {
        return SchemeName;
    }

    public void setSchemeName(String schemeName) {
        SchemeName = schemeName;
    }

    public String getSchemeNameHN() {
        return SchemeNameHN;
    }

    public void setSchemeNameHN(String schemeNameHN) {
        SchemeNameHN = schemeNameHN;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }
}
