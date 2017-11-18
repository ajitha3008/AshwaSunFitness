package com.braingalore.ashwasunfitness.adapters;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Service;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.braingalore.ashwasunfitness.fragments.YouTubeFragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import com.braingalore.ashwasunfitness.R;

import java.util.ArrayList;

public class DashboardItemAdapter extends BaseAdapter {

    LayoutInflater vi;

    Context context;

    ArrayList<String> griRowItems;

    ViewHolder holder = null;

    ImageLoader imageLoader;

    DisplayImageOptions options;

    public DashboardItemAdapter(Context context, ArrayList<String> uris, GridView gridView) {
        this.context = context;
        this.griRowItems = uris;
        vi = (LayoutInflater) context
                .getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        imageLoader = ImageLoader.getInstance();

        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .displayer(new FadeInBitmapDisplayer(500)) //fade in images
                .resetViewBeforeLoading()
                .build();

        this.imageLoader.init(new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(options).build());
        boolean pauseOnScroll = false; // or true
        boolean pauseOnFling = true; // or false
        PauseOnScrollListener listener = new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling);
        gridView.setOnScrollListener(listener);
    }

    @Override
    public int getCount() {
        return griRowItems.size();
    }

    @Override
    public Object getItem(int i) {
        return griRowItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = vi.inflate(R.layout.dashboard_grid_item, parent,
                    false);
            holder = new ViewHolder();
        }
        holder.imageView = (ImageView) convertView.findViewById(R.id.img);
        holder.caption = (TextView) convertView.findViewById(R.id.caption);
        // holder.progressBar = (ProgressBar) convertView
        // .findViewById(R.id.progress);
        holder.caption.setText(getName(griRowItems.get(position)));
        holder.imageView.setOnClickListener(new OnImageClickListener(position));

        String tempStr = "assets://" + griRowItems.get(position);
        lazyLoading(imageLoader, tempStr, holder.imageView,
                options);
        return convertView;
    }

    private String getName(String uri) {
        if (uri.contains("ic_language")) {
            return context.getString(R.string.website);
        }
        if (uri.contains("ic_video_library")) {
            return context.getString(R.string.videos);
        } else {
            return "default";
        }
    }

    class OnImageClickListener implements View.OnClickListener {

        int _postion;

        // constructor
        public OnImageClickListener(int position) {
            this._postion = position;
        }

        @Override
        public void onClick(View v) {
            if (griRowItems.get(_postion).contains("ic_language")) {
                try {
                    Uri webpage = Uri.parse("https://ashwa700.wixsite.com/imfit");
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                    context.startActivity(myIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(context, "No application can handle this request. Please install a web browser or check your URL", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            } else if (griRowItems.get(_postion).contains("ic_video_library")) {
                FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                YouTubeFragment youtubeFragment = new YouTubeFragment();
                fragmentTransaction.replace(R.id.fragment_container, youtubeFragment, "youtube");
                fragmentTransaction.commit();
            }
        }
    }

    static class ViewHolder {
        ImageView imageView;
        // ProgressBar progressBar;
        TextView caption;
        // DisplayImageOptions options;
    }

    public static void lazyLoading(ImageLoader imageLoader, String tempStr, ImageView imageView, DisplayImageOptions options) {
        imageLoader.displayImage(tempStr, imageView, options,
                new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    }
                });
    }
}