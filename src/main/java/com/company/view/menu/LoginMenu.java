//package com.company.view.menu;
//
//
//import org.example.DAO.BasketsDAO;
//import org.example.DAO.UsersDAO;
//
//import java.sql.ResultSet;
//import java.util.Scanner;
//
//public class LoginMenu extends BasketsDAO.ConnectionFactory.DAO {
//
//public static void display() {
//    System.out.println("\n\n");
//    System.out.println("     ****************************************");
//    System.out.println("     *             LOGIN  MENU              *");
//    System.out.println("     ****************************************");
//    System.out.println("     What do you want to do ?");
//    System.out.println("     (1) Login");
//    System.out.println("     (2) Create user");
//    System.out.println("     (0) Exit program");
//    System.out.println();
//    System.out.println("Your choice : ");
//}
//    public void loginMenu() throws Exception {
//        UsersDAO usersDAO = new UsersDAO();
//
//
//        boolean isRunning = true;
//
//        System.out.println();
//        System.out.println("     ****************************************");
//        System.out.println("     *             LOGIN  MENU              *");
//        System.out.println("     ****************************************");
//        System.out.println("     What do you want to do ?");
//        System.out.println("     (1) Login");
//        System.out.println("     (2) Create user");
//        System.out.println("     (0) Exit program");
//        System.out.println();
//        System.out.println("Your choice : ");
//        Scanner in = new Scanner(System.in);
//        int option = in.nextInt();
//
//
//        switch (option) {
//            case 1:
//                login();
//                break;
//            case 2:
//
//                System.out.println("Input login : ");
//                Scanner in1 = new Scanner(System.in);
//                String login = in1.nextLine();
//                if (!usersDAO.isLoginTaken(login)) {
//                    System.out.println("Nie ma logina");
//                    System.out.println("Input password ");
//                    String passwordU = in1.nextLine();
//                    System.out.println("Input email : ");
//                    String emailU = in1.nextLine();
//
//                    usersDAO.viewUserTable();
//
//                    loginMenu();
//                } else {
//                    System.out.println("jest login");
//                    loginMenu();
//                }
//
//                break;
//            case 3:
//                UsersDAO userDAO2 = new UsersDAO();
//                userDAO2.viewUserTable();
//                loginMenu();
//                break;
//            case 0:
//                isRunning = false;
//                break;
//
//        }
//    }
//
//    public void login() throws Exception {
//
//        System.out.println("Input login : ");
//        Scanner in = new Scanner(System.in);
//        String login = in.nextLine();
//
//        UsersDAO userDAO = new UsersDAO();
//
//        if (userDAO.isLoginTaken(login)) {
//            connect();
//            String SELECT_SQL = "Select * From Users where login = '" + login + "';";
//            ResultSet rs = st.executeQuery(SELECT_SQL);
//            System.out.println("Input password : ");
//            String inputPassword = in.nextLine();
//            String password = rs.getString("password");
//
//            if (inputPassword.equals(password)) {
//                System.out.println("poprawny");
//                int role = rs.getInt("idRole");
//
//                if (role == 1) {
//                    System.out.println("You are Admin!!!");
//                    AdminMenu adminMenu = new AdminMenu();
//                    adminMenu.adminMenu();
//                } else {
//                    System.out.println("You are Customer");
//                    CustomerMenu customerMenu = new CustomerMenu();
//                    //customerMenu.customMenu();
//                }
//
//            } else {
//                System.out.println("niepoprawny");
//                loginMenu();
//
//            }
//
//        } else {
//            System.out.println("No such user : " + login);
//            loginMenu();
//        }
//    }
//}
//
//
//
//
