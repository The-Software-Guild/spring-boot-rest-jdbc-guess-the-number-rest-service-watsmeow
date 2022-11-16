package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.model.Round;

public class GameDao implements GameDaoInterface {

    public static void beginGame(){
        //sends POST request
        //returns an int = gameID from service layer
        //annotations return code 201 CREATED
    }

    public static void guessNumber(Round round) {
        //sends POST request with number guess as JSON, JSON is serialized into object behind scenes
        //service layer calculates results of guess
        //returns Round object with results
    }

    public static void listAllGames() {
        //sends GET request to retrieve list of all games in database
        //returns list from the service layer, which calls the GameDao
        //in progress games cannot display answer
    }

    public static void getGameByID(int gameID) {
        //GET to return game object
        //takes in gameID
        //returns game object from the service layer, which calls the GameDao
    }
}
