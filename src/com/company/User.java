package com.company;

import java.util.Scanner;

/**
 * this class is defined to describe a user player
 * @author Sarv79
 * @version 2020
 */
public class User extends Player {
    /**
     * making a new usert player
     * @param name
     */
    public User(String name){
        super();
        this.name=name;
    }

    /**
     * displaying a user player's hand
     */
    public void displayHand(){
        hand.display();
        for(int i=0;i<hand.getCards().size();i++){
            if(i>=9){
                System.out.print("    "+(i+1)+"    ");
            }
            else
                System.out.print("    "+(i+1)+"     ");
        }
        System.out.println();
    }

    /**
     * choosing a card to play
     * @param lastCard
     * @return
     */
    @Override
    public Card chooseCard(Card lastCard){
        Scanner userScanner=new Scanner(System.in);
        System.out.println();
        Card chosen;
        while (true){
            System.out.println("choose a card");
            int chosenCardIndex=userScanner.nextInt();
            if(chosenCardIndex<1 || chosenCardIndex>hand.getCards().size()){
                System.out.print("invalid choice");
                continue;
            }
            chosen=hand.getCards().get(chosenCardIndex-1);
            if(chosen.getColor()==lastCard.getColor())
                break;
            else if((lastCard instanceof NumeralCard) && (chosen instanceof NumeralCard)
            && ((NumeralCard) lastCard).getNumber()==((NumeralCard) chosen).getNumber())
                break;
            else if((lastCard instanceof ActionCard) && (chosen instanceof ActionCard)
            && ((ActionCard) lastCard).getAction().equals(((ActionCard) chosen).getAction()))
                break;
            if((chosen instanceof ActionCard) && ((ActionCard) chosen).getAction().contains("Wild")){
                if((lastCard instanceof NumeralCard) && hand.voidInColor(lastCard.getColor()) &&
                        hand.voidInNumber(((NumeralCard) lastCard).getNumber()))
                    break;
                else if((lastCard instanceof ActionCard) && !((ActionCard) lastCard).getAction().contains("Wild")
                        && hand.voidInColor(lastCard.getColor()) && hand.voidIntActionType(((ActionCard) lastCard).getAction())){
                    break;
                }
            }
            System.out.println("invalid choice");
        }
        hand.removeCard(chosen);
        if((chosen instanceof ActionCard)&&((ActionCard) chosen).getAction().contains("Wild")){
            System.out.println("which color would you choose?");
            System.out.println("1)Blue 2)Red 3)Yellow 4)Green");
            String colorToSet=userScanner.next().toUpperCase();
            chosen.setColor(colorToSet.charAt(0));
        }
        return chosen;
    }
}
