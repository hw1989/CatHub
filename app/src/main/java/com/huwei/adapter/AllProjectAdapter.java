package com.huwei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.huwei.activity.AllFileActivity;
import com.huwei.activity.CodeActivity;
import com.huwei.activity.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by wei.hu on 2016/1/27.
 */
public class AllProjectAdapter  extends RecyclerView.Adapter<AllProjectAdapter.ViewHolder>{
    private Context context = null;
    private LayoutInflater inflater = null;
    private ArrayList<File> list=null;
    public AllProjectAdapter(Context context){
        this.context=context;
        inflater = LayoutInflater.from(context);
        list=new ArrayList<>();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.allfile_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        File file=list.get(position);
        if(file.isDirectory()){
            holder.tv_name.setText(file.getName());
            holder.iv_icon.setImageResource(R.drawable.fdm);
        }else{
            holder.tv_name.setText(file.getName());
            holder.iv_icon.setImageResource(R.drawable.fdf);
        }
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView iv_icon=null;
        public TextView  tv_name=null;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_icon=(ImageView)itemView.findViewById(R.id.iv_all_file_icon);
            tv_name=(TextView)itemView.findViewById(R.id.tv_all_file_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=(int)v.getTag();
            File file=list.get(position);
            if(file.isDirectory()){

            }else{
                AllFileActivity activity=(AllFileActivity)(AllProjectAdapter.this.context);
                Intent intent=new Intent(activity, CodeActivity.class);
                intent.putExtra("code",file.getName());
                activity.startActivity(intent);
            }
        }
    }
    public void setDataSource(File file){
        if(file!=null){
            if(file.exists()&&file.isDirectory()){
                this.list=new ArrayList<>();
                this.list.addAll(Arrays.asList(file.listFiles()));
                notifyDataSetChanged();
            }
        }
    }
}
