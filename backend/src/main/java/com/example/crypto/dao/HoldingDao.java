package com.example.crypto.dao;

import com.example.crypto.model.Holding;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HoldingDao {

    private final JdbcTemplate jdbcTemplate;

    public HoldingDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holding> getHoldingsByUserId(int userId) {
        String sql = "SELECT * FROM holdings WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Holding holding = new Holding();
            holding.setId(rs.getInt("id"));
            holding.setUserId(rs.getInt("user_id"));
            holding.setSymbol(rs.getString("symbol"));
            holding.setAmount(rs.getDouble("amount"));
            return holding;
        });
    }

    public Holding getHoldingByUserIdAndSymbol(int userId, String symbol) {
        String sql = "SELECT * FROM holdings WHERE user_id = ? AND symbol = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{userId, symbol}, (rs, rowNum) -> {
                Holding holding = new Holding();
                holding.setId(rs.getInt("id"));
                holding.setUserId(rs.getInt("user_id"));
                holding.setSymbol(rs.getString("symbol"));
                holding.setAmount(rs.getDouble("amount"));
                return holding;
            });
        } catch (Exception e) {
            // Ако няма резултат, връща null или хвърля изключение
            return null;
        }
    }

    public void addHolding(Holding holding) {
        String sql = "INSERT INTO holdings (user_id, symbol, amount) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, holding.getUserId(), holding.getSymbol(), holding.getAmount());
    }

    public void updateHolding(Holding holding) {
        String sql = "UPDATE holdings SET amount = ? WHERE user_id = ? AND symbol = ?";
        jdbcTemplate.update(sql, holding.getAmount(), holding.getUserId(), holding.getSymbol());
    }

    public void deleteHolding(int userId, String symbol) {
        String sql = "DELETE FROM holdings WHERE user_id = ? AND symbol = ?";
        jdbcTemplate.update(sql, userId, symbol);
    }
}
