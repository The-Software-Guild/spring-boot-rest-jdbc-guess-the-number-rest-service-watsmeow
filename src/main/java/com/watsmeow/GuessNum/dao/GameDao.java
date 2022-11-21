package com.watsmeow.GuessNum.dao;

import com.watsmeow.GuessNum.entity.Game;
import com.watsmeow.GuessNum.entity.Round;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class GameDao implements GameDaoInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Retrieves a list of all games from DB
    public List<Game> listAllGames() {
        final String sql = "SELECT * FROM games;";
        return jdbcTemplate.query(sql, new GameMapper());
    }

    // Retrieves a single game from DB using the game ID
    @Override
    public Game getGameByID(int gameID) {
        final String sql = "SELECT id, answer, isFinished FROM Games WHERE id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, new GameMapper(), gameID);
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    // Inserts a new game into the DB
    public Game beginGame(Game game){
        final String sql = "INSERT INTO Games (answer, isFinished) VALUES (?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con -> {
            PreparedStatement statement = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, game.getAnswer());
            statement.setBoolean(2, game.getIsFinished());
            return statement;
        }), keyHolder);
        game.setGameID(keyHolder.getKey().intValue());
        return game;
    }

    // Updates the DB to mark a game as complete if it is complete
    public void updateGame(Game game) {
        final String sql = "UPDATE Games SET isFinished = true WHERE id = ?";
        jdbcTemplate.update((con -> {
            PreparedStatement statement = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, game.getGameID());
            return statement;
        }));
    }

    // Bringing the games in
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
