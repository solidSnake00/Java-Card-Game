package com.mygdx.game.data;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Card {
    private int x;
    private int y;
    private int height;
    private int width;
    private CardTypeEnum cardTypeEnum;
    private CardIndexEnum cardIndexEnum;
    private String name;
    private Texture image;
    private Rectangle collisionRectangle;
    private Boolean onBack;

    private String imageFilePath;
    private Texture backImage;
    public Boolean visible;
    public Random random;



    public Card(){
        onBack=true;
        collisionRectangle=new Rectangle();
    }

    public Card(String imageFilePath,CardTypeEnum cardTypeEnum, CardIndexEnum cardIndexEnum) {
        x=y=0;
        this.cardTypeEnum = cardTypeEnum;
        this.cardIndexEnum = cardIndexEnum;
        this.imageFilePath=imageFilePath;
        name= (cardTypeEnum.name() + " " + cardIndexEnum.name()).toLowerCase();
        image=new Texture(imageFilePath+".png");
        onBack=true;
        width=42;
        height=60;
        collisionRectangle=new Rectangle(x,y,width*2,height*2);
        backImage=new Texture("card_back.png");
        visible=true;
        random=new Random();
    }
    public Card(CardTypeEnum cardTypeEnum, CardIndexEnum cardIndexEnum) {
        x=y=0;
        this.cardTypeEnum = cardTypeEnum;
        this.cardIndexEnum = cardIndexEnum;
        name= (cardTypeEnum.name() + " " + cardIndexEnum.name()).toLowerCase();
        onBack=true;
        width=42;
        height=60;
        collisionRectangle=new Rectangle(x,y,width,height);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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

    public Boolean getOnBack() {
        return onBack;
    }

    public void setOnBack(Boolean onBack) {
        this.onBack = onBack;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Texture getImage() {
        if (onBack){
            return backImage;
            //System.out.println("-------card update: backImage");
        }else {
            return image;
        }
        //return image;
    }

    public void setImage(Texture image) {
        this.image = image;
    }
    public void setImage(String imageFilePath) {
        image=new Texture(imageFilePath);
    }

    public CardTypeEnum getCardTypeEnum() {
        return cardTypeEnum;
    }

    public void setCardTypeEnum(CardTypeEnum cardTypeEnum) {
        this.cardTypeEnum = cardTypeEnum;
    }

    public CardIndexEnum getCardIndexEnum() {
        return cardIndexEnum;
    }

    public void setCardIndexEnum(CardIndexEnum cardIndexEnum) {
        this.cardIndexEnum = cardIndexEnum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
    }

    public void setCollisionRectangleX(int x){
        collisionRectangle.x=x;
    }
    public void setCollisionRectangleY(int y){
        collisionRectangle.y=y;
    }

    public void disposeCardImage(){
        image.dispose();
        backImage.dispose();
    }
    ///////////////////////////////////////////////

    public Boolean checkCollision(Rectangle rectangle){
        return collisionRectangle.overlaps(rectangle);
    }

    public void update(){
        if (onBack){
            setImage(backImage);
            //System.out.println("-------card update: backImage");
        }else {
            setImage(image);
        }
    }

    public int getRandX(){
        return x+random.nextInt(200-100);
    }


}
