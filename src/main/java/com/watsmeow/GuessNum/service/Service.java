package com.watsmeow.GuessNum.service;


import com.watsmeow.GuessNum.model.Round;

public class Service implements ServiceInterface {

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

    public static void getRoundsByGameID(int gameID) {
        //GET to retrieve a list of all rounds for gameID sorted by time
        //takes in
        //returns list from service layer, which uses RoundsDao
    }
}
