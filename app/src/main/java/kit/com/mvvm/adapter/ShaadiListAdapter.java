package kit.com.mvvm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import kit.com.mvvm.MyApplication;
import kit.com.mvvm.R;
import kit.com.mvvm.database.modal.Shaadi;

public class ShaadiListAdapter extends RecyclerView.Adapter<ShaadiListAdapter.ShaadiViewHolder> {
    private Context mContext;

    private List<Shaadi> shaadiList;

    public ShaadiListAdapter(Context mContext, List<Shaadi> shaadiList) {
        this.mContext = mContext;
        this.shaadiList = shaadiList;
    }

    @NonNull
    @Override
    public ShaadiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.shaadi_card_view, parent, false);
        return new ShaadiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShaadiViewHolder holder, int position) {

        final Shaadi item = shaadiList.get(position);

        holder.mName.setText(item.getName().getTitle() +
                " " + item.getName().getFirst() + " " + item.getName().getLast());
        holder.mAddress1.setText(item.getDob().getAge() + ", "+ item.getGender());
        holder.mAddress2.setText(item.getLocation().getStreet().getName() +", "+
                item.getLocation().getPostcode()+", "+
                item.getLocation().getCity() +", "+
                item.getLocation().getState() +
                ", " + item.getLocation().getCountry());
        String url = item.getPicture().getLarge();
        holder.imgProfile.setImageURI(url);



        if(!item.isAccepted() && !item.isDeclined()){
            holder.mStatus.setVisibility(View.GONE);
            holder.buttonLayout.setVisibility(View.VISIBLE);
        }else {
            holder.mStatus.setVisibility(View.VISIBLE);
            holder.buttonLayout.setVisibility(View.GONE);
        }

        if(item.isDeclined()){
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,android.R.color.holo_red_dark));
            holder.mStatus.setText("The profile was Declined");
        }else if(item.isAccepted()){
            holder.mStatus.setTextColor(ContextCompat.getColor(mContext,android.R.color.holo_green_light));
            holder.mStatus.setText("The profile was Accepted");
        }

        holder.mBtnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setAccepted(true);
                MyApplication.getDatabseManager().update(true,false,item.getEmail());
            }
        });

        holder.mBtnDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item.setDeclined(true);
                MyApplication.getDatabseManager().update(false,true,item.getEmail());
            }
        });

    }

    @Override
    public int getItemCount() {
        return shaadiList.size();
    }

    class ShaadiViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView imgProfile;
        TextView mName, mAddress1, mAddress2,mStatus;
        FloatingActionButton mBtnDecline, mBtnAccept;
        LinearLayout buttonLayout;

        public ShaadiViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProfile = itemView.findViewById(R.id.img_profile);
            mName = itemView.findViewById(R.id.user_name_value);
            mAddress1 = itemView.findViewById(R.id.address1);
            mAddress2 = itemView.findViewById(R.id.address2);
            mBtnDecline = itemView.findViewById(R.id.btn_decline);
            mBtnAccept = itemView.findViewById(R.id.btn_accept);
            buttonLayout=itemView.findViewById(R.id.button_layouts);
            mStatus=itemView.findViewById(R.id.txt_status);
        }
    }
}
