//package com.company.view;
//
//import com.company.models.users.User;
//
//import java.io.FileNotFoundException;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//public class TableView {
//
//    public static void displayAllCategoriesByCategoryList(CategoryList categoryList) throws FileNotFoundException {
//
//        System.out.println(DataHandler.stringFromFile("src/main/resources/graphics/categoriesLogo.txt"));
//
//        ArrayList<Category> listOfCategory = categoryList.getCategoryList();
//
//        String idTitle = "Id";
//        String nameTitle = "Name";
//        String isAvailableTitle = "Availability";
//
//        String[] headers = {idTitle, nameTitle, isAvailableTitle};
//        Object[][] data = new Object[listOfCategory.size()][headers.length];
//
//        for (int i = 0; i < listOfCategory.size(); i++) {
//            Category category = listOfCategory.get(i);
//            data[i][0] = category.getId();
//            data[i][1] = category.getName();
//            data[i][2] = category.isAvailable();
//        }
//
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//
//    }
//
//    public static void displayAllCategories() throws FileNotFoundException {
//        System.out.println(DataHandler.stringFromFile("src/main/resources/graphics/categoriesLogo.txt"));
//
//        CategoryList categoryList = new CategoryList();
//
//        ArrayList<Category> listOfCategory = categoryList.getCategoryList();
//
//        String idTitle = "Id";
//        String nameTitle = "Name";
//        String isAvailableTitle = "Availability";
//
//        String[] headers = {idTitle, nameTitle, isAvailableTitle};
//        Object[][] data = new Object[listOfCategory.size()][headers.length];
//
//        for (int i = 0; i < listOfCategory.size(); i++) {
//            Category category = listOfCategory.get(i);
//            data[i][0] = category.getId();
//            data[i][1] = category.getName();
//            data[i][2] = category.isAvailable();
//        }
//
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }
//
//    public static void displayAllOrders() throws FileNotFoundException {
//        System.out.println(DataHandler.stringFromFile("src/main/resources/graphics/ordersLogo.txt"));
//
//        OrderList ordersList = new OrderList(new OrdersDAO().readAllOrders());
//
//        List<Order> orders = ordersList.getOrders();
//
//        String idTitle = "Id";
//        String userIdTitle = "UserId";
//        String statusTitle = "Status";
//        String dateTitle = "Date";
//
//        String[] headers = {idTitle, userIdTitle, statusTitle, dateTitle};
//        Object[][] data = new Object[orders.size()][headers.length];
//
//        for (int i = 0; i < orders.size(); i++) {
//            Order order = orders.get(i);
//            data[i][0] = order.getId();
//            data[i][1] = order.getUserId();
//            data[i][2] = order.getStatus();
//            data[i][3] = order.getDate();
//        }
//
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//    }
//
//    public static void displayBasketPretty(Basket basket) throws FileNotFoundException {
//
//        System.out.println(DataHandler.stringFromFile("src/main/resources/graphics/basketLogo.txt"));
//
//        HashMap<Product, Integer> basketM = basket.getProducts();
//
//        Set<Product> keyS = basketM.keySet();
//        List<Product> keyList = new ArrayList<>(keyS);
//
//        String idTitle = "Id";
//        String nameTitle = "Name";
//        String priceTitle = "Price";
//        String amountTitle = "Amount";
//        String totalValue = "Total Value";
//
//        String[] headers = {idTitle, nameTitle, priceTitle, amountTitle, totalValue};
//        Object[][] data = new Object[keyList.size()][headers.length];
//
//        for (int i = 0; i < keyList.size(); i++) {
//            Product product = keyList.get(i);
//            data[i][0] = product.getId();
//            data[i][1] = product.getName();
//            data[i][2] = product.getPrice();
//            data[i][3] = basketM.get(product);
//            data[i][4] = basketM.get(product) * product.getPrice();
//
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
//        String totalPriceMessage = "Total price of this basket: ";
//        System.out.println("" + Chalk.on(totalPriceMessage).green() + Chalk.on(String.valueOf(basket.calculateTotalValue())).magenta().bold() + " PLN.\n");
//    }
//
//    public static void displayProductsByProductsList(ProductList productList, User user) {
//        List<Product> products = productList.getProducts();
//
//        String idTitle = "Id";
//        String nameTitle = "Name";
//        String priceTitle = "Price";
//        String amountTitle = "Amount";
//        String isAvailableTitle = "Availability";
//        String categoryTitle = "Category";
//        String ratingTitle = "Rating";
//
//        String[] headers = {idTitle, nameTitle, priceTitle, amountTitle, isAvailableTitle, categoryTitle, ratingTitle};
//        Object[][] data = new Object[products.size()][headers.length];
//
//        for (int i = 0; i < products.size(); i++) {
//            Product product = products.get(i);
//            data[i][0] = product.getId();
//            data[i][1] = product.getName();
//            data[i][2] = product.getPrice();
//            data[i][3] = product.getAmount();
//            data[i][4] = product.isAvailable();
//            data[i][5] = product.getCategory();
//            data[i][6] = product.getRating();
//
//        }
//        System.out.println(FlipTableConverters.fromObjects(headers, data));
////        String totalPriceMessage = "Total price of this basket: ";
////        System.out.println("" + Chalk.on(totalPriceMessage).green() + Chalk.on(String.valueOf(basket.calculateTotalValue())).magenta().bold() + " PLN.\n");
//
//
//
//
//    }
//
//    public static void displayProducts(User user) throws FileNotFoundException {
//
//        System.out.println(DataHandler.stringFromFile("src/main/resources/graphics/productListLogo.txt"));
//
//        String query = "SELECT * FROM Products;";
//        try {
////            System.out.println("\n");
////            DatabaseSqlite ds = new DatabaseSqlite();
////            ResultSet rs = ds.executeQuery(query);
//
//            String lineAbove = "┌" + TerminalView.repeatString("-", 94) + "┐";
//            System.out.println(lineAbove);
//
//            String idTitle = "Id";
//            String nameTitle = "Name";
//            String priceTitle = "Price";
//            String amountTitle = "Amount";
//            String isAvailableTitle = "Availability";
//            String categoryTitle = "Category";
//            String ratingTitle = "Rating";
//
//            System.out.format("| %4s | %15s | %10s | %6s | %18s | %13s | %10s |\n"
//                    , idTitle, nameTitle, priceTitle, amountTitle, isAvailableTitle
//                    , categoryTitle, ratingTitle);
//
//            String lineAfterTitles = "├" + TerminalView.repeatString("-", 94) + "┤";
//            System.out.println(lineAfterTitles);
//
//            ResultSet rs = new DatabaseSqlite().executeQuery(query);
//            while (rs.next()) {
//
//                int idInt = rs.getInt("Id");
//                String id = String.valueOf(idInt);
//
//                String name = rs.getString("Name");
//
//                double priceDouble = rs.getDouble("Price");
//                String price = String.valueOf(priceDouble);
//
//                int amountInt = rs.getInt("Amount");
//                String amount = String.valueOf(amountInt);
//
//                boolean isAvailableBool = (rs.getInt("IsAvailable") != 0);
//                String isAvailable = convertIsAvailableToString(isAvailableBool);
//
//                // TODO: Optimilize!
//                int categoryInt = (rs.getInt("CategoryId"));
//                String category = new CategoryList().getCategoryNameById(categoryInt);
//
//                int ratingInt = rs.getInt("Rating");
//                String rating = String.valueOf(ratingInt);
//
//                if (user.getIsAdmin() == 1) {
//                    boolean isInStorage = (rs.getInt("IsInStorage") != 0);
//                }
////                                      id      name    price  amou   avail   categ   rating
////                String productTable = "|%1$-3s|%2$-20s|%3$-8s|%4$-6s|%5$-18s|%6$-15s|%7$-4s|\n";
////                System.out.printf(productTable, name, id, price, amount, isAvailable, category, rating);
//
//                System.out.format("| %4s | %15s | %10s | %6s | %18s | %13s | %10s |\n", id, name, price, amount, isAvailable, category, rating);
//            }
//            String lineBelow = "└" + TerminalView.repeatString("-", 94) + "┘";
//            System.out.println(lineBelow);
//        } catch (SQLException e) {
//            System.err.println("Error! Displaying all products failed! ");
//            e.printStackTrace();
//        }
//    }
//
//    public static String convertIsAvailableToString(boolean isAvailableBool) {
//        String convertedString = "";
//
//        if (isAvailableBool == true) {
//            return convertedString = "Available";
//        } else {
//            return convertedString = "Not available";
//        }
//    }
//
//    public String convertIsInStorageToString(boolean isInStorageBool) {
//        String convertedString = "";
//
//        if (isInStorageBool == true) {
//            return convertedString = "In storage";
//        } else {
//            return convertedString = "Not in storage";
//        }
//    }
//
//
////    public static void displayProductTable(ProductList productList, User user) {
////        List<Product> products = productList.getProducts();
////
////        int id;
////        String productName;
////        double price;
////        int amount;
////        boolean isAvailable;
////        int category;
////        int rating;
////
////        for (Product product : products) {
////
////        }
////
////    }
//
////    public void displayPlayerStatistics() {
//////        TODO:
////        String[] headers = {"Remaining Cards", "Coins", "Experience", "Level", "    ", "Cards in pot"};
////        Object[][] data = {
////                {this.health, this.coins, this.experience, this.level, "    ", potCardsToString()}
////        };
////        System.out.println(FlipTableConverters.fromObjects(headers, data));
////    }
//}
