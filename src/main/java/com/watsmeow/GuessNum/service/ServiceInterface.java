package com.watsmeow.GuessNum.service;

import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;

import java.util.List;

public interface ServiceInterface {

    /**
     * Gets a list of all games, if game is incomplete ensures answer is not shared with user
     */
    List<Game> listAllGames();

    /**
     * Gets a specific game by id, if game is incomplete ensures answer is not shared with user
     */
    Game getGameByID(int gameID);

    /**
     * Inserts a new round into the DB, calls checkGuess, returns a round
     */
    Game beginGame();

    /**
     * Inserts a new round into the DB, calls checkGuess, returns a round
     */
    Round guessNumber(Round round);

    /**
     * Lists all rounds associated with a specific game ID
     */
    List<Round> getRoundsByGameID(int gameID);
}
