package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * this class is defined for playing UNO
 * @author Sarv79
 * @version 2020
 */
public class UNO {
    private ArrayList<Player> players;
    private UNOCardDistributer distributer;
    private boolean direction;//if true, the game is rounding clockwise, else, it is anticlockwise
    private boolean gameOver;

    /**
     * starting a new UNO game
     */
    public UNO(){
        players=new ArrayList<>();
        direction=true;
        gameOver=false;
        distributer=new UNOCardDistributer();
        initialPlayers();
        distributer.shuffle();
        while((distributer.getLastCard() instanceof ActionCard) &&
                ((ActionCard) distributer.getLastCard()).getAction().contains("Wild")){
            distributer.shuffle();
        }
        distributeCards();
        play();
    }

    /**
     * to control playing uno
     */
    public void play(){
        Random turnRandomizer=new Random();
        int counter=turnRandomizer.nextInt(players.size()-1)-1;
        int sub=1;
        int fine=0;
        while (!gameOver){
            if (distributer.getLastCard() instanceof ActionCard){
                if(((ActionCard) distributer.getLastCard()).getAction().equals("Skip")&&
                        !((ActionCard) distributer.getLastCard()).getActionIsDone()) {
                    sub = 2;
                    fine=0;
                    ((ActionCard) distributer.getLastCard()).setActionIsDone(true);
                }
                else if (((ActionCard) distributer.getLastCard()).getAction().equals("Reverse")&&
                        !((ActionCard) distributer.getLastCard()).getActionIsDone()) {
                    direction = !direction;
                    fine=0;
                    ((ActionCard) distributer.getLastCard()).setActionIsDone(true);
                }
                else if(((ActionCard) distributer.getLastCard()).getAction().equals("Wild Draw")
                && !((ActionCard) distributer.getLastCard()).getActionIsDone() && !distributer.previousDraw())
                    fine+=4;
                else if(((ActionCard) distributer.getLastCard()).getAction().equals("Wild Draw")
                        && !((ActionCard) distributer.getLastCard()).getActionIsDone()){
                    fine=0;
                    fine+=4;
                }
                else if(((ActionCard) distributer.getLastCard()).getAction().equals("Draw2+")
                        && !((ActionCard) distributer.getLastCard()).getActionIsDone() && !distributer.previousDraw())
                    fine+=2;
                else if(((ActionCard) distributer.getLastCard()).getAction().equals("Draw2+")
                && !((ActionCard) distributer.getLastCard()).getActionIsDone()) {
                    fine=0;
                    fine += 2;
                }
                else {
                    fine = 0;
                    sub=1;
                }
            }
            else {
                fine=0;
            }
            if(direction)
                counter+=sub;
            else
                counter-=sub;
            turn(counter,fine);
            sub=1;
        }
        printResults();
    }
    /**
     * implementing turns
     * @param counter as the turns counter
     * @param fine
     */
    public void turn(int counter,int fine){
        display();
        if(counter<0){
            int abs=(-1)*counter;
            abs-=1;
            counter+=players.size()*(abs/players.size() +1 );
        }
        Player playerInTurn=players.get(counter%players.size());
        System.out.println();
        System.out.println(playerInTurn.getName()+"'s turn");
        System.out.println(playerInTurn.getHand().getCards().size()+" cards");
        Card lastCaard=distributer.getLastCard();
        Card chosenCard=playerInTurn.playaTurn(lastCaard);
        if(chosenCard==null && fine!=0){
            for(int i=0;i<fine;i++){
                distributer.giveFineCard(playerInTurn);
            }
            chosenCard=playerInTurn.playaTurn(lastCaard);
            fine=0;
        }
        if(chosenCard==null && fine==0){
            distributer.giveFineCard(playerInTurn);
            chosenCard=playerInTurn.playaTurn(lastCaard);
        }
        if(chosenCard!=null) {
            distributer.addCard(chosenCard);
        }
        if(playerInTurn.getHand().getCards().isEmpty())
            gameOver=true;
    }

    /**
     * getting details about the number of players, also adding them to the players list
     */
    public void initialPlayers(){
        System.out.println("Choose the number of players: 3, 4,or 5?");
        Scanner playersScanner=new Scanner(System.in);
        int numOfPlayers;
        while (true){
            numOfPlayers=playersScanner.nextInt();
            if(numOfPlayers>=3 && numOfPlayers<=5)
                break;
            else
                System.out.println("invalid number; try again");
        }
        System.out.println("How many robot players do you want to have?");
        int numOfRobots;
        while (true){
            numOfRobots=playersScanner.nextInt();
            if(numOfRobots<=numOfPlayers){
                break;
            }
            System.out.println("invalid number. try again");
        }
        addUserPlayers(numOfPlayers-numOfRobots);
        addRobotPlayers(numOfRobots);
    }

    /**
     * adding robot players to the players list
     * @param numOfRobots
     */
    public void addRobotPlayers(int numOfRobots){
        for (int i=0;i<numOfRobots;i++){
            players.add(new Robot(i));
        }
    }

    /**
     * adding user players to the players list
     * @param numOfUserPlayers
     */
    public void addUserPlayers(int numOfUserPlayers){
        Scanner userScanner=new Scanner(System.in);
        for (int i=0;i<numOfUserPlayers;i++){
            System.out.println("Enter the name of player #"+(i+1));
            String name=userScanner.next();
            players.add(new User(name));
        }
    }

    /**
     * distribute cards between the players as the game starts
     */
    public void distributeCards(){
        for (int i=0;i<players.size();i++){
            distributer.giveAHand(players.get(i));
        }
    }

    /**
     * displaying the game in each turn
     */
    public void display(){
        System.out.println();
        System.out.println("--------------------------");
        System.out.println(players.get(0).name+"              "+players.get(1).getName());
        for (int i=0;i<4;i++){
            System.out.print("        ");
            distributer.getLastCard().displayALine(i);
            System.out.println();
        }
        System.out.print("        ");
        if(direction)
            System.out.println("ClockWise");
        else
            System.out.println("AntiClockWise");
        if (players.size()==3){
            System.out.println("         "+players.get(2).getName());
        }
        else if(players.size()==4)
            System.out.println(players.get(3).getName()+"              "+players.get(2).getName());
        else if(players.size()==5){
                System.out.println(players.get(4).getName()+"              "+players.get(2).getName());
                System.out.println("         "+players.get(3).getName());
        }
        System.out.println("--------------------------");
    }

    /**
     * to print the winner and other's scores when the game ends
     */
    public void printResults(){
        for(Player temp: players){
            if(temp.getHand().getCards().isEmpty()) {
                System.out.println("The Winner is: 1) " + temp.getName());
                players.remove(temp);//for-each bug
                break;
            }
        }
        for(Player temp: players){
            temp.setScore();
        }
        players=sortPlayers();
        for(int i=0;i<players.size();i++){
            System.out.println(i+2+") "+players.get(i).getName()+" : "+players.get(i).getScore());
        }
        System.out.println("Thanks for playing :)");
    }

    /**
     * sorting the players by their scores at the end of the game
     */
    public ArrayList<Player> sortPlayers(){
        ArrayList<Player> sortedPlayers=new ArrayList<>();
        for(int i=0;i<players.size();i++){
            Player minScore=players.get(i);
            for(int j=i;j<players.size();j++){
                if(players.get(j).getScore()<minScore.getScore())
                    minScore=players.get(j);
            }
            sortedPlayers.add(minScore);
        }
        return sortedPlayers;
    }
}
