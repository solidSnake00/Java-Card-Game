package com.mygdx.game.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Main;
import com.mygdx.game.data.*;
import com.mygdx.game.gui.RestartButton;
import com.mygdx.game.match.Match;
import com.mygdx.game.match.MatchStatusEnum;

import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class GameScreen extends ScreenAdapter implements InputProcessor {

    FileHandle fileHandle;
    SpriteBatch batch;
    OrthographicCamera camera;
    BitmapFont font;
    Texture image;
    int x,y;
    Vector3 mousePos;
    Vector2 vector2;
    /////////////////////////////////////////////////////////////
    PlayerObject player;
    PlayerObject opponent;
    Match match;
    Deck mainDeck;
    Stack<Card> cards=new Stack<>();
    String emptyCard="card_empty";
    Player player1;
    private Viewport fitViewport;
    ////////////////////////////////////////////////////////////

    MiddleGround middleGround;
    Random random;

    int midX,midY;

    Boolean isTouched;

    float timeSinceCollision;
    ShapeRenderer shapeRenderer;

    private Sound cardPlaceSound;
    private Sound cardMatchedSound;

    private BitmapFont bitmapFont;

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    private RestartButton restartButton;
    private final Main main;

    public GameScreen(Main main){
        this.main=main;
    }


    @Override
    public void show() {
        super.show();
        cardPlaceSound=Gdx.audio.newSound(Gdx.files.internal("cardPlace2.ogg"));
        cardMatchedSound=Gdx.audio.newSound(Gdx.files.internal("cardFan1.ogg"));

        restartButton=new RestartButton(300,550,"sign_restart.png");

        //////////////////////////////
        bitmapFont=new BitmapFont();
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Moder DOS 437 Win.ttf"));
        parameter=new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=18;
        parameter.borderWidth=2;

        bitmapFont=generator.generateFont(parameter);
        ///////////////////////////////////

        shapeRenderer=new ShapeRenderer();
        isTouched=false;
        midX=0;
        midY=0;
        random=new Random();
        fileHandle=Gdx.files.local("doors.csv");
        Gdx.app.log("GameScree.class","start");
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 360, 600);
        camera.update();
        image=new Texture("card_clubs_02.png");

        font=new BitmapFont();
        x=y=0;
        mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
        vector2=new Vector2();
        //////////////////////////////////////////////
        mainDeck=new Deck();
        cards.push(new Card("card_diamonds_A",CardTypeEnum.DIAMONDS, CardIndexEnum.ACE));
        cards.push(new Card("card_diamonds_02",CardTypeEnum.DIAMONDS, CardIndexEnum.TWO));
        cards.push(new Card("card_diamonds_03",CardTypeEnum.DIAMONDS, CardIndexEnum.THREE));
        cards.push(new Card("card_diamonds_04",CardTypeEnum.DIAMONDS, CardIndexEnum.FOUR));
        cards.push(new Card("card_diamonds_05",CardTypeEnum.DIAMONDS, CardIndexEnum.FIVE));
        cards.push(new Card("card_diamonds_06",CardTypeEnum.DIAMONDS, CardIndexEnum.SIX));
        cards.push(new Card("card_diamonds_07",CardTypeEnum.DIAMONDS, CardIndexEnum.SEVEN));
        cards.push(new Card("card_diamonds_08",CardTypeEnum.DIAMONDS, CardIndexEnum.EIGHT));
        cards.push(new Card("card_diamonds_09",CardTypeEnum.DIAMONDS, CardIndexEnum.NINE));
        cards.push(new Card("card_diamonds_10",CardTypeEnum.DIAMONDS, CardIndexEnum.TEN));
        cards.push(new Card("card_diamonds_J",CardTypeEnum.DIAMONDS, CardIndexEnum.JACK));
        cards.push(new Card("card_diamonds_Q",CardTypeEnum.DIAMONDS, CardIndexEnum.QUEEN));
        cards.push(new Card("card_diamonds_K",CardTypeEnum.DIAMONDS, CardIndexEnum.KING));

        cards.push(new Card("card_hearts_A",CardTypeEnum.HEARTS, CardIndexEnum.ACE));
        cards.push(new Card("card_hearts_02",CardTypeEnum.HEARTS, CardIndexEnum.TWO));
        cards.push(new Card("card_hearts_03",CardTypeEnum.HEARTS, CardIndexEnum.THREE));
        cards.push(new Card("card_hearts_04",CardTypeEnum.HEARTS, CardIndexEnum.FOUR));
        cards.push(new Card("card_hearts_05",CardTypeEnum.HEARTS, CardIndexEnum.FIVE));
        cards.push(new Card("card_hearts_06",CardTypeEnum.HEARTS, CardIndexEnum.SIX));
        cards.push(new Card("card_hearts_07",CardTypeEnum.HEARTS, CardIndexEnum.SEVEN));
        cards.push(new Card("card_hearts_08",CardTypeEnum.HEARTS, CardIndexEnum.EIGHT));
        cards.push(new Card("card_hearts_09",CardTypeEnum.HEARTS, CardIndexEnum.NINE));
        cards.push(new Card("card_hearts_10",CardTypeEnum.HEARTS, CardIndexEnum.TEN));
        cards.push(new Card("card_hearts_J",CardTypeEnum.HEARTS, CardIndexEnum.JACK));
        cards.push(new Card("card_hearts_Q",CardTypeEnum.HEARTS, CardIndexEnum.QUEEN));
        cards.push(new Card("card_hearts_K",CardTypeEnum.HEARTS, CardIndexEnum.KING));

        cards.push(new Card("card_spades_A",CardTypeEnum.SPADES, CardIndexEnum.ACE));
        cards.push(new Card("card_spades_02",CardTypeEnum.SPADES, CardIndexEnum.TWO));
        cards.push(new Card("card_spades_03",CardTypeEnum.SPADES, CardIndexEnum.THREE));
        cards.push(new Card("card_spades_04",CardTypeEnum.SPADES, CardIndexEnum.FOUR));
        cards.push(new Card("card_spades_05",CardTypeEnum.SPADES, CardIndexEnum.FIVE));
        cards.push(new Card("card_spades_06",CardTypeEnum.SPADES, CardIndexEnum.SIX));
        cards.push(new Card("card_spades_07",CardTypeEnum.SPADES, CardIndexEnum.SEVEN));
        cards.push(new Card("card_spades_08",CardTypeEnum.SPADES, CardIndexEnum.EIGHT));
        cards.push(new Card("card_spades_09",CardTypeEnum.SPADES, CardIndexEnum.NINE));
        cards.push(new Card("card_spades_10",CardTypeEnum.SPADES, CardIndexEnum.TEN));
        cards.push(new Card("card_spades_J",CardTypeEnum.SPADES, CardIndexEnum.JACK));
        cards.push(new Card("card_spades_Q",CardTypeEnum.SPADES, CardIndexEnum.QUEEN));
        cards.push(new Card("card_spades_K",CardTypeEnum.SPADES, CardIndexEnum.KING));

        cards.push(new Card("card_clubs_A",CardTypeEnum.CLUBS, CardIndexEnum.ACE));
        cards.push(new Card("card_clubs_02",CardTypeEnum.CLUBS, CardIndexEnum.TWO));
        cards.push(new Card("card_clubs_03",CardTypeEnum.CLUBS, CardIndexEnum.THREE));
        cards.push(new Card("card_clubs_04",CardTypeEnum.CLUBS, CardIndexEnum.FOUR));
        cards.push(new Card("card_clubs_05",CardTypeEnum.CLUBS, CardIndexEnum.FIVE));
        cards.push(new Card("card_clubs_06",CardTypeEnum.CLUBS, CardIndexEnum.SIX));
        cards.push(new Card("card_clubs_07",CardTypeEnum.CLUBS, CardIndexEnum.SEVEN));
        cards.push(new Card("card_clubs_08",CardTypeEnum.CLUBS, CardIndexEnum.EIGHT));
        cards.push(new Card("card_clubs_09",CardTypeEnum.CLUBS, CardIndexEnum.NINE));
        cards.push(new Card("card_clubs_10",CardTypeEnum.CLUBS, CardIndexEnum.TEN));
        cards.push(new Card("card_clubs_J",CardTypeEnum.CLUBS, CardIndexEnum.JACK));
        cards.push(new Card("card_clubs_Q",CardTypeEnum.CLUBS, CardIndexEnum.QUEEN));
        cards.push(new Card("card_clubs_K",CardTypeEnum.CLUBS, CardIndexEnum.KING));


        mainDeck.setCardList(cards);


        player=new Player(0,"player 1");
        opponent=new Opponent(0,"opponent 1");
        ///////////
        middleGround=new MiddleGround(new Deck(),150,300);
        ///////////

        match=new Match(player,opponent,middleGround,mainDeck);
        match.begin();
        System.out.println(player.getDeck().getDeckSize());
        System.out.println(opponent.getDeck().getDeckSize());


        for (int i=0;i<player.getDeck().getDeckSize();i++){
            System.out.println(player.getDeck().getCardList().get(i).getName());
        }
        System.out.println("--------------------------------------------");

        for (int i=0;i<opponent.getDeck().getDeckSize();i++){
            System.out.println(opponent.getDeck().getCardList().get(i).getName());
        }
        player.getDeck().setX(75);
        System.out.println("Screen height= "+Gdx.graphics.getHeight());
        player.getDeck().setY(25);
        player1= (Player) player;

        fitViewport=new FitViewport(360,600,camera);

        Gdx.input.setInputProcessor(this);
        timeSinceCollision=0;

        batch=new SpriteBatch();



        //////////////////////////////////////////////
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        ScreenUtils.clear(Color.BLACK, false);//0x00A83FFF
        fitViewport.apply();
        camera.update();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.setColor(new Color(0x00A83FFF));
        shapeRenderer.rect(0,0,360,600);
        shapeRenderer.end();


        mousePos.x=Gdx.input.getX();
        mousePos.y=Gdx.input.getY();
        mousePos=camera.unproject(mousePos,fitViewport.getScreenX(),fitViewport.getScreenY(),fitViewport.getScreenWidth(), fitViewport.getScreenHeight());

        if (match.getMatchStatusEnum()==MatchStatusEnum.OPPONENT_TURN){
            timeSinceCollision += delta;
            if(timeSinceCollision > 1.0f) {
                // do collision stuff
                if (!opponent.getDeck().getCardList().isEmpty()) {
                    middleGround.getDeck().getCardList().push(opponent.getDeck().getCardList().pop());
                    cardPlaceSound.play(1);
                    if (middleGround.getDeck().getDeckSize()>1) {
                        if (middleGround.getDeck().getCardList().peek().getCardIndexEnum().name().
                                equals(middleGround.getDeck().getCardList().get(middleGround.getDeck().getDeckSize()-2).getCardIndexEnum().name())){
                            for (int i=0;i<middleGround.getDeck().getDeckSize();i++){
                                opponent.getDeck().getCardList().push(middleGround.getDeck().getCardList().get(i));

                            }
                            cardMatchedSound.play(1);
                            Collections.shuffle(opponent.getDeck().getCardList());
                            middleGround.getDeck().getCardList().clear();
                        }
                    }
                    match.setMatchStatusEnum(MatchStatusEnum.PLAYER_TURN);
                }
                timeSinceCollision=0;
            }

        }

        if (player.getDeck().getCardList().isEmpty() || opponent.getDeck().getCardList().isEmpty()){
            match.setMatchStatusEnum(MatchStatusEnum.END);
        }



        if (!Gdx.input.isTouched()) {
            x=y=-100;
            player1.getSelectedCard().setOnBack(true);

            if (!player.getDeck().getCardList().isEmpty()) {
                player.getDeck().getCardList().peek().visible = true;
            }

            if (player.getDeck().getCardList().isEmpty()) {
                player1.getSelectedCard().visible = false;
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            player.getDeck().getCardList().pop();
        }

        //middleGround.drawShape(camera);

        if (match.getMatchStatusEnum()==MatchStatusEnum.END){

                batch.begin();
                batch.setProjectionMatrix(camera.combined);
                if (player.getDeck().getCardList().isEmpty()) {
                    bitmapFont.draw(batch, "Opponent win", 50, 300);
                }else if (opponent.getDeck().getCardList().isEmpty()) {
                    bitmapFont.draw(batch, "You Win", 50, 300);
                }
                batch.end();


        }

        middleGround.drawShape(camera);

        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        restartButton.draw(batch);


        bitmapFont.draw(batch,"Your Cards: "+player.getDeck().getDeckSize(),10,200);
        bitmapFont.draw(batch,"Opponent's Cards: "+opponent.getDeck().getDeckSize(),10,500);

        if (match.getMatchStatusEnum()!=MatchStatusEnum.END) {

            player.getDeck().drawDeck(batch, true);
            middleGround.drawDeck(batch);

            if (!player.getDeck().getCardList().isEmpty() && isTouched) {
                player1.drawSelectedCard(batch, Gdx.input.isTouched(),
                        x - player1.getSelectedCard().getWidth(), y - player1.getSelectedCard().getWidth());
            }
        }

        batch.end();


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        fitViewport.update(width,height,true);
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        image.dispose();
        player.getDeck().disposeDeckImages();
        player1.getSelectedCard().disposeCardImage();
        middleGround.dispose();
        shapeRenderer.dispose();

        cardPlaceSound.dispose();
        cardMatchedSound.dispose();
        bitmapFont.dispose();
        generator.dispose();
        restartButton.getImage().dispose();
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("deck y= "+player.getDeck().getY());
        mousePos.x=screenX;
        mousePos.y=screenY;
        mousePos=camera.unproject(mousePos,fitViewport.getScreenX(),fitViewport.getScreenY(),fitViewport.getScreenWidth(), fitViewport.getScreenHeight());
        System.out.println(mousePos.y);
        if (mousePos.y<player.getDeck().getY()+150 && mousePos.y>0) {
            isTouched = true;
        }

        if (mousePos.x>restartButton.getX() && mousePos.x<restartButton.getX()+32 &&
        mousePos.y>restartButton.getY() && mousePos.y<restartButton.getY()+32){
            cardPlaceSound.play(1);

            main.setScreen(new GameScreen(main));
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (isTouched && match.getMatchStatusEnum()== MatchStatusEnum.PLAYER_TURN) {
            if (player1.getSelectedCard().getCollisionRectangle().overlaps(middleGround.getRectangle())) {

                if (player.getDeck().getDeckSize() > 0) {
                    middleGround.getDeck().getCardList().push(player.getDeck().getCardList().pop());
                    cardPlaceSound.play(1);

                    if (middleGround.getDeck().getDeckSize()>1) {
                        if (middleGround.getDeck().getCardList().peek().getCardIndexEnum().name().
                                equals(middleGround.getDeck().getCardList().get(middleGround.getDeck().getDeckSize()-2).getCardIndexEnum().name())){
                            for (int i=0;i<middleGround.getDeck().getDeckSize();i++){
                                player.getDeck().getCardList().push(middleGround.getDeck().getCardList().get(i));

                            }
                            cardMatchedSound.play(1);
                            Collections.shuffle(player.getDeck().getCardList());
                            for(int i=0;i<player.getDeck().getDeckSize();i++){
                                player.getDeck().getCardList().get(i).setOnBack(true);
                            }
                            middleGround.getDeck().getCardList().clear();
                        }
                    }
                    System.out.println("player's deck number= "+player.getDeck().getDeckSize());
                    match.setMatchStatusEnum(MatchStatusEnum.OPPONENT_TURN);
                }
            }
        }
        isTouched=false;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (isTouched) {

            x = (int) mousePos.x;
            y = (int) mousePos.y;
            //System.out.println(mousePos.y);

            if (player.getDeck().getDeckSize() > 0) {
                player1.setSelectedCard(player.getDeck().getCardList().peek());

                player1.getSelectedCard().setOnBack(false);
                player1.getSelectedCard().setCollisionRectangleX(x);
                player1.getSelectedCard().setCollisionRectangleY(y);
                player.getDeck().getCardList().peek().visible = false;
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
