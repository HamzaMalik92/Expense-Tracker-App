package com.hamzamalik.expensetracker;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class tiems_spending_viewholder extends RecyclerView.ViewHolder {
    private TextView catName;
private ImageView imageCat;

    public ImageView getImageCat() {
        return imageCat;
    }

    public TextView getCatName() {
        return catName;
    }

    public TextView getSpending() {
        return spending;
    }

    public TextView getDate() {
        return date;
    }

    private TextView spending;
    private TextView date;
    public tiems_spending_viewholder(View itemView) {
        super(itemView);
        catName=itemView.findViewById(R.id.txt_catName);
        imageCat=itemView.findViewById(R.id.img_cat);
        spending=itemView.findViewById(R.id.txt_spending);
        date=itemView.findViewById(R.id.txt_date);

    }



    public TextView get_catName() {
        return catName;
    }
}
