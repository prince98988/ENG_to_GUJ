package com.example.guj_to_eng;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class CategoryAdapter extends FragmentPagerAdapter {


    private Context mContext;
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {  // return fragment depends on position
        if (position == 0) {
            return new into();
        }
        else if (position == 1) {
            return new present();
        } else if (position == 2) {
            return new past();
        } else  {
            return new future();

        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.t0);
        }
        else if (position == 1) {
            return mContext.getString(R.string.t1);
        } else if (position == 2) {
            return mContext.getString(R.string.t2);
        } else {
            return mContext.getString(R.string.t3);
        }

    }
}