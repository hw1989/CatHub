package com.huwei.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huwei.activity.AddProjectActivity;
import com.huwei.activity.NewProjectActivity;
import com.huwei.activity.R;

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
        this.context=context;
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
        View view=inflater.inflate(R.layout.pathlist_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        File file=list.get(position);
        holder.tv_name.setText(file.getAbsoluteFile().getName());
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView tv_name=null;
        public ImageView iv_arrow=null;
        public ViewHolder(View itemView) {
            super(itemView);
//            View view1=itemView.findViewWithTag("name_"+getAdapterPosition());
//            View view2=itemView.findViewWithTag("img_"+getAdapterPosition());
//            if(view1==null){
                tv_name=(TextView)itemView.findViewById(R.id.tv_path_name);
                tv_name.setTag("name_"+getLayoutPosition());
//            }else{
//                tv_name=(TextView)view1;
//            }
//            if(view2==null){
                iv_arrow=(ImageView)itemView.findViewById(R.id.iv_path_arrow);
                iv_arrow.setTag("img_"+getLayoutPosition());
//            }else{
//                iv_arrow=(ImageView)view2;
//            }
//            int p1=getPosition();
//            int p2=getLayoutPosition();
//            int p3=getAdapterPosition();
//            itemView.setTag(getPosition());
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            AddProjectActivity activity=(AddProjectActivity)PathListAdapter.this.context;
            Bundle bundle=new Bundle();
            int position=(Integer)v.getTag();
            bundle.putString("path", list.get(position).getAbsolutePath());
            Intent intent=new Intent(activity, NewProjectActivity.class);
            intent.putExtras(bundle);
            activity.startActivity(intent);
            activity.finish();
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
