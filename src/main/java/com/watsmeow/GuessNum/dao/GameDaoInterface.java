package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Game;

import java.util.List;

public interface GameDaoInterface {

    /**
     * Retrieves a list of all games from DB
     */
    List<Game> listAllGames();

    /**
     * Retrieves a single game from DB using the game ID
     */
    Game getGameByID(int gameID);

    /**
     * Inserts a new game into the DB
     */
    Game beginGame(Game game);

    /**
     * Updates the DB to mark a game as complete if it is complete
     */
    void updateGame(Game game);
}
