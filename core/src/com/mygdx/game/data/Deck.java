package com.mygdx.game.data;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Deck {
    private int x;
    private int y;
    private Stack<Card> cardList;

    public Deck(){
        cardList=new Stack<Card>();
    }

    public void setOneCard(Card card){
        cardList.add(card);
    }

    public Stack<Card> getCardList() {
        return cardList;
    }

    public void setCardList(Stack<Card> cardList) {
        this.cardList = cardList;
    }

    public int getDeckSize(){
        return cardList.size();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawDeck(SpriteBatch batch, Boolean onBack){
        if (onBack){
            int s=0;

            for (int i=0;i< cardList.size();i++){

                if (cardList.get(i).visible) {
                    batch.draw(cardList.get(i).getImage(), x + s, y, cardList.get(i).getWidth() * 2, cardList.get(i).getHeight() * 2);
                    s += 3;//4
                }
            }

        }

    }

    public void disposeDeckImages(){
        for (int i=0;i<getDeckSize();i++){
            cardList.get(i).disposeCardImage();
        }
    }
}
