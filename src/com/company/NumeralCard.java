package com.company;

/**
 * numeral cards are the type of cards having a number
 * this class extends Card class
 * @author Sarv79
 * @version 2020
 */
public class NumeralCard extends Card {
    private int number;

    /**
     * making a new numeral card
     * @param color as the card's color
     * @param number as the number of the card
     */
    public NumeralCard(char color,int number){
        super(color);
        this.number=number;
    }

    /**
     * getting a numeral card's number
     * @return
     */
    public int getNumber() {
        return number;
    }
}
