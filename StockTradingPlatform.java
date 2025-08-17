import java.util.*;

// Stock class
class Stock {
    private String symbol;
    private String companyName;
    private double price;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
    }

    public String getSymbol() { return symbol; }
    public String getCompanyName() { return companyName; }
    public double getPrice() { return price; }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return symbol + " (" + companyName + ") : $" + price;
    }
}

// Portfolio class
class Portfolio {
    private Map<String, Integer> holdings; // stock symbol -> quantity
    private double cashBalance;

    public Portfolio(double initialBalance) {
        this.cashBalance = initialBalance;
        this.holdings = new HashMap<>();
    }

    public void buyStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("❌ Quantity must be greater than zero.");
            return;
        }
        double cost = stock.getPrice() * quantity;
        if (cost > cashBalance) {
            System.out.println("❌ Insufficient balance to buy " + stock.getSymbol());
            return;
        }
        cashBalance -= cost;
        holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
        System.out.println("✅ Bought " + quantity + " shares of " + stock.getSymbol());
    }

    public void sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            System.out.println("❌ Quantity must be greater than zero.");
            return;
        }
        if (!holdings.containsKey(stock.getSymbol()) || holdings.get(stock.getSymbol()) < quantity) {
            System.out.println("❌ Not enough shares to sell " + stock.getSymbol());
            return;
        }
        double revenue = stock.getPrice() * quantity;
        cashBalance += revenue;
        holdings.put(stock.getSymbol(), holdings.get(stock.getSymbol()) - quantity);
        if (holdings.get(stock.getSymbol()) == 0) {
            holdings.remove(stock.getSymbol());
        }
        System.out.println("✅ Sold " + quantity + " shares of " + stock.getSymbol());
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n📊 Portfolio Summary:");
        System.out.printf("Cash Balance: $%.2f\n", cashBalance);
        double totalValue = cashBalance;

        for (String symbol : holdings.keySet()) {
            int qty = holdings.get(symbol);
            if (market.containsKey(symbol)) {
                double stockValue = market.get(symbol).getPrice() * qty;
                totalValue += stockValue;
                System.out.printf("%s - Shares: %d | Value: $%.2f\n", symbol, qty, stockValue);
            }
        }
        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }
}

// User class
class User {
    private String name;
    private Portfolio portfolio;

    public User(String name, double initialBalance) {
        this.name = name;
        this.portfolio = new Portfolio(initialBalance);
    }

    public Portfolio getPortfolio() { return portfolio; }
    public String getName() { return name; }
}

// Main Trading Platform
public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Market Data
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 150.0));
        market.put("GOOG", new Stock("GOOG", "Alphabet Inc.", 2800.0));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 700.0));
        market.put("AMZN", new Stock("AMZN", "Amazon Inc.", 3300.0));

        // Create user
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        User user = new User(name, 10000.0);

        boolean running = true;
        while (running) {
            System.out.println("\n==== Stock Trading Menu ====");
            System.out.println("1. View Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            if (!sc.hasNextInt()) {
                System.out.println("❌ Please enter a valid number!");
                sc.next(); // clear invalid input
                continue;
            }
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n📈 Market Data:");
                    for (Stock stock : market.values()) {
                        System.out.println(stock);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol to buy: ");
                    String buySymbol = sc.next().toUpperCase();
                    if (!market.containsKey(buySymbol)) {
                        System.out.println("❌ Invalid stock symbol");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("❌ Invalid quantity!");
                        sc.next();
                        break;
                    }
                    int buyQty = sc.nextInt();
                    user.getPortfolio().buyStock(market.get(buySymbol), buyQty);
                    break;

                case 3:
                    System.out.print("Enter stock symbol to sell: ");
                    String sellSymbol = sc.next().toUpperCase();
                    if (!market.containsKey(sellSymbol)) {
                        System.out.println("❌ Invalid stock symbol");
                        break;
                    }
                    System.out.print("Enter quantity: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("❌ Invalid quantity!");
                        sc.next();
                        break;
                    }
                    int sellQty = sc.nextInt();
                    user.getPortfolio().sellStock(market.get(sellSymbol), sellQty);
                    break;

                case 4:
                    user.getPortfolio().displayPortfolio(market);
                    break;

                case 5:
                    running = false;
                    System.out.println("👋 Exiting... Goodbye " + user.getName() + "!");
                    break;

                default:
                    System.out.println("❌ Invalid choice");
            }
        }
        sc.close();
    }
}
