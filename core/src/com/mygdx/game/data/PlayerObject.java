package com.mygdx.game.data;

public abstract class PlayerObject {
    private int id;
    private String name;
    private Deck deck;

    public PlayerObject(){}
    public PlayerObject(int id,String name){
        this.id=id;
        this.name=name;
        deck=new Deck();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Card peekOnDeck(){
        return getDeck().getCardList().peek();
    }
    public Card getTopCard(){
        return getDeck().getCardList().pop();
    }

}
