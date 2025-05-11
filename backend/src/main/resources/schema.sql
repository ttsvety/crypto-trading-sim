CREATE DATABASE crypto_sim;

CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    balance DECIMAL(12, 2) DEFAULT 10000
);

CREATE TABLE IF NOT EXISTS transactions(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    type VARCHAR(10), --BUY or SELL
    symbol VARCHAR(10),
    quantity DECIMAL(18, 8),
    price DECIMAL(12, 2),
    total DECIMAL(12, 2),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS holdings(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    symbol VARCHAR(10),
    amount DECIMAL(18, 8),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users (username, balance) VALUES ('testuser', 10000.00);
