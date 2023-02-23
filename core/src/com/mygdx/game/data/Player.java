package com.mygdx.game.data;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends PlayerObject{
    private PlayerStatusEnum playerStatusEnum;
    private Card selectedCard;
   public Boolean emptyHand;

    public Player(int id,String name){
        super(id,name);
        playerStatusEnum=PlayerStatusEnum.ON_GOING;
        selectedCard=new Card();
        emptyHand=true;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
        emptyHand=false;
    }

    public PlayerStatusEnum getPlayerStatusEnum() {
        return playerStatusEnum;
    }

    public void setPlayerStatusEnum(PlayerStatusEnum playerStatusEnum) {
        this.playerStatusEnum = playerStatusEnum;
    }

    public void drawSelectedCard(SpriteBatch batch, Boolean isTouched, int x, int y){
        if (!emptyHand){
            batch.draw(selectedCard.getImage(),x,y,selectedCard.getImage().getWidth()*2,selectedCard.getImage().getHeight()*2);
        }
    }


}
