package com.watsmeow.GuessNum.controller;

import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import com.watsmeow.GuessNum.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    ServiceInterface service;

    // GET request to get a list of all games, both complete and incomplete
    @GetMapping("/allGames")
    public List<Game> listAllGames() {
        return service.listAllGames();
    }


    // GET request to get a specific game by ID
    @GetMapping("/game/{gameID}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameID) {
        Game game = service.getGameByID(gameID);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

    // POST request to begin a new game
    @PostMapping("/beginGame")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame() {
        Game returnedGame = service.beginGame();
        return returnedGame.getGameID();
    }

    // POST request to make a guess
    @PostMapping("/guess")
    public Round guessNumber(@RequestBody Round round) {
        return service.guessNumber(round);
    }

    // GET request to get all rounds associated with a game ID
    @GetMapping("/rounds/{gameID}")
    public List<Round> getRoundsByGameID(@PathVariable int gameID) {
        return service.getRoundsByGameID(gameID);
    }
}
