package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import jdk.vm.ci.meta.SpeculationLog;

import java.util.Random;

public class MiddleGround {
    private int x;
    private int y;

    private Rectangle rectangle;
    private final ShapeRenderer shapeRenderer;
    private Deck deck;
    private Random random;

    public MiddleGround(Deck deck, int x,int y) {
        this.deck = deck;
        this.x=x;
        this.y=y;
        rectangle=new Rectangle(x,y,150,200);
        shapeRenderer=new ShapeRenderer();
        random=new Random();
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void drawShape(Camera camera){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x,y,84,120);
        shapeRenderer.end();
    }

    public void drawDeck(SpriteBatch batch){
        if (!deck.getCardList().isEmpty()) {
            deck.getCardList().peek().setOnBack(false);
            batch.draw(deck.getCardList().peek().getImage(), x, y, 84, 120);
        }
    }
    public void drawDeck2(SpriteBatch batch, int midX,int midY){
        int s=0;
        int f=0;
        if (!deck.getCardList().isEmpty()) {
            for (int i=0;i<deck.getDeckSize();i++) {
                deck.getCardList().get(i).setOnBack(false);
                //s=random.nextInt(200-100);
                batch.draw(deck.getCardList().get(i).getImage(), x+midX, y+midY, 84, 120);



            }
        }
    }

    public void dispose(){
        shapeRenderer.dispose();
        if (!deck.getCardList().isEmpty()) {
            deck.getCardList().peek().getImage().dispose();
        }

    }
}
