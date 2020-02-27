package bih.nic.in.chatrawasinspection.activty;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.Area_Entity;
import bih.nic.in.chatrawasinspection.entity.Bank_Entity;
import bih.nic.in.chatrawasinspection.entity.Block_Entity;
import bih.nic.in.chatrawasinspection.entity.Category_Entity;
import bih.nic.in.chatrawasinspection.entity.DistrictEntity;
import bih.nic.in.chatrawasinspection.entity.FinYear_Model;
import bih.nic.in.chatrawasinspection.entity.Gender_Entity;
import bih.nic.in.chatrawasinspection.entity.Panchayat_Entity;
import bih.nic.in.chatrawasinspection.entity.Village_Entity;
import bih.nic.in.chatrawasinspection.entity.Ward_Entity;
import bih.nic.in.chatrawasinspection.utility.CommonPref;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;

public class EjananiEntryForm extends AppCompatActivity {

    Spinner spn_yr,spn_yojna,spn_dist,spn_block,spn_areatype,spn_vill,spn_ward,spn_panchayat;
    ArrayList<FinYear_Model> FYearList = new ArrayList<FinYear_Model>();
    ArrayList<DistrictEntity> DistList = new ArrayList<DistrictEntity>();
    ArrayList<Block_Entity> BlockList = new ArrayList<Block_Entity>();
    ArrayList<Panchayat_Entity> Panchayat_List = new ArrayList<Panchayat_Entity>();
    ArrayList<Bank_Entity> Bank_List = new ArrayList<Bank_Entity>();
    ArrayList<Village_Entity> Village_List = new ArrayList<Village_Entity>();
    ArrayList<Ward_Entity> Ward_List = new ArrayList<Ward_Entity>();
    ArrayList<Area_Entity> Area_List = new ArrayList<Area_Entity>();
    ArrayList<Category_Entity> Category_List = new ArrayList<Category_Entity>();
    ArrayList<Gender_Entity> CGender_List = new ArrayList<Gender_Entity>();
    ArrayList<String> FyearArray;
    ArrayList<String> DistArray;
    ArrayList<String> BLKArray;
    ArrayList<String> Area_Array;
    ArrayList<String> Gender_Array;
    ArrayList<String> Category_Array;
    ArrayList<String> Bank_Array;
    ArrayList<String> Vill_Array;
    ArrayList<String> Pan_Array;
    ArrayList<String> ward_Array;
    ArrayAdapter<String> Fyearadapter;
    ArrayAdapter<String> Distadapter;
    ArrayAdapter<String> BLKadapter;
    ArrayAdapter<String> areaadapter;
    ArrayAdapter<String> panadapter;
    ArrayAdapter<String> catadapter;
    ArrayAdapter<String> genderadapter;
    ArrayAdapter<String> vill_adapter;
    ArrayAdapter<String> ward_adapter;
    ArrayAdapter<String> bank_adapter;
    String var_spn_bank_id="",var_spn_bank_nm="";
    String var_spn_agri_id="",var_spn_agri_year="",var_spn_dist_id="",var_spn_dist_nm="",var_block_id="",var_block_nm="",var_area_id="",var_area_nm="";
    DataBaseHelper dataBaseHelper;
    Spinner spn_child_gender,spn_cat,spn_bank;
    String var_spn_gender_id="",var_spn_gender_nm="",var_spn_cat_id="",var_spn_cat_nm="",var_pan_id="",var_pan_nm="";
    String var_vill_id="",var_vill_nm="",var_spn_ward_id="",var_spn_ward_nm="";
    EditText edt_childname,edt_childname_hindi,edt_fanme_eng,edt_fname_hindi,edt_mothname_eng,edt_mothname_hindi;
    EditText edt_nochild,edt_child_wht,edt_adh_mother,edt_Mother_Nm_aadhar,child_aadhar_no,edt_ben_mob;
    EditText edt_remarks,edt_bank_acc,edt_ifsc;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejanani_entry_form);
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);
        Initialization();
        loadFinancialYear();
        loadBankList();
        loadDistrictList();
        loadAreaTypeList();
        loadCategoryList();
        loadGenderList();


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
                    spn_areatype.setSelection(0);
                    spn_panchayat.setSelection(0);
                    spn_vill.setSelection(0);
                    spn_ward.setSelection(0);


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

                    spn_panchayat.setSelection(0);
                    spn_vill.setSelection(0);
                    spn_ward.setSelection(0);

                    Panchayat_List = dataBaseHelper.getPanchayatLocal(var_block_id,var_area_id);
                    if (Panchayat_List.size() <= 0) {
                        new LoadPanchaytList(var_block_id).execute();
                    } else {
                        loadPanchayatList(var_block_id,var_area_id);
                    }

                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_panchayat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_pan_id = Panchayat_List.get(pos - 1).getPan_code();
                    var_pan_nm = Panchayat_List.get(pos - 1).getPan_Name();

                    spn_vill.setSelection(0);
                    spn_ward.setSelection(0);

                    Village_List = dataBaseHelper.getVillageLocal(var_pan_id);
                    if (Village_List.size() <= 0) {
                        new LoadVillageList(var_pan_id).execute();
                    } else {
                        loadVillageList(var_pan_id);
                    }

                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_vill.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_vill_id = Village_List.get(pos - 1).getVill_code();
                    var_vill_nm = Village_List.get(pos - 1).getVill_Name();

                    spn_ward.setSelection(0);

                    Ward_List = dataBaseHelper.getWardLocal(var_block_id,var_pan_id,var_area_id);
                    if (Ward_List.size() <= 0) {
                        new LoadWardList(var_pan_id).execute();
                    } else {
                        loadWardList(var_block_id,var_pan_id,var_area_id);
                    }

                } else {

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });
        spn_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_ward_id = Ward_List.get(pos - 1).getWARDCODE();
                    var_spn_ward_nm = Ward_List.get(pos - 1).getWARDNAME();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_child_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_gender_id = CGender_List.get(pos - 1).getGender_Id();
                    var_spn_gender_nm = CGender_List.get(pos - 1).getGender_Nm();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_cat_id = Category_List.get(pos - 1).getCat_Id();
                    var_spn_cat_nm = Category_List.get(pos - 1).getCat_Nm();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        spn_bank.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                int pos = position;
                if (pos > 0) {
                    var_spn_bank_id = Bank_List.get(pos - 1).getBank_Code();
                    var_spn_bank_nm= Bank_List.get(pos - 1).getBank_Nm();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }

        });

        edt_childname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForEnglish(edt_childname);
            }
        });
        edt_fanme_eng.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForEnglish(edt_fanme_eng);
            }
        });
        edt_mothname_eng.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForEnglish(edt_mothname_eng);
            }
        });

        edt_Mother_Nm_aadhar.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForEnglish(edt_Mother_Nm_aadhar);
            }
        });
        edt_remarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForEnglish(edt_remarks);
            }
        });
        edt_childname_hindi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForHindi(edt_childname_hindi);
            }
        });

        edt_fname_hindi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForHindi(edt_fname_hindi);
            }
        });

        edt_mothname_hindi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                checkForHindi(edt_mothname_hindi);
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
        spn_child_gender=(Spinner)findViewById(R.id.spn_child_gender);
        spn_cat=(Spinner)findViewById(R.id.spn_cat);
        spn_bank=(Spinner)findViewById(R.id.spn_bank);
        edt_childname=findViewById(R.id.edt_childname);
        edt_childname_hindi=findViewById(R.id.edt_childname_hindi);
        edt_fanme_eng=findViewById(R.id.edt_fanme_eng);
        edt_fname_hindi=findViewById(R.id.edt_fname_hindi);
        edt_mothname_eng=findViewById(R.id.edt_mothname_eng);
        edt_mothname_hindi=findViewById(R.id.edt_mothname_hindi);
        edt_nochild=findViewById(R.id.edt_nochild);
        edt_child_wht=findViewById(R.id.edt_child_wht);
        edt_adh_mother=findViewById(R.id.edt_adh_mother);
        edt_Mother_Nm_aadhar=findViewById(R.id.edt_Mother_Nm_aadhar);
        child_aadhar_no=findViewById(R.id.child_aadhar_no);
        edt_ben_mob=findViewById(R.id.edt_ben_mob);
        edt_remarks=findViewById(R.id.edt_remarks);
        edt_bank_acc=findViewById(R.id.edt_bank_acc);
        edt_ifsc=findViewById(R.id.edt_ifsc);
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

    public void loadBankList() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Bank_List = dataBaseHelper.getBankLocal();
        Bank_Array = new ArrayList<String>();
        Bank_Array.add("-select-");
        int i = 0;
        for (Bank_Entity financial_year : Bank_List) {
            Bank_Array.add(financial_year.getBank_Nm());
            i++;
        }
        bank_adapter = new ArrayAdapter<>(this, R.layout.dropdowlist, Bank_Array);
        bank_adapter.setDropDownViewResource(R.layout.dropdowlist);
        spn_bank.setAdapter(bank_adapter);

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

    public void loadCategoryList() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Category_List = dataBaseHelper.getCategoryLocal();
        Category_Array = new ArrayList<String>();
        Category_Array.add("-select-");
        int i = 0;
        for (Category_Entity financial_year : Category_List) {
            Category_Array.add(financial_year.getCat_Nm());
            i++;
        }
        catadapter = new ArrayAdapter<>(this, R.layout.dropdowlist, Category_Array);
        catadapter.setDropDownViewResource(R.layout.dropdowlist);
        spn_cat.setAdapter(catadapter);

//        if (getIntent().hasExtra("KeyId")) {
//            spn_yr.setSelection(((ArrayAdapter<String>) spn_yr.getAdapter()).getPosition(_spin_agri_yr));
//        }

    }

    public void loadGenderList() {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        CGender_List = dataBaseHelper.getGenderLocal();
        Gender_Array = new ArrayList<String>();
        Gender_Array.add("-select-");
        int i = 0;
        for (Gender_Entity financial_year : CGender_List) {
            Gender_Array.add(financial_year.getGender_Nm());
            i++;
        }
        genderadapter = new ArrayAdapter<>(this, R.layout.dropdowlist, Gender_Array);
        genderadapter.setDropDownViewResource(R.layout.dropdowlist);
        spn_child_gender.setAdapter(genderadapter);

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

    public void loadPanchayatList(String blockid,String areaid) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Panchayat_List = dataBaseHelper.getPanchayatLocal(blockid,areaid);
        String[] typeNameArray = new String[Panchayat_List.size() + 1];
        typeNameArray[0] = "-प्रखंड चयन करे-";
        int i = 1;
        for (Panchayat_Entity type : Panchayat_List) {
            typeNameArray[i] = type.getPan_Name();
            i++;
        }
        panadapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, typeNameArray);
        panadapter.setDropDownViewResource(R.layout.dropdownlist);
        spn_panchayat.setAdapter(panadapter);
        int setID=0;
        for ( i = 0; i < Panchayat_List.size(); i++) {

            if (Panchayat_List.get(i).getPan_code().equalsIgnoreCase(CommonPref.getUserDetails(EjananiEntryForm.this).getPanchayatCode())) {
                setID = i;
            }
            if(setID!=0) {
                spn_panchayat.setSelection(setID+1);
                spn_panchayat.setEnabled(false);
            }
        }

//        if(getIntent().hasExtra("KeyId"))
//        {
//            spn_block.setSelection(((ArrayAdapter<String>) spn_block.getAdapter()).getPosition(blockspin));
//        }

    }


    public void loadVillageList(String panid) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Village_List = dataBaseHelper.getVillageLocal(panid);
        String[] typeNameArray = new String[Village_List.size() + 1];
        typeNameArray[0] = "-प्रखंड चयन करे-";
        int i = 1;
        for (Village_Entity type : Village_List) {
            typeNameArray[i] = type.getVill_Name();
            i++;
        }
        vill_adapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, typeNameArray);
        vill_adapter.setDropDownViewResource(R.layout.dropdownlist);
        spn_vill.setAdapter(vill_adapter);
        int setID=0;
        for ( i = 0; i < Village_List.size(); i++) {

            if (Village_List.get(i).getVill_code().equalsIgnoreCase(CommonPref.getUserDetails(EjananiEntryForm.this).getPanchayatCode())) {
                setID = i;
            }
            if(setID!=0) {
                spn_vill.setSelection(setID+1);
                spn_vill.setEnabled(false);
            }
        }

//        if(getIntent().hasExtra("KeyId"))
//        {
//            spn_block.setSelection(((ArrayAdapter<String>) spn_block.getAdapter()).getPosition(blockspin));
//        }

    }


    public void loadWardList(String blkid,String panid,String var_area_id) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Ward_List = dataBaseHelper.getWardLocal(blkid,panid,var_area_id);
        String[] typeNameArray = new String[Ward_List.size() + 1];
        typeNameArray[0] = "-प्रखंड चयन करे-";
        int i = 1;
        for (Ward_Entity type : Ward_List) {
            typeNameArray[i] = type.getWARDNAME();
            i++;
        }
        ward_adapter = new ArrayAdapter<String>(this, R.layout.dropdownlist, typeNameArray);
        ward_adapter.setDropDownViewResource(R.layout.dropdownlist);
        spn_ward.setAdapter(ward_adapter);
        int setID=0;
        for ( i = 0; i < Ward_List.size(); i++) {

            if (Ward_List.get(i).getWARDCODE().equalsIgnoreCase(CommonPref.getUserDetails(EjananiEntryForm.this).getPanchayatCode())) {
                setID = i;
            }
            if(setID!=0) {
                spn_ward.setSelection(setID+1);
                spn_ward.setEnabled(false);
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
        String blockcode;

        LoadPanchaytList(String blkcode)
        {
            this.blockcode=blkcode;

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


            return WebServiceHelper.getPqanchayatList(blockcode);

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
                    loadPanchayatList(blockcode,var_area_id);
                    Toast.makeText(EjananiEntryForm.this, "Panchayat List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(EjananiEntryForm.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private class LoadVillageList extends AsyncTask<String, Void, ArrayList<Village_Entity>> {
        String panid;

        LoadVillageList(String pan_id)
        {
            this.panid=pan_id;

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
        protected ArrayList<Village_Entity> doInBackground(String... param) {


            return WebServiceHelper.getVillageList(panid);

        }

        @Override
        protected void onPostExecute(ArrayList<Village_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(EjananiEntryForm.this);


                long i = helper.setVillageList(result,panid);
                if (i > 0) {
                    loadVillageList(panid);
                    Toast.makeText(EjananiEntryForm.this, "Village List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(EjananiEntryForm.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class LoadWardList extends AsyncTask<String, Void, ArrayList<Ward_Entity>> {
        String panid;

        LoadWardList(String pan_id)
        {
            this.panid=pan_id;

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
        protected ArrayList<Ward_Entity> doInBackground(String... param) {


            return WebServiceHelper.getWardList(panid);

        }

        @Override
        protected void onPostExecute(ArrayList<Ward_Entity> result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }

            if (result != null) {
                Log.d("Resultgfg", "" + result);

                DataBaseHelper helper = new DataBaseHelper(EjananiEntryForm.this);


                long i = helper.setWardList(result);
                if (i > 0) {
                    loadWardList(var_block_id,var_pan_id,var_area_id);
                    Toast.makeText(EjananiEntryForm.this, "Ward List Loaded", Toast.LENGTH_SHORT).show();
                } else {

                }

            } else {
                Toast.makeText(EjananiEntryForm.this, "Result:null", Toast.LENGTH_SHORT).show();
            }
        }
    }


    public void checkForEnglish(EditText etxt) {
        if (etxt.getText().length() > 0) {
            String s = etxt.getText().toString();
            if (isInputInEnglish(s)) {
                //OK
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please enter in english.", Toast.LENGTH_SHORT).show();
                etxt.setText("");
            }
        }
    }

    public void checkForHindi(EditText etxt) {
        if (etxt.getText().length() > 0) {
            String s = etxt.getText().toString();
            if (isInputInEnglish(s)) {
                Toast.makeText(EjananiEntryForm.this, "Please enter in Hindi.", Toast.LENGTH_SHORT).show();
                etxt.setText("");
            } else {
                //OK
            }
        }
    }

    public static boolean isInputInEnglish(String txtVal) {

        String regx = "^[a-zA-Z ]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txtVal);
        return matcher.find();
    }
}
