package com.huwei.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huwei.activity.MainActivity;
import com.huwei.activity.ProjectInfoActivity;
import com.huwei.activity.R;
import com.huwei.entity.ProjectEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by wei.hu on 2016/1/21.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private ArrayList<ProjectEntity> list=null;
    private Context context=null;
    private LayoutInflater inflater=null;
    private SimpleDateFormat format=null;
    public MainAdapter(Context context) {
        this.context=context;
        inflater=LayoutInflater.from(context);
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        view=inflater.inflate(R.layout.main_item1_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        ProjectEntity entity=list.get(position);
        holder.tv_project_name.setText(entity.getProjectname());
        Date date=new Date();
        date.setTime(entity.getCreattime());
        holder.tv_project_time.setText(format.format(date));
        holder.tv_project_describ.setText(entity.getProjectdescrib());
        if(entity.getType()==ProjectEntity.Type.C.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_c);
        }else if(entity.getType()==ProjectEntity.Type.CPP.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_cpp);
        }else if(entity.getType()==ProjectEntity.Type.CSharp.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_csharp);
        }else if(entity.getType()==ProjectEntity.Type.CSS.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_css);
        }else if(entity.getType()==ProjectEntity.Type.Java.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_java);
        }else if(entity.getType()==ProjectEntity.Type.JavaScript.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_js);
        }else if(entity.getType()==ProjectEntity.Type.Html.getValue()){
            holder.iv_project_type.setImageResource(R.drawable.project_html);
        }else{
           holder.iv_project_type.setImageResource(R.drawable.project_unknow);
        }
    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView iv_project_type=null;
        public TextView tv_project_name=null;
        public TextView tv_project_time=null;
        public TextView tv_project_describ=null;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_project_type=(ImageView)itemView.findViewById(R.id.iv_project_type);
            tv_project_name=(TextView)itemView.findViewById(R.id.tv_project_name);
            tv_project_time=(TextView)itemView.findViewById(R.id.tv_project_time);
            tv_project_describ=(TextView)itemView.findViewById(R.id.tv_project_describ);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position=(int)v.getTag();
            MainActivity activity=(MainActivity)MainAdapter.this.context;
            Intent intent=new Intent(activity, ProjectInfoActivity.class);
            intent.putExtra("project",list.get(position));
            activity.startActivity(intent);
        }
    }
    public void setDataSource(ArrayList<ProjectEntity> list){
        if(list!=null){
            if(list.size()>0){
                this.list=list;
                notifyDataSetChanged();
            }
        }
    }
}
