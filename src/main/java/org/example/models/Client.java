package org.example.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.util.Date;

public class Client {
  private String id;
  private String name;
  private Date data;
  private String state;

  public Client(String name, String date, String state) throws ParseException {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    this.id = UUID.randomUUID();
    this.name = name;
    this.data = simpleDateFormat.parse(date);
    this.state = state;
  }

  public String getId () {
    return this.id.toString();
  }

  public String getName() {
    return this.name;
  }

  public Date getDate() {
    return this.data;
  }

  public String getState() {
    return this.state;
  }

}