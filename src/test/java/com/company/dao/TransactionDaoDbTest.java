//package com.company.dao;
//
//import com.company.model.Quest;
//import com.company.model.Transaction;
//import com.company.model.user.Student;
//import com.company.model.user.User;
//import org.junit.jupiter.api.*;
//import org.mockito.Mockito;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TransactionDaoDbTest {
//    private static TransactionDao transactionDao;
//    private static ConnectionFactory connectionFactory;
//
//    @BeforeAll
//    static void setUp() {
//        connectionFactory = new ConnectionFactory(
//                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
//                , "org.postgresql.Driver"
//                , "pirqathgcgzhbg"
//                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
//        );
//        System.out.println("qasdasdsad");
//        transactionDao = new TransactionDaoDb(connectionFactory);
////        createTables();
//    }
//
//    @BeforeEach
//    public void create() {
//        createTables();
//    }
//
//    @AfterEach
//    public void drop() {
//        dropTables();
//    }
//
//
//    @Disabled("Error: relation 'Transactions' does not exit")
//    @Test
//    public void should_returnListOfTransactions_when_userIsProvided() {
//        User cristiano = new Student()
//                .setId(1)
//                .setName("Cristiano")
//                .setSurname("Ronaldo")
//                .setLogin("cr7")
//                .setPassword("pass")
//                .setEmail("cristiano@gmail")
//                .setAvatarSource("../resoruce/avatars/cristiano@gmail.com_logo")
//                .setRoleEnum(3);
//
//        List<Transaction> transactionsExpected = new ArrayList<>();
//
//        Transaction transaction = new Transaction(
//                1
//                , 1
//                , "Cristiano Ronaldo"
//                , Timestamp.valueOf("2018-11-12 01:02:03.123456789")
//                , 2, "I was blind now I see"
//                , 20);
//
//        Transaction transaction2 = new Transaction(
//                2
//                , 1
//                , "Cristiano Ronaldo"
//                , Timestamp.valueOf("2018-11-11 01:02:03.123456789")
//                , 3, "Martial-Art Workshop"
//                , 44);
//        transactionsExpected.add(transaction);
//        transactionsExpected.add(transaction2);
//
//        List<Transaction> transactionsActual = transactionDao.getMyTransactions(cristiano);
//
//        assertEquals(transactionsExpected, transactionsActual);
//    }
//
////    @Test
////    public void testReadQuestListByMentorId() {
////        List<Quest> expectedList = new ArrayList<>(Arrays.asList(questDaoDb.getById(2), questDaoDb.getById(6)));
////        QuestDaoDb mockQuestDaoDb = Mockito.mock(QuestDaoDb.class);
////        Mockito.when(mockQuestDaoDb.readQuestListByMentorById(1)).thenReturn(expectedList);
////
////        List<Quest> mentorQuests = mockQuestDaoDb.readQuestListByMentorById(1);
////        assertEquals(expectedList, mentorQuests);
////        Mockito.verify(mockQuestDaoDb).readQuestListByMentorById(1);
////    }
//
//    @Test
//    public void should_returnListOfTransactions_when_userIdIsProvided() {
//        createTables();
//        List<Transaction> expectedList = new ArrayList<>(Arrays.asList(transactionDao.getById(1), transactionDao.getById(2)));
//        TransactionDaoDb mockTransactionDaoDb = Mockito.mock(TransactionDaoDb.class);
//        Mockito.when(mockTransactionDaoDb.getMyTransactionsById(1)).thenReturn(expectedList);
//
//        List<Transaction> transactions = mockTransactionDaoDb.getMyTransactionsById(1);
//        assertEquals(expectedList, transactions);
//        Mockito.verify(mockTransactionDaoDb).getMyTransactionsById(1);
//    }
//
//
//    public void dropTables() {
//        PreparedStatement ps = null;
//        try {
//            ps = connectionFactory.getConnection().prepareStatement
//                    ("DROP TABLE IF EXISTS \"Awards\";\n" +
//                            "DROP TABLE  IF EXISTS \"Quests\";\n" +
//                            "DROP TABLE  IF EXISTS \"Students_Awards\";\n" +
//                            "DROP TABLE  IF EXISTS \"Roles\" CASCADE;\n" +
//                            "DROP TABLE  IF EXISTS \"Transactions\";\n" +
//                            "DROP TABLE  IF EXISTS \"Student_Detailss\";\n" +
//                            "DROP TABLE  IF EXISTS \"Students_Modules\";\n" +
//                            "DROP TABLE  IF EXISTS \"Modules\";\n" +
//                            "DROP TABLE  IF EXISTS \"users\" CASCADE;");
//            ps.executeUpdate();
//            ps.close();
//            connectionFactory.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    public static void createTables() {
//        PreparedStatement ps = null;
//        try {
//            ps = connectionFactory.getConnection().prepareStatement
//                    (
//                            "create table \"users\"\n" +
//                                    "(\n" +
//                                    "    id       serial not null\n" +
//                                    "        constraint users_pk\n" +
//                                    "            primary key,\n" +
//                                    "    name     varchar,\n" +
//                                    "    surname  varchar,\n" +
//                                    "    login    varchar,\n" +
//                                    "    password varchar,\n" +
//                                    "    email    varchar,\n" +
//                                    "    role_id  integer,\n" +
//                                    "    avatar   varchar\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table users\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "create unique index users_id_uindex\n" +
//                                    "    on users (id);\n" +
//                                    "\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Cristiano', 'Ronaldo', 'cr7', 'pass', 'cristiano@gmail', 3, '../resoruce/avatars/cristiano@gmail.com_logo');\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Nikola', 'Tesla', 'tesla', 'pass', 'tesla@gmail', 2, '../resoruce/avatars/tesla@gmail.com_logo');\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Bill', 'Gates', 'microsoft', 'pass', 'microsoft@gmail', 1, '../resoruce/avatars/microsoft@gmail.com_logo');\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Warren', 'Buffet', 'berkshire', 'pass', 'berkshire@gmail', 2, '../resoruce/avatars/berkshire@gmail.com_logo');\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Jeff', 'Bezos', 'amazon', 'pass', 'amazon@gmail', 3, '../resoruce/avatars/amazon@gmail.com_logo');\n" +
//                                    "insert into public.\"users\" (name, surname, login, password, email, role_id, avatar) values ('Linus', 'Torvalds', 'linux', 'pass', 'linux@gmail', 1, '../resoruce/avatars/linux@gmail.com_logo_logo');\n" +
//                                    "\n" +
//                                    "create table \"Roles\"\n" +
//                                    "(\n" +
////                            "    id   serial not null,\n" +
//
//                                    "    id   serial not null\n" +
//                                    "        constraint roles_pk\n" + // TODO: not sure
//                                    "            primary key,\n" +
//
//                                    "    role text\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Roles\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//
//                                    "create unique index roles_id_uindex\n" + // TODO: not sure
//                                    "    on \"Roles\" (id);\n" +
//
//                                    "\n" +
//                                    "insert into public.\"Roles\" (role) values ('admin');\n" +
//                                    "insert into public.\"Roles\" (role) values ('mentor');\n" +
//                                    "insert into public.\"Roles\" (role) values ('student');\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "create table \"Student_Detailss\"\n" +
//                                    "(\n" +
//                                    "    id         serial not null\n" +
//                                    "        constraint student_detailss_pk\n" +
//                                    "            primary key,\n" +
//                                    "    coins      integer default 0,\n" +
//                                    "    student_id integer,\n" +
//                                    "    mentor_id  integer default 2\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Student_Detailss\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "create unique index student_detailss_id_uindex\n" +
//                                    "    on \"Student_Detailss\" (id);\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "insert into public.\"Student_Detailss\" (coins, student_id, mentor_id) values (220, 1, 2);\n" +
//                                    "insert into public.\"Student_Detailss\" (coins, student_id, mentor_id) values (600, 5, 2);\n" +
//                                    "\n" +
//                                    "create table \"Modules\"\n" +
//                                    "(\n" +
//                                    "    id   serial not null\n" +
//                                    "        constraint modules_pk\n" +
//                                    "            primary key,\n" +
//                                    "    name text\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Modules\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "insert into public.\"Modules\" (name) values ('JAVA');\n" +
//                                    "insert into public.\"Modules\" (name) values ('PYTHON');\n" +
//                                    "insert into public.\"Modules\" (name) values ('WEB');\n" +
//                                    "insert into public.\"Modules\" (name) values ('ADVANCE');\n" +
//                                    "\n" +
//                                    "create table \"Students_Modules\"\n" +
//                                    "(\n" +
//                                    "    id            serial not null\n" +
//                                    "        constraint \"User_Modules_pkey\"\n" +
//                                    "            primary key,\n" +
//                                    "    student_id    integer\n" +
//                                    "        constraint \"User_Modules_user_id_fkey\"\n" +
//                                    "            references users,\n" +
//                                    "    module_id     integer\n" +
//                                    "        constraint \"User_Modules_module_id_fkey\"\n" +
//                                    "            references \"Modules\",\n" +
//                                    "    enrollment_at timestamp\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Students_Modules\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "insert into public.\"Students_Modules\" (student_id, module_id, enrollment_at) values (1, 1, '2020-07-23 18:13:48.000000');\n" +
//                                    "insert into public.\"Students_Modules\" (student_id, module_id, enrollment_at) values (5, 2, '2020-07-23 18:14:02.000000');\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "create table \"Transactions\"\n" +
//                                    "(\n" +
//                                    "    id         serial not null,\n" +
//                                    "    user_id    integer,\n" +
//                                    "    award_id   integer,\n" +
//                                    "    price      integer,\n" +
//                                    "    created_at text\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Transactions\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "insert into public.\"Transactions\" (user_id, award_id, price, created_at) values (1, 2, 20, '2018-11-12 01:02:03.123456789');\n" +
//                                    "insert into public.\"Transactions\" (user_id, award_id, price, created_at) values (1, 3, 44, '2018-11-11 01:02:03.123456789');" + "\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "create table \"Students_Awards\"\n" +
//                                    "(\n" +
//                                    "    id        serial  not null\n" +
//                                    "        constraint users_awards_pk\n" +
//                                    "            primary key,\n" +
//                                    "    user_id   integer not null,\n" +
//                                    "    award_id  integer not null,\n" +
//                                    "    price     integer not null,\n" +
//                                    "    bought_at text    not null\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Students_Awards\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "create unique index users_awards_id_uindex\n" +
//                                    "    on \"Students_Awards\" (id);\n" +
//                                    "\n" +
//                                    "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 2, 20, '2018-11-12 01:02:03.123456789');\n" +
//                                    "insert into public.\"Students_Awards\" (user_id, award_id, price, bought_at) values (1, 3, 44, '2018-11-11 01:02:03.123456789');\n" + "\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "create table \"Quests\"\n" +
//                                    "(\n" +
//                                    "    id          serial not null,\n" +
//                                    "    title       text,\n" +
//                                    "    description text,\n" +
//                                    "    coins       text,\n" +
//                                    "    image       text,\n" +
//                                    "    mentor_id   integer\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Quests\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Dodge This!', 'Dodge 5x times morning question', 5, 'dodgeThis.jpg', 2);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Telephone Booth Call', '3x times teleporting from telephone booth to headquarter', 30, 'telephoneBooth.png', 5);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Destroy bugs for test', 'Find and fix 3 bugs in someone else''s program', 7, 'bugs.jpg', 20);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Help friend in need', 'Review your friend''s code for test', 6, 'helpFriend.jpg', 20);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Avoid bullets', 'Answer mentor''s question correctly', 666, 'dodgeBullets.jpg', 20);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Be everywhere', 'Attendence more than 80%', 9, 'appearences.png', 19);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Faster than ever', 'Submit project one day earlier', 50, 'neoFly.png', 20);\n" +
//                                    "insert into public.\"Quests\" (title, description, coins, image, mentor_id) values ('Best Trio', 'Stand on the podium in Kahoot', 12, 'podium.jpg', 19);\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "create table \"Awards\"\n" +
//                                    "(\n" +
//                                    "    id            serial not null,\n" +
//                                    "    title         text,\n" +
//                                    "    description   text,\n" +
//                                    "    price         integer,\n" +
//                                    "    image         text,\n" +
//                                    "    data_creation timestamp,\n" +
//                                    "    creator_id    integer\n" +
//                                    ");\n" +
//                                    "\n" +
//                                    "alter table \"Awards\"\n" +
//                                    "    owner to pirqathgcgzhbg;\n" +
//                                    "\n" +
//                                    "\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Nokia Software', '1h programming course on oldstyle Nokia', 20, 'nokianeo.png', '2020-04-27 10:54:49.000000', 2);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('I was blind now I see', '1h workshop of binary numbers as real lfe images', 50, 'blindsee.jpg', '2020-04-27 11:04:47.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Martial-Art Workshop', '1h of learning java-jitsu with mentor for test', 60, 'morpheusCome.png', '2020-04-28 11:04:49.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Mr Robot', '1h of programming the bot of your favourite mentor', 70, '01.png', '2020-04-27 11:06:32.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Plugged in for test', '1h of studying in simulator with friend', 50, 'simulator.jpg', '2020-04-26 11:06:37.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Consult The Oracle', '45 minutes of interwiev with oracle from school office', 40, 'oracle.jpg', '2020-04-27 11:06:46.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Holy Trinity Help', 'Get help with your project for 1h from three mentors at the same time', 100, 'holy3.jpg', '2020-04-26 11:06:53.000000', 20);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ('Eyewear Of Matrix', 'All mentors wear matrix sunglasses whole day test', 120, 'sunglasses.jpg', '2020-04-28 11:07:05.000000', 19);\n" +
//                                    "insert into public.\"Awards\" (title, description, price, image, data_creation, creator_id) values ( 'Hackagov', '30 min of hacking Polish National Bank''s website', 30, 'hacking.jpg', '2020-07-08 20:44:34.195000', 19);\n" +
//                                    "\n");
//            ps.executeUpdate();
//            ps.close();
//            connectionFactory.close();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//}