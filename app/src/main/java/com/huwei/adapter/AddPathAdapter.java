package com.huwei.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huwei.activity.R;

import java.io.File;

/**
 * Created by wei.hu on 2016/1/22.
 */
public class AddPathAdapter extends RecyclerView.Adapter<AddPathAdapter.ViewHolder>{
    private Context context=null;
    private LayoutInflater inflater=null;
    private File file=null;
    public AddPathAdapter(Context context){
        inflater=LayoutInflater.from(context);
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.addproject_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //防止同一对象出现，原对象修改
        File file=new File(this.file.getAbsolutePath());
        int i=0;
        while (file!=null){
            if(position==i){
                holder.tv_path.setText(file.getName());
                break;
            }
            i++;
            file=file.getParentFile();
        }
    }

    @Override
    public int getItemCount() {
        int i=0;
        if(this.file!=null){
            //防止同一对象出现，原对象修改
            File file=new File(this.file.getAbsolutePath());
            while (file!=null){
                i++;
                file=file.getParentFile();
            }
        }
        return i;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_path=null;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_path=(TextView)itemView.findViewById(R.id.tv_addpath_root);
        }
    }
    public void setDataSource(File file){
        if(file!=null){
            this.file=file;
            notifyDataSetChanged();
        }
    }
}
