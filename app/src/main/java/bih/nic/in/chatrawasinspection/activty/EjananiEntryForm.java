package bih.nic.in.chatrawasinspection.activty;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import bih.nic.in.chatrawasinspection.entity.EjananiEntryDetail;
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
    Button btn_Save;
    Integer keyid ;
    boolean edit;
    String UserId="";
    ArrayList<EjananiEntryDetail> EntryList = new ArrayList<>();
    String _spin_fin_yr="",_spin_bank="",districtspin="",_spin_area="",_spin_cat="",_spin_gender="",blockspin="",_spin_panchayat="",_spin_village="",_spin_ward="";




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

        try {

            keyid = getIntent().getExtras().getInt("KeyId");
            String isEdit = "";
            isEdit = getIntent().getExtras().getString("isEdit");
            Log.d("kvfrgv", "" + keyid + "" + isEdit);
            if (keyid > 0 && isEdit.equals("Yes")) {
                edit = true;
                ShowEditEntry(keyid);

            }

        } catch (Exception e) {
            Log.e("EXP", e.getLocalizedMessage());
            e.printStackTrace();
        }



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
        btn_Save=findViewById(R.id.btn_Save);
        edt_ifsc.addTextChangedListener(inputTextWatcher);
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

        if (getIntent().hasExtra("KeyId")) {
            spn_yr.setSelection(((ArrayAdapter<String>) spn_yr.getAdapter()).getPosition(_spin_fin_yr));
        }

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

        if (getIntent().hasExtra("KeyId")) {
            spn_bank.setSelection(((ArrayAdapter<String>) spn_bank.getAdapter()).getPosition(_spin_bank));
        }

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

        if(getIntent().hasExtra("KeyId"))
        {
            spn_dist.setSelection(((ArrayAdapter<String>) spn_dist.getAdapter()).getPosition(districtspin));
        }

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

        if (getIntent().hasExtra("KeyId")) {
            spn_areatype.setSelection(((ArrayAdapter<String>) spn_areatype.getAdapter()).getPosition(_spin_area));
        }

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

        if (getIntent().hasExtra("KeyId")) {
            spn_cat.setSelection(((ArrayAdapter<String>) spn_cat.getAdapter()).getPosition(_spin_cat));
        }

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

        if (getIntent().hasExtra("KeyId")) {
            spn_child_gender.setSelection(((ArrayAdapter<String>) spn_child_gender.getAdapter()).getPosition(_spin_gender));
        }

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

        if(getIntent().hasExtra("KeyId"))
        {
            spn_block.setSelection(((ArrayAdapter<String>) spn_block.getAdapter()).getPosition(blockspin));
        }

    }

    public void loadPanchayatList(String blockid,String areaid) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Panchayat_List = dataBaseHelper.getPanchayatLocal(blockid,areaid);
        String[] typeNameArray = new String[Panchayat_List.size() + 1];
        typeNameArray[0] = "-पंचायत चयन करे-";
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

        if(getIntent().hasExtra("KeyId"))
        {
            spn_panchayat.setSelection(((ArrayAdapter<String>) spn_panchayat.getAdapter()).getPosition(_spin_panchayat));
        }

    }


    public void loadVillageList(String panid) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Village_List = dataBaseHelper.getVillageLocal(panid);
        String[] typeNameArray = new String[Village_List.size() + 1];
        typeNameArray[0] = "-गाँव चयन करे-";
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

        if(getIntent().hasExtra("KeyId"))
        {
            spn_vill.setSelection(((ArrayAdapter<String>) spn_vill.getAdapter()).getPosition(_spin_village));
        }

    }


    public void loadWardList(String blkid,String panid,String var_area_id) {
        dataBaseHelper = new DataBaseHelper(EjananiEntryForm.this);

        Ward_List = dataBaseHelper.getWardLocal(blkid,panid,var_area_id);
        String[] typeNameArray = new String[Ward_List.size() + 1];
        typeNameArray[0] = "-वार्ड चयन करे-";
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

        if(getIntent().hasExtra("KeyId"))
        {
            spn_ward.setSelection(((ArrayAdapter<String>) spn_ward.getAdapter()).getPosition(_spin_ward));
        }

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



    private String Validate() {

        String isvalid = "yes";

        if ((spn_yr != null && spn_yr.getSelectedItem() != null)) {
            if ((String) spn_yr.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select financial year.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }

        if ((spn_dist != null && spn_dist.getSelectedItem() != null)) {
            if ((String) spn_dist.getSelectedItem() != "-चुनें-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select district.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }
        if ((spn_block != null && spn_block.getSelectedItem() != null)) {
            if ((String) spn_block.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select block.", Toast.LENGTH_LONG).show();
                return "no";
            }
        }

        if ((spn_areatype != null && spn_areatype.getSelectedItem() != null)) {
            if ((String) spn_areatype.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select area type.", Toast.LENGTH_LONG).show();
                return "no";
            }
        }

        if ((spn_panchayat != null && spn_panchayat.getSelectedItem() != null)) {
            if ((String) spn_panchayat.getSelectedItem() != "-पंचायत चयन करे-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select Panchayat.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }
        if ((spn_vill != null && spn_vill.getSelectedItem() != null)) {
            if ((String) spn_vill.getSelectedItem() != "-गाँव चयन करे-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select village.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }
        if ((spn_ward != null && spn_ward.getSelectedItem() != null)) {
            if ((String) spn_ward.getSelectedItem() != "-वार्ड चयन करे-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select ward.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }

        if ((spn_cat != null && spn_cat.getSelectedItem() != null)) {
            if ((String) spn_cat.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select category.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }

        if ((spn_child_gender != null && spn_child_gender.getSelectedItem() != null)) {
            if ((String) spn_child_gender.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select gender.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }

        if ((spn_bank != null && spn_bank.getSelectedItem() != null)) {
            if ((String) spn_bank.getSelectedItem() != "-select-") {
                isvalid = "yes";
            } else {
                Toast.makeText(EjananiEntryForm.this, "Please select bank.", Toast.LENGTH_LONG).show();

                return "no";
            }
        }


        if (edt_childname.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter child name in english.", Toast.LENGTH_LONG).show();
            edt_childname.requestFocus();
            return "no";
        }

        if (edt_childname_hindi.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter child name in hindi.", Toast.LENGTH_LONG).show();
            edt_childname_hindi.requestFocus();
            return "no";
        }

        if (edt_fanme_eng.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter father name in eng.", Toast.LENGTH_LONG).show();
            edt_fanme_eng.requestFocus();
            return "no";
        }

        if (edt_fname_hindi.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter father name in hindi.", Toast.LENGTH_LONG).show();
            edt_fname_hindi.requestFocus();
            return "no";
        }
        if (edt_mothname_eng.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter mother name in english.", Toast.LENGTH_LONG).show();
            edt_mothname_eng.requestFocus();
            return "no";
        }

        if (edt_mothname_hindi.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter mother name in hindi.", Toast.LENGTH_LONG).show();
            edt_mothname_hindi.requestFocus();
            return "no";
        }
        if (edt_nochild.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter child number.", Toast.LENGTH_LONG).show();
            edt_nochild.requestFocus();
            return "no";
        }

        if (edt_child_wht.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter child wheight.", Toast.LENGTH_LONG).show();
            edt_child_wht.requestFocus();
            return "no";
        }


        if (edt_adh_mother.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter mothers aadhaar no.", Toast.LENGTH_LONG).show();
            edt_adh_mother.requestFocus();
            return "no";
        }


        if (edt_Mother_Nm_aadhar.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter mothers name as per aadhaar.", Toast.LENGTH_LONG).show();
            edt_Mother_Nm_aadhar.requestFocus();
            return "no";
        }

        if (child_aadhar_no.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter child aadhaar number.", Toast.LENGTH_LONG).show();
            child_aadhar_no.requestFocus();
            return "no";
        }
        if (edt_ben_mob.getText().toString().trim().length() < 10) {
            Toast.makeText(EjananiEntryForm.this, "Please enter valid mobile number.", Toast.LENGTH_LONG).show();
            edt_ben_mob.requestFocus();
            return "no";
        }
        if (edt_remarks.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter remarks.", Toast.LENGTH_LONG).show();
            edt_remarks.requestFocus();
            return "no";
        }
        if (edt_bank_acc.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter account no.", Toast.LENGTH_LONG).show();
            edt_bank_acc.requestFocus();
            return "no";
        }
        if (edt_ifsc.getText().toString().trim().length() <= 0) {
            Toast.makeText(EjananiEntryForm.this, "Please enter ifsc code.", Toast.LENGTH_LONG).show();
            edt_ifsc.requestFocus();
            return "no";
        }

        return isvalid;

    }
    private TextWatcher inputTextWatcher = new TextWatcher() {

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (edt_ifsc.getText().toString().length() == 11) {
                String ifsc=edt_ifsc.getText().toString();
                if (isIfscCodeValid(ifsc)==true){
                    edt_ifsc.setTextColor(Color.parseColor("#00FF00"));
                    btn_Save.setEnabled(true);

                }
                else {
                    edt_ifsc.setTextColor(Color.parseColor("#ff0000"));
                    btn_Save.setEnabled(false);
                }


            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static boolean isIfscCodeValid(String email)
    {
        String regExp = "^[A-Z]{4}[0][A-Z0-9]{6}$";
        boolean isvalid = false;

        if (email.length() > 0) {
            isvalid = email.matches(regExp);
        }
        return isvalid;
    }


    public void ShowEditEntry(Integer keyid) {
        DataBaseHelper helper = new DataBaseHelper(EjananiEntryForm.this);
        UserId = CommonPref.getUserDetails(getApplicationContext()).getUserID();
        // Log.d("valjhhhhhhhues",""+UserId+"%"+keyid);
        //EntryList = helper.getAllEntryById(UserId, keyid);
        Intent intent=getIntent();
        EjananiEntryDetail entryinfo;
        entryinfo =(EjananiEntryDetail) intent.getSerializableExtra("data");

        ArrayList<EjananiEntryDetail> basicInfo = EntryList;
        btn_Save.setText("UPDATE");

        edt_childname.setText(entryinfo.getBabyNameEng());
        edt_childname_hindi.setText(entryinfo.getBabyNameHindi());
        edt_fanme_eng.setText(entryinfo.getFatherNameEng());
        edt_fname_hindi.setText(entryinfo.getFatherNameHindi());
        edt_mothname_eng.setText(entryinfo.getMotherNameEng());
        edt_mothname_hindi.setText(entryinfo.getMotherNameHindi());
        edt_nochild.setText(entryinfo.getJanamLiyeSisuKiSankhya());
        edt_child_wht.setText(entryinfo.getBabyWeight());
        edt_adh_mother.setText(entryinfo.getMotherAadgharNo());

        child_aadhar_no.setText(entryinfo.getBabyAadharNo());
        edt_ben_mob.setText(entryinfo.getMobileNo());
        edt_remarks.setText(entryinfo.getRemark());
        edt_bank_acc.setText(entryinfo.getBankAccountNo());
        edt_ifsc.setText(entryinfo.getIfscCode());

        _spin_fin_yr=entryinfo.getFinancialYearName();
        _spin_bank=entryinfo.getBankName();
        districtspin=entryinfo.getDistrictName();
        _spin_area=entryinfo.getAreaTypeName();
        _spin_cat=entryinfo.getCategoryName();
        _spin_gender=entryinfo.getBabyGenderName();
        blockspin=entryinfo.getBlockName();
        _spin_panchayat=entryinfo.getPanchayatName();
        _spin_village=entryinfo.getVillageName();
        _spin_ward=entryinfo.getWardName();
        loadFinancialYear();
        loadBankList();
        loadDistrictList();
        loadAreaTypeList();
        loadCategoryList();
        loadGenderList();
        loadBlockList(entryinfo.getDistrictCode());
        loadPanchayatList(entryinfo.getDistrictCode(),entryinfo.getAreaTypeId());
        loadVillageList(entryinfo.getPanchayatCode());
        loadWardList(entryinfo.getBlockCode(),entryinfo.getPanchayatCode(),entryinfo.getAreaTypeId());

//        typeofland = basicInfo.get(0).getType_of_land_Name();
//        orderofexperiment = basicInfo.get(0).getOrder_of_experiment();
//        whethercondition = basicInfo.get(0).getWeather_condition_during_crop_season();
//        weatherconditionname = basicInfo.get(0).getWeatherconditionname();
//        sourceofseed = basicInfo.get(0).getSource_of_seed();
//        sourceofseed_name = basicInfo.get(0).getSourceofseed_name();
//        typeofmanure_name = basicInfo.get(0).getTypeofmanure_name();
//        cultivation_name = basicInfo.get(0).getSystemofcultivation_name();
//        varitiescrop_name = basicInfo.get(0).getVaritiesofcrop_name();
//        typeofmanure = basicInfo.get(0).getType_of_manure();
//        varityofcrop = basicInfo.get(0).getVarities_of_crop();
//        subvarity_ofcrop = basicInfo.get(0).getSub_varitiesofcrop_name();
//        systemofcultivation = basicInfo.get(0).getSystem_of_cutivation();
//        unitoftheareacovgcrop = basicInfo.get(0).getUnitareaCoverage();
//
//
//        var_surveyNo = basicInfo.get(0).getSurvey_no_khesra_no();
//
//        agrival = basicInfo.get(0).getAgri_year_nm();
//        loadFinancialYear();
//        //   addItemsOnSpinner();
//        addUnitAreaCoverageCrop();
//        addUnitOperationalSizeHolding();
//
//        cropval = dataBaseHelper.getNameFor("CropType", "Crop_Id", "Crop_Name", basicInfo.get(0).getCrop());
//
//        seasonval = dataBaseHelper.getNameFor("Season", "season_id", "season_name", basicInfo.get(0).getSeason());
//
//        loadSeason();
//        loadFinancialYear();
//        st_spn_season_id = basicInfo.get(0).getSeason();
//        st_spn_croptyp = basicInfo.get(0).getCrop();
//        st_spn_panchayat_code = basicInfo.get(0).getPanchayat();
//        loadPanchayatData(PanchayatList);
//
//        addUnitOperationalSizeHolding();
//        addSubTypeVaritiesOfCrop();
//        // addUnitOperationalSizeHolding();
//        addUnitAreaCoverageCrop();
//        loadCRopVarities();
//        loadCutivationSpinner();
//        loadManureType();
//        loadSourceOfSeed();
//        loadWeatherCondition();
//        //addItemsOnSpinner();
//        loadLandType();
//
//
//        if (getIntent().hasExtra("KeyId")) {
//            spn_subtype_of_varities_of_crop.setSelection((((ArrayAdapter<String>) spn_subtype_of_varities_of_crop.getAdapter()).getPosition(basicInfo.get(0).getSub_varitiesofcrop_name())));
//        }
//
//        if (getIntent().hasExtra("KeyId")) {
//            spn_order_of_experiment.setSelection((((ArrayAdapter<String>) spn_order_of_experiment.getAdapter()).getPosition(basicInfo.get(0).getOrder_of_experiment())));
//        }


    }
}
