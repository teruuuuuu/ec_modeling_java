package jp.co.teruuu.ec_modeling.app.app_service.dto;

import java.time.LocalDateTime;

public class ItemDetail {
  int itemId;
  String name;
  int price;
  int number;
  int currentPrice;
  String description;
  LocalDateTime updateDate;

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(int currentPrice) {
    this.currentPrice = currentPrice;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(LocalDateTime updateDate) {
    this.updateDate = updateDate;
  }
}
