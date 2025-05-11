package com.example.crypto.service;

import com.example.crypto.dao.UserDao;
import com.example.crypto.model.User;
import org.springframework.stereotype.Service;
import com.example.crypto.service.TransactionService;

@Service
public class UserService {

    private final UserDao userDao;
    

    public UserService(UserDao userDao){
        this.userDao = userDao;
    }

    public User getUserById(int id){
        // Ако потребителят не съществува, създава го с баланс 10000.00
        userDao.createInitialBalance(id);
        return userDao.getUserById(id);
    }

    public void updateBalance(int id, double newBalance){
        userDao.updateBalance(id, newBalance);
    }

    public void resetBalance(int userId){
    userDao.resetBalance(userId);
    userDao.clearHoldings(userId);
    userDao.clearTransaction(userId);
}

    
}
