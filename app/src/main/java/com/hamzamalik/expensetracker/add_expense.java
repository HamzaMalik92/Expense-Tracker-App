package com.hamzamalik.expensetracker;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Timer;

public class add_expense extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
  //  // create / open database
  //  SQLiteDatabase expense_tracker_db =
  //      openOrCreateDatabase("Expense Tracker Db", MODE_PRIVATE, null);

  EditText editDate;
  EditText editTextExpense;
  DatePickerDialog datePickerDialog;
  boolean isCatSelected = false;
  String selectedCatName = "";
  record_per_month record = new record_per_month();

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_expense);

    editTextExpense = findViewById(R.id.editTextMoney);
    editDate = findViewById(R.id.date_picker_dialog);

    // set date
    editDate.setOnClickListener(v -> datePickerDialog.show());

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    datePickerDialog = new DatePickerDialog(add_expense.this, this, year, month, day);
    datePickerDialog.setCancelable(true);

    FloatingActionButton floatingActionButton = findViewById(R.id.favbtn_add_expense);
    floatingActionButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (editTextExpense.getText().toString().length() == 0) {
              Toast.makeText(add_expense.this, "Enter expenses", Toast.LENGTH_SHORT).show();
            } else if (editDate.getText().toString().length() == 0) {
              Toast.makeText(add_expense.this, "Date is required", Toast.LENGTH_SHORT).show();
            } else if (!isCatSelected) {
              Toast.makeText(add_expense.this, "Select Category", Toast.LENGTH_SHORT).show();
            } else {

              //              Toast.makeText(
              //                      add_expense.this,
              //                      "Expense : "
              //                          + expenseRecord.getSpending()
              //                          + " Date : "
              //                          + expenseRecord.getDate()
              //                          + " Category : "
              //                          + expenseRecord.getCategory(),
              //                      Toast.LENGTH_SHORT)
              //                  .show();

              System.out.println("record.record_list_length()" + record.record_list_length());

              // convert User object user to JSON format
              Gson gson = new Gson();

              //
              // Storing data into SharedPreferences
              SharedPreferences sharedPreferences =
                  getSharedPreferences("expense_tracker_records", MODE_PRIVATE);

              // Creating an Editor object to edit(write to the file)
              SharedPreferences.Editor myEdit = sharedPreferences.edit();

              // Storing the key and its value as the data fetched from edittext
              String itemIDs = sharedPreferences.getString("list", "");
              System.out.println("ItemIDs" + itemIDs);

              String id;

              if (!itemIDs.equals("")) {
                ArrayList<Integer> arrayList = gson.fromJson(itemIDs, ArrayList.class);
                arrayList.add(arrayList.size()+1);
                id = arrayList.size()+ "";
                myEdit.putString("list", gson.toJson(arrayList));
              } else {
                ArrayList<Integer> arrayList = new ArrayList<>();
                arrayList.add(1);
                myEdit.putString("list", gson.toJson(arrayList));
                id = 1 + "";
              }
              expense_record expenseRecord =
                  new expense_record(
                      new Integer(id),
                      new Integer(editTextExpense.getText().toString()),
                      selectedCatName,
                      editDate.getText().toString());
              String er_json = gson.toJson(expenseRecord);
              myEdit.putString(new Double(id)+"", er_json);

              // store in SharedPreferences

              // Once the changes have been made,
              // we need to commit to apply those changes made,
              // otherwise, it will throw an error
              myEdit.commit();


              // go back to home screen
                finish();



            }
          }
        });
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    month++;
    String date = dayOfMonth + "/" + month + "/" + year;
    editDate.setText(date);
  }

  public void selectCategory(View view) {
    int cat_id = view.getId();
    CardView cardView;
    cardView = findViewById(cat_id);
    if (cat_id == R.id.card_food) {

      if (selectedCatName != "Food") {
        selectedCatName = "Food";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_food);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_grocery) {
      if (selectedCatName != "Grocery") {
        selectedCatName = "Grocery";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_grocery);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_taxes) {
      if (selectedCatName != "Taxes") {
        selectedCatName = "Taxes";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_taxes);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_health) {
      if (selectedCatName != "Health") {
        selectedCatName = "Health";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_health);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_transport) {
      if (selectedCatName != "Transport") {
        selectedCatName = "Transport";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_transport);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_utility) {
      if (selectedCatName != "Utility") {
        selectedCatName = "Utility";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_utility);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_bills) {
      if (selectedCatName != "Bills") {
        selectedCatName = "Bills";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_bills);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_edu) {
      if (selectedCatName != "Education") {
        selectedCatName = "Education";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_edu);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
    if (cat_id == R.id.card_others) {
      if (selectedCatName != "Others") {
        selectedCatName = "Others";
        isCatSelected = true;
        cardView.setCardBackgroundColor(Color.parseColor("#FFFF4444"));
        cardView.setCardElevation(10);
      } else {
        selectedCatName = "";
        isCatSelected = false;
        cardView.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
        cardView.setCardElevation(3);
      }

    } else {
      CardView cardView_temp = findViewById(R.id.card_others);
      cardView_temp.setCardBackgroundColor(Color.parseColor("#FF33B5E5"));
      cardView_temp.setCardElevation(3);
    }
  }
}
