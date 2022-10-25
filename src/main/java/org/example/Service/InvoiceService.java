package org.example.Service;

import org.example.Controller.ClientController;
import org.example.models.Invoice;
import org.example.models.Client;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class InvoiceService {

  private ClientController clientController;

  public InvoiceService(ClientController clientController){
    this.clientController = clientController;
  }

  private int calculate(Date date){
    LocalDate today = LocalDate.now();
    LocalDate d = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    Period time = Period.between(d, today);
    return (time.getMonths() * 30) + time.getDays();
  }

  public List<Invoice> filterInvoices(Invoice[] invoices) {
    List<Invoice> finalListFilter = new ArrayList<>(Arrays.asList(invoices));
        String[] states = {"Rio Grande do Sul", "Paraná", "Santa Catarina"};
        for(Invoice inv: invoices){
            Client cl = this.clientController.findById(inv.getClient());
            if (inv.getValue() < 2000){
                finalListFilter.remove(inv);
            }else if(inv.getValue() >= 2000 && inv.getValue() <= 2500 && calculate(inv.getDate()) <= 30){
                finalListFilter.remove(inv);
            }else if(inv.getValue() > 2500 && inv.getValue() <= 3000 && calculate(cl.getDate()) <= 60){
                finalListFilter.remove(inv);
            } else if(inv.getValue() > 4000 && Arrays.asList(states).contains(cl.getState())) {
                finalListFilter.remove(inv);
            }
        }
        return finalListFilter;
  }

}
