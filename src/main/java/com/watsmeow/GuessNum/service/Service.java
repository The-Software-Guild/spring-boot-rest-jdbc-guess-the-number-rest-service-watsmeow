package com.watsmeow.GuessNum.service;


import com.watsmeow.GuessNum.dao.GameDaoInterface;
import com.watsmeow.GuessNum.dao.RoundDaoInterface;
import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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

    public Round guessNumber(Round round) {
        int gameID = round.getGameID();
        round.setGameID(gameID);
        String guess = round.getGuess();
        Game game = gameDao.getGameByID(gameID);
        String answer = game.getAnswer();
        round = checkGuess(guess, answer, game);
        return roundDao.createRound(round);
    }

    public Round checkGuess(String guess, String answer, Game game) {
        int partialMatch = 0;
        int exactMatch = 0;
        String result = "";
        if (guess.length() != 4) {
            result = "e:0:p:0";
        }
        if (guess == answer) {
            result = "e:4:p:0 - You Win!";
            game.setIsFinished(true);
            gameDao.updateGame(game);
        } else if (guess != answer) {
            Map<Character, Integer> indices = new HashMap<>();
            for (int i = 0; i < 4; i++) {
                indices.put(answer.charAt(i), +1);
                if (guess.charAt(i) == answer.charAt(i)) {
                    exactMatch += 1;
                    indices.get(answer.charAt(i), -1);
                }
            }
            // it is a partial if it exists but is in the wrong position
            for (int i = 0; i < 4; i++) {
                String currentIndex = String.valueOf(guess.charAt(i));
                if (
                        //the key exists and the value is greater than 0, it's a partial
                ),  {
                    partialMatch += 1;
                    indices.get(answer.charAt(i), -1);
                }
            }
            result = String.format("e:%s:p:%s",
                    exactMatch,
                    partialMatch);
        }
        Calendar calendar = Calendar.getInstance();
        Timestamp timeOfGuess = new Timestamp(calendar.getTime().getTime());
        return new Round(guess, timeOfGuess, result);
    }

    public List<Round> getRoundsByGameID(int gameID) {
        return roundDao.getRoundsByGameID(gameID);
    }
}
