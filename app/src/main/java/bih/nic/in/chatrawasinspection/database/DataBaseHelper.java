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

import bih.nic.in.chatrawasinspection.entity.Deptlist;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.Schemelist;
import bih.nic.in.chatrawasinspection.entity.UserDetails;

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






}