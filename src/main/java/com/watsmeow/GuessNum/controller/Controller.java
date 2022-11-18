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


    @GetMapping("/allGames")
    public List<Game> listAllGames() {
        return service.listAllGames();
    }

    @GetMapping("/game/{gameID}")
    public ResponseEntity<Game> getGameByID(@PathVariable int gameID) {
        Game game = service.getGameByID(gameID);
        if (game == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(game);
    }

    @PostMapping("/beginGame")
    @ResponseStatus(HttpStatus.CREATED)
    public int beginGame() {
        Game returnedGame = service.beginGame();
        return returnedGame.getGameID();
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
