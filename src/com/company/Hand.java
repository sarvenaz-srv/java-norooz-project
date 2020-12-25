package com.company;

import java.util.ArrayList;

/**
 * this class describes the list of cards a player has
 * @author Sarv79
 * @version 2020
 */
public class Hand {
    private ArrayList<Card> cards;

    /**
     * generating a new hand
     */
    public Hand(){
        cards=new ArrayList<>();
    }

    /**
     * adding a card to the hand
     * @param cardToAdd
     */
    public void addCard(Card cardToAdd){
        cards.add(cardToAdd);
    }

    /**
     * removing a card from the hand
     * @param cardToRemove
     */
    public void removeCard(Card cardToRemove){
        cards.remove(cardToRemove);
    }

    /**
     * checking if a hand is void in a specific color
     * @param color
     * @return true if it is void, false otherwise
     */
    public boolean voidInColor(char color){
        for(int i=0;i<cards.size();i++){
            if(cards.get(i).color==color)
                return false;
        }
        return true;
    }

    /**
     * checking if a hand is void in a specific number
     * @param num
     * @return true if it is void,false otherwise
     */
    public boolean voidInNumber(int num){
        for (int i=0;i<cards.size();i++){
            if(cards.get(i) instanceof NumeralCard && ((NumeralCard) cards.get(i)).getNumber()==num)
                return false;
        }
        return true;
    }

    /**
     * checking if a hand is void in a specific action
     * @param act
     * @return true if it is void,false otherwise
     */
    public boolean voidIntActionType(String act){
        for (int i=0;i<cards.size();i++){
            if(cards.get(i) instanceof ActionCard && ((ActionCard) cards.get(i)).getAction().equals(act))
                return false;
        }
        return true;
    }

    /**
     * displaying a player's hand
     */
    public void display(){
        for (int i=0;i<4;i++){
            for (int j=0;j<cards.size();j++){
                cards.get(j).displayALine(i);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * getting a hand's cards
     * @return
     */
    public ArrayList<Card> getCards(){
        return cards;
    }

    /**
     * calculating a hand's sum of scores
     * @return score
     */
    public int calculateScore(){
        int score=0;
        for(Card temp: cards){
            if(temp instanceof NumeralCard){
                score+=((NumeralCard) temp).getNumber();
            }
            else if((temp instanceof ActionCard) && ((ActionCard) temp).getAction().contains("Wild")){
                score+=50;
            }
            else {
                score+=20;
            }
        }
        return score;
    }
}
