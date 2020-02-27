package bih.nic.in.chatrawasinspection.activty;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.Area_Entity;
import bih.nic.in.chatrawasinspection.entity.Block_Entity;
import bih.nic.in.chatrawasinspection.entity.DistrictEntity;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.Panchayat_Entity;
import bih.nic.in.chatrawasinspection.utility.CommonPref;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;

public class EjananiEntryForm extends AppCompatActivity {

    Spinner spn_yr,spn_yojna,spn_dist,spn_block,spn_areatype,spn_vill,spn_ward,spn_panchayat;
    ArrayList<FinYear_Model> FYearList = new ArrayList<FinYear_Model>();
    ArrayList<DistrictEntity> DistList = new ArrayList<DistrictEntity>();
    ArrayList<Block_Entity> BlockList = new ArrayList<Block_Entity>();
    ArrayList<Panchayat_Entity> Panchayat_List = new ArrayList<Panchayat_Entity>();
    ArrayList<Area_Entity> Area_List = new ArrayList<Area_Entity>();
    ArrayList<String> FyearArray;
    ArrayList<String> DistArray;
    ArrayList<String> BLKArray;
    ArrayList<String> Area_Array;
    ArrayList<String> Pan_Array;
    ArrayAdapter<String> Fyearadapter;
    ArrayAdapter<String> Distadapter;
    ArrayAdapter<String> BLKadapter;
    ArrayAdapter<String> areaadapter;
    ArrayAdapter<String> panadapter;
    String var_spn_agri_id="",var_spn_agri_year="",var_spn_dist_id="",var_spn_dist_nm="",var_block_id="",var_block_nm="",var_area_id="",var_area_nm="";
    DataBaseHelper dataBaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejanani_entry_form);
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);
        Initialization();
        loadFinancialYear();
        loadDistrictList();
        loadAreaTypeList();


        spn_yr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_agri_id = FYearList.get(pos - 1).getFYearCode();
                    var_spn_agri_year = FYearList.get(pos - 1).getFYearName();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_dist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_dist_id = DistList.get(pos - 1).getDistCode();
                    var_spn_dist_nm = DistList.get(pos - 1).getDistName();

                    BlockList = dataBaseHelper.getBlockLocal(var_spn_dist_id);
                    if (BlockList.size() <= 0) {
                        new LoadBlockList(var_spn_dist_id).execute();
                    } else {
                        loadBlockList(var_spn_dist_id);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_block.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_block_id = BlockList.get(pos - 1).getBlockCode();
                    var_block_nm = BlockList.get(pos - 1).getBlockName();



                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_areatype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_area_id = Area_List.get(pos - 1).getAreaCode();
                    var_area_nm = Area_List.get(pos - 1).getAreaName();

                    Panchayat_List = dataBaseHelper.getPanchayatLocal(var_area_id);
                    if (Panchayat_List.size() <= 0) {
                        new LoadPanchaytList(var_spn_dist_id).execute();
                    } else {
                        loadBlockList(var_spn_dist_id);
                    }

                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });


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


    public void loadFinancialYear() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        FYearList = dataBaseHelper.getFinancialYearLocal();
        FyearArray = new ArrayList<String>();
        FyearArray.add("-select-");
        int i = 0;
        for (FinYear_Model financial_year : FYearList) {
            FyearArray.add(financial_year.getFYearName());
            i++;
        }
        Fyearadapter = new ArrayAdapter<>(this, R.layout.dropdowlist, FyearArray);
        Fyearadapter.setDropDownViewResource(R.layout.dropdowlist);
        spn_yr.setAdapter(Fyearadapter);

//        if (getIntent().hasExtra("KeyId")) {
//            spn_yr.setSelection(((ArrayAdapter<String>) spn_yr.getAdapter()).getPosition(_spin_agri_yr));
//        }

    }

    public void loadDistrictList() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);


        DistList = dataBaseHelper.getDistrictLocal();
        String[] typeNameArray = new String[DistList.size() + 1];
        typeNameArray[0] = "-चुनें-";
        int i = 1;
        for (DistrictEntity type : DistList) {
            typeNameArray[i] = type.getDistName();
            i++;
        }
        Distadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, typeNameArray);
        Distadapter.setDropDownViewResource(R.layout.dropdownlist);
        spn_dist.setAdapter(Distadapter);
        int setID=0;
        for ( i = 0; i < DistList.size(); i++) {

            if (DistList.get(i).getDistCode().equalsIgnoreCase(CommonPref.getUserDetails(EjananiEntryForm.this).getDistrictCode())) {
                setID = i;
            }
            if(setID!=0) {
                spn_dist.setSelection(setID+1);
                spn_dist.setEnabled(false);
            }
        }

//        if(getIntent().hasExtra("KeyId"))
//        {
//            spn_dist.setSelection(((ArrayAdapter<String>) spn_dist.getAdapter()).getPosition(districtspin));
//        }

    }

    public void loadAreaTypeList() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Area_List = dataBaseHelper.getAreaLocal();
        Area_Array = new ArrayList<String>();
        Area_Array.add("-select-");
        int i = 0;
        for (Area_Entity financial_year : Area_List) {
            Area_Array.add(financial_year.getAreaName());
            i++;
        }
        areaadapter = new ArrayAdapter<>(this, R.layout.dropdowlist, Area_Array);
        areaadapter.setDropDownViewResource(R.layout.dropdowlist);
        spn_areatype.setAdapter(areaadapter);

//        if (getIntent().hasExtra("KeyId")) {
//            spn_yr.setSelection(((ArrayAdapter<String>) spn_yr.getAdapter()).getPosition(_spin_agri_yr));
//        }

    }

    public void loadBlockList(String distid) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        BlockList = dataBaseHelper.getBlockLocal(distid);
        String[] typeNameArray = new String[BlockList.size() + 1];
        typeNameArray[0] = "-प्रखंड चयन करे-";
        int i = 1;
        for (Block_Entity type : BlockList) {
            typeNameArray[i] = type.getBlockName();
            i++;
        }
        BLKadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, typeNameArray);
        BLKadapter.setDropDownViewResource(R.layout.dropdownlist);
        spn_block.setAdapter(BLKadapter);
        int setID=0;
        for ( i = 0; i < BlockList.size(); i++) {

            if (BlockList.get(i).getBlockCode().equalsIgnoreCase(CommonPref.getUserDetails(EjananiEntryForm.this).getBlockCode())) {
                setID = i;
            }
            if(setID!=0) {
                spn_block.setSelection(setID+1);
                spn_block.setEnabled(false);
            }
        }

//        if(getIntent().hasExtra("KeyId"))
//        {
//            spn_block.setSelection(((ArrayAdapter<String>) spn_block.getAdapter()).getPosition(blockspin));
//        }

    }


    private class LoadBlockList extends AsyncTask<String, Void, ArrayList<Block_Entity>> {
        String distcode;

        LoadBlockList(String distid)
        {
            this.distcode=distid;

        }


        private final ProgressDialog dialog = new ProgressDialog(EjananiEntryForm.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(EjananiEntryForm.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Block List...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Block_Entity> doInBackground(String... param) {


            return WebServiceHelper.getBlockList(distcode);

        }

        @Override
        protected void onPostExecute(ArrayList<Block_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(EjananiEntryForm.this);


                long i = helper.setBlockList(result);
                if (i > 0) {
                    loadBlockList(distcode);
                    Toast.makeText(EjananiEntryForm.this, "Block List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(EjananiEntryForm.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private class LoadPanchaytList extends AsyncTask<String, Void, ArrayList<Panchayat_Entity>> {
        String distcode;

        LoadPanchaytList(String distid)
        {
            this.distcode=distid;

        }


        private final ProgressDialog dialog = new ProgressDialog(EjananiEntryForm.this);

        private final android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(EjananiEntryForm.this).create();

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("Loading Block List...");
            this.dialog.show();
        }

        @Override
        protected ArrayList<Panchayat_Entity> doInBackground(String... param) {


            return WebServiceHelper.getPqanchayatList(distcode);

        }

        @Override
        protected void onPostExecute(ArrayList<Panchayat_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(EjananiEntryForm.this);


                long i = helper.setPanchayatList(result);
                if (i > 0) {
                    loadBlockList(distcode);
                    Toast.makeText(EjananiEntryForm.this, "Panchayat List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(EjananiEntryForm.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
