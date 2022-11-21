package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Round;

import java.util.List;

public interface RoundDaoInterface {

    /**
     * Retrieves a list of all rounds from DB
     */
    List<Round> getRoundsByGameID(int gameID);

    /**
     * Inserts a new round into the rounds table, which is associated with a game by game ID
     */
    Round createRound(Round round);
}
