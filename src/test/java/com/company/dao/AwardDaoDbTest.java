package com.company.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AwardDaoDbTest {
    private static AwardDaoDb awardDaoDb;

    @BeforeEach
    public void setup() {
        ConnectionFactory connectionFactory = new ConnectionFactory(
                "jdbc:postgresql://ec2-54-217-206-236.eu-west-1.compute.amazonaws.com:5432/da8tt4mh63b7nc"
                , "org.postgresql.Driver"
                , "pirqathgcgzhbg"
                , "15c50442ada3956b30448ed4f67f2ec081ffedc990ade3019893a9d6b51655ed"
        );
        awardDaoDb = new AwardDaoDb(connectionFactory);

    }

    @AfterEach
    public void cleanUp() {
        awardDaoDb.dropTables();
        awardDaoDb.createTables();
    }

    @Test
    public void testUpdateAwardTitleByIdWhenAddingText() {
        String expected = "Plugged in for test";
        awardDaoDb.updateAwardTitleById(5, "Plugged in for test");
        System.out.println(awardDaoDb.getById(5));
        String actual = awardDaoDb.getById(5).getTitle();
        assertEquals(expected, actual);
    }

    @Test
    public void updateAwardDescriptionByIdWhenAddingText() {
        String expected = "1h of learning java-jitsu with mentor for test";
        awardDaoDb.updateAwardDescriptionById(3, "1h of learning java-jitsu with mentor for test");
        String actual = awardDaoDb.getById(3).getDescription();
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdateAwardPriceById() {
        awardDaoDb.updateAwardPriceById(9, 777);
        int actual = awardDaoDb.getById(9).getPrice();
        assertEquals(777, actual);

    }


    @Test
    public void testUpdatePriceByIdWhenBelowZero() {
        assertThrows(IllegalArgumentException.class, () -> awardDaoDb.updateAwardPriceById(3, -14), "Price can't be below 0");
    }

}