package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * this class is defined to hold all of the cards necessary for UNO
 * it also distributes the cards between players
 * it puts the remaining cards in the game's deck
 */
public class UNOCardDistributer {
    ArrayList<Card> allOfCards;

    /**
     * In order to make a new distributer, we should add all of the cards to the list
     */
    public UNOCardDistributer(){
        allOfCards=new ArrayList<>();
        addCards();
    }

    /**
     * this method compeletes the list of cards when initializing a distributer
     */
    private void addCards(){
        allOfCards.add(new NumeralCard('B',0));
        allOfCards.add(new NumeralCard('R',0));
        allOfCards.add(new NumeralCard('Y',0));
        allOfCards.add(new NumeralCard('G',0));
        for(int i=1;i<=9;i++){
            for (int j=0;j<2;j++){
                allOfCards.add(new NumeralCard('B',i));
                allOfCards.add(new NumeralCard('R',i));
                allOfCards.add(new NumeralCard('Y',i));
                allOfCards.add(new NumeralCard('G',i));
            }
        }
        for (int i=0;i<2;i++){
            allOfCards.add(new ActionCard('B',"Skip"));
            allOfCards.add(new ActionCard('R',"Skip"));
            allOfCards.add(new ActionCard('Y',"Skip"));
            allOfCards.add(new ActionCard('G',"Skip"));
            allOfCards.add(new ActionCard('B',"Reverse"));
            allOfCards.add(new ActionCard('R',"Reverse"));
            allOfCards.add(new ActionCard('Y',"Reverse"));
            allOfCards.add(new ActionCard('G',"Reverse"));
            allOfCards.add(new ActionCard('B',"Draw2+"));
            allOfCards.add(new ActionCard('R',"Draw2+"));
            allOfCards.add(new ActionCard('Y',"Draw2+"));
            allOfCards.add(new ActionCard('G',"Draw2+"));
        }
        for (int i=0;i<4;i++){
            allOfCards.add(new ActionCard('W',"Wild"));
            allOfCards.add(new ActionCard('W',"Wild Draw"));
        }
    }

    /**
     * giving 7 cards to each player as the game starts
     * @param playerToGiveHand
     */
    public void giveAHand(Player playerToGiveHand){
        Hand temp=new Hand();
        Random handRandomizer=new Random();
        for(int i=0;i<7;i++){
            int index=handRandomizer.nextInt(allOfCards.size()-1);
            temp.addCard(allOfCards.get(index));
            allOfCards.remove(allOfCards.get(index));
        }
        playerToGiveHand.setHand(temp);
    }

    /**
     * when a player chooses a card to play, this card will be added to the list
     * @param cardToAdd
     */
    public void addCard(Card cardToAdd){
        allOfCards.add(cardToAdd);
    }

    /**
     * making a shuffle order for the cards
     */
    public void shuffle(){
        Collections.shuffle(allOfCards);
    }

    /**
     * getting the main card of the game
     * @return the last card of the collection
     */
    public Card getLastCard(){
        return allOfCards.get(allOfCards.size()-1);
    }

    /**
     * to see if the previous draw card has done its task
     * @return false if it's not done yet, true otherwise
     */
    public boolean previousDraw(){
        if(allOfCards.get(allOfCards.size()-2) instanceof ActionCard
        && ((ActionCard) allOfCards.get(allOfCards.size()-2)).getAction().contains("Draw")
        && !((ActionCard) allOfCards.get(allOfCards.size()-2)).getActionIsDone())
            return false;
        return true;
    }

    /**
     * giving cards to the players during the game
     * @param playerToGiveCard
     */
    public void giveFineCard(Player playerToGiveCard){
        playerToGiveCard.getHand().addCard(allOfCards.get(0));
        allOfCards.remove(0);
    }
}
