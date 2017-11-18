package com.braingalore.ashwasunfitness.fragments;

import android.app.Fragment;
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

public class ConnectFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.connect_fragment, null);
        ((MainActivity)getActivity()).disableBackInSupportActionBar();
        ImageView facebook = (ImageView) v.findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.facebook.com/Ashwasunfit/");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                getActivity().startActivity(myIntent);
            }
        });
        ImageView twitter = (ImageView) v.findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://twitter.com/ashwasun");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                getActivity().startActivity(myIntent);
            }
        });
        ImageView youtube = (ImageView) v.findViewById(R.id.youtube);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://www.youtube.com/channel/UCa_Rnu28ckweS7vv2sY1PDQ/about");
                Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                getActivity().startActivity(myIntent);
            }
        });
        ImageView mail = (ImageView) v.findViewById(R.id.mail);
        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setData(Uri.fromParts("mailto", "ashwasunfitness@gmail.com", null));
                intent.putExtra(Intent.EXTRA_SUBJECT, "AshwaSun:: Contact Us");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    getActivity().startActivity(intent);
                }
            }
        });
        ImageView phone = (ImageView) v.findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberToDial = "+917406977007";
                CallingUtils.dialIntent(getActivity(), numberToDial, view);
            }
        });
        return v;
    }
}