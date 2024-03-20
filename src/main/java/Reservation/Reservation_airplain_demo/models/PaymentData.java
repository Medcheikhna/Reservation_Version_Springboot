package Reservation.Reservation_airplain_demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentData {
    @JsonProperty("card_number")
    private String cardNumber;
    @JsonProperty
    private String owner;
    @JsonProperty("account_number")
    private String accountNumber;
    @JsonProperty("expiration_date")
    private String expirationDate;
    @JsonProperty
    private float amount;
    @JsonProperty
    private String cvv;

    public PaymentData() {
    }

    public PaymentData(String cardNumber, String owner, String accountNumber, String expirationDate, float amount, String cvv) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.expirationDate = expirationDate;
        this.amount = amount;
        this.cvv = cvv;
    }
// Getters and setters

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

