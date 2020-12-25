package com.company;

import java.util.ArrayList;

/**
 * this class is defined to describe game cards, by their colors
 * @author Sarv79
 * @version 2020
 */
public class Card {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    protected char color;
    /**
     * making a new card with a specific color
     * @param color as the chosen color
     */
    public Card(char color){
        this.color=color;
    }

    /**
     * getting a card's color
     * @return color
     */
    public char getColor() {
        return color;
    }

    /**
     * changing a card's color when it is wild
     * @param colorToSet
     */
    public void setColor(char colorToSet){
        this.color=colorToSet;
    }

    /**
     * displayin a line of the 4-line appearance of the card
     * @param lineIndex
     */
    public void displayALine(int lineIndex){
            System.out.print(cardDisplayLines(color).get(lineIndex));
    }

    /**
     * this method creates a list of strings for displaying a card line by line(4 lines for each card)
     * @param color
     * @return
     */
    private ArrayList<String> cardDisplayLines(char color){
        ArrayList<String> result=new ArrayList<>();
        String displayingColor;
        if(color=='R')
            displayingColor=ANSI_RED;
        else if(color=='B')
            displayingColor=ANSI_BLUE;
        else if(color=='G')
            displayingColor=ANSI_GREEN;
        else if (color=='Y')
            displayingColor=ANSI_YELLOW;
        else
            displayingColor=ANSI_RESET;
        result.add(displayingColor+"|$$$$$$$|"+ANSI_RESET);
        if(this instanceof NumeralCard) {
            result.add(displayingColor + "|" + ((NumeralCard) this).getNumber() + "      |" + ANSI_RESET);
            result.add(displayingColor+"|      "+((NumeralCard) this).getNumber()+"|");
        }
        else if(this instanceof ActionCard){
            if(((ActionCard) this).getAction().equals("Wild Draw")){
                result.add(displayingColor+"|WD     |"+ANSI_RESET);
                result.add(displayingColor+"|     WD|"+ANSI_RESET);
            }
            else {
                result.add(displayingColor + "|" + ((ActionCard) this).getAction().toUpperCase().charAt(0) + "      |" + ANSI_RESET);
                result.add(displayingColor + "|      " + ((ActionCard) this).getAction().toUpperCase().charAt(0) + "|" + ANSI_RESET);
            }
        }
        result.add(displayingColor+"|$$$$$$$|"+ANSI_RESET);
        return result;
    }
}
