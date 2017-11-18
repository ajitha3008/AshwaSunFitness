package com.braingalore.ashwasunfitness.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.braingalore.ashwasunfitness.MainActivity;
import com.braingalore.ashwasunfitness.R;
import com.braingalore.ashwasunfitness.banner.SlidingImageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ajitha3008 on 11/11/17.
 */

public class HomeFragment extends Fragment {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES = {R.drawable.boy, R.drawable.girl};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    String about = "<html><body style=\"text-align:justify\">Ashwath, a graduate in Engineering, has worked with major IT giants close to a decade. His greatest passion also is in Fitness and Nutrition. He realized at a young age that being healthy is the ultimate way of leading fruitful life.<br>" +
            "<br>" +
            "As a stepping stone to the realization of his dreams and passion, he pursued and acquired international certificates like Nutritionist and Wellness advisor. He has also gained expertise as a Fitness, Functional and Pilates Trainer. Ashwath has served hundreds of people to achieve their fitness quotient and lead Healthy life.<br>" +
            "<br>" +
            "After all, Health is our greatest wealth. Let us attain it, come what may!<br>" +
            "<br>" +
            "Ashwath is ready to help you to accomplish your Fitness goals. Since he carries a Vision with him - Let\'s make \"IT\" FIT!! He has launched a YouTube channel \"AshwaSun Fitness\" exclusively for working professionals. Let\'s all join our hands and get Transformed.<br>" +
            "<br>" +
            "Please do Like, Share & Subscribe to the channel and get the most out of it :-)</body></Html>";
    private WebView aboutView;
    String htmlText = " %s ";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /** Inflating the layout for this fragment **/
        View v = inflater.inflate(R.layout.home_fragment, null);
        for (int i = 0; i < IMAGES.length; i++) {
            ImagesArray.add(IMAGES[i]);
        }
        ((MainActivity)getActivity()).disableBackInSupportActionBar();
        aboutView = (WebView) v.findViewById(R.id.webView_about);
        aboutView.loadData(String.format(htmlText, about), "text/html", "utf-8");
        mPager = (ViewPager) v.findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(getActivity(), ImagesArray));
        CirclePageIndicator indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5 * density);
        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

        return v;
    }

}
