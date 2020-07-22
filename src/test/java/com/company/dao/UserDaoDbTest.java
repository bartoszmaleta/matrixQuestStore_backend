//package com.company.dao;
//
//import com.company.model.user.Student;
//import com.company.model.user.User;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserDaoDbTest {
//    private static ConnectionFactory connectionFactory;
//    private static UserDao userDao;
////    ConnectionFactory connectionFactory;
////    UserDao userDao;
//
//    @BeforeAll // or BeforeEach if we cleanUp DB after each Test and create new one!
//    static void setUp() {
////        connectionFactory = new ConnectionFactory(
////                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com.com/da8tt4mh63b7nc/da8tt4mh63b7nc"
////                , "org.postgresql.Driver"
////                , "pirqathgcgzhbg"
////                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
////        );
//        connectionFactory = new ConnectionFactory();
////        userDao = new UserDaoDb(connectionFactory);
//        userDao = new UserDaoDb();
//    }
//
//    @Test
//    public void should_returnUser_when_idIsProvidedId() {
//        User cristiano = new Student()
//                .setId(22)
//                .setName("Cristiano")
//                .setSurname("Ronaldo")
//                .setLogin("cr7")
//                .setPassword("pass")
//                .setEmail("cristiano@gmail.com")
//                .setRoleEnum(3);
//
//        assertEquals(cristiano, userDao.getById(22));
//    }
//
//    @Test
//    public void should_returnTrue_when_userIsAdded() {
//        User newUser = new Student()
//                .setName("Added")
//                .setSurname("User")
//                .setLogin("addUser")
//                .setPassword("pass")
//                .setEmail("addedUser@")
//                .setRoleEnum(3);
//        assertTrue(userDao.insert(newUser));
//
////        this.userDao.insert(newUser);
////        assertEquals(newUser, this.userDao.readUserByEmailAndPassword("addedUser@", "pass"));
//    }
//
//    @Test
//    public void should_returnTrue_when_userWithProvidedIdIsDeleted() {
//        int idUser = 0;
//        assertTrue(userDao.delete(idUser));
//    }
//
//    @Test
//    public void should_returnUserId_when_userEmailIsProvided() {
//        String userEmail = "tesla@";
//        int userId = 2;
//        int idUserFromDao = userDao.readUserIdByEmail(userEmail);
//        assertEquals(userId, idUserFromDao);
//    }
//
////    @Test
////    public void should_returnUserId_when_userEmailIsProvided() {
////        String userEmail = "addedUser2@gmail.com";
////        User newUser = new Student()
////                .setName("XYZAdded2")
////                .setSurname("User2")
////                .setLogin("addUser2")
////                .setPassword("pass")
////                .setEmail(userEmail)
////                .setRoleEnum(3);
////        this.userDao.insert(newUser);
////        int idUser = this.userDao.readUserIdByEmail(userEmail);
////        System.out.println("idUser = " + idUser);
////
////        System.out.println("newUser " + newUser.toString());
////        System.out.println("fromDaoUser " + this.userDao.getById(idUser).toString());
////        assertEquals(newUser, this.userDao.getById(idUser));
////        this.userDao.delete(idUser);
////    }
//
//
//
//}