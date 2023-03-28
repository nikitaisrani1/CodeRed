package com.righttickk.CodeRed.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.righttickk.CodeRed.Model.Logs;
import com.righttickk.CodeRed.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    private Context mContext;
    private List<Logs> mLogs;

    public LogAdapter(Context mContext, List<Logs> mLogs) {
        this.mContext = mContext;
        this.mLogs = mLogs;
    }

    @NonNull
    @Override
    public LogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.log_items,parent,false);
        return new LogAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapter.ViewHolder holder, int position) {
        Logs logs = mLogs.get(position);
        holder.date.setText(logs.getLastDate());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.dateView);
        }
    }
}
