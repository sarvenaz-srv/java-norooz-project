package com.company;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * this class is defined to describe each UNO player by its score and the list of cards
 * @author Sarv79
 * @version 2020
 */
public class Player {
    protected int score;
    protected Hand hand;
    protected String name;
    /**
     * the new players' scores are 0 at first
     */
    public Player(){
        this.score=0;
        hand=new Hand();
    }

    /**
     * setting a hand for the player
     * @param handToSet
     */
    public void setHand(Hand handToSet){
        this.hand=handToSet;
    }

    /**
     * getting a player's hand
     * @return
     */
    public Hand getHand(){
        return hand;
    }

    /**
     * playing a turn if possible
     * @param lastCard
     * @return
     */
    public Card playaTurn(Card lastCard){
        if(this instanceof User){
            ((User) this).displayHand();
        }
        if (lastCard instanceof NumeralCard){
            if(hand.voidInColor(lastCard.getColor()) && hand.voidInNumber(((NumeralCard) lastCard).getNumber())
            && hand.voidIntActionType("Wild")&& hand.voidIntActionType("Wild Draw")) {
                System.out.println("oops! there is no choice");
                return null;
            }
        }
        else if(lastCard instanceof ActionCard){
            if(((ActionCard) lastCard).getAction().contains("Draw") && !((ActionCard) lastCard).getActionIsDone()){
                if(hand.voidIntActionType(((ActionCard) lastCard).getAction())) {
                    System.out.println("Sorry :( you don't have any "+((ActionCard) lastCard).getAction()+" cards");
                    ((ActionCard) lastCard).setActionIsDone(true);
                    return null;
                }
            }
            else if(hand.voidIntActionType(((ActionCard) lastCard).getAction()) && hand.voidInColor(lastCard.getColor())) {
                System.out.println("oops! there is no choice");
                return null;
            }
        }
        return chooseCard(lastCard);
    }

    /**
     * choose a card to play if possible
     * @param lastCard
     * @return
     */
    public Card chooseCard(Card lastCard) {
        return null;
    }

    /**
     * getting a player's name
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * setting a score at the end of the game
     */
    public void setScore(){
        score=hand.calculateScore();
    }

    /**
     * to get a player's score at the end of the game
     * @return
     */
    public int getScore(){
        return score;
    }
}
