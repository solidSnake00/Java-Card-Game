package com.mygdx.game.match;

import com.mygdx.game.data.*;

import java.util.Collections;
import java.util.Stack;

public class Match {
    private PlayerObject player;
    private PlayerObject opponent;
    private MiddleGround middleGround;
    private MatchStatusEnum matchStatusEnum;
    private Deck mainDeck;


    public Match(PlayerObject player, PlayerObject opponent, MiddleGround middleGround, Deck mainDeck){
        this.player=player;
        this.opponent=opponent;
        this.middleGround=middleGround;
        matchStatusEnum=MatchStatusEnum.PLAYER_TURN;
        this.mainDeck=mainDeck;
    }

    public PlayerObject getPlayer() {
        return player;
    }

    public void setPlayer(PlayerObject player) {
        this.player = player;
    }

    public PlayerObject getOpponent() {
        return opponent;
    }

    public void setOpponent(PlayerObject opponent) {
        this.opponent = opponent;
    }

    public MiddleGround getMiddleGround() {
        return middleGround;
    }

    public void setMiddleGround(MiddleGround middleGround) {
        this.middleGround = middleGround;
    }

    public MatchStatusEnum getMatchStatusEnum() {
        return matchStatusEnum;
    }

    public void setMatchStatusEnum(MatchStatusEnum matchStatusEnum) {
        this.matchStatusEnum = matchStatusEnum;
    }

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(Deck mainDeck) {
        this.mainDeck = mainDeck;
    }

    public void begin(){
        Collections.shuffle(mainDeck.getCardList());
        Stack<Card> playerCardStack=new Stack<>();
        Stack<Card> opponentCardStack=new Stack<>();

        for (int i=0;i< 26;i++){
            playerCardStack.push(mainDeck.getCardList().pop());

        }
        for (int i= 0;i< 26;i++){
            opponentCardStack.push(mainDeck.getCardList().pop());
        }

        player.getDeck().setCardList(playerCardStack);
        opponent.getDeck().setCardList(opponentCardStack);
    }
}
