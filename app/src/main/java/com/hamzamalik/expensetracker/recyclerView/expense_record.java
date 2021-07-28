package com.hamzamalik.expensetracker.recyclerView;

import java.util.ArrayList;

public class expense_record {
    private int ID;
    private int spending;
    private String category;
    private String date;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setSpending(int spending) {
        this.spending = spending;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public expense_record(){

    }
    public expense_record(int ID, int spending, String category, String date) {
        this.ID = ID;
        this.spending = spending;
        this.category = category;
        this.date = date;

    }

    public int getID() {
        return ID;
    }

    public int getSpending() {
        return spending;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }
}

