package bih.nic.in.chatrawasinspection.adapter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import bih.nic.in.chatrawasinspection.database.DataBaseHelper;
import bih.nic.in.chatrawasinspection.entity.StudentList;

/**
 * Created by nicsi on 11/23/2017.
 */
public class Adaptor_Student_ListDetail extends BaseAdapter {
    Context activity;

    ArrayList<StudentList> ThrList=new ArrayList<>();

    public Adaptor_Student_ListDetail(Context context, ArrayList<StudentList> rlist) {
        this.activity=context;
        this.ThrList=rlist;

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
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ViewHolder holder;
//        if (!(row != null)) {
        if (row == null) {
            LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
            row = inflater.inflate(R.layout.child_student_list, parent, false);
            //LayoutInflater mInflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            //row = mInflater.inflate(R.layout.adaptor_facilitypropraty_detail, null);

            holder = new ViewHolder();
            holder.BenId=(TextView)row.findViewById(R.id.txt_Ben_ID);
            holder.BenName=(TextView)row.findViewById(R.id.txt_Ben_Name);
            holder.BenFName=(TextView)row.findViewById(R.id.txt_Ben_FH_Name);
           holder.img_done=(ImageView)row.findViewById(R.id.img_done);
           holder.img_done_cast=(ImageView)row.findViewById(R.id.img_done_cast);


            row.setTag(holder);
        }
        else
            holder = (ViewHolder) row.getTag();

        if(ThrList.size()>0) {

            DisplayMetrics disMetrics = activity.getResources().getDisplayMetrics();
            int ThumbWidth = 0;
            if (disMetrics.widthPixels < disMetrics.heightPixels) {
                ThumbWidth = activity.getResources().getDisplayMetrics().widthPixels / 2;
            }
            else {
                ThumbWidth = activity.getResources().getDisplayMetrics().heightPixels / 2;
            }
            try {

                Log.e("Position/Size",""+position+"---" + ThrList.size());
//                byte[] bloc = ThrList.get(position).getImagefacility();
//                Bitmap bmp = BitmapFactory.decodeByteArray(bloc, 0, bloc.length);
//                holder.Image.setImageBitmap(Utiilties.GenerateThumbnail(bmp,ThumbWidth, ThumbWidth));
//                holder.Image.setScaleType(ImageView.ScaleType.FIT_XY);

                Log.e("INPosition/Size",""+position+"---" + ThrList.size());
                String Benid= ThrList.get(position).getBeneficiaryId();
                String Benname=ThrList.get(position).getBen_NameHN();
                String Benfhname= ThrList.get(position).getBenFH_NameHn();
                holder.BenId.setText(Benid);
                holder.BenName.setText(Benname);
                holder.BenFName.setText(Benfhname);
                if(isphototaken(ThrList.get(position).getBeneficiaryId()).contains("yes"))
                {
                    if(isphototakencast(ThrList.get(position).getBeneficiaryId()).contains("yes"))
                    {
                        holder.img_done_cast.setVisibility(View.VISIBLE);
                    }
                    else {
                        holder.img_done_cast.setVisibility(View.GONE);
                    }
                    holder.img_done.setVisibility(View.VISIBLE);
                }

                else
                {
                    holder.img_done.setVisibility(View.GONE);
                }
               // holder.Date.setText(ThrList.get(position).getEntryDate());

            }
            catch (Exception ex) {
            }
        }
        return row;
    }

    private class ViewHolder {
        TextView BenName,BenId,BenFName;
        ImageView img_done,img_done_cast;
    }
    public String isphototaken(String benid)
    {
        Cursor cur=null;
        String istaken="no";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(activity);
        SQLiteDatabase db;
        db = dataBaseHelper.getReadableDatabase();
        try {

            dataBaseHelper = new DataBaseHelper(activity);
            db = dataBaseHelper.getReadableDatabase();
            cur = db.rawQuery("Select * from InsertStudentPhoto where BenficiaryId='" + benid + "'", null);

            if (cur.moveToNext()) {

                if (!cur.isNull(0)) {
                    istaken="yes";

                }
            }
            cur.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return istaken;
    }

    public String isphototakencast(String benid)
    {
        Cursor cur=null;
        String istaken="no";
        DataBaseHelper dataBaseHelper = new DataBaseHelper(activity);
        SQLiteDatabase db;
        db = dataBaseHelper.getReadableDatabase();
        try {

            dataBaseHelper = new DataBaseHelper(activity);
            db = dataBaseHelper.getReadableDatabase();
            cur = db.rawQuery("Select * from InsertStudentPhoto where photo2 IS NOT NULL and BenficiaryId='" + benid + "'", null);

            if (cur.moveToNext()) {

                if (!cur.isNull(0)) {
                    istaken="yes";
                }
            }
            cur.close();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return istaken;
    }



}

