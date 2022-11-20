package com.watsmeow.GuessNum.DaoTests;

import com.watsmeow.GuessNum.TestApplicationConfiguration;
import com.watsmeow.GuessNum.dao.GameDao;
import com.watsmeow.GuessNum.dao.RoundDao;
import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.Timestamp;
import java.util.Calendar;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoTests extends TestCase {

    @Autowired
    RoundDao roundDao;

    @Autowired
    GameDao gameDao;


//    @org.junit.jupiter.api.BeforeAll
//    public static void SetUpClass() {
//
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() {
//
//    }
//
//    @org.junit.jupiter.api.BeforeEach
//    public void setUp()  {
//
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    public void tearDown() {
//
//    }

    @Test
    public void testCreateRound() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        game = gameDao.beginGame(game);
        Calendar calendar = Calendar.getInstance();
        Timestamp timeOfGuess = new Timestamp(calendar.getTime().getTime());
        Round round = new Round("1234", timeOfGuess, "e:1:p:3");
        int gameID = game.getGameID();
        round.setGameID(gameID);
        roundDao.createRound(round);
        Assertions.assertTrue(round.getGuess().equals("1234"));
    }

    @Test
    public void testGetRoundsByGameID() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        game = gameDao.beginGame(game);
        Calendar calendar = Calendar.getInstance();
        Timestamp timeOfGuess = new Timestamp(calendar.getTime().getTime());
        Round round = new Round("1234", timeOfGuess, "e:1:p:3");
        int gameID = game.getGameID();
        round.setGameID(gameID);
        roundDao.createRound(round);
        Assertions.assertTrue(roundDao.getRoundsByGameID(game.getGameID()).size() > 0);
    }

}
