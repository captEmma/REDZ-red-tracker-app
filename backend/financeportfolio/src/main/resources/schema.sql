CREATE DATABASE IF NOT EXISTS financeportfolio;

USE financeportfolio;

CREATE TABLE IF NOT EXISTS portfolio_item(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(10) NOT NULL,
    purchase_price DOUBLE NOT NULL,
    purchase_date DATETIME NOT NULL
);