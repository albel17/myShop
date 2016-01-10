package myApp.form;

import java.util.ArrayList;

public class RestForm {
    private ArrayList<String> topClientsEmails;
    private ArrayList<String> topClientsMoney;
    private ArrayList<String> topProductsNames;
    private ArrayList<String> topProductsPrices;
    private ArrayList<String> topProductsMoney;
    private int allMoneyThisMonth;
    private int allMoneyThisWeek;

    public RestForm(ArrayList<String> topClientsEmails, ArrayList<String> topClientsMoney, ArrayList<String> topProductsNames, ArrayList<String> topProductsPrices, ArrayList<String> topProductsMoney, int allMoneyThisMonth, int allMoneyThisWeek) {
        this.topClientsEmails = topClientsEmails;
        this.topClientsMoney = topClientsMoney;
        this.topProductsNames = topProductsNames;
        this.topProductsPrices = topProductsPrices;
        this.topProductsMoney = topProductsMoney;
        this.allMoneyThisMonth = allMoneyThisMonth;
        this.allMoneyThisWeek = allMoneyThisWeek;
    }

    public ArrayList<String> getTopClientsEmails() {
        return topClientsEmails;
    }

    public void setTopClientsEmails(ArrayList<String> topClientsEmails) {
        this.topClientsEmails = topClientsEmails;
    }

    public ArrayList<String> getTopClientsMoney() {
        return topClientsMoney;
    }

    public void setTopClientsMoney(ArrayList<String> topClientsMoney) {
        this.topClientsMoney = topClientsMoney;
    }

    public ArrayList<String> getTopProductsNames() {
        return topProductsNames;
    }

    public void setTopProductsNames(ArrayList<String> topProductsNames) {
        this.topProductsNames = topProductsNames;
    }

    public ArrayList<String> getTopProductsPrices() {
        return topProductsPrices;
    }

    public void setTopProductsPrices(ArrayList<String> topProductsPrices) {
        this.topProductsPrices = topProductsPrices;
    }

    public ArrayList<String> getTopProductsMoney() {
        return topProductsMoney;
    }

    public void setTopProductsMoney(ArrayList<String> topProductsMoney) {
        this.topProductsMoney = topProductsMoney;
    }

    public int getAllMoneyThisMonth() {
        return allMoneyThisMonth;
    }

    public void setAllMoneyThisMonth(int allMoneyThisMonth) {
        this.allMoneyThisMonth = allMoneyThisMonth;
    }

    public int getAllMoneyThisWeek() {
        return allMoneyThisWeek;
    }

    public void setAllMoneyThisWeek(int allMoneyThisWeek) {
        this.allMoneyThisWeek = allMoneyThisWeek;
    }
}
