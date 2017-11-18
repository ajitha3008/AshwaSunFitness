package com.braingalore.ashwasunfitness.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.braingalore.ashwasunfitness.MainActivity;
import com.braingalore.ashwasunfitness.R;
import com.braingalore.ashwasunfitness.utils.CallingUtils;

/**
 * Created by ajitha3008 on 11/11/17.
 */

public class YouTubeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.youtube_channel, null);
        ((MainActivity)getActivity()).enableBackInSupportActionBar();
        return v;
    }
}