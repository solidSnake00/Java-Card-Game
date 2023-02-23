package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RestartButton {
    private int x;
    private int y;

    private Texture image;

    public RestartButton(int x, int y, String filePath){
        this.x=x;
        this.y=y;
        image=new Texture(filePath);
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

    public Texture getImage() {
        return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }

    public void draw(SpriteBatch batch){
        batch.draw(image,x,y,32,32);
    }
}
