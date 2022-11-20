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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameDaoTests extends TestCase {

    @Autowired
    GameDao gameDao;

    @Test
    public void testBeginGame() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        gameDao.beginGame(game);
        Assertions.assertTrue(gameDao.listAllGames().size() > 0);
    }

    @Test
    public void getGameByID() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        game = gameDao.beginGame(game);
        int gameID = game.getGameID();
        Game returnedGame = gameDao.getGameByID(gameID);
        int returnedID = returnedGame.getGameID();
        Assertions.assertTrue(gameID == returnedID);
    }

    @Test
    public void testUpdateGame() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        game = gameDao.beginGame(game);
        gameDao.updateGame(game);
        Game returnedGame = gameDao.getGameByID(game.getGameID());
        Assertions.assertEquals(returnedGame.getIsFinished(), true);
    }

    @Test
    public void testListAllGames() {
        Game game = new Game();
        game.setIsFinished(false);
        game.setAnswer("1423");
        gameDao.beginGame(game);
        Assertions.assertTrue(gameDao.listAllGames().size() > 0);
    }
}
