package bih.nic.in.chatrawasinspection.webservice;

import android.util.Base64;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.entity.Area_Entity;
import bih.nic.in.chatrawasinspection.entity.Bank_Entity;
import bih.nic.in.chatrawasinspection.entity.BenfiList;
import bih.nic.in.chatrawasinspection.entity.Block_Entity;
import bih.nic.in.chatrawasinspection.entity.Category_Entity;
import bih.nic.in.chatrawasinspection.entity.Deptlist;
import bih.nic.in.chatrawasinspection.entity.DistrictEntity;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.GenderList;
import bih.nic.in.chatrawasinspection.entity.Gender_Entity;
import bih.nic.in.chatrawasinspection.entity.HostelList;
import bih.nic.in.chatrawasinspection.entity.Panchayat_Entity;
import bih.nic.in.chatrawasinspection.entity.Schemelist;
import bih.nic.in.chatrawasinspection.entity.StudentList;
import bih.nic.in.chatrawasinspection.entity.StudentPhoto;
import bih.nic.in.chatrawasinspection.entity.UserDetails;
import bih.nic.in.chatrawasinspection.entity.Versioninfo;
import bih.nic.in.chatrawasinspection.entity.Village_Entity;
import bih.nic.in.chatrawasinspection.entity.Ward_Entity;

public class WebServiceHelper {

    public static final String SERVICENAMESPACE = "http://ejanani.bih.nic.in/";
    public static final String SERVICEURL = "http://ejanani.bih.nic.in/ws_ejananiapp_v2.asmx";

    public static final String APPVERSION_METHOD = "getAppLatest";

    public static final String AUTHENTICATE_METHOD = "Authenticate";
    public static final String Get_FinYear = "Get_FinYear";
    public static final String Get_Area = "Get_Area";
    public static final String Get_Bank = "Get_Bank";
    public static final String Get_Block = "Get_Block";
    public static final String Get_Category = "Get_Category";
    public static final String Get_Gender = "Get_Gender";

    private static final String GetFYearList = "Get_FinYear";
    private static final String GetDistList = "Get_District";
    private static final String GetBLKList = "Get_Block";
    private static final String GetPanchayt_List = "Get_Panchyat";
    private static final String Get_Village_List = "Get_Village";
    private static final String Get_Ward_List = "Get_Ward";
    private static final String GetArea_List = "Get_Area";
    private static final String GetCateg_List = "Get_Category";
    private static final String Get_Gender_List = "Get_Gender";
    private static final String Get_Bank_List = "Get_Bank";

    static String rest;


    public static Versioninfo CheckVersion(String imei, String version) {
        Versioninfo versioninfo;
        SoapObject res1;
        try {

            //res1=getServerData(APPVERSION_METHOD,Versioninfo.Versioninfo_CLASS,"IMEI","Ver",imei,version);
            SoapObject request = new SoapObject(SERVICENAMESPACE, APPVERSION_METHOD);
            request.addProperty("IMEI", imei);
            request.addProperty("Ver", version);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, Versioninfo.Versioninfo_CLASS.getSimpleName(), Versioninfo.Versioninfo_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + APPVERSION_METHOD, envelope);
            res1 = (SoapObject) envelope.getResponse();
            SoapObject final_object = (SoapObject) res1.getProperty(0);

            versioninfo = new Versioninfo(final_object);

        } catch (Exception e) {

            return null;
        }
        return versioninfo;

    }

    public static ArrayList<FinYear_Model> getFYearList() {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE, GetFYearList);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, FinYear_Model.FYEARCLASS.getSimpleName(), FinYear_Model.FYEARCLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetFYearList, envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();

        ArrayList<FinYear_Model> deptlistArrayList = new ArrayList<FinYear_Model>();

        for (int i = 0; i < TotalProperty; i++) {
            if (res1.getProperty(i) != null) {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    FinYear_Model finYear_model = new FinYear_Model(final_object);
                    deptlistArrayList.add(finYear_model);
                }
            } else
                return deptlistArrayList;
        }


        return deptlistArrayList;


    } public static ArrayList<GenderList> GetGenderList() {
        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE, Get_Gender);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, FinYear_Model.FYEARCLASS.getSimpleName(), FinYear_Model.FYEARCLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Get_Gender, envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();

        ArrayList<GenderList> deptlistArrayList = new ArrayList<GenderList>();

        for (int i = 0; i < TotalProperty; i++) {
            if (res1.getProperty(i) != null) {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    GenderList finYear_model = new GenderList(final_object);
                    deptlistArrayList.add(finYear_model);
                }
            } else
                return deptlistArrayList;
        }


        return deptlistArrayList;


    }

  /*  public static ArrayList<Schemelist> getSchemelist(String dEPTID) {

        SoapObject res1;
        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE1, SCHEME_METHOD);
            request.addProperty("_DeptId",dEPTID);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, Schemelist.SCHEMELIST_CLASS.getSimpleName(), Schemelist.SCHEMELIST_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL1);
            androidHttpTransport.call(SERVICENAMESPACE1 + SCHEME_METHOD, envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();

        ArrayList<Schemelist> schemelistArrayList = new ArrayList<Schemelist>();
        //healthFacilityList = null;

        for (int i = 0; i < TotalProperty; i++) {
            if (res1.getProperty(i) != null) {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Schemelist schemelist = new Schemelist(final_object);
                    schemelistArrayList.add(schemelist);
                }
            } else
                return schemelistArrayList;
        }


        return schemelistArrayList;


    }



    public static String sendHostelData(String[] data, String devicename, String Version) {
        SoapObject request = new SoapObject(SERVICENAMESPACE, UPLOAD_HOSTELPHOTO);
        request.addProperty("_DeptId", data[0]);
        request.addProperty("_SchemeId", data[1]);
        request.addProperty("DistCode", data[2]);
        request.addProperty("HostelCode", data[3]);

        request.addProperty("_Latitude", data[4]);
        request.addProperty("_Longitude", data[5]);
        request.addProperty("_HosImage", data[6]);
        request.addProperty("_AppVersion", Version);
        request.addProperty("_DeviceType", devicename);



        try {
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                    SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.implicitTypes = true;
            envelope.setOutputSoapObject(request);
            *//*
             * envelope.addMapping(SERVICENAMESPACE,
             * FormList.RANGE_CLASS5.getSimpleName(), FormList.RANGE_CLASS5);
             *//*
            HttpTransportSE androidHttpTransport = new HttpTransportSE(
                    SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + UPLOAD_HOSTELPHOTO,
                    envelope);
            // res2 = (SoapObject) envelope.getResponse();
            rest = envelope.getResponse().toString();
            // rest=res2.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return rest;
    }





    public static ArrayList<StudentList> get_StudnetList_APP_V1(String DeptCode,String SchemeCode,String DistCode,String BlockCode) {

        try {
            SoapObject request = new SoapObject(SERVICENAMESPACE, GETSTUDENTLIST);
            request.addProperty("_DeptId",DeptCode);
            request.addProperty("_SchemeId",SchemeCode);
            request.addProperty("_DistrictCode",DistCode);
            request.addProperty("_BlockCode",BlockCode);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, StudentList.SCHEMELIST_CLASS.getSimpleName(), StudentList.SCHEMELIST_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GETSTUDENTLIST, envelope);
            res1 = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = 0;
        if (res1 != null) TotalProperty = res1.getPropertyCount();

        ArrayList<StudentList> deptlistArrayList = new ArrayList<>();
        //healthFacilityList = null;

        for (int i = 0; i < TotalProperty; i++) {
            if (res1.getProperty(i) != null) {
                Object property = res1.getProperty(i);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    StudentList deptlist = new StudentList(final_object);
                    deptlistArrayList.add(deptlist);
                }
            } else
                return deptlistArrayList;
        }


        return deptlistArrayList;


    }
*/

    public static UserDetails Login(String User_ID, String Pwd, String DeptId) {
        try {
            SoapObject res1;
            //res1=getServerData(AUTHENTICATE_METHOD, UserDetails.getUserClass(),"UserID","Password",User_ID,Pwd);
            SoapObject request = new SoapObject(SERVICENAMESPACE, AUTHENTICATE_METHOD);
            request.addProperty("Uid", User_ID.toLowerCase());
            request.addProperty("Psw", Pwd);
            //request.addProperty("_DeptId", DeptId);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            envelope.addMapping(SERVICENAMESPACE, UserDetails.USER_CLASS.getSimpleName(), UserDetails.USER_CLASS);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + AUTHENTICATE_METHOD, envelope);
            res1 = (SoapObject) envelope.getResponse();
            if (res1 != null) {
                return new UserDetails(res1);
            } else
                return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static ArrayList<FinYear_Model> getFinancialYear() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetFYearList);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, FinYear_Model.FYEARCLASS.getSimpleName(), FinYear_Model.FYEARCLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetFYearList,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<FinYear_Model> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    FinYear_Model district = new FinYear_Model(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<DistrictEntity> getDistrictList() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetDistList);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, DistrictEntity.DistClass.getSimpleName(), DistrictEntity.DistClass);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetDistList,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<DistrictEntity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    DistrictEntity district = new DistrictEntity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Block_Entity> getBlockList(String distid) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetBLKList);
        request.addProperty("dist", distid);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Block_Entity.BLKCLASS.getSimpleName(), Block_Entity.BLKCLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetBLKList,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Block_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Block_Entity district = new Block_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Area_Entity> getAreaList() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetArea_List);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Area_Entity.Area_CLASS.getSimpleName(), Area_Entity.Area_CLASS);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetArea_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Area_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Area_Entity district = new Area_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }



    public static ArrayList<Category_Entity> getCategList() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetCateg_List);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Category_Entity.Category_Class.getSimpleName(), Category_Entity.Category_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetCateg_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Category_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Category_Entity district = new Category_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Gender_Entity> getgenderList() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Get_Gender_List);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Gender_Entity.Gender_Class.getSimpleName(), Gender_Entity.Gender_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Get_Gender_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Gender_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Gender_Entity district = new Gender_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Bank_Entity> getBankList() {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Get_Bank_List);
        //request.addProperty("APIKey", API_Key);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Bank_Entity.Bank_Class.getSimpleName(), Bank_Entity.Bank_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Get_Bank_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Bank_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Bank_Entity district = new Bank_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }

    public static ArrayList<Panchayat_Entity> getPqanchayatList(String block) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, GetPanchayt_List);
        request.addProperty("Block", block);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Panchayat_Entity.Panchayat_Class.getSimpleName(), Panchayat_Entity.Panchayat_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + GetPanchayt_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Panchayat_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Panchayat_Entity district = new Panchayat_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Village_Entity> getVillageList(String pan_id) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Get_Village_List);
        request.addProperty("Panchyat", pan_id);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Village_Entity.Village_Class.getSimpleName(), Village_Entity.Village_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Get_Village_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Village_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Village_Entity district = new Village_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


    public static ArrayList<Ward_Entity> getWardList(String pan_id) {

        SoapObject request = new SoapObject(SERVICENAMESPACE, Get_Ward_List);
        request.addProperty("Panchyat", pan_id);
        SoapObject res1;
        try {

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            envelope.addMapping(SERVICENAMESPACE, Ward_Entity.Ward_Class.getSimpleName(), Ward_Entity.Ward_Class);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(SERVICEURL);
            androidHttpTransport.call(SERVICENAMESPACE + Get_Ward_List,
                    envelope);

            res1 = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        int TotalProperty = res1.getPropertyCount();

        ArrayList<Ward_Entity> pvmArrayList = new ArrayList<>();

        for (int ii = 0; ii < TotalProperty; ii++) {
            if (res1.getProperty(ii) != null) {
                Object property = res1.getProperty(ii);
                if (property instanceof SoapObject) {
                    SoapObject final_object = (SoapObject) property;
                    Ward_Entity district = new Ward_Entity(final_object);
                    pvmArrayList.add(district);
                }
            } else
                return pvmArrayList;
        }


        return pvmArrayList;
    }


}
