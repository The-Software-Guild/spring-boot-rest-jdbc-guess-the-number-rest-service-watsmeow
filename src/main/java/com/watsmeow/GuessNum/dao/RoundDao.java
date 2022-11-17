package com.watsmeow.GuessNum.dao;

import org.springframework.stereotype.Repository;

@Repository
public class RoundDao implements RoundDaoInterface {

    public static void getRoundsByGameID(int gameID) {
        //GET to retrieve a list of all rounds for gameID sorted by time
        //takes in
        //returns list from service layer, which uses RoundsDao
    }
}
