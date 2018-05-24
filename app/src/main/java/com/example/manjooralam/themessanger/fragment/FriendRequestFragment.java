package com.example.manjooralam.themessanger.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manjooralam.themessanger.R;
import com.example.manjooralam.themessanger.adapter.FriendRequestPagerAdapter;

/**
 * Created by manjooralam on 10/28/2017.
 */

public class FriendRequestFragment extends Fragment {

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.friend_request_fragment, container, false);

        initViews(view);
        initialPageSetup();
        return view;
    }

    private void initialPageSetup() {

    }

    /**
     * method for initialising views
     * @param view
     */
    private void initViews(View view) {

        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager(ViewPager viewPager) {
        FriendRequestPagerAdapter friendRequestPagerAdapter = new FriendRequestPagerAdapter(getChildFragmentManager());
        friendRequestPagerAdapter.addFrag(new FriendRequestReceivedFragment(), "RECEIVED");
        friendRequestPagerAdapter.addFrag(new FriendRequestSentFragment(), "SENT");
        viewPager.setAdapter(friendRequestPagerAdapter);
    }
}
