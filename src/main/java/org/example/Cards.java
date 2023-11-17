package org.example;

public class Cards implements Card{

    private String cardName;
    private double cardPrice;
    private String cardColor;
    private String cardImage;

    public Cards(String cardName, double cardPrice, String cardColor, String cardImage) {
        this.cardName = cardName;
        this.cardPrice = cardPrice;
        this.cardColor = cardColor;
        this.cardImage = cardImage;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }

    public void setCardColor(String cardColor) {
        this.cardColor = cardColor;
    }

    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    @Override
    public String getCardName() {
        return this.cardName;
    }

    @Override
    public double getCardPrice() {
        return this.cardPrice;
    }

    @Override
    public String getCardColor() {
        return this.cardColor;
    }

    @Override
    public String getCardImage() {
        return this.cardImage;
    }
}
