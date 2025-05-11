package com.example.crypto.dao;

import com.example.crypto.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    private final JdbcTemplate jdbcTemplate;

    public UserDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public User getUserById(int id){
        String sql = "SELECT * FROM users WHERE id = ?";    
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNUm) -> { //– Това е анонимен клас, който конвертира един ред от резултата (ResultSet) в Java обект (User). rs = ResultSetrowNum = номер на реда (рядко ти трябва)
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setBalance(rs.getDouble("balance"));
            return user;
        });
    }

    public void updateBalance(int id, double newBalance){
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        jdbcTemplate.update(sql, newBalance, id);
    }

    public void createInitialBalance(int userId) {
    String sqlCheck = "SELECT COUNT(*) FROM users WHERE id = ?";
    Integer count = jdbcTemplate.queryForObject(sqlCheck, Integer.class, userId);
    
    if (count != null && count == 0) {
        String sqlInsert = "INSERT INTO users (id, balance) VALUES (?, ?)";
        jdbcTemplate.update(sqlInsert, userId, 10000.0);
    }
}

public void resetBalance(int userId) {
    String sql = "UPDATE users SET balance = 10000 WHERE id = ?";
    jdbcTemplate.update(sql, userId);
}

public void clearHoldings(int userId) {
        String sql = "DELETE FROM holdings WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

public void clearTransaction(int userId) {
        String sql = "DELETE FROM transactions WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

}
