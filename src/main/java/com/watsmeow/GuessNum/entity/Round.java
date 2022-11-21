package com.watsmeow.GuessNum.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

//Round entity
public class Round {

    private int id;
    private int gameID;

    private Timestamp timeStamp;
    private String guess;

    private String guessResult;

    public Round (String guess, Timestamp timeStamp, String guessResult) {
        this.timeStamp = timeStamp;
        this.guessResult = guessResult;
        this.guess = guess;
    }

    public void setRoundID(int id) {
        this.id = id;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getGuess() {
        return guess;
    }

    public int getRoundByID() {
        return id;
    }

    public int getGameID() {
        return gameID;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public String getGuessResult() {
        return guessResult;
    }

}
