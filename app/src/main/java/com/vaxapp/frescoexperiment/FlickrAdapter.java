package com.vaxapp.frescoexperiment;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by valeria on 2/11/15.
 */
public class FlickrAdapter extends RecyclerView.Adapter<FlickrAdapter.ViewHolder> {

    private final List<FlickrPhoto> urls;

    public FlickrAdapter(List<FlickrPhoto> myDataset) {
        this.urls = myDataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FlickrPhoto photo = urls.get(position);
        String url = photo.getUrl();
        if (!TextUtils.isEmpty(url)) {
            Log.d("onBindViewHolder", "url : " + url);
            Uri uri = Uri.parse(url);
            holder.imageView.setImageURI(uri);
            holder.textView.setText(photo.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return urls == null ? 0 : urls.size();
    }

    public void clear() {
        if(urls != null){
            urls.clear();
            notifyDataSetChanged();
        }
    }

    public void addAll(List<FlickrPhoto> photos) {
        urls.addAll(photos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final SimpleDraweeView imageView;
        final TextView textView;

        public ViewHolder(View v) {
            super(v);
            imageView = (SimpleDraweeView) v.findViewById(R.id.image_view);
            textView = (TextView) v.findViewById(R.id.tv_title);
        }
    }
}
