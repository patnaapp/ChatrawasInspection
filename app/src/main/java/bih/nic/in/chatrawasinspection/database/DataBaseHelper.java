package bih.nic.in.chatrawasinspection.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.entity.Area_Entity;
import bih.nic.in.chatrawasinspection.entity.Bank_Entity;
import bih.nic.in.chatrawasinspection.entity.Block_Entity;
import bih.nic.in.chatrawasinspection.entity.Category_Entity;
import bih.nic.in.chatrawasinspection.entity.Deptlist;
import bih.nic.in.chatrawasinspection.entity.DistrictEntity;
import bih.nic.in.chatrawasinspection.entity.EjananiEntryDetail;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.Gender_Entity;
import bih.nic.in.chatrawasinspection.entity.Panchayat_Entity;
import bih.nic.in.chatrawasinspection.entity.Schemelist;
import bih.nic.in.chatrawasinspection.entity.UserDetails;
import bih.nic.in.chatrawasinspection.entity.Village_Entity;
import bih.nic.in.chatrawasinspection.entity.Ward_Entity;
import bih.nic.in.chatrawasinspection.entity.Yojna_Entity;

public class DataBaseHelper extends SQLiteOpenHelper {
    // The Android's default system path of your application database.
    private static String DB_PATH = "";// "/data/data/com.bih.nic.app.biharmunicipalcorporation/databases/";
    //private static String DB_NAME = "chatrawasInsp.db";
    private static String DB_NAME = "PACSDB1";

    // private static String DB_NAME = "PACSDB1";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 3);
        Log.e("DataBaseHelper","1");
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {

            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
            //CreateNewTables(db);

        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            //this.getReadableDatabase();

            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.NO_LOCALIZED_COLLATORS
                            | SQLiteDatabase.OPEN_READWRITE);


        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;

    }

    public boolean databaseExist() {


        File dbFile = new File(DB_PATH + DB_NAME);

        return dbFile.exists();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);
        this.getReadableDatabase().close();

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }

    public void openDataBase() throws SQLException {

        // Open the database
        this.getReadableDatabase();
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public long setSchemeLocal(ArrayList<Deptlist> list) {
        long c = -1;
        ArrayList<Deptlist> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Dept_Id", info.get(i).getDept_Id());
                    values.put("Dept_Name", info.get(i).getDept_RefNameHN());
                    values.put("Scheme_NameHn", info.get(i).getScheme_RefNameHN());

                    c = db.insert("Scheme", null, values);

                    if(c>0)
                    {
                        Log.e("Data","Inserted");
                    }
                    else
                    {
                        Log.e("Data","Not Inserted");
                    }
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }
    public long setFYearLocal(ArrayList<FinYear_Model> list) {
        long c = -1;
        ArrayList<FinYear_Model> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("FyearId", info.get(i).getFYearCode());
                    values.put("FyearName", info.get(i).getFYearName());


                    c = db.insert("FyearList", null, values);

                    if(c>0)
                    {
                        Log.e("Data","Inserted");
                    }
                    else
                    {
                        Log.e("Data","Not Inserted");
                    }
                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }


    public ArrayList<Deptlist> getDept() {


        ArrayList<Deptlist> typeList = new ArrayList<Deptlist>();

        try {
//CREATE TABLE `Gender` ( `GenderID` TEXT, `GenderName` TEXT )
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db
                    .rawQuery(
                            "SELECT Dept_Id,Dept_Name from Dept",
                            null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                Deptlist gen = new Deptlist();
                gen.setDept_Id(cur.getString(cur
                        .getColumnIndex("Dept_Id")));
                gen.setDept_RefNameHN(cur.getString(cur
                        .getColumnIndex("Dept_Name")));

                typeList.add(gen);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return typeList;
    }

    public UserDetails getUserDetails(String userId, String pass) {

        UserDetails userInfo = null;

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{userId.trim(), pass};

            Cursor cur = db.rawQuery(
                    "Select * from UserDetail WHERE UserID = '" + params[0] + "' and Password = '" + params[1] + "'", null);

            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {

                userInfo = new UserDetails();
                userInfo.setUserRole(cur.getString(cur.getColumnIndex("UserRole")));
                userInfo.setRoleDescription(cur.getString(cur.getColumnIndex("RoleDescription")));
                userInfo.setDeptId(cur.getString(cur.getColumnIndex("DeptId")));
                userInfo.setDistrictCode(cur.getString(cur.getColumnIndex("DistrictCode")));
                userInfo.setDistrictName(cur.getString(cur.getColumnIndex("DistrictName")));
                userInfo.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                userInfo.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                userInfo.setGrpTypeID(cur.getString(cur.getColumnIndex("GrpTypeID")));
                userInfo.setUserID(cur.getString(cur.getColumnIndex("UserID")));
                userInfo.setPassword(cur.getString(cur.getColumnIndex("Password")));
                userInfo.setUserName(cur.getString(cur.getColumnIndex("UserName")));
                userInfo.setSchemeId(cur.getString(cur.getColumnIndex("SchemeId")));

            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            userInfo = null;
        }
        return userInfo;
    }

    public long insertUserDetails(UserDetails result) {


        long c = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            ContentValues values = new ContentValues();

            values.put("UserRole", result.getUserRole());
            values.put("RoleDescription", result.getRoleDescription());
            values.put("DeptId", result.getDeptId());
            values.put("DistrictCode", result.getDistrictCode());
            values.put("DistrictName", result.getDistrictName());
            values.put("BlockCode", result.getBlockCode());
            values.put("BlockName", result.getBlockName());
            values.put("PanchayatCode", result.getPanchayatCode());
            values.put("GrpTypeID", result.getGrpTypeID());
            values.put("UserID", result.getUserID());
            values.put("Password", result.getPassword());
            values.put("SchemeId",result.getSchemeId());

            String[] whereArgs = new String[]{result.getUserID()};

            c = db.update("UserDetail", values, "UserID=? ", whereArgs);

            if (!(c > 0)) {

                c = db.insert("UserDetail", null, values);
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
        return c;

    }

    public long getUserCount() {

        long x = 0;
        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("Select * from UserDetail", null);

            x = cur.getCount();

            cur.close();
            db.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return x;
    }

    public void  insertSchemeData(ArrayList<Schemelist> result) {

        long c = -1;
        try {
            // CREATE TABLE "BankList" ( `BankId` TEXT, `BankName` TEXT, `BankType` TEXT )

            SQLiteDatabase db = this.getWritableDatabase();
            // db.execSQL("Delete from RANGE");
            for (Schemelist schemelist : result) {

                ContentValues values = new ContentValues();
                values.put("Dept_Id", schemelist.getDeptId());
                values.put("Scheme_Id", schemelist.getSchemeId());
                values.put("Scheme_Name", schemelist.getSchemeName());
                values.put("Scheme_NameHn", schemelist.getSchemeNameHN());


                String[] whereArgs = new String[]{schemelist.getSchemeId()};
                c = db.update("Scheme1", values, "Scheme_Id=?", whereArgs);
                if (!(c > 0)) {

                    c = db.insert("Scheme1", null, values);
                }

            }
            db.close();


        } catch (Exception e) {
            // TODO: handle exception
        }
        // return plantationList;
    }
    public ArrayList<Schemelist> getScheme() {


        ArrayList<Schemelist> typeList = new ArrayList<Schemelist>();

        try {
//CREATE TABLE `Gender` ( `GenderID` TEXT, `GenderName` TEXT )
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cur = db
                    .rawQuery(
                            "SELECT Scheme_Id,Scheme_NameHn from Scheme", null);
            int x = cur.getCount();

            while (cur.moveToNext()) {

                Schemelist gen = new Schemelist();
                gen.setSchemeId(cur.getString(cur
                        .getColumnIndex("Scheme_Id")));
                gen.setSchemeNameHN(cur.getString(cur
                        .getColumnIndex("Scheme_NameHn")));
                gen.setDeptId(cur.getString(cur
                        .getColumnIndex("Dept_Id")));

                typeList.add(gen);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception

        }
        return typeList;
    }



    public Deptlist getDeptList() {
        Deptlist progress = new Deptlist();
        try {
            Cursor cur=null;
            SQLiteDatabase db = this.getReadableDatabase();

            cur = db.rawQuery(
                    "Select * from Dept", null);

            if (cur.moveToNext()) {
                progress = setProgressData(cur);
            }
            cur.close();
            db.close();

        }


        catch (Exception e) {
            // TODO: handle exception

        }

        return progress;

    }
    public Deptlist setProgressData(Cursor cur) {
        Deptlist progress = new Deptlist();
        progress.setDept_Id(cur.getString(cur.getColumnIndex("Dept_Id")).trim());
        progress.setDept_RefNameHN(cur.getString(cur.getColumnIndex("Dept_Name")).trim());
        return progress;
    }


    public ArrayList<FinYear_Model> getFinancialYearLocal() {
        ArrayList<FinYear_Model> bdetail = new ArrayList<FinYear_Model>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from FyearList order by FyearId", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                FinYear_Model financial_year = new FinYear_Model();
                financial_year.setFYearCode(cur.getString(cur.getColumnIndex("FyearId")));
                financial_year.setFYearName((cur.getString(cur.getColumnIndex("FyearName"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }
    public ArrayList<Yojna_Entity> getYojnaLocal() {
        ArrayList<Yojna_Entity> bdetail = new ArrayList<Yojna_Entity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Yojna order by YojnaCode", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Yojna_Entity financial_year = new Yojna_Entity();
                financial_year.setYojna_Code(cur.getString(cur.getColumnIndex("YojnaCode")));
                financial_year.setYojna_Name((cur.getString(cur.getColumnIndex("YojnaName"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public ArrayList<DistrictEntity> getDistrictLocal() {
        ArrayList<DistrictEntity> bdetail = new ArrayList<DistrictEntity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Districts order by DistCode", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                DistrictEntity financial_year = new DistrictEntity();
                financial_year.setDistCode(cur.getString(cur.getColumnIndex("DistCode")));
                financial_year.setDistName((cur.getString(cur.getColumnIndex("DistName"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }
    public ArrayList<Area_Entity> getAreaLocal() {
        ArrayList<Area_Entity> bdetail = new ArrayList<Area_Entity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Area order by Area_ID", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Area_Entity financial_year = new Area_Entity();
                financial_year.setAreaCode(cur.getString(cur.getColumnIndex("Area_ID")));
                financial_year.setAreaName((cur.getString(cur.getColumnIndex("Area_Name"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public ArrayList<Category_Entity> getCategoryLocal() {
        ArrayList<Category_Entity> bdetail = new ArrayList<Category_Entity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Category order by Cat_Id", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Category_Entity financial_year = new Category_Entity();
                financial_year.setCat_Id(cur.getString(cur.getColumnIndex("Cat_Id")));
                financial_year.setCat_Nm((cur.getString(cur.getColumnIndex("Cat_Nm"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public ArrayList<Gender_Entity> getGenderLocal() {
        ArrayList<Gender_Entity> bdetail = new ArrayList<Gender_Entity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Gender order by Gender_Id", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Gender_Entity financial_year = new Gender_Entity();
                financial_year.setGender_Id(cur.getString(cur.getColumnIndex("Gender_Id")));
                financial_year.setGender_Nm((cur.getString(cur.getColumnIndex("Gender_Nm"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }
    public ArrayList<Bank_Entity> getBankLocal() {
        ArrayList<Bank_Entity> bdetail = new ArrayList<Bank_Entity>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from BankList order by Bank_Code", null);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Bank_Entity financial_year = new Bank_Entity();
                financial_year.setBank_Nm(cur.getString(cur.getColumnIndex("Bank_Name")));
                financial_year.setBank_Code((cur.getString(cur.getColumnIndex("Bank_Code"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public long setFinancialYear(ArrayList<FinYear_Model> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<FinYear_Model> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("FyearList",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("FyearId", info.get(i).getFYearCode());
                    values.put("FyearName", info.get(i).getFYearName());


                    String[] whereArgs = new String[]{info.get(i).getFYearCode()};

                    c = db.update("FyearList", values, "FyearId=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("FyearList", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }
    public long setYojna(ArrayList<Yojna_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Yojna_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Yojna",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("YojnaCode", info.get(i).getYojna_Code());
                    values.put("YojnaName", info.get(i).getYojna_Name());


                    String[] whereArgs = new String[]{info.get(i).getYojna_Code()};

                    c = db.update("Yojna", values, "YojnaCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Yojna", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }


    public long setDistList(ArrayList<DistrictEntity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<DistrictEntity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Districts",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("DistCode", info.get(i).getDistCode());
                    values.put("DistName", info.get(i).getDistName());


                    String[] whereArgs = new String[]{info.get(i).getDistCode()};

                    c = db.update("Districts", values, "DistCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Districts", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }


    public ArrayList<Block_Entity> getBlockLocal(String distcode) {
        ArrayList<Block_Entity> bdetail = new ArrayList<Block_Entity>();

        String[] params = new String[] { distcode };
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Blocks where DistCode=?", params);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Block_Entity financial_year = new Block_Entity();
                financial_year.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                financial_year.setBlockName((cur.getString(cur.getColumnIndex("BlockName"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }


    public ArrayList<Panchayat_Entity> getPanchayatLocal(String block,String areatype) {
        ArrayList<Panchayat_Entity> bdetail = new ArrayList<Panchayat_Entity>();

        String[] params = new String[] {block,areatype };
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Panchayat_New where BlockCode=? and areaType=?", params);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Panchayat_Entity financial_year = new Panchayat_Entity();
                financial_year.setPan_code(cur.getString(cur.getColumnIndex("Panchayat_Code")));
                financial_year.setPan_Name((cur.getString(cur.getColumnIndex("Panchayat_Nm"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public ArrayList<Village_Entity> getVillageLocal(String pan_id) {
        ArrayList<Village_Entity> bdetail = new ArrayList<Village_Entity>();

        String[] params = new String[] {pan_id};
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Village where Panchayat_id=?", params);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Village_Entity financial_year = new Village_Entity();
                financial_year.setVill_code(cur.getString(cur.getColumnIndex("Village_id")));
                financial_year.setVill_Name((cur.getString(cur.getColumnIndex("Village_Nm"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }
    public ArrayList<Ward_Entity> getWardLocal(String blkid,String panid,String areatype) {
        ArrayList<Ward_Entity> bdetail = new ArrayList<Ward_Entity>();

        String[] params = new String[] {blkid,panid,areatype};
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cur = db.rawQuery("select * from Ward_NEW where Block_Code=? And Pan_Code=? And Area_Ttype=?", params);
            int x = cur.getCount();
            while (cur.moveToNext()) {
                Ward_Entity financial_year = new Ward_Entity();
                financial_year.setWARDCODE(cur.getString(cur.getColumnIndex("Ward_Code")));
                financial_year.setWARDNAME((cur.getString(cur.getColumnIndex("Ward_Nm"))));
                bdetail.add(financial_year);
            }
            cur.close();
            db.close();
        } catch (Exception e) {
        }
        return bdetail;
    }

    public long setBlockList(ArrayList<Block_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Block_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Blocks",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("BlockCode", info.get(i).getBlockCode());
                    values.put("BlockName", info.get(i).getBlockName());


                    String[] whereArgs = new String[]{info.get(i).getBlockCode()};

                    c = db.update("Blocks", values, "BlockCode=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Blocks", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setPanchayatList(ArrayList<Panchayat_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Panchayat_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Panchayat_New",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Panchayat_Code", info.get(i).getPan_code());
                    values.put("Panchayat_Nm", info.get(i).getPan_Name());
                    values.put("BlockCode", info.get(i).getBlock_code());
                    values.put("areaType", info.get(i).getArea_type());


                    String[] whereArgs = new String[]{info.get(i).getPan_code()};

                    c = db.update("Panchayat_New", values, "Panchayat_Code=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Panchayat_New", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }


    public long setVillageList(ArrayList<Village_Entity> list,String pan_Id) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Village_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Village",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Village_id", info.get(i).getVill_code());
                    values.put("Village_Nm", info.get(i).getVill_Name());
                    values.put("Panchayat_id", pan_Id);



                    String[] whereArgs = new String[]{info.get(i).getVill_code()};

                    c = db.update("Village", values, "Village_id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Village", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setWardList(ArrayList<Ward_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Ward_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Ward_NEW",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Ward_Code", info.get(i).getWARDCODE());
                    values.put("Ward_Nm", info.get(i).getWARDNAME());
                    values.put("Pan_Code", info.get(i).getPanchayatCode());
                    values.put("Area_Ttype", info.get(i).getAreaCode());
                    values.put("Block_Code", info.get(i).getBlockCode());



                    String[] whereArgs = new String[]{info.get(i).getWARDCODE()};

                    c = db.update("Ward_NEW", values, "Ward_Code=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Ward_NEW", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setAreaList(ArrayList<Area_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Area_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Area",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Area_ID", info.get(i).getAreaCode());
                    values.put("Area_Name", info.get(i).getAreaName());


                    String[] whereArgs = new String[]{info.get(i).getAreaCode()};

                    c = db.update("Area", values, "Area_ID=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Area", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }


    public long setCategoryList(ArrayList<Category_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Category_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Category",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Cat_Id", info.get(i).getCat_Id());
                    values.put("Cat_Nm", info.get(i).getCat_Nm());


                    String[] whereArgs = new String[]{info.get(i).getCat_Id()};

                    c = db.update("Category", values, "Cat_Id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Category", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setGenderList(ArrayList<Gender_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Gender_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("Gender",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Gender_Id", info.get(i).getGender_Id());
                    values.put("Gender_Nm", info.get(i).getGender_Nm());


                    String[] whereArgs = new String[]{info.get(i).getGender_Id()};

                    c = db.update("Gender", values, "Gender_Id=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("Gender", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long setBankList(ArrayList<Bank_Entity> list) {


        long c = -1;


        DataBaseHelper dh = new DataBaseHelper(myContext);

        try {
            dh.createDataBase();


        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return -1;
        }

        ArrayList<Bank_Entity> info = list;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        db.delete("BankList",null,null);
        if (info != null) {
            try {
                for (int i = 0; i < info.size(); i++) {

                    values.put("Bank_Code", info.get(i).getBank_Code());
                    values.put("Bank_Name", info.get(i).getBank_Nm());


                    String[] whereArgs = new String[]{info.get(i).getBank_Code()};

                    c = db.update("BankList", values, "Bank_Code=?", whereArgs);
                    if (!(c > 0)) {

                        c = db.insert("BankList", null, values);
                    }

                    //c = db.insert("Financial_Year", null, values);


                }
                db.close();


            } catch (Exception e) {
                e.printStackTrace();
                return c;
            }
        }
        return c;


    }

    public long deleteEjananiEntryData(String id){

        long c = -1;

        try {

            SQLiteDatabase db = this.getWritableDatabase();
            String[] DeleteWhere = {String.valueOf(id)};
            c = db.delete("EjananiNewEntry", "id=?", DeleteWhere);

            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            return c;
        }
        return c;
    }


    public ArrayList<EjananiEntryDetail> getEjananiEntryData(){
        //PondInspectionDetail info = null;

        ArrayList<EjananiEntryDetail> infoList = new ArrayList<EjananiEntryDetail>();

        try {

            SQLiteDatabase db = this.getReadableDatabase();

            String[] params = new String[]{"1"};

            Cursor cur = db.rawQuery(
                    "Select * from EjananiNewEntry", null);
            int x = cur.getCount();
            // db1.execSQL("Delete from UserDetail");

            while (cur.moveToNext()) {


                EjananiEntryDetail info = new EjananiEntryDetail();

                info.setId(cur.getInt(cur.getColumnIndex("id")));
                info.setFinancialYearId(cur.getString(cur.getColumnIndex("FinancialYearId")));
                info.setFinancialYearName(cur.getString(cur.getColumnIndex("FinancialYearName")));
                info.setYojnaId(cur.getString(cur.getColumnIndex("YojnaId")));
                info.setYojnaName(cur.getString(cur.getColumnIndex("YojnaName")));
                info.setDistrictCode(cur.getString(cur.getColumnIndex("DistrictCode")));
                info.setDistrictName(cur.getString(cur.getColumnIndex("DistrictName")));
                info.setBlockCode(cur.getString(cur.getColumnIndex("BlockCode")));
                info.setBlockName(cur.getString(cur.getColumnIndex("BlockName")));
                info.setAreaTypeId(cur.getString(cur.getColumnIndex("AreaTypeId")));
                info.setAreaTypeName(cur.getString(cur.getColumnIndex("AreaTypeName")));
                info.setPanchayatCode(cur.getString(cur.getColumnIndex("PanchayatCode")));
                info.setPanchayatName(cur.getString(cur.getColumnIndex("PanchayatName")));
                info.setVillageCode(cur.getString(cur.getColumnIndex("VillageCode")));
                info.setVillageName(cur.getString(cur.getColumnIndex("VillageName")));
                info.setWardCode(cur.getString(cur.getColumnIndex("WardCode")));
                info.setWardName(cur.getString(cur.getColumnIndex("WardName")));
                info.setBabyNameEng(cur.getString(cur.getColumnIndex("BabyNameEng")));
                info.setBabyNameHindi(cur.getString(cur.getColumnIndex("BabyNameHindi")));
                info.setFatherNameEng(cur.getString(cur.getColumnIndex("FatherNameEng")));
                info.setFatherNameHindi(cur.getString(cur.getColumnIndex("FatherNameHindi")));
                info.setMotherNameEng(cur.getString(cur.getColumnIndex("MotherNameEng")));
                info.setMotherNameHindi(cur.getString(cur.getColumnIndex("MotherNameHindi")));
                info.setBabyGenderId(cur.getString(cur.getColumnIndex("BabyGenderId")));
                info.setBabyGenderName(cur.getString(cur.getColumnIndex("BabyGenderName")));
                info.setCategoryId(cur.getString(cur.getColumnIndex("CategoryId")));
                info.setCategoryName(cur.getString(cur.getColumnIndex("CategoryName")));
                info.setJanamLiyeSisuKiSankhya(cur.getString(cur.getColumnIndex("JanamLiyeSisuKiSankhya")));
                info.setBabyWeight(cur.getString(cur.getColumnIndex("BabyWeight")));
                info.setMotherAadgharNo(cur.getString(cur.getColumnIndex("MotherAadgharNo")));
                info.setBabyAadharNo(cur.getString(cur.getColumnIndex("BabyAadharNo")));
                info.setMobileNo(cur.getString(cur.getColumnIndex("MobileNo")));
                info.setRemark(cur.getString(cur.getColumnIndex("Remark")));
                info.setBankAccountNo(cur.getString(cur.getColumnIndex("BankAccountNo")));

                info.setIfscCode(cur.getString(cur.getColumnIndex("IfscCode")));
                info.setBankName(cur.getString(cur.getColumnIndex("BankName")));
                info.setLatitude(cur.getString(cur.getColumnIndex("Latitude")));
                info.setLongitude(cur.getString(cur.getColumnIndex("Longitude")));
                info.setEntryBy(cur.getString(cur.getColumnIndex("EntryBy")));
                info.setEntryDate(cur.getString(cur.getColumnIndex("EntryDate")));
                info.setAppVersion(cur.getString(cur.getColumnIndex("AppVersion")));


//                if (!cur.isNull(cur.getColumnIndex("photo"))) {
//
//                    byte[] imgData = cur.getBlob(cur.getColumnIndex("photo"));
//                    Bitmap bmp = BitmapFactory.decodeByteArray(imgData, 0,imgData.length);
//
//                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                    bmp.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
//                    String encodedImg1 = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
//                    info.setPhoto(encodedImg1);
//                }


                infoList.add(info);
            }

            cur.close();
            db.close();

        } catch (Exception e) {
            // TODO: handle exception
            //info = null;
        }
        return infoList;
    }
    public long InsertInBasicDetails(EjananiEntryDetail result) {

        long c = 0;
        try {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("FinancialYearId", result.getFinancialYearId());
            values.put("FinancialYearName", result.getFinancialYearName());
            values.put("YojnaId", result.getYojnaId());
            values.put("YojnaName", result.getYojnaName());
            values.put("DistrictCode", result.getDistrictCode());
            values.put("DistrictName", result.getDistrictName());
            values.put("BlockCode", result.getBlockCode());
            values.put("BlockName", result.getBlockName());
            values.put("AreaTypeId", result.getAreaTypeId());
            values.put("AreaTypeName", result.getAreaTypeName());
            values.put("PanchayatCode", result.getPanchayatCode());
            values.put("PanchayatName", result.getPanchayatName());
            values.put("VillageCode", result.getVillageCode());
            values.put("VillageName", result.getVillageName());
            values.put("WardCode", result.getWardCode());
            values.put("WardName", result.getWardName());
            values.put("BabyNameEng", result.getBabyNameEng());
            values.put("BabyNameHindi", result.getBabyNameHindi());
            values.put("FatherNameEng", result.getFatherNameEng());
            values.put("FatherNameHindi", result.getFatherNameHindi());
            values.put("MotherNameEng", result.getMotherNameEng());
            values.put("MotherNameHindi", result.getMotherNameHindi());
            values.put("BabyGenderId", result.getBabyGenderId());
            values.put("BabyGenderName", result.getBabyGenderName());
            values.put("CategoryId", result.getCategoryId());
            values.put("CategoryName", result.getCategoryName());
            values.put("JanamLiyeSisuKiSankhya", result.getJanamLiyeSisuKiSankhya());
            values.put("BabyWeight", result.getBabyWeight());
            values.put("MotherAadgharNo", result.getMotherAadgharNo());
            values.put("BabyAadharNo", result.getBabyAadharNo());
            values.put("MobileNo", result.getMobileNo());
            values.put("Remark", result.getRemark());
            values.put("BankAccountNo", result.getBankAccountNo());
            values.put("IfscCode", result.getIfscCode());
            values.put("BankName", result.getBankName());
            values.put("EntryBy", result.getEntryBy());
            values.put("EntryDate", result.getEntryDate());


            c = db.insert("EjananiNewEntry", null, values);

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
        return c;

    }

}
