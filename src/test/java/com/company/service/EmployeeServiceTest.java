package com.company.service;

import com.company.dao.AwardDaoDb;
import com.company.dao.ConnectionFactory;
import com.company.dao.QuestDaoDb;
import com.company.dao.UserDaoDb;
import com.company.model.user.Mentor;
import com.company.model.user.Student;
import com.company.model.user.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private static MentorService mentorService;
    private static UserDaoDb userDaoDb;

    @BeforeAll
    static void setUp() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        mentorService = new MentorService(connectionFactory);
        userDaoDb = Mockito.mock(UserDaoDb.class);
    }

    @Test
    public void getAllMentors() {
        User user2 = new Mentor(2, "Nikola", "Tesla", "tesla", "pass", "tesla@gmail", 2, "../resoruce/avatars/tesla@gmail.com_logo");
        User user4 = new Mentor(4, "Warren", "Buffet", "berkshire", "pass", "berkshire@gmail", 2, "../resoruce/avatars/berkshire@gmail.com_logo");

        List<User> expectedMentors = new ArrayList<>();
        expectedMentors.add(user2);
        expectedMentors.add(user4);

        Mockito.when(userDaoDb.getMentors()).thenReturn(expectedMentors);
        assertEquals(userDaoDb.getMentors(), mentorService.getAllMentors());
    }

    @Test
    public void getAllStudents() {
        User user1 = new Student(1, "Cristiano", "Ronaldo", "cr7", "pass", "cristiano@gmail", 3, "../resoruce/avatars/cristiano@gmail.com_logo");
        User user5 = new Student(5, "Jeff", "Bezos", "amazon", "pass", "amazon@gmail", 3, "../resoruce/avatars/amazon@gmail.com_logo");
        List<User> expectedStudents = new ArrayList<>();
        expectedStudents.add(user1);
        expectedStudents.add(user5);

        Mockito.when(userDaoDb.getStudents()).thenReturn(expectedStudents);
        assertEquals(userDaoDb.getStudents(), mentorService.getAllStudents());
    }
}