package com.hamzamalik.expensetracker.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hamzamalik.expensetracker.R;
import com.hamzamalik.expensetracker.database.dbHandler;

import java.util.ArrayList;
import java.util.Collections;

public class items_spending_adapter extends RecyclerView.Adapter<items_spending_viewHolder> {
    ArrayList<expense_record> records;

    public items_spending_adapter(ArrayList<expense_record> records) {
        Collections.reverse(records);
        this.records = records;
    }


    @Override
    public items_spending_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create layout programmatically
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.items_spending_viewholder, parent, false);

        return new items_spending_viewHolder(view);
    }

    // reference to each view in recyler view
    @Override
    public void onBindViewHolder(@NonNull items_spending_viewHolder holder, int position) {
        holder.getCatName().setText(records.get(position).getCategory());
        holder.getDate().setText(records.get(position).getDate());
        holder.getSpending().setText("Rs. " + records.get(position).getSpending());

        int img_cat = R.drawable.ic_baseline_account_balance_wallet_24;

        if (records.get(position).getCategory().contains("Food")) {
            img_cat = R.drawable.ic_baseline_fastfood_24;

        } else if (records.get(position).getCategory().contains("Grocery")) {
            img_cat = R.drawable.ic_baseline_shopping_cart_24;
        } else if (records.get(position).getCategory().contains("Taxes")) {
            img_cat = R.drawable.ic_baseline_insert_chart_24;
        } else if (records.get(position).getCategory().contains("Health")) {
            img_cat = R.drawable.baseline_health_and_safety_black_24dp;
        } else if (records.get(position).getCategory().contains("Transport")) {
            img_cat = R.drawable.ic_baseline_emoji_transportation_24;
        } else if (records.get(position).getCategory().contains("Utility")) {
            img_cat = R.drawable.ic_baseline_miscellaneous_services_24;
        } else if (records.get(position).getCategory().contains("Bills")) {
            img_cat = R.drawable.ic_baseline_request_bills;
        } else if (records.get(position).getCategory().contains("Education")) {
            img_cat = R.drawable.ic_baseline_school_24;
        }
        holder.getImageCat().setBackgroundResource(img_cat);
    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public void removeItem(int position, Context context) {

        int recordID = records.get(position).getID();
        dbHandler db = new dbHandler(context);
        db.deleteRecordByID(recordID);
        db.close();

        records.remove(position);

        notifyItemRemoved(position);

    }
}
