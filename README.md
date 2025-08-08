# REDZ-red-tracker-app
## SQL queries
**Add these in a query tab in MySQL Workbench for ease of access.**
### Queries to run exactly once each
- `create database if not exists financeportfolio;`
- `use financeportfolio;`
- `CREATE TABLE IF NOT EXISTS portfolio_item(
    symbol VARCHAR(10) PRIMARY KEY,
    company_name VARCHAR(80),
    shares DOUBLE NOT NULL,
    purchase_price DOUBLE NOT NULL
);`
- `CREATE TABLE IF NOT EXISTS users(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(20) NOT NULL,
    cash INTEGER NOT NULL
);`
- `CREATE TABLE IF NOT EXISTS transactions(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    symbol VARCHAR(10) NOT NULL,
    company_name VARCHAR(80),
    shares DOUBLE NOT NULL,
    purchase_price DOUBLE NOT NULL,
    cash DOUBLE NOT NULL,
    purchase_date TIMESTAMP NOT NULL,
    foreign key (symbol) references portfolio_item(symbol)
);`
- `insert into users (username, cash) values ("tester", 500000);`

### Queries to have on hand
- check all stocks: `select * from portfolio_item;`
- check users: `select * from users;`
- check transactions: `select * from transactions;`
- clear all stocks: `delete from portfolio_item;`
