package com.example.crypto.service;

import com.example.crypto.dao.TransactionDao;
import com.example.crypto.dao.UserDao;
import com.example.crypto.model.Transaction;
import com.example.crypto.model.Holding;
import com.example.crypto.model.User;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;
    private final HoldingService holdingService;
    private final UserDao userDao;

    public TransactionService(TransactionDao transactionDao, HoldingService holdingService, UserDao userDao) {
        this.transactionDao = transactionDao;
        this.holdingService = holdingService;
        this.userDao = userDao;
    }

    public List<Transaction> getTransactionsByUserId(int userId) {
        return transactionDao.getTransactionsByUserId(userId);
    }
    

    public void addTransaction(Transaction transaction) {
        System.out.println("Transaction processing:" + transaction);
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
        transaction.setTotal(transaction.getQuantity() * transaction.getPrice());

        if (transaction.getQuantity() <= 0 || transaction.getPrice() <= 0 || transaction.getTotal() <= 0) {
            throw new IllegalArgumentException("Invalid transaction values.");
        }

        User user = userDao.getUserById(transaction.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("The user does not exist.");
        }

        String type = transaction.getType().toUpperCase();
        if ("BUY".equals(type)) {
            if (user.getBalance() < transaction.getTotal()) {
                throw new IllegalArgumentException("Insufficient balance.");
            }
            userDao.updateBalance(user.getId(), user.getBalance() - transaction.getTotal());
            holdingService.addOrUpdateHolding(user.getId(), transaction.getSymbol(), transaction.getQuantity());

        } else if ("SELL".equals(type)) {
            Holding existing = holdingService.getHoldingByUserIdAndSymbol(user.getId(), transaction.getSymbol());
            if (existing == null || existing.getAmount() < transaction.getQuantity()) {
                throw new IllegalArgumentException("Insufficient quantity.");
            }
            userDao.updateBalance(user.getId(), user.getBalance() + transaction.getTotal());
            holdingService.addOrUpdateHolding(user.getId(), transaction.getSymbol(), -transaction.getQuantity());

        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + transaction.getType());
        }

        transactionDao.addTransaction(transaction);
        System.out.println("The transaction has been successfully recorded.");
    }

}
