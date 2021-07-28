package com.hamzamalik.expensetracker.screens;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.hamzamalik.expensetracker.R;
import com.hamzamalik.expensetracker.database.dbHandler;
import com.hamzamalik.expensetracker.recyclerView.expense_record;

import java.util.Calendar;

public class addExpenseScreen extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

  EditText editDate;
  EditText editTextExpense;
  DatePickerDialog datePickerDialog;
  boolean isCatSelected = false;
  String selectedCatName = "";

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_expense_screen);


    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle("Expense Tracker");

    editTextExpense = findViewById(R.id.editTextMoney);
    editDate = findViewById(R.id.date_picker_dialog);

    // set date
    editDate.setOnClickListener(v -> datePickerDialog.show());

    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    datePickerDialog = new DatePickerDialog(addExpenseScreen.this, this, year, month, day);
    datePickerDialog.setCancelable(true);

    FloatingActionButton floatingActionButton = findViewById(R.id.favbtn_add_expense);
    floatingActionButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if (editTextExpense.getText().toString().length() == 0) {
              Toast.makeText(addExpenseScreen.this, "Enter expenses", Toast.LENGTH_SHORT).show();
            } else if (editDate.getText().toString().length() == 0) {
              Toast.makeText(addExpenseScreen.this, "Date is required", Toast.LENGTH_SHORT).show();
            } else if (!isCatSelected) {
              Toast.makeText(addExpenseScreen.this, "Select Category", Toast.LENGTH_SHORT).show();
            } else {

              // using database to store a new spending record

              dbHandler db=new dbHandler(getApplicationContext());
              int id=db.getLastID()+1;

              expense_record record=new expense_record(id,Integer.parseInt(editTextExpense.getText().toString()),selectedCatName,editDate.getText().toString());

              db.addRecord(record);
              db.close();

              // go back to home screen
                finish();



            }
          }
        });
  }

  @Override
  public boolean onOptionsItemSelected( MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      this.finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
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
