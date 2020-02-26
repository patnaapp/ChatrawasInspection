package bih.nic.in.chatrawasinspection.activty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import bih.nic.in.chatrawasinspection.R;

public class EjananiEntryForm extends AppCompatActivity {

    Spinner spn_yr,spn_yojna,spn_dist,spn_block,spn_areatype,spn_vill,spn_ward,spn_panchayat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejanani_entry_form);

    }



    private void Initialization(){

        spn_yr=(Spinner)findViewById(R.id.spn_fyear);
        spn_yojna=(Spinner)findViewById(R.id.spn_yojna);
        spn_dist=(Spinner)findViewById(R.id.spn_distname);
        spn_block=(Spinner)findViewById(R.id.spn_block);
        spn_areatype=(Spinner)findViewById(R.id.spn_areatype);
        spn_panchayat=(Spinner)findViewById(R.id.spn_panch);
        spn_vill=(Spinner)findViewById(R.id.spn_vill);
        spn_ward=(Spinner)findViewById(R.id.spn_ward);
    }
}
