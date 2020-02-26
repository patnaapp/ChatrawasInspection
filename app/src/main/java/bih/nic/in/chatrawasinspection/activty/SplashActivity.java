package bih.nic.in.chatrawasinspection.activty;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.Deptlist;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.GenderList;
import bih.nic.in.chatrawasinspection.entity.Versioninfo;
import bih.nic.in.chatrawasinspection.utility.CommonPref;
import bih.nic.in.chatrawasinspection.utility.GlobalVariables;
import bih.nic.in.chatrawasinspection.utility.MarshmallowPermission;
import bih.nic.in.chatrawasinspection.utility.Utiilties;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;

public class SplashActivity extends Activity {
    Context context;
    MarshmallowPermission permission;
    long isDataDownloaded=-1;
    public static SharedPreferences prefs;
    @SuppressLint("NewApi")
    ActionBar actionBar;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        context=this;

        DataBaseHelper helper = new DataBaseHelper(this);
        try {
            helper.createDataBase();

            Log.e("DB Created", "YES");
        } catch (IOException e) {
            Log.e("DB Created", "Errorrrrrrrrr");
            e.printStackTrace();
        }

    }

    private void checkAppUseMode()
    {
        if(!Utiilties.isGPSEnabled(SplashActivity.this)){
            Utiilties.displayPromptForEnablingGPS(SplashActivity.this);
        }else {
            boolean net = false;

            permission = new MarshmallowPermission(this, Manifest.permission.ACCESS_NETWORK_STATE);
            if (permission.result == -1 || permission.result == 0)
                net = Utiilties.isOnline(SplashActivity.this);
            if (net) {

                if (!prefs.getBoolean("firstTime", false)) {


                    //start();
                    //if(!prefs.getBoolean("fieldDownloaded",false))
                    //	 new loadAppData().execute("");
                   loadAppData();
                    //else start();

                  //  start();
                } else {
                    new CheckUpdate().execute();
                    //new downloadFyearData().execute("");

                    //start();
                }
            } else if (!prefs.getBoolean("firstTime", false)) {

                final AlertDialog alertDialog = new AlertDialog.Builder(
                        SplashActivity.this).create();
                alertDialog.setTitle(getResources().getString(R.string.no_internet_connection));
                alertDialog.setMessage(getResources().getString(R.string.enable_internet_for_firsttime));
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog closed


                        GlobalVariables.isOffline = false;
                        Intent I = new Intent(
                                android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(I);
                        alertDialog.cancel();


                        //start();
                    }
                });

                alertDialog.show();
            } else {

                if (prefs.getBoolean("firstTime", false))
                    checkOnline();


            }
        }
    }


    protected void checkOnline() {
        // TODO Auto-generated method stub
        super.onResume();

        boolean net=false;

        MarshmallowPermission permission=new MarshmallowPermission(this,Manifest.permission.READ_PHONE_STATE);
        if(permission.result==-1 || permission.result==0)  net=Utiilties.isOnline(SplashActivity.this);

        if (!net) {

            AlertDialog.Builder ab = new AlertDialog.Builder(	SplashActivity.this);
            ab.setMessage(getResources().getString(R.string.no_internet_connection));
            ab.setPositiveButton(getResources().getString(R.string.turnon_internet),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {
                            GlobalVariables.isOffline = false;
                            Intent I = new Intent(
                                    android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(I);
                        }
                    });
            ab.setNegativeButton(getResources().getString(R.string.continue_offline),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog,
                                            int whichButton) {

                            GlobalVariables.isOffline = true;
                            start();
                        }
                    });


            ab.show();

        } else {

            GlobalVariables.isOffline = false;
            new CheckUpdate().execute();

        }
    }

    private void start() {
        String USERID="";

        USERID = CommonPref.getUserDetails(SplashActivity.this).getUserID();
        Intent i;
        if(USERID.equalsIgnoreCase("")) {

            i = new Intent(getApplicationContext(), Activity_Hostel_Home.class);
            startActivity(i);
        }else {
            i = new Intent(getApplicationContext(), Activity_Hostel_Home.class);
            startActivity(i);
        }

        finish();

    }



    private void showDailog(AlertDialog.Builder ab,
                            final Versioninfo versioninfo) {

        if (versioninfo.isVerUpdated()) {

            if (versioninfo.getPriority() == 0) {

                start();
            } else if (versioninfo.getPriority() == 1) {

                ab.setTitle(versioninfo.getUpdateTile());
                ab.setMessage(versioninfo.getUpdateMsg());

                ab.setPositiveButton(getResources().getString(R.string.update),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

						         Intent launchIntent = getPackageManager()
                                        .getLaunchIntentForPackage(
                                                "com.android.vending");
                                ComponentName comp = new ComponentName(
                                        "com.android.vending",
                                        "com.google.android.finsky.activities.LaunchUrlHandlerActivity"); // package
                                // name
                                // and
                                // activity
                                launchIntent.setComponent(comp);
                                launchIntent.setData(Uri
                                        .parse("market://details?id="
                                                + getApplicationContext()
                                                .getPackageName()));

                                try {
                                    startActivity(launchIntent);
                                    finish();
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(
                                            Intent.ACTION_VIEW, Uri
                                            .parse(versioninfo
                                                    .getAppUrl())));
                                    finish();
                                }

                                dialog.dismiss();
                            }


                        });
                ab.setNegativeButton(getResources().getString(R.string.ignore),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {

                                dialog.dismiss();

                                start();
                            }

                        });

                ab.show();

            } else if (versioninfo.getPriority() == 2) {

                ab.setTitle(versioninfo.getUpdateTile());
                ab.setMessage(versioninfo.getUpdateMsg());
                ab.setPositiveButton(getResources().getString(R.string.update),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {


                                Intent launchIntent = getPackageManager()
                                        .getLaunchIntentForPackage(
                                                "com.android.vending");
                                ComponentName comp = new ComponentName(
                                        "com.android.vending",
                                        "com.google.android.finsky.activities.LaunchUrlHandlerActivity"); // package

                                launchIntent.setComponent(comp);
                                launchIntent.setData(Uri
                                        .parse("market://details?id="
                                                + getApplicationContext()
                                                .getPackageName()));

                                try {
                                    startActivity(launchIntent);
                                    finish();
                                } catch (android.content.ActivityNotFoundException anfe) {
                                    startActivity(new Intent(
                                            Intent.ACTION_VIEW, Uri
                                            .parse(versioninfo
                                                    .getAppUrl())));
                                    finish();
                                }

                                dialog.dismiss();
                                // finish();
                            }
                        });
                ab.show();
            }
        } else {

            start();
        }

    }

    private class CheckUpdate extends AsyncTask<Void, Void, Versioninfo> {


        CheckUpdate() {

        }


        @Override
        protected void onPreExecute() {

        }

        @SuppressLint("MissingPermission")
        @Override
        protected Versioninfo doInBackground(Void... Params) {

            TelephonyManager tm = null;
            String imei = null;

            permission=new MarshmallowPermission(SplashActivity.this,Manifest.permission.READ_PHONE_STATE);
            if(permission.result==-1 || permission.result==0)
            {
                try
                {
                    tm= (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

                    if(tm!=null) imei = tm.getDeviceId();
                }catch(Exception e){}
            }

            String version = null;
            try {
                version = getPackageManager().getPackageInfo(getPackageName(),
                        0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Versioninfo versioninfo = WebServiceHelper.CheckVersion(imei,
                    version);

            return versioninfo;

        }

        @Override
        protected void onPostExecute(final Versioninfo versioninfo) {

            final AlertDialog.Builder ab = new AlertDialog.Builder(
                    SplashActivity.this);
            ab.setCancelable(false);
            if (versioninfo != null && versioninfo.isValidDevice()) {

          /*      CommonPref.setCheckUpdate(getApplicationContext(),
                        System.currentTimeMillis());*/

                if (versioninfo.getAdminMsg().trim().length() > 0
                        && !versioninfo.getAdminMsg().trim()
                        .equalsIgnoreCase("anyType{}")) {

                    ab.setTitle(versioninfo.getAdminTitle());
                    ab.setMessage(Html.fromHtml(versioninfo.getAdminMsg()));
                    ab.setPositiveButton(getString(R.string.ok),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    dialog.dismiss();

                                    showDailog(ab, versioninfo);

                                }
                            });
                    ab.show();
                } else {
                    showDailog(ab, versioninfo);
                }
            } else {
                if (versioninfo != null) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.wrong_device_text),
                            Toast.LENGTH_LONG).show();

                }
                else
                {
                    start();

                }
            }

        }
    }
    private class DownLoadYear extends AsyncTask<String, Void,ArrayList<FinYear_Model>> {




        @Override
        protected void onPreExecute() {


        }

        @Override
        protected ArrayList<FinYear_Model> doInBackground(String...arg) {


            return WebServiceHelper.getFYearList();

        }

        @Override
        protected void onPostExecute(ArrayList<FinYear_Model> result) {

            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());


            long i= helper.setFYearLocal(result);


            Log.d("egcyifgduh",""+result);



            if(i>0)
            {
                SharedPreferences.Editor editor = SplashActivity.prefs.edit();
                editor.putBoolean("fieldDownloaded", true);
                editor.commit();
                checkPref();

            }
            else
            {
                Toast.makeText(getApplicationContext(), "loading_fail",Toast.LENGTH_SHORT).show();

            }


        }
    }
//    private class DownLoadGender extends AsyncTask<String, Void,ArrayList<GenderList>> {
//
//
//
//
//        @Override
//        protected void onPreExecute() {
//
//
//        }
//
//        @Override
//        protected ArrayList<GenderList> doInBackground(String...arg) {
//
//
//            return WebServiceHelper.GetGenderList();
//
//        }
//
//        @Override
//        protected void onPostExecute(ArrayList<GenderList> result) {
//
//            DataBaseHelper helper=new DataBaseHelper(getApplicationContext());
//
//
//            long i= helper.setSchemeLocal(result);
//
//
//            Log.d("egcyifgduh",""+result);
//
//
//
//            if(i>0)
//            {
//                SharedPreferences.Editor editor = SplashActivity.prefs.edit();
//                editor.putBoolean("fieldDownloaded", true);
//                editor.commit();
//                checkPref();
//
//            }
//            else
//            {
//                Toast.makeText(getApplicationContext(), "loading_fail",Toast.LENGTH_SHORT).show();
//
//            }
//
//
//        }
//    }



    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        checkAppUseMode();
    }
    public void loadAppData()
    {
        new DownLoadYear().execute("");

    }

    public void checkPref()
    {
        ProgressDialog dialog = new ProgressDialog(
                SplashActivity.this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage(getResources().getString(R.string.authenticating));

        // dialog.show();

        boolean p1=SplashActivity.prefs.getBoolean("fieldDownloaded", false);

        if(p1)
        {
            //dialog.dismiss();
            SharedPreferences.Editor editor = SplashActivity.prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
            start();
        }
    }
}
