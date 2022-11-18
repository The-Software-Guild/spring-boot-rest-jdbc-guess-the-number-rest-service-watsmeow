package com.watsmeow.GuessNum.service;


import com.watsmeow.GuessNum.dao.GameDaoInterface;
import com.watsmeow.GuessNum.dao.RoundDaoInterface;
import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class Service implements ServiceInterface {

    @Autowired
    GameDaoInterface gameDao;

    @Autowired
    RoundDaoInterface roundDao;

    public List<Game> listAllGames() {
        List<Game> games = gameDao.listAllGames();
        for (Game game : games) {
            if (!game.getIsFinished()) {
                game.setAnswer("Game is in Progress, Cannot Display Answer");
            }
        }
        return games;
    }

    public Game getGameByID(int gameID) {
        Game game = gameDao.getGameByID(gameID);
        if (game != null && !game.getIsFinished()) {
            game.setAnswer("Game is in Progress, Cannot Display Answer");
        }
        return game;
    }

    public Game beginGame(){
        Game game = new Game();
        Random random = new Random();
        String answer = String.format("%04d", random.nextInt(10000));
        game.setAnswer(answer);
        game.setIsFinished(false);
        return gameDao.beginGame(game);
    }

    public static void guessNumber(Round round) {
        //sends POST request with number guess as JSON, JSON is serialized into object behind scenes
        //service layer calculates results of guess
        //returns Round object with results
    }


    public static void getRoundsByGameID(int gameID) {
        //GET to retrieve a list of all rounds for gameID sorted by time
        //takes in
        //returns list from service layer, which uses RoundsDao
    }
}
