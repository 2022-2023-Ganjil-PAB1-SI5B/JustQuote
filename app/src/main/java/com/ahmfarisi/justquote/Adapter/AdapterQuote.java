package com.ahmfarisi.justquote.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmfarisi.justquote.Model.QuoteModel;
import com.ahmfarisi.justquote.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterQuote extends RecyclerView.Adapter<AdapterQuote.ViewHolder>{
    private List<QuoteModel> listQuote = new ArrayList<>();
    private Context ctx;

    public AdapterQuote(List<QuoteModel> listQuote, Context ctx) {
        this.listQuote = listQuote;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(ctx).inflate(R.layout.item_quote, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuoteModel QM = listQuote.get(position);
        holder.tvText.setText(QM.getText());
        holder.tvAuthor.setText(QM.getAuthor());
    }

    @Override
    public int getItemCount() {
        return listQuote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvText, tvAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
            tvAuthor = itemView.findViewById(R.id.tv_author);
        }
    }

}
