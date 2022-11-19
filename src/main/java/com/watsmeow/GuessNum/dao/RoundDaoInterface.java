package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Round;

import java.util.List;

public interface RoundDaoInterface {

    List<Round> getRoundsByGameID(int gameID);

    Round createRound(Round round);
}
