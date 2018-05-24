package com.example.manjooralam.themessanger.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.manjooralam.themessanger.R;
import com.example.manjooralam.themessanger.activity.CreateGroupActivity;
import com.example.manjooralam.themessanger.model.FriendsModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by manjooralam on 10/31/2017.
 */

public class AddGroupListFriendAdapter extends RecyclerView.Adapter<AddGroupListFriendAdapter.MyViewHolders> {
    private CreateGroupActivity mActivity;
    ArrayList<FriendsModel> friendsModelsList;
    private FirebaseDatabase mFirebaseDatabase;
    private RecyclerView addFriend;
   // public CircleImageView ivDone,ivOk;

    public AddGroupListFriendAdapter(CreateGroupActivity mActivity, ArrayList<FriendsModel> friendsModelsList) {

        this.mActivity = mActivity;
        this.friendsModelsList = friendsModelsList;
    }

    @Override
    public AddGroupListFriendAdapter.MyViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_all_users, parent, false);
        return new AddGroupListFriendAdapter.MyViewHolders(view);

    }

    @Override
    public void onBindViewHolder(final AddGroupListFriendAdapter.MyViewHolders holder, final int position) {

         if(friendsModelsList.get(position).selected){
             holder.ivOk.setVisibility(View.VISIBLE);
         }else {
             holder.ivOk.setVisibility(View.INVISIBLE);
         }
                holder.tvName.setText(friendsModelsList.get(position).name);
                if (!friendsModelsList.get(position).thumb_image.equals("default"))
                    Glide.with(mActivity).load(friendsModelsList.get(position).thumb_image).centerCrop().into(holder.ivProfilePic);

                holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!friendsModelsList.get(position).selected) {
                           friendsModelsList.get(position).selected = true;
                            holder.ivOk.setVisibility(View.VISIBLE);
                        }else {
                            friendsModelsList.get(position).selected = false;
                            holder.ivOk.setVisibility(View.INVISIBLE);
                        }

                        ((CreateGroupActivity)mActivity).setupAddedFriendAdapter();
                    }
                });
    }

    @Override
    public int getItemCount() {
        return this.friendsModelsList.size();
    }

    public class MyViewHolders extends RecyclerView.ViewHolder {
        private TextView tvName;

        private ImageView ivProfilePic,ivOk;
        private RelativeLayout rootLayout;
        public MyViewHolders(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            ivProfilePic = itemView.findViewById(R.id.civ_profile_pic);
            ivOk = itemView.findViewById(R.id.iv_ok);
            rootLayout = itemView.findViewById(R.id.root_layout);
        }
    }
}
