# StockTradingPlatform
New Repository
The Stock Trading Platform is a Java-based simulation of a real-world trading environment, built using the principles of Object-Oriented Programming (OOP). It provides users with the ability to view live market data, execute buy/sell operations, and track their portfolio performance over time. The application demonstrates how trading systems work in practice, while maintaining simplicity for learning and experimentation.

🔹 Key Features

Market Data Simulation

A set of popular stocks (e.g., AAPL, TSLA, MSFT, AMZN) are available for trading.

Stock prices are updated randomly to mimic real-time market fluctuations.

Users can view the latest stock list with current prices before making decisions.

Buy & Sell Operations

Users start with an initial cash balance (e.g., $10,000).

They can purchase shares of any available stock, provided they have enough funds.

Users can also sell stocks from their portfolio, instantly updating cash and holdings.

Portfolio Management

Displays user’s current cash balance, owned stocks, average cost per share, and market value.

Tracks unrealized gains/losses, giving a clear picture of performance.

Maintains a structured list of holdings using the Holding class.

Transaction History

Every buy/sell operation is logged as a transaction with symbol, quantity, price, and timestamp.

Users can review their trading history to analyze past decisions.

Persistence with File I/O

The portfolio (cash, holdings, and transactions) is saved into a file (portfolio.dat).

When the program is restarted, the user can continue from where they left off.

Demonstrates practical use of serialization in Java.

🔹 OOP Design

The project is built using Object-Oriented Programming (OOP) concepts:

Stock – Represents an individual stock with attributes like symbol, name, and current price.

Market – Maintains a collection of stocks and updates their prices.

Holding – Represents the number of shares and average cost of a stock owned by the user.

Transaction – Stores details of each buy/sell action performed by the user.

Portfolio – Manages user’s cash, holdings, and transactions.

TradingPlatform (Main) – Provides the user interface and ties everything together.

This modular approach makes the system extensible (new features like dividends, short-selling, or database integration can be added easily).

🔹 Learning Outcomes

Demonstrates stock trading logic in a simplified environment.

Applies OOP concepts: abstraction, encapsulation, inheritance (optionally), and polymorphism.

Implements file handling and serialization to persist user data.

Shows how to simulate dynamic market conditions using randomization.

Provides a foundation for web-based trading systems (e.g., upgrading to Spring Boot + REST APIs).
