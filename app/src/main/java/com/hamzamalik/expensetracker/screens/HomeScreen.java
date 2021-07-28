package com.hamzamalik.expensetracker.screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hamzamalik.expensetracker.R;
import com.hamzamalik.expensetracker.database.dbHandler;
import com.hamzamalik.expensetracker.recyclerView.expense_record;
import com.hamzamalik.expensetracker.recyclerView.items_spending_adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class HomeScreen extends AppCompatActivity {

    TextView txt_monthly_spending;
    TextView txt_month_name;

    int monthlySpending;
    int currentMonthNo;

    RecyclerView spending_recyclerView;
    items_spending_adapter items_spendingAdapter;
    String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};
    ArrayList<expense_record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        txt_monthly_spending = findViewById(R.id.txt_monthly_spending);
        txt_month_name = findViewById(R.id.txt_month);

        Calendar calendar = Calendar.getInstance();
        currentMonthNo = calendar.get(Calendar.MONTH);

        txt_month_name.setText(monthName[currentMonthNo]);  // set current month in textView

        // fab - navigate to add expense screen
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(), addExpenseScreen.class);
                        startActivity(intent);
                    }
                });

    }

    @Override
    protected void onResume() {
        super.onResume();

        // using database to get records
        dbHandler db = new dbHandler(getApplicationContext());

        records = db.fetchAllRecords();

        if (db.getCount() > 0) {

            findViewById(R.id.txt_add_daily_spending).setVisibility(View.GONE);
            findViewById(R.id.spending_recycleView).setVisibility(View.VISIBLE);

            // reference to recycler view
            spending_recyclerView = findViewById(R.id.spending_recycleView);
            items_spendingAdapter = new items_spending_adapter(records);
            spending_recyclerView.setAdapter(items_spendingAdapter);

            spending_recyclerView.setLayoutManager(
                    new LinearLayoutManager(this)); // show items in list or grid

            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(spending_recyclerView);
            db.close();

            setMonthlySpending();

        } else {
            findViewById(R.id.txt_add_daily_spending).setVisibility(View.VISIBLE);
            findViewById(R.id.spending_recycleView).setVisibility(View.GONE);
        }

    }

    void setMonthlySpending() {

        dbHandler db = new dbHandler(getApplicationContext());

        records = db.fetchAllRecords();

        if (db.getCount() > 0) {

            findViewById(R.id.txt_add_daily_spending).setVisibility(View.GONE);
            findViewById(R.id.spending_recycleView).setVisibility(View.VISIBLE);

            monthlySpending = 0;

            for (int i = 0; i < records.size(); i++) {

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date d = null;
                try {
                    d = dateFormat.parse(records.get(i).getDate());
                    System.out.println("HAmza0" + d.getMonth() + " # " + currentMonthNo);

                    if (d != null && d.getMonth() == currentMonthNo) {
                        monthlySpending += records.get(i).getSpending();

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
            txt_monthly_spending.setText(monthlySpending + ""); // set spending in textView

        } else {

            findViewById(R.id.txt_add_daily_spending).setVisibility(View.VISIBLE);
            findViewById(R.id.spending_recycleView).setVisibility(View.GONE);
            txt_monthly_spending.setText("00.00");

        }
        db.close();
    }

    // to remove record when user swipe it to Left
    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {

            items_spendingAdapter.removeItem(viewHolder.getAdapterPosition(), getApplicationContext());

            setMonthlySpending();


        }
    };

}
