package com.hamzamalik.expensetracker;

import java.util.ArrayList;

public class expense_record {
    private int itemNo;
    private int spending;
    private String category;
    private String date;

    expense_record(int itemNo, int spending, String category, String date) {
        this.itemNo = itemNo;
        this.spending = spending;
        this.category = category;
        this.date = date;

    }

    int getItemNo() {
        return itemNo;
    }

    int getSpending() {
        return spending;
    }

    String getCategory() {
        return category;
    }

    String getDate() {
        return date;
    }
}

class record_per_month {

    double total_expense=0.0;
    ArrayList<expense_record> spending_records= new ArrayList<>();

    record_per_month() {

    }

     ArrayList<expense_record> get_Spending_records() {
        return spending_records;
    }
    int record_list_length(){
        return spending_records.size();
    }

    void add_spending(expense_record e){
        spending_records.add(e);
        total_expense+=e.getSpending();

    }




    boolean remove_spending(int itemID){
        expense_record e;
        if (spending_records.size()>0){
            for (int i=0;i<spending_records.size();i++) {
                e=spending_records.get(i);
                if (e.getItemNo() == itemID) {
                    spending_records.remove(e);
                    total_expense-=e.getSpending();
                    return true;
                }
            }
        }
        return false;
    }

}