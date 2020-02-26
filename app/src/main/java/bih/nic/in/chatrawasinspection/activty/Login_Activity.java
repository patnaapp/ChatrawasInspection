package bih.nic.in.chatrawasinspection.activty;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.SQLException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.Schemelist;
import bih.nic.in.chatrawasinspection.entity.UserDetails;
import bih.nic.in.chatrawasinspection.utility.CommonPref;
import bih.nic.in.chatrawasinspection.utility.GlobalVariables;
import bih.nic.in.chatrawasinspection.utility.Utiilties;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;

public class Login_Activity extends Activity {

    TextView txt_dep_name;
    AutoCompleteTextView userName;
    EditText userPass;
    //String DeptName="",DeptCode="";
    DataBaseHelper localDBHelper;
    String[] param;
    String uid = "";
    String pass = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);



        localDBHelper = new DataBaseHelper(Login_Activity.this);
        txt_dep_name =(TextView)findViewById(R.id.txt_dep_name);
        //DeptName = getIntent().getStringExtra("DeptTypeName");
        //DeptCode = getIntent().getStringExtra("DeptTypeCode");
        //txt_dep_name.setText(DeptName);

    }

    protected void onResume() {
        super.onResume();
        //getIMEI();

    }

    public void Login(View view) {

        userName = (AutoCompleteTextView) findViewById(R.id.edt_user_id);
        userPass = (EditText) findViewById(R.id.edt_Pass);
        param = new String[2];
        param[0] = userName.getText().toString();
        param[1] = userPass.getText().toString();
        //param[2] =DeptCode;

        if (!param[0].equals("") && !param[0].equals("null")) {

            if (!param[1].equals("") && !param[1].equals("null")) {
                new LoginTask().execute(param);
            } else {
                userPass.setError("password cannot be blank");
            }
        } else if (param[1].equals("") || param[1].equals("null")) {
            userName.setError("User Id cannot be blank");
            userPass.setError("password cannot be blank");
        } else {
            userName.setError("User Id cannot be blank");
        }
    }

        //new LoginTask().execute(param);



    private class LoginTask extends AsyncTask<String, Void, UserDetails> {

        private final ProgressDialog dialog = new ProgressDialog(
                Login_Activity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(
                Login_Activity.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage(getResources().getString(R.string.authenticating));
            this.dialog.show();
        }

        @Override
        protected UserDetails doInBackground(String... param) {

            if (!Utiilties.isOnline(Login_Activity.this)) {
                UserDetails userDetails = new UserDetails();
                userDetails.setAuthenticated(true);
                return userDetails;
            } else {
                return WebServiceHelper.Login(param[0], param[1],param[2]);
            }

        }

        @Override
        protected void onPostExecute(final UserDetails result) {

            if (this.dialog.isShowing()) this.dialog.dismiss();

            // final EditText userPass = (EditText) findViewById(R.id.password);
            //final AutoCompleteTextView userName = (AutoCompleteTextView) findViewById(R.id.email);


            if (result != null && result.isAuthenticated() == false) {

                alertDialog.setTitle(getString(R.string.failed));
                alertDialog.setMessage(getString(R.string.login_failed));
                alertDialog.show();

            } else if (!(result != null)) {
                AlertDialog.Builder ab = new AlertDialog.Builder(Login_Activity.this);
                ab.setTitle(getResources().getString(R.string.login_failed));
                //ab.setMessage(getResources().getString(R.string.server_down_text));
                ab.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton) {

                        dialog.dismiss();

                    }
                });


                ab.create().getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
                ab.show();

            } else {

                //---------------------Online-------------------------------------
                if (Utiilties.isOnline(Login_Activity.this)) {


                    uid = param[0];
                    pass = param[1];

                    if (result != null ) {




                            try {

                                GlobalVariables.LoggedUser = result;
                                GlobalVariables.LoggedUser.setUserID(userName
                                        .getText().toString().trim().toLowerCase());

                                GlobalVariables.LoggedUser.setPassword(userPass
                                        .getText().toString().trim());


                                CommonPref.setUserDetails(getApplicationContext(), GlobalVariables.LoggedUser);


                                long c = setLoginStatus(GlobalVariables.LoggedUser);

                                if (c > 0) {
                                    start();
                                } else {
                                    Toast.makeText(Login_Activity.this, getResources().getString(R.string.authentication_failed),
                                            Toast.LENGTH_SHORT).show();
                                }


                            } catch (Exception ex) {
                                Toast.makeText(Login_Activity.this, getResources().getString(R.string.authentication_failed),
                                        Toast.LENGTH_SHORT).show();
                            }


                    } else {
                        alertDialog.setTitle(getResources().getString(R.string.server_down_title));
                        alertDialog.setMessage(getResources().getString(R.string.server_down_text));
                        alertDialog.show();
                        return;

                    }

                    // offline -------------------------------------------------------------------------

                } else {

                    if (localDBHelper.getUserCount() > 0) {

                        GlobalVariables.LoggedUser = localDBHelper
                                .getUserDetails(userName.getText()
                                                .toString().trim().toLowerCase(),
                                        userPass.getText().toString());

                        if (GlobalVariables.LoggedUser != null) {

                            CommonPref.setUserDetails(getApplicationContext(), GlobalVariables.LoggedUser);

								/*GlobalVariables.Last_Visited = GlobalVariables.LoggedUser
                                        .get_LastVisited();*/
                            start();


                        } else {

                            Toast.makeText(
                                    getApplicationContext(),getString(R.string.loading_fail),
                                    Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(
                                getApplicationContext(),
                                getResources().getString(R.string.enable_internet_for_firsttime),
                                Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
    }
    private long setLoginStatus(UserDetails details) {


        localDBHelper = new DataBaseHelper(getApplicationContext());
        long c = localDBHelper.insertUserDetails(details);

        return c;
    }

    public void start() {
        Intent iUserHome = new Intent(getApplicationContext(), Activity_Hostel_Home.class);
        startActivity(iUserHome);
        // }
        finish();


    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

//       Toast.makeText(login.this,"Finished",Toast.LENGTH_SHORT).show();
    }

   /* public class SchemeLoader extends AsyncTask<String, Void, ArrayList<Schemelist>> {
        String DepartmentCode = "";

        SchemeLoader(String deptcode) {
            this.DepartmentCode = deptcode;
        }

        private final ProgressDialog dialog = new ProgressDialog(Login_Activity.this);

        private final AlertDialog alertDialog = new AlertDialog.Builder(Login_Activity.this).create();

        @Override
        protected void onPreExecute() {
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("योजना लोड हो रहा है ...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Schemelist> doInBackground(String... params) {

            ArrayList<Schemelist> res1 = WebServiceHelper.getSchemelist(DepartmentCode);

            return res1;
        }

        @Override
        protected void onPostExecute(ArrayList<Schemelist> result) {

            if (this.dialog.isShowing()) {


                DataBaseHelper placeData = new DataBaseHelper(Login_Activity.this);
                placeData.insertSchemeData(result);

                this.dialog.dismiss();
            }
            *//*if (CommonPref.getUserDetails(Login_Activity.this).getUserRole().equals("HOSADM")) {
                Intent iUserHome = new Intent(getApplicationContext(), Student_Home_Activity.class);
                startActivity(iUserHome);
            }
           else if (CommonPref.getUserDetails(Login_Activity.this).getUserRole().equals("DSTADM")) {
                Intent iUserHome = new Intent(getApplicationContext(), Hostel_List.class);
                startActivity(iUserHome);
            }

            else {*//*
                //Intent iUserHome = new Intent(getApplicationContext(), HomeActivity.class);
                Intent iUserHome = new Intent(getApplicationContext(), Activity_Hostel_Home.class);
                startActivity(iUserHome);
           // }
            finish();
//            Intent iUserHome = new Intent(getApplicationContext(), Student_Home_Activity.class);
//            startActivity(iUserHome);
//
//            finish();
//
        }

    }*/
}
