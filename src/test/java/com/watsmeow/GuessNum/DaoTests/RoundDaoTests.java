package com.watsmeow.GuessNum.DaoTests;

import com.watsmeow.GuessNum.TestApplicationConfiguration;
import com.watsmeow.GuessNum.dao.RoundDao;
import com.watsmeow.GuessNum.dao.RoundDaoInterface;
import com.watsmeow.GuessNum.entity.Round;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class RoundDaoTests extends TestCase {

    @Autowired
    RoundDao roundDao;

    public RoundDaoTests() {}

    @org.junit.jupiter.api.BeforeAll
    public static void SetUpClass() {

    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() {

    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp()  {

    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {

    }

    @org.junit.jupiter.api.Test
    public void testCreateRound() {
        Calendar calendar = Calendar.getInstance();
        Timestamp timeOfGuess = new Timestamp(calendar.getTime().getTime());
        Round round = new Round("1234", timeOfGuess, "e:1:p:3");
        int gameID = 777;
        round.setGameID(gameID);
        roundDao.createRound(round);
        Assertions.assertTrue(round.getGuess().equals("1234"));
    }

    @org.junit.jupiter.api.Test
    public void testGetRoundsByGameID() {

    }

}
