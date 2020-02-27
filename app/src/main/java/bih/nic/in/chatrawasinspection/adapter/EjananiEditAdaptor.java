package bih.nic.in.chatrawasinspection.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.activty.EjananiEditFormActivity;
import bih.nic.in.chatrawasinspection.activty.EjananiEntryForm;
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.EjananiEntryDetail;
import bih.nic.in.chatrawasinspection.entity.UserDetails;
import bih.nic.in.chatrawasinspection.utility.Utiilties;
import bih.nic.in.chatrawasinspection.webservice.WebServiceHelper;


public class EjananiEditAdaptor extends BaseAdapter {

    public DataBaseHelper dataBaseHelper;

    UserDetails userInfo;

    Activity activity;
    LayoutInflater mInflater;

    ArrayList<EjananiEntryDetail> ThrList=new ArrayList<>();

    public EjananiEditAdaptor(EjananiEditFormActivity listViewshowedit, ArrayList<EjananiEntryDetail> rlist) {
        this.activity=listViewshowedit;
        this.ThrList=rlist;
        mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        this.userInfo = userInfo;

    }

    @Override
    public int getCount() {
        return ThrList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        //if (convertView == null) {
        convertView = mInflater.inflate(R.layout.adaptor_ejanani_edit, null);

        holder = new ViewHolder();
        holder.tv_financial_year=(TextView)convertView.findViewById(R.id.tv_financial_year);
        holder.tv_yojna_name=(TextView)convertView.findViewById(R.id.tv_yojna_name);
        holder.tv_baby_name=(TextView)convertView.findViewById(R.id.tv_baby_name);
        holder.tv_baby_weight=(TextView)convertView.findViewById(R.id.tv_baby_weight);
        holder.tv_father_name=(TextView)convertView.findViewById(R.id.tv_father_name);
        holder.tv_mother_name=(TextView)convertView.findViewById(R.id.tv_mother_name);

        holder.btn_remove=(Button) convertView.findViewById(R.id.btn_remove);
        holder.btn_edit=(Button)convertView.findViewById(R.id.btn_edit);
        holder.btn_upload=(Button)convertView.findViewById(R.id.btn_upload);

        convertView.setTag(holder);

        holder.tv_financial_year.setText(ThrList.get(position).getFinancialYearName());
        holder.tv_yojna_name.setText(ThrList.get(position).getYojnaName());
        holder.tv_baby_name.setText(ThrList.get(position).getBabyNameEng());
        holder.tv_baby_weight.setText(ThrList.get(position).getBabyWeight());
        holder.tv_father_name.setText(ThrList.get(position).getFatherNameEng());
        holder.tv_mother_name.setText(ThrList.get(position).getMotherNameEng());

        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(activity)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.confirmation)
                        .setMessage("क्या आप डाटा हटाना चाहते है?")
                        .setCancelable(false)
                        .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dataBaseHelper = new DataBaseHelper(activity);
                                String eid = String.valueOf(ThrList.get(position).getId());
                                long c = dataBaseHelper.deleteEjananiEntryData(eid);

                                if(c>0)
                                {
                                    Toast.makeText(activity, "Deleted Successfully",Toast.LENGTH_SHORT).show();
                                    ThrList.remove(position);
                                    notifyDataSetChanged();
                                }
                                else
                                {
                                    Toast.makeText(activity, "Failed to delete",Toast.LENGTH_SHORT).show();

                                }
                            }
                        })
                        .setNegativeButton("नहीं", null)
                        .show();


            }
        });

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, EjananiEntryForm.class);
                EjananiEntryDetail info = ThrList.get(position);
                i.putExtra("data", info);
                activity.startActivity(i);
            }
        });

        holder.btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(activity)
                        .setIcon(R.mipmap.ic_launcher)
                        .setTitle(R.string.confirmation)
                        .setMessage("क्या आप डाटा अपलोड करना चाहते है?")
                        .setCancelable(false)
                        .setPositiveButton("हाँ", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (Utiilties.isOnline(activity)) {
                                    EjananiEntryDetail info = ThrList.get(position);

                                    new UploadEjananiUpdatedDetail(info, position).execute();
                                }
                                else {

                                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("नहीं", null)
                        .show();


            }
        });

//        }
//        else {
//            holder = (PondEditAdaptor.ViewHolder) convertView.getTag();
//        }
        return convertView;
    }

    private class ViewHolder {
        TextView tv_financial_year,tv_yojna_name,tv_baby_name,tv_baby_weight,tv_father_name,tv_mother_name;
        Button btn_remove,btn_edit,btn_upload;

    }

    private class UploadEjananiUpdatedDetail extends AsyncTask<String, Void, String> {
        EjananiEntryDetail data;
        Integer position;
        private final ProgressDialog dialog = new ProgressDialog(activity);

        UploadEjananiUpdatedDetail(EjananiEntryDetail data, Integer position) {
            this.data = data;
            this.position = position;
        }

        @Override
        protected void onPreExecute() {

            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.setMessage("अपलोड हो राहा है...");
            this.dialog.show();
        }

        @Override
        protected String doInBackground(String... param) {

            String res = null;

            //String res = WebServiceHelper.uploadSanrachanDetail(this.data);
            return res;

        }

        @Override
        protected void onPostExecute(String result) {
            if (this.dialog.isShowing()) {
                this.dialog.dismiss();
            }
            Log.d("Responsevalue",""+result);

            if (result != null) {
                if (result.equalsIgnoreCase("1")) {
                    DataBaseHelper localDBHelper = new DataBaseHelper(activity);
                    // long c = placeData.deleterowconLab(userid);
                    long isDel = localDBHelper.deleteEjananiEntryData(String.valueOf(this.data.getId()));
                    if (isDel > 0) {
                        Log.e("messagdelete", "Data deleted !!");
                        ThrList.remove(position);
                        notifyDataSetChanged();
                    } else {
                        Log.e("message", "data is uploaded but not deleted !!");
                    }

                    Toast.makeText(activity, "प्रेषण हो गया", Toast.LENGTH_SHORT).show();
                    chk_msg("डेटा अपलोड हो गया", "डेटा अपलोड हो गया");
                }
                else  if (result.equalsIgnoreCase("0")) {
                    Toast.makeText(activity, "प्रेषण फेल !", Toast.LENGTH_SHORT).show();
                }

            }
            else {

                Toast.makeText(activity, "null record", Toast.LENGTH_SHORT).show();
            }



        }
    }

    public void chk_msg(String title, String msg) {
        // final String wantToUpdate;
        AlertDialog.Builder ab = new AlertDialog.Builder(activity);
        ab.setCancelable(false);
        ab.setIcon(R.mipmap.ic_launcher);
        ab.setTitle(title);
        //ab.setMessage(msg);
        Dialog dialog = new Dialog(activity);
        dialog.setCanceledOnTouchOutside(false);
        ab.setPositiveButton("ओके", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

                dialog.dismiss();

            }
        });

        ab.show();
    }

}
