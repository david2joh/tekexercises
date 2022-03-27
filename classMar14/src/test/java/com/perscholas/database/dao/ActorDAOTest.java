package com.perscholas.database.dao;

import com.perscholas.database.entity.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorDAOTest {
    private static ActorDAO actorDAO;

    @BeforeAll
    public static void setup() {
        actorDAO = new ActorDAO();
    }

    @Test
    public void getActorTest() {
        // given
        Actor expected = new Actor();
        expected.setId(1);
        expected.setFirstName("Scarlett");
        expected.setLastName("Johansson");

        //when
        Actor actual = actorDAO.findByID(1);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Disabled
    @Test
    public void insert() {
        // given
        Actor expected = new Actor();
        expected.setFirstName("Scarlett");
        expected.setLastName("OHara");

        // when
        expected = actorDAO.save(expected);
        Actor actual = actorDAO.findByID(expected.getId());

        //then
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(expected.getLastName(), actual.getLastName());
    }


    @ParameterizedTest
    @CsvSource({"1,Scarlett, Johansson ", "2 ,Mark, Hamil"})
    void testJUnit5CsvParameters(int id, String firstName, String lastName) {
        Actor expected = new Actor();
        expected.setId(id);
        expected.setFirstName(firstName);
        expected.setLastName(lastName);

        //when
        Actor actual = actorDAO.findByID(id);

        //then
        Assertions.assertEquals(expected, actual);
    }
}

