
package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {

    private String invoice;
    private int value;
    private Date date;
    private String client;


    public Invoice(String invoice, int value, String date, String client) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.invoice = invoice;
        this.value = value;
        this.date = simpleDateFormat.parse(date);
        this.client = client;
    }

    public String getInvoice(){
        return this.invoice;
    }

    public int getValue(){
        return this.value;
    }

    public Date getDate(){
        return this.date;
    }

    public String getClient(){
        return this.client;
    }
}