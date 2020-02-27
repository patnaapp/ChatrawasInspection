package bih.nic.in.chatrawasinspection.activty;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.adapter.EjananiEditAdaptor;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.EjananiEntryDetail;

public class EjananiEditFormActivity extends AppCompatActivity {

    ListView listView;
    LinearLayout ll_panchayat;
    EjananiEditAdaptor adaptor_showedit_listDetail;
    ProgressDialog dialog;
    DataBaseHelper dataBaseHelper;
    ArrayList<EjananiEntryDetail> data;
    String listid;
    TextView tv_Norecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejanani_edit_form);

        listView=(ListView)findViewById(R.id.listviewshow);
        tv_Norecord=(TextView) findViewById(R.id.tv_Norecord);

        dataBaseHelper = new DataBaseHelper(this);
        dialog=new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Loading...");
        setReportListViewData();

        if(data.size()> 0){
            //updateTitle();
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.invalidate();
            //((DraftAdapter)dataListView.getAdapter()).notifyDataSetChanged();
            adaptor_showedit_listDetail = new EjananiEditAdaptor(this, data);
            adaptor_showedit_listDetail.dataBaseHelper = dataBaseHelper;
            listView.setAdapter(adaptor_showedit_listDetail);
        }else{
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }

    private void setReportListViewData()
    {
        data=dataBaseHelper.getEjananiEntryData();
        adaptor_showedit_listDetail =new EjananiEditAdaptor(this,data);
        listView.setAdapter(adaptor_showedit_listDetail);
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(data.size()> 0){
            //tv_title.setText("जल संरचनाओं का सत्यापन/सर्वेक्षण  ("+data.size()+")");
            tv_Norecord.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            listView.invalidate();
            //((DraftAdapter)dataListView.getAdapter()).notifyDataSetChanged();
            adaptor_showedit_listDetail = new EjananiEditAdaptor(this, data);
            listView.setAdapter(adaptor_showedit_listDetail);
        }else{
            listView.setVisibility(View.GONE);
            tv_Norecord.setVisibility(View.VISIBLE);
        }
    }
}
