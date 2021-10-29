package net.fabricmc.example;

import java.util.Date;

public class Account {
  private final String label;
  private final String id;
  private double amount = 0.0;
  private Date createdAt;
  private boolean isActive = true;

  public Account(String label) {
    this.label = label;
    this.id = Utils.generateRandomNumber(11);
  }

  public String getLabel() {
    return label;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  //todo setUser

  @Override
  public String toString() {
    return "Account{" +
      "label='" + label + '\'' +
      ", amount=" + amount +
      ", createdAt=" + createdAt +
      ", isActive=" + isActive +
      '}';
  }
}
