package bih.nic.in.chatrawasinspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.entity.HostalPhoto;
import bih.nic.in.chatrawasinspection.entity.StudentList;
import bih.nic.in.chatrawasinspection.utility.Utiilties;

/**
 * Created by nicsi on 11/23/2017.
 */
public class Adaptor_Hostel_EdidListDetail extends BaseAdapter {
    Context activity;

    ArrayList<HostalPhoto> HostelList=new ArrayList<>();

    public Adaptor_Hostel_EdidListDetail(Context context, ArrayList<HostalPhoto> rlist) {
        this.activity=context;
        this.HostelList=rlist;

    }

    @Override
    public int getCount() {
        return HostelList.size();
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
//        if (!(row != null)) {
        if (row == null) {
            LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
            row = inflater.inflate(R.layout.child_hostaledit_list, parent, false);
            //LayoutInflater mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //row = mInflater.inflate(R.layout.adaptor_facilitypropraty_detail, null);

            holder = new ViewHolder();
            holder.Hostel=(TextView)row.findViewById(R.id.txt_Hostel);
            //holder.Dept=(TextView)row.findViewById(R.id.txt_dept);
           //holder.Hostel=(TextView)row.findViewById(R.id.txt_Hostel);c
           holder.Image=(ImageView)row.findViewById(R.id.list_hostImg);


            row.setTag(holder);
        }
        else
            holder = (ViewHolder) row.getTag();

        if(HostelList.size()>0) {

            DisplayMetrics disMetrics = activity.getResources().getDisplayMetrics();
            int ThumbWidth = 0;
            if (disMetrics.widthPixels < disMetrics.heightPixels) {
                ThumbWidth = activity.getResources().getDisplayMetrics().widthPixels / 2;
            }
            else {
                ThumbWidth = activity.getResources().getDisplayMetrics().heightPixels / 2;
            }
            try {

                Log.e("Position/Size",""+position+"---" + HostelList.size());
                byte[] bloc = HostelList.get(position).getPhotoByte1();
               Bitmap bmp = BitmapFactory.decodeByteArray(bloc, 0, bloc.length);
                holder.Image.setImageBitmap(Utiilties.GenerateThumbnail(bmp,ThumbWidth, ThumbWidth));
                holder.Image.setScaleType(ImageView.ScaleType.FIT_XY);

                Log.e("INPosition/Size",""+position+"---" + HostelList.size());
                //String Dist= HostelList.get(position).getBeneficiaryId();
                //String Dept=HostelList.get(position).getBen_Name();
                String Hostel= HostelList.get(position).getHostName();
               // holder.Dist.setText(Dist);
                //holder.Dept.setText(Dept);
                holder.Hostel.setText(Hostel);
               //holder.Date.setText(ThrList.get(position).getEntryDate());

            }
            catch (Exception ex) {
            }
        }
        return row;
    }

    private class ViewHolder {
        TextView Hostel;
        ImageView Image;
    }


}

