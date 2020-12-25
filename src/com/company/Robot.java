package com.company;

import java.util.Random;

/**
 * this class is defined to describe robot(computer) as a kind of player
 * @author Sarv79
 * @version 2020
 */
public class Robot extends Player {
    /**
     * making a new robot
     * @param i
     */
    public Robot(int i){
        super();
        if (i%10==0)
            name=i+1+"st robot";
        else if(i%10==1)
            name=i+1+"nd robot";
        else if (i%10==2)
            name=i+1+"rd robot";
        else
            name=i+1+"th robot";
    }

    /**
     * making computer's choice as a player
     * in a way that computer can choose a card which has the maximum value
     * @param lastCard
     * @return
     */
    @Override
    public Card chooseCard(Card lastCard) {
        Card result=null;
        NumeralCard maxNum = new NumeralCard('C', 0);
        for (Card tempCard : hand.getCards()) {
            if ((tempCard instanceof ActionCard) && !((ActionCard) tempCard).getAction().contains("Wild")) {
                if (lastCard.getColor() == tempCard.getColor()) {
                    hand.removeCard(tempCard);
                    return tempCard;
                }
                if ((lastCard instanceof ActionCard) &&
                        ((ActionCard) lastCard).getAction().equals(((ActionCard) tempCard).getAction())) {
                    hand.removeCard(tempCard);
                    return tempCard;
                }
            } else if (tempCard instanceof NumeralCard) {
                if (lastCard.getColor() == tempCard.getColor() && ((NumeralCard) tempCard).getNumber() >= maxNum.getNumber())
                    maxNum = (NumeralCard) tempCard;
                if ((lastCard instanceof NumeralCard) && ((NumeralCard) lastCard).getNumber() == ((NumeralCard) tempCard).getNumber()) {
                    hand.removeCard(tempCard);
                    return tempCard;
                }
            }
        }
        if (maxNum.getColor() != 'C') {
            hand.removeCard(maxNum);
            return maxNum;
        }
        for (Card temp: hand.getCards()){
            if((temp instanceof ActionCard) && ((ActionCard) temp).getAction().contains("Wild")){
                hand.removeCard(temp);
                Random colorRandomize=new Random();
                int randomColor=colorRandomize.nextInt();
                if(randomColor%4==0){
                    temp.setColor('B');
                }
                else if(randomColor%4==1){
                    temp.setColor('G');
                }
                else if(randomColor%4==2){
                    temp.setColor('Y');
                }
                else {
                    temp.setColor('R');
                }
                return temp;
            }
        }
        return null;
    }
}

