package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDao implements GameDaoInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Game> listAllGames() {
        final String sql = "SELECT * FROM games;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    @Override
    public Game getGameByID(int gameID) {
        final String sql = "SELECT id, answer, isFinished FROM games WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

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

    private static final class GameMapper implements RowMapper<Game> {
        public Game mapRow(ResultSet rs, int index) throws SQLException {
            Game game = new Game();
            game.setGameID(rs.getInt("id"));
            game.setAnswer(rs.getString("answer"));
            game.setIsFinished(rs.getBoolean("isFinished"));
            return game;
        }
    }
}
