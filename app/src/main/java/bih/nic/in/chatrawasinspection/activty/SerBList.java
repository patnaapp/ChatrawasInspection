package bih.nic.in.chatrawasinspection.activty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

import bih.nic.in.chatrawasinspection.R;
import bih.nic.in.chatrawasinspection.entity.BenfiList;

public class SerBList extends RecyclerView.Adapter<SerBList.ViewHolder> {


    Context context;
    View view1;
    ViewHolder viewHolder1;
    TextView textView;
    ArrayList<BenfiList> ListItem = new ArrayList<>();
    private PopupWindow mPopupWindow;


    public SerBList(Context context1, ArrayList<BenfiList> SubjectValues1) {

        ListItem = SubjectValues1;
        context = context1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView  bid,bname,bfname;

        TextView level;

        public ViewHolder(View v) {

            super(v);

            bid = (TextView) v.findViewById(R.id.bid);
            bname = (TextView) v.findViewById(R.id.b_name);
            bfname = (TextView) v.findViewById(R.id.bfname);




        }
    }

    @Override
    public SerBList.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view1 = LayoutInflater.from(context).inflate(R.layout.adaptor_beneficiarylist, parent, false);

        viewHolder1 = new ViewHolder(view1);
        return viewHolder1;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bid.setText(ListItem.get(position).getBeneficiaryId());
        holder.bname.setText(ListItem.get(position).getBen_Name());
       


    }


    @Override
    public int getItemCount() {

        return ListItem.size();
    }
}
