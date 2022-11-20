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
import java.util.stream.Collectors;

@Repository
public class RoundDao implements RoundDaoInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Round createRound(Round round) {
        final String sql = "INSERT INTO Rounds (gameID, timeStamp, guess, guessResult) VALUES (?, ?, ?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con -> {
            PreparedStatement statement = con.prepareStatement(
                    sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, round.getGameID());
            statement.setTimestamp(2, round.getTimeStamp());
            statement.setString(3, round.getGuess());
            statement.setString(4, round.getGuessResult());
            return statement;
        }), keyHolder);
        round.setRoundID(keyHolder.getKey().intValue());
        return round;
    }

    public List<Round> getRoundsByGameID(int gameID) {
        final String sql = "SELECT * FROM Rounds WHERE gameID = ?;";
        try {
            return jdbcTemplate.queryForStream(sql, new RoundDao.RoundMapper(), gameID)
                    .collect(Collectors.toList());
        } catch(EmptyResultDataAccessException e) {
            return null;
        }
    }

    private static final class RoundMapper implements RowMapper<Round> {
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            Round round = new Round(rs.getString("guess"), rs.getTimestamp("timeStamp"),
                    rs.getString("guessResult"));
            round.setRoundID(rs.getInt("id"));
            round.setGameID(rs.getInt("gameID"));
            return round;
        }
    }
}
