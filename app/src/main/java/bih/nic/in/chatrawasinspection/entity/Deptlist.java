package bih.nic.in.chatrawasinspection.entity;

import android.util.Log;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;

/**
 * Created by nicsi on 2/8/2018.
 */

public class Deptlist implements KvmSerializable {
    public static Class<Deptlist> DEPTLIST_CLASS = Deptlist.class;

    private String Dept_Id = "";
    private String Dept_RefNameHN = "";
    private String schemeId = "";
    private String Scheme_RefNameHN = "";



    public Deptlist() {
    }


    @SuppressWarnings("deprecation")
    public Deptlist(SoapObject object){

        this.Dept_Id=object.getProperty("Dept_Id").toString();
        this.Dept_RefNameHN=object.getProperty("Dept_RefNameHN").toString();
        this.Scheme_RefNameHN=object.getProperty("Dept_NameHN").toString();
        Log.e("DEPTID","x"+object.getProperty("Dept_Id").toString());
        Log.e("Dept_RefNameHN","X"+object.getProperty("Dept_RefNameHN").toString());

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

    public String getDept_Id() {
        return Dept_Id;
    }

    public void setDept_Id(String dept_Id) {
        Dept_Id = dept_Id;
    }

    public String getDept_RefNameHN() {
        return Dept_RefNameHN;
    }

    public void setDept_RefNameHN(String dept_RefNameHN) {
        Dept_RefNameHN = dept_RefNameHN;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getScheme_RefNameHN() {
        return Scheme_RefNameHN;
    }

    public void setScheme_RefNameHN(String scheme_RefNameHN) {
        Scheme_RefNameHN = scheme_RefNameHN;
    }
}
