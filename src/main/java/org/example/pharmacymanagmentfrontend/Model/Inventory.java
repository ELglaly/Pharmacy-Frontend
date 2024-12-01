package org.example.pharmacymanagmentfrontend.Model;

import javafx.scene.chart.PieChart;

public class Inventory {
  private String name;
  private int quantity;
  private Boolean available;
  private Boolean lowStock;

  public Inventory(String name, int quantity) {
      this.name = name;
      this.quantity = quantity;
      this.available = quantity>0? true:false;
      this.lowStock = quantity<10? true: false;

  }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.available = quantity>0? true:false;
        this.lowStock = quantity<10? true: false;
    }

    public Boolean getAvailable() {
        return available;
    }


    public Boolean getLowStock() {
        return lowStock;
    }

}
