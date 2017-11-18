package com.braingalore.ashwasunfitness.fragments;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.braingalore.ashwasunfitness.MainActivity;
import com.braingalore.ashwasunfitness.R;
import com.braingalore.ashwasunfitness.adapters.DashboardItemAdapter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ajitha3008 on 11/11/17.
 */

public class DashBoardFragment extends Fragment {
    GridView gridView;
    DashboardItemAdapter adapter;
    ArrayList<String> menuNames;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.dashboard_fragment, null);
        menuNames = new ArrayList<>();
        menuNames.add(getString(R.string.videos));
        menuNames.add(getString(R.string.website));
        gridView = (GridView) v.findViewById(R.id.dashboard_items);
        adapter = new DashboardItemAdapter(getActivity(), getFilePaths(), gridView);
        gridView.setAdapter(adapter);
        ((MainActivity)getActivity()).disableBackInSupportActionBar();
        return v;
    }

    private ArrayList<String> getFilePaths() {
        ArrayList<String> uris = new ArrayList<>();
        AssetManager assetManager = getActivity().getAssets();
        try {
            String[] files = assetManager.list("dashboard_menu_images");

            for (String strImageName : files) {
                String pathAssets = "dashboard_menu_images" + File.separator + strImageName;
                uris.add(pathAssets);

            }
        } catch (Exception e) {
            //FirebaseCrash.report(new Exception("Exception while getting asset URIs" + e));
        }
        return uris;
    }
}