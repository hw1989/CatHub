package com.huwei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wei.hu on 2016/1/22.
 */
public class PathListAdapter extends RecyclerView.Adapter<PathListAdapter.ViewHolder> {
    private Context context = null;
    private LayoutInflater inflater = null;
    private ArrayList<File> list=null;
    private FileFilter filter=null;
    public PathListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        list=new ArrayList<>();
        filter=new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if(pathname!=null){
                    if(pathname.isDirectory()){
                        return true;
                    }
                }
                return false;
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void setDataSource(final File file){
        if(file.isDirectory()){
            File[] cache= file.listFiles(filter);
            if(cache!=null){
                if(cache.length>0){
                    list.addAll(Arrays.asList(cache));
                    cache=null;
                    notifyDataSetChanged();
                }
            }
        }
    }
}
