package com.watsmeow.GuessNum.entity;

public class Game {

    private int gameID;

    private boolean isFinished;

    private String answer;

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public void setIsFinished(boolean gameStatus) {
        this.isFinished = gameStatus;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsFinished() {
        return isFinished;
    }
}
