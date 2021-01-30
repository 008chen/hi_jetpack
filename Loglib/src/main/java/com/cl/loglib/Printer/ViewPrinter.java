package com.cl.loglib.Printer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cl.loglib.LogConfig;
import com.cl.loglib.LogMo;
import com.cl.loglib.LogType;
import com.cl.loglib.R;

import java.util.ArrayList;
import java.util.List;

public class ViewPrinter implements ILogPrinter {

    private RecyclerView recyclerView;
    private LogAdapter logAdapter;
    private ViewPrinterProvider viewProvider;

    public ViewPrinter(Activity activity) {
        FrameLayout rootView = activity.findViewById(android.R.id.content);
        this.recyclerView = new RecyclerView(activity);
        logAdapter = new LogAdapter(LayoutInflater.from(recyclerView.getContext()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(logAdapter);
        viewProvider = new ViewPrinterProvider(rootView,recyclerView);
    }

    public ViewPrinterProvider getViewProvider() {
        return viewProvider;
    }

    @Override
    public void print(@NonNull LogConfig logConfig, int level, String tag, @NonNull String printData) {
        logAdapter.addItem(new LogMo(System.currentTimeMillis(),level,tag,printData));
        recyclerView.smoothScrollToPosition(logAdapter.getItemCount()-1);
    }

    public static class LogAdapter extends RecyclerView.Adapter<LogViewHolder> {
        private LayoutInflater inflater;
        private List<LogMo> logs = new ArrayList<>();

        public LogAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }


        public void addItem(LogMo logMo) {
            logs.add(logMo);
            notifyItemChanged(logs.size() - 1);
        }

        @NonNull
        @Override
        public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = inflater.inflate(R.layout.log_item, parent, false);
            return new LogViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
            LogMo logItem = logs.get(position);
            int color = getHighlightColor(logItem.level);
            holder.tagView.setTextColor(color);
            holder.messageView.setTextColor(color);

            holder.tagView.setText(logItem.getFlattened());
            holder.messageView.setText(logItem.log);
        }

        private int getHighlightColor(int Level) {
            int highLight;
            switch (Level) {
                case LogType.V:
                    highLight = 0xffbbbbbb;
                    break;
                case LogType.D:
                    highLight = 0xffffffff;
                    break;
                case LogType.I:
                    highLight = 0xff6a8759;
                    break;
                case LogType.W:
                    highLight = 0xffbbb529;
                    break;
                case LogType.E:
                    highLight = 0xffff6b68;
                    break;
                default:
                    highLight = 0xffffff00;
                    break;
            }
            return highLight;

        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private static class LogViewHolder extends RecyclerView.ViewHolder {
        TextView tagView;
        TextView messageView;

        public LogViewHolder(@NonNull View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag);
            messageView = itemView.findViewById(R.id.message);
        }
    }
}
