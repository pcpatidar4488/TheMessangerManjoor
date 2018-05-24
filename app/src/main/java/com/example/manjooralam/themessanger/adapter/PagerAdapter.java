package com.example.manjooralam.themessanger.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.manjooralam.themessanger.fragment.ChatFragment;
import com.example.manjooralam.themessanger.fragment.FriendRequestFragment;
import com.example.manjooralam.themessanger.fragment.FriendsFragment;
import com.example.manjooralam.themessanger.fragment.ChatListFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0:
                FriendsFragment tab1 = new FriendsFragment();
                return tab1;
            case 1:
                ChatFragment tab2 = new ChatFragment();
                return tab2;
            case 2:
                FriendRequestFragment tab3 = new FriendRequestFragment();
                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}