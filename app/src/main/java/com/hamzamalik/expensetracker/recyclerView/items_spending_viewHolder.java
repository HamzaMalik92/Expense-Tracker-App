package com.hamzamalik.expensetracker.recyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzamalik.expensetracker.R;

import org.w3c.dom.Text;

public class items_spending_viewHolder extends RecyclerView.ViewHolder {

    private final TextView catName;
    private final ImageView imageCat;
    private final TextView spending;
    private final TextView date;

    public items_spending_viewHolder(View itemView) {
        super(itemView);
        catName = itemView.findViewById(R.id.txt_catName);
        imageCat = itemView.findViewById(R.id.img_cat);
        spending = itemView.findViewById(R.id.txt_spending);
        date = itemView.findViewById(R.id.txt_date);

    }


    public ImageView getImageCat() {
        return imageCat;
    }

    public TextView getSpending() {
        return spending;
    }

    public TextView getDate() {
        return date;
    }

    public TextView getCatName() {
        return catName;
    }
}
