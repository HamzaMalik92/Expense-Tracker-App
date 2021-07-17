package com.hamzamalik.expensetracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

  TextView monthly_spending;
  TextView month;

  RecyclerView spending_recyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // disable dark mode
    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);



     monthly_spending=findViewById(R.id.txt_monthly_spending);
     month=findViewById(R.id.txt_month);



    // fab - navigate to add expense screen
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            Intent intent = new Intent(getApplicationContext(), add_expense.class);
            startActivity(intent);
          }
        });

  }

  @Override
  protected void onResume() {
    super.onResume();
    SharedPreferences sharedPreferences =
            getSharedPreferences("expense_tracker_records", MODE_PRIVATE);
    String itemIDs = sharedPreferences.getString("list", "");
    String id;
    Calendar calendar = Calendar.getInstance();
    int currentMonth = calendar.get(Calendar.MONTH);
    String[] monthName={"January","February","March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};
    month.setText(monthName[currentMonth]);

    Gson gson = new Gson();
    ArrayList<expense_record> expense_records=new ArrayList<>();
    if (!itemIDs.equals("")) {
      ArrayList<Integer> arrayList = gson.fromJson(itemIDs, ArrayList.class);
      ArrayList<expense_record > records=new ArrayList<>();
      System.out.println("arrarylist"+( arrayList));
      Map<String, ?> items = sharedPreferences.getAll();
      int monthlySpending=0;

      for(int i=0;i<arrayList.size();i++){
        String er = sharedPreferences.getString( ( arrayList.get(i)) +"", "");
        if(er!=""){
          expense_record  record= gson.fromJson(er, expense_record.class);
          records.add(record);


          SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
          Date d = null;
          try {
            d = dateFormat.parse(record.getDate());
            System.out.println("d.getMonth()"+d.getMonth());

            if(d!=null&&d.getMonth()==currentMonth){
monthlySpending+=record.getSpending();
System.out.println("record.getSpending();"+record.getSpending());
            }
          } catch (ParseException e) {
            e.printStackTrace();
          }
          System.out.println("monthly"+monthlySpending+"  "+Calendar.MONTH+"  "+currentMonth);


        }
      }
      System.out.println("records");
      System.out.println(records);

      // reference to recycler view

      spending_recyclerView = findViewById(R.id.spending_recycleview);
      spending_recyclerView.setAdapter(new items_spending_adapter(records));
      spending_recyclerView.setLayoutManager(
              new LinearLayoutManager(this)); // show items in list or grid
      monthly_spending.setText(monthlySpending+"");


    }else{
monthly_spending.setText("00.00");

    }
  }
}
