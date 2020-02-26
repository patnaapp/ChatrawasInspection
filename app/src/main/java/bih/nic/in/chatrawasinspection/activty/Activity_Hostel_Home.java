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
import bih.nic.in.chatrawasinspection.entity.BenfiList;
import bih.nic.in.chatrawasinspection.entity.EntryDetailDistAdm;
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


       /* btn_hostupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utiilties.isOnline(Activity_Hostel_Home.this)) {

                    AlertDialog.Builder ab = new AlertDialog.Builder(
                            Activity_Hostel_Home.this);
                    ab.setTitle("अपलोड !");
                    ab.setMessage("क्या आप सर्वर पर लंबित सभी अपलोड करना चाहते हैं?");
                    ab.setPositiveButton("नहीं", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                        }
                    });

                    ab.setNegativeButton("हाँ", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                            // UploadNewEntry(CommonPref.getUserDetails(Activity_Hostel_Home.this).getUserID());
                            DataBaseHelper dbHelper = new DataBaseHelper(
                                    getApplicationContext());

                            ArrayList<BenfiList> dataProgress = dbHelper.getUploadTableList(CommonPref.getUserDetails(getApplicationContext()).getUserID());
                            if (dataProgress.size() > 0) {


                                for (BenfiList data : dataProgress) {
                                  //  new UploadPendingTask2(data).execute();

                                }
                            }
                        }



                    });
                    ab.create().getWindow().getAttributes().windowAnimations = R.style.alert_animation;
                    ab.show();

                } else

                {
                    Toast.makeText(Activity_Hostel_Home.this, " कोई इंटरनेट कनेक्शन नहीं\n ! \n कृपया अपनी इंटरनेट कनेक्टिविटी जांचें.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });*/

       // getPendingCounts();

    }


}
