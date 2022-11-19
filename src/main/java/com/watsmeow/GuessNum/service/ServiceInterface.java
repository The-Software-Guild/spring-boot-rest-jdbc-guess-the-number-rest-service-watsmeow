package com.watsmeow.GuessNum.service;

import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;

import java.util.List;

public interface ServiceInterface {
    List<Game> listAllGames();

    Game getGameByID(int gameID);

    Game beginGame();

    Round guessNumber(Round round);

    List<Round> getRoundsByGameID(int gameID);
}
