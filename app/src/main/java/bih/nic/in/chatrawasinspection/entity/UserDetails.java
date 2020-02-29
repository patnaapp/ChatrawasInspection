package bih.nic.in.chatrawasinspection.entity;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.Hashtable;


public class UserDetails implements KvmSerializable {
    public static Class<UserDetails> USER_CLASS = UserDetails.class;
    private boolean isAuthenticated = false;
    private String UserRole = "";
    private String RoleDescription = "";
    private String DeptId = "";
    private String DistrictCode = "";
    private String DistrictName = "";
    private String BlockCode = "";
    private String PanchayatCode = "";
    private String GrpTypeID = "";
    private String UserID = "";
    private String Password = "";
    private String BlockName = "";
    private String UserName = "";
    private String schemeId="";
    private String schemeName="";


    private String DeptCode="";
    private String DeptName="";
    private String HospitalCode="";
    private String Hospital="";
    private String HopitalType="";



    public UserDetails() {
    }

    @SuppressWarnings("deprecation")
    public UserDetails(SoapObject obj) {
        this.setAuthenticated(Boolean.parseBoolean(obj.getProperty("isAuthenticated").toString()));
        this.setUserID(obj.getProperty("UserID").toString());
        this.setUserName(obj.getProperty("UserName").toString());
        this.setUserRole(obj.getProperty("UserRole").toString());
        this.setDeptId(obj.getProperty("DeptId").toString());
        this.setDeptCode(obj.getProperty("DeptCode").toString());
        this.setDeptName(obj.getProperty("DeptName").toString());
        this.setDistrictCode(obj.getProperty("DistrictCode").toString());
        this.setDistrictName(obj.getProperty("DistrictName").toString());
        this.setHospitalCode(obj.getProperty("HospitalCode").toString());
        this.setPanchayatCode(obj.getProperty("PanchayatCode").toString());
        this.setHospital(obj.getProperty("Hospital").toString());
        this.setHopitalType(obj.getProperty("HopitalType").toString());

//        this.setRoleDescription(obj.getProperty("RoleDescription").toString());
//
//
//        this.setBlockCode(obj.getProperty("BlockCode").toString());
//        this.setBlockName(obj.getProperty("BlockName").toString());
//
//        this.setGrpTypeID(obj.getProperty("GrpTypeID").toString());
//
//        this.setPassword(obj.getProperty("Password").toString());


    }


    public static Class<UserDetails> getUserClass() {
        return USER_CLASS;
    }

    public static void setUserClass(Class<UserDetails> userClass) {
        USER_CLASS = userClass;
    }


    @Override
    public int getPropertyCount() {
        // TODO Auto-generated method stub
        return 13;
    }

    @Override
    public Object getProperty(int index) {
        Object object = null;
        switch (index) {
            case 0: {
                object = this.isAuthenticated;
                break;
            }
            case 1: {
                object = this.getUserRole();
                break;
            }
            case 2: {
                object = this.RoleDescription;
                break;
            }
            case 3: {
                object = this.DeptId;
                break;
            }
            case 4: {
                object = this.DistrictCode;
                break;
            }

            case 5: {
                object = this.DistrictName;
                break;
            }
            case 6: {
                object = this.BlockCode;
                break;
            }

            case 7: {
                object = this.PanchayatCode;
                break;

            }

            case 8: {
                object = this.GrpTypeID;
                break;
            }

            case 9: {
                object = this.UserID;
                break;
            }

            case 10: {
                object = this.Password;
                break;
            }
            case 11: {
                object = this.BlockName;
                break;
            }
            case 12: {
                object = this.UserName;
                break;
            }

        }
        return object;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable arg1,
                                PropertyInfo propertyInfo) {
        switch (index) {
            case 0: {
                propertyInfo.name = "isAuthenticated";
                propertyInfo.type = PropertyInfo.BOOLEAN_CLASS;
                break;
            }
            case 1: {
                propertyInfo.name = "UserRole";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 2: {
                propertyInfo.name = "RoleDescription";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 3: {
                propertyInfo.name = "DeptId";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 4: {
                propertyInfo.name = "DistrictCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 5: {
                propertyInfo.name = "DistrictName";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 6: {
                propertyInfo.name = "BlockCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 7: {
                propertyInfo.name = "PanchayatCode";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 8: {
                propertyInfo.name = "GrpTypeID";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 9: {
                propertyInfo.name = "UserID";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 10: {

                propertyInfo.name = "Password";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }
            case 11: {

                propertyInfo.name = "BlockName";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

            case 12: {

                propertyInfo.name = "UserName";
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                break;
            }

        }
    }

    @Override
    public void setProperty(int index, Object obj) {
        switch (index) {
            case 0: {
                this.isAuthenticated = Boolean.parseBoolean(obj.toString());
                break;
            }
            case 1: {
                this.UserRole = obj.toString();
                break;
            }
            case 2: {
                this.RoleDescription = obj.toString();
                break;
            }
            case 3: {
                this.DeptId = obj.toString();
                break;
            }
            case 4: {
                this.DistrictCode = obj.toString();
                break;
            }
            case 5: {
                this.DistrictName = obj.toString();
                break;
            }

            case 6: {
                this.BlockCode = obj.toString();
                break;
            }

            case 7: {
                this.PanchayatCode = obj.toString();
                break;
            }
            case 8: {
                this.GrpTypeID = obj.toString();
                break;
            }
            case 9: {
                this.UserID = obj.toString();
                break;
            }
            case 10: {
                this.Password = obj.toString();
                break;
            }

            case 11: {
                this.BlockName = obj.toString();
                break;
            }
            case 12: {
                this.UserName = obj.toString();
                break;
            }
        }
    }


    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }

    public String getRoleDescription() {
        return RoleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        RoleDescription = roleDescription;
    }

    public String getDeptId() {
        return DeptId;
    }

    public void setDeptId(String deptId) {
        DeptId = deptId;
    }

    public String getDistrictCode() {
        return DistrictCode;
    }

    public void setDistrictCode(String districtCode) {
        DistrictCode = districtCode;
    }

    public String getPanchayatCode() {
        return PanchayatCode;
    }

    public void setPanchayatCode(String panchayatCode) {
        PanchayatCode = panchayatCode;
    }

    public String getGrpTypeID() {
        return GrpTypeID;
    }

    public void setGrpTypeID(String grpTypeID) {
        GrpTypeID = grpTypeID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBlockCode() {
        return BlockCode;
    }

    public void setBlockCode(String blockCode) {
        BlockCode = blockCode;
    }

    public String getDistrictName() {
        return DistrictName;
    }

    public void setDistrictName(String districtName) {
        DistrictName = districtName;
    }

    public String getBlockName() {
        return BlockName;
    }

    public void setBlockName(String blockName) {
        BlockName = blockName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getDeptCode() {
        return DeptCode;
    }

    public void setDeptCode(String deptCode) {
        DeptCode = deptCode;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String deptName) {
        DeptName = deptName;
    }

    public String getHospitalCode() {
        return HospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        HospitalCode = hospitalCode;
    }

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    public String getHopitalType() {
        return HopitalType;
    }

    public void setHopitalType(String hopitalType) {
        HopitalType = hopitalType;
    }


}
