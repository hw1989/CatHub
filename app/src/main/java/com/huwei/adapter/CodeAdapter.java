package com.huwei.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.huwei.activity.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by wei.hu on 2016/1/27.
 */
public class CodeAdapter  extends RecyclerView.Adapter<CodeAdapter.ViewHolder>{
    private ArrayList<SpannableStringBuilder> list=null;
    private Context context=null;
    private LayoutInflater inflater=null;
    private DecimalFormat format=null;
    //设置字体
    private Typeface face=null;
    public CodeAdapter(Context context){
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        format=new DecimalFormat("00000");
        face=Typeface.createFromAsset(context.getAssets(),"DroidSansMono.ttf");
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.code_item_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_linenum.setText(format.format(position));
        SpannableStringBuilder builder=list.get(position);
        if(builder.toString().trim().length()>0){
            holder.tv_linecode.setText(list.get(position));
        }else{
            holder.tv_linecode.setText("");
        }

    }

    @Override
    public int getItemCount() {
        if(list!=null){
            return list.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_linenum=null;
        public TextView tv_linecode=null;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_linenum=(TextView)itemView.findViewById(R.id.tv_code_linenum);
            tv_linecode=(TextView)itemView.findViewById(R.id.tv_code_line);
            tv_linenum.setVisibility(View.GONE);
            if(face!=null){
                tv_linecode.setTypeface(face);
            }
        }
    }
    public void setDataSource(ArrayList<SpannableStringBuilder> list){
        if(list!=null){
            if(list.size()>0){
                this.list=list;
                notifyDataSetChanged();
            }
        }
    }
}
