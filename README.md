# REDZ-red-tracker-app

## Presentation

### What have you been learning over the last 4 weeks?
Sql, flask, 

### Describe the challenge - what were you asked to do?

### How did you plan?
No team leader, Github issues

### Did you divide roles, e.g. backend or frontend?
Backend, frontend, working in pairs

### Did you use pair-programming?
- Pair programming + regularly checking each other's work

### Maybe describe project management tools you used e.g. trello
- We used Github issues

### Maybe describe your git branching strategy: feature, dev, prod etc
- named branches -> dev
- + screenshot
 
### What features made it into scope for the Minimum Viable Product?
- Browse a portfolio
- View the performance of the portfolio (ideally in some graphical manner)
- Add items to the portfolio
- Remove items from the portfolio

### Which features were left out?
- Better historical portfolio
- Multiple users
  
### Did you manage to iterate and add extra features?
- Top gainers/losers
- Transaction history
- History of the portfolio

### Diagram of the app architecture showing the different technologies
- Spring boot, flask (yfinance), MySQL, React

### Diagram of your SQL schema
- Any other options you considered and why you made the choices you did

### Show how the controller layer / business logic interacts with the DB
- screenshot, repositories

### DEMO

### Describe the challenges, and how you overcame those that you resolved
- backend: Getting Yahoo api working, getting started
- frontend: connecting the backend to the frontend

### How you would plan to overcome any that are still outstanding
- 

### What would you have done differently if you got to start over again?
- Start frontend implementation sooner
- Changing the database all the time

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
- `CREATE TABLE IF NOT EXISTS historical(
id integer primary key auto_increment,
portfolio_date TIMESTAMP NOT NULL,
worth double not null
);`
- `insert into users (username, cash) values ("tester", 500000);`

### Queries to have on hand
- check all stocks: `select * from portfolio_item;`
- check users: `select * from users;`
- check transactions: `select * from transactions;`
- clear all stocks: `delete from portfolio_item;`

  
