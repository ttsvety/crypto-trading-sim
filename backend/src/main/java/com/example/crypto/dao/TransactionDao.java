package com.example.crypto.dao;

import com.example.crypto.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class TransactionDao {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, symbol, quantity, price, total, type, timestamp) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transaction.getUserId(),
                transaction.getSymbol(),
                transaction.getQuantity(),
                transaction.getPrice(),
                transaction.getTotal(),
                transaction.getType(),
                transaction.getTimestamp()
        );
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY timestamp DESC";
        return jdbcTemplate.query(sql, new Object[]{userId}, this::mapRowToTransaction);
    }

    private Transaction mapRowToTransaction(ResultSet rs, int rowNum) throws SQLException {
        Transaction t = new Transaction();
        t.setId(rs.getInt("id"));
        t.setUserId(rs.getInt("user_id"));
        t.setSymbol(rs.getString("symbol"));
        t.setQuantity(rs.getDouble("quantity"));
        t.setPrice(rs.getDouble("price"));
        t.setTotal(rs.getDouble("total"));
        t.setType(rs.getString("type"));
        t.setTimestamp(rs.getTimestamp("timestamp"));
        return t;
    }

}
