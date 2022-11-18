package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Game;

import java.util.List;

public interface GameDaoInterface {
    List<Game> listAllGames();

    Game getGameByID(int gameID);

    Game beginGame(Game game);
}
