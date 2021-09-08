package com.example.pizzadiroma;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.ViewHolder> {
    ContentValues contentValues;
    private String[] mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecycleViewAdapter(Context context, String[] data, ContentValues contentValues_from_other_side) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.contentValues = contentValues_from_other_side;
    }

    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_menu, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.p_name.setText(this.mData[position]);
        if (position == 1) {
            holder.p_name.setText("" + contentValues.get("name"));
            holder.p_weight.setText("" + contentValues.get("weight"));
            holder.p_price.setText("" + contentValues.get("price"));
//            holder.p_img.setText("" + contentValues.get("img"));
        }
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        TextView p_price;
        TextView p_name;
        TextView p_weight;
        ImageView p_img;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            p_name = itemView.findViewById(R.id.product_name);
            p_price = itemView.findViewById(R.id.product_price);
            p_weight = itemView.findViewById(R.id.product_weight);
            p_img = itemView.findViewById(R.id.product_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData[id];
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
