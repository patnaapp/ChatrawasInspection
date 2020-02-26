package bih.nic.in.chatrawasinspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.entity.BenfiList;
import bih.nic.in.chatrawasinspection.entity.HostelList;

public class AdaptorBenefiList extends BaseAdapter {
    Context activity;

    ArrayList<BenfiList> HosList=new ArrayList<>();

    public AdaptorBenefiList(Context context, ArrayList<BenfiList> hlist) {
        this.activity=context;
        this.HosList=hlist;

    }

    @Override
    public int getCount() {
        return HosList.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;
        if (row == null) {
            LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
            row = inflater.inflate(R.layout.adaptor_beneficiarylist, parent, false);


            holder = new ViewHolder();
            holder.bid=(TextView)row.findViewById(R.id.bid);
            holder.bname=(TextView)row.findViewById(R.id.b_name);
            holder.bfname=(TextView)row.findViewById(R.id.bfname);
/*
            holder.BenName=(TextView)row.findViewById(R.id.txt_Ben_Name);
           holder.BenFName=(TextView)row.findViewById(R.id.txt_Ben_FH_Name);
*/



            row.setTag(holder);
        }
        else
            holder = (ViewHolder) row.getTag();

        if(HosList.size()>0) {

            DisplayMetrics disMetrics = activity.getResources().getDisplayMetrics();
            int ThumbWidth = 0;
            if (disMetrics.widthPixels < disMetrics.heightPixels) {
                ThumbWidth = activity.getResources().getDisplayMetrics().widthPixels / 2;
            }
            else {
                ThumbWidth = activity.getResources().getDisplayMetrics().heightPixels / 2;
            }
            try {

                Log.e("INPosition/Size",""+position+"---" + HosList.size());
                String HostName= HosList.get(position).getBen_NameHN();
                // String Benname=HosList.get(position).getBen_Name();
                //String Benfhname= HosList.get(position).getBenFH_Name();
                holder.bid.setText(HostName);
                //holder.BenName.setText(Benname);
                //holder.BenFName.setText(Benfhname);
                // holder.Date.setText(ThrList.get(position).getEntryDate());

            }
            catch (Exception ex) {
            }
        }
        return row;
    }

    private class ViewHolder {
        TextView bid,bname,bfname;
    }


}

