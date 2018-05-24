package com.example.manjooralam.themessanger.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.manjooralam.themessanger.R;
import com.example.manjooralam.themessanger.activity.AllUsersActivity;
import com.example.manjooralam.themessanger.activity.ChatActivity;
import com.example.manjooralam.themessanger.activity.MainActivity;
import com.example.manjooralam.themessanger.activity.OtherUserProfileActivity;
import com.example.manjooralam.themessanger.activity.SettingsActivity;
import com.example.manjooralam.themessanger.model.FriendsModel;
import com.example.manjooralam.themessanger.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.manjooralam.themessanger.R.id.rootLayout;

/**
 * Created by manjooralam on 10/21/2017.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private MainActivity mActivity;
    ArrayList<String> friendsList;
    ArrayList<FriendsModel> friendsModelsList;
    private FirebaseDatabase mFirebaseDatabase;

    public FriendsAdapter(MainActivity mActivity, ArrayList<String> friendsList) {

        this.mActivity = mActivity;
        this.friendsList = friendsList;
        this.mFirebaseDatabase = FirebaseDatabase.getInstance();
        friendsModelsList = new ArrayList<>();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_all_users, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        mFirebaseDatabase.getReference().child("Users").child(friendsList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FriendsModel friendData = new FriendsModel();
                friendData.image = dataSnapshot.child("thumb_image").getValue().toString();
                friendData.name = dataSnapshot.child("name").getValue().toString();
                friendsModelsList.add(friendData);

                holder.tvName.setText(dataSnapshot.child("name").getValue().toString());
                holder.tvStatus.setText(dataSnapshot.child("status").getValue().toString());
                if(dataSnapshot.child("online").getValue().equals("true")){
                    holder.ivOnlineStatus.setVisibility(View.VISIBLE);
                }else {
                    holder.ivOnlineStatus.setVisibility(View.INVISIBLE);
                }
                if (!dataSnapshot.child("thumb_image").getValue().toString().equals("default"))
                    Glide.with(mActivity).load(dataSnapshot.child("thumb_image").getValue().toString()).centerCrop().into(holder.ivProfilePic);

                holder.rootLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent chatActivityIntent = new Intent(mActivity, ChatActivity.class);
                        chatActivityIntent.putExtra("from", "FriendsFragment");
                        chatActivityIntent.putExtra("chat_user_name", friendsModelsList.get(position).name);
                        chatActivityIntent.putExtra("chat_user_image", friendsModelsList.get(position).image);
                        chatActivityIntent.putExtra("chat_user_id", friendsList.get(position));
                        mActivity.startActivity(chatActivityIntent);

                    }
                });
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





       /* holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(allUsersActivity, OtherUserProfileActivity.class);
                intent.putExtra("other_user_id",userList.get(position).user_id);
                intent.putExtra("other_user_name",userList.get(position).name);
                intent.putExtra("other_status",userList.get(position).status);
                intent.putExtra("other_image",userList.get(position).image);
                allUsersActivity.startActivity(intent);
            }
        });
*/

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvStatus;
        private ImageView ivProfilePic, ivOnlineStatus;
        private RelativeLayout rootLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvStatus = itemView.findViewById(R.id.tv_status);
            tvName = itemView.findViewById(R.id.tv_name);
            ivProfilePic = itemView.findViewById(R.id.civ_profile_pic);
            rootLayout = itemView.findViewById(R.id.root_layout);
            ivOnlineStatus = itemView.findViewById(R.id.civ_online_status);


        }
    }
}
