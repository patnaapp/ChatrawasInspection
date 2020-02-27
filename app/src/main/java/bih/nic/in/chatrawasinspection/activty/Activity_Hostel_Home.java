package bih.nic.in.chatrawasinspection.activty;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.Area_Entity;
import bih.nic.in.chatrawasinspection.entity.Bank_Entity;
import bih.nic.in.chatrawasinspection.entity.BenfiList;
import bih.nic.in.chatrawasinspection.entity.Category_Entity;
import bih.nic.in.chatrawasinspection.entity.DistrictEntity;
import bih.nic.in.chatrawasinspection.entity.EntryDetailDistAdm;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.Gender_Entity;
import bih.nic.in.chatrawasinspection.entity.Schemelist;
import bih.nic.in.chatrawasinspection.entity.StudentPhoto;
import bih.nic.in.chatrawasinspection.utility.CommonPref;
import bih.nic.in.chatrawasinspection.utility.Utiilties;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;



public class Activity_Hostel_Home extends Activity {
    ImageView menu_inflater;
    Button bnt_hostrntry,btn_Hostedit,btn_hostupload,bnt_new_reg;
    TextView tv_user_role,tv_Hostelpending;
    DataBaseHelper dataBaseHelper;
    String DistrictName="";
    long count;
    ProgressDialog pd1;
    String version = "";
    Spinner spn_schmeme;
    ArrayList<Schemelist> SchemeList = new ArrayList<Schemelist>();
    ArrayAdapter<String> Schemeadapter;
    String SchemeId="";
    ArrayList<FinYear_Model> FYearList = new ArrayList<FinYear_Model>();
    ArrayList<DistrictEntity> DistList = new ArrayList<DistrictEntity>();
    ArrayList<Area_Entity> Area_List = new ArrayList<Area_Entity>();
    ArrayList<Category_Entity> Category_List = new ArrayList<Category_Entity>();
    ArrayList<Gender_Entity> Gender_List = new ArrayList<Gender_Entity>();
    ArrayList<Bank_Entity> Bank_List = new ArrayList<Bank_Entity>();
    ArrayList<String> FyearArray;
    ArrayList<String> DistArray;
    ArrayAdapter<String> Fyearadapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__hostel__home);

        dataBaseHelper = new DataBaseHelper(Activity_Hostel_Home.this);
        pd1 = new ProgressDialog(Activity_Hostel_Home.this);
        DistrictName = CommonPref.getUserDetails(Activity_Hostel_Home.this).getDistrictName();

        bnt_hostrntry=(Button)findViewById(R.id.bnt_hostrntry);
        btn_Hostedit=(Button)findViewById(R.id.btn_Hostedit);
        btn_hostupload=(Button)findViewById(R.id.btn_hostupload);
        bnt_new_reg=(Button)findViewById(R.id.bnt_new_reg);
        menu_inflater = (ImageView) findViewById(R.id.menu_inflater);
        tv_user_role = (TextView) findViewById(R.id.tv_user_role);
        spn_schmeme=(Spinner)findViewById(R.id.spn_schmeme);
        tv_Hostelpending = (TextView) findViewById(R.id.tv_Hostelpending);

        tv_user_role.setText(DistrictName);

        FYearList = dataBaseHelper.getFinancialYearLocal();
        if (FYearList.size() <= 0) {
            new FINANCIALYEAR_New().execute();
        }
        DistList = dataBaseHelper.getDistrictLocal();
        if (DistList.size() <= 0) {
            new DistrictList_New().execute();
        }
        Area_List = dataBaseHelper.getAreaLocal();
        if (Area_List.size() <= 0) {
            new AreaList_New().execute();
        }
        Category_List = dataBaseHelper.getCategoryLocal();
        if (Category_List.size() <= 0) {
            new CategoryList_New().execute();
        }
        Gender_List = dataBaseHelper.getGenderLocal();
        if (Gender_List.size() <= 0) {
            new GenderList_New().execute();
        }
        Bank_List = dataBaseHelper.getBankLocal();
        if (Bank_List.size() <= 0) {
            new BankList_New().execute();
        }


        spn_schmeme.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                if (arg2 > 0) {
                    Schemelist data = SchemeList.get(arg2 - 1);
                    // SchemeTypeCode = data.getSchemeId();
                    SchemeId = data.getSchemeId();
                    PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("SCHEMEID", SchemeId).commit();
                    Toast.makeText(getApplicationContext(),SchemeId,Toast.LENGTH_LONG).show();


                } else {
                    SchemeId = "0";
                    //SchemeTpeName = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                SchemeId = "0";

            }
        });
        bnt_hostrntry=(Button)findViewById(R.id.bnt_hostrntry);
        bnt_hostrntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EjananiEntryForm.class);
                startActivity(intent);
            }
        });
        menu_inflater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Activity_Hostel_Home.this, menu_inflater);
                popup.getMenuInflater().inflate(R.menu.main, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();

                        //noinspection SimplifiableIfStatement
                        if (id == R.id.action_logout) {

                            Intent i = new Intent(getBaseContext(), EjananiEntryForm.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);


                            return true;

                        }

                        return true;
                    }
                });

                popup.show();
            }
        });


    }
    private class FINANCIALYEAR_New extends AsyncTask<String, Void, ArrayList<FinYear_Model>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Financial Year...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<FinYear_Model> doInBackground(String... param) {


            return WebServiceHelper.getFinancialYear();

        }

        @Override
        protected void onPostExecute(ArrayList<FinYear_Model> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setFinancialYear(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Financial Year Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class DistrictList_New extends AsyncTask<String, Void, ArrayList<DistrictEntity>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Financial Year...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<DistrictEntity> doInBackground(String... param) {


            return WebServiceHelper.getDistrictList();

        }

        @Override
        protected void onPostExecute(ArrayList<DistrictEntity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setDistList(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Financial Year Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class AreaList_New extends AsyncTask<String, Void, ArrayList<Area_Entity>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Financial Year...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Area_Entity> doInBackground(String... param) {


            return WebServiceHelper.getAreaList();

        }

        @Override
        protected void onPostExecute(ArrayList<Area_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setAreaList(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Area List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CategoryList_New extends AsyncTask<String, Void, ArrayList<Category_Entity>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Financial Year...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Category_Entity> doInBackground(String... param) {


            return WebServiceHelper.getCategList();

        }

        @Override
        protected void onPostExecute(ArrayList<Category_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setCategoryList(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Category List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GenderList_New extends AsyncTask<String, Void, ArrayList<Gender_Entity>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Financial Year...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Gender_Entity> doInBackground(String... param) {


            return WebServiceHelper.getgenderList();

        }

        @Override
        protected void onPostExecute(ArrayList<Gender_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setGenderList(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Category List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private class BankList_New extends AsyncTask<String, Void, ArrayList<Bank_Entity>> {

        private final ProgressDialog dialog = new ProgressDialog(Activity_Hostel_Home.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(Activity_Hostel_Home.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Bank List...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Bank_Entity> doInBackground(String... param) {


            return WebServiceHelper.getBankList();

        }

        @Override
        protected void onPostExecute(ArrayList<Bank_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(Activity_Hostel_Home.this);


                long i = helper.setBankList(result);
                if (i > 0) {
                    Toast.makeText(Activity_Hostel_Home.this, "Bank List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(Activity_Hostel_Home.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
