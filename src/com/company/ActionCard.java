package com.company;

/**
 * this class is defined to describe the cards which have an action instead of numbers
 */
public class ActionCard extends Card {
    private String action;
    private boolean actionIsDone;

    /**
     * making a new action card
     * @param color
     * @param action
     */
    public ActionCard(char color, String action){
        super(color);
        this.action=action;
        if(action.equals("Wild"))
            actionIsDone=true;
        else
            actionIsDone=false;
    }

    /**
     * getting a card's action
     * @return
     */
    public String getAction() {
        return action;
    }

    /**
     * changing the actionIsDone boolean if it is done
     * @param actionIsDone
     */
    public void setActionIsDone(boolean actionIsDone) {
        this.actionIsDone = actionIsDone;
    }

    /**
     * seeing if an action is done or not
     * @return
     */
    public boolean getActionIsDone(){
        return actionIsDone;
    }
}
