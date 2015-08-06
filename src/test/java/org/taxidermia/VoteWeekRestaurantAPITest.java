package org.taxidermia;

import org.junit.Test;
import org.taxidermia.voteweekrestaurant.model.Person;
import org.taxidermia.voteweekrestaurant.model.Restaurant;
import org.taxidermia.voteweekrestaurant.model.Vote;

import static org.junit.Assert.*;
import java.util.List;

public class VoteWeekRestaurantAPITest {


    @Test
    public void testPerson() throws Exception {
        Person person = new VoteWeekRestaurantAPI().person("Pepe");
        assertEquals("Pepe" ,person.getNickName());
    }

    @Test
    public void testPersons() throws Exception {
        VoteWeekRestaurantAPI voteWeekRestaurantAPI = new VoteWeekRestaurantAPI();
        voteWeekRestaurantAPI.person("nickName");
        List<Person> personList = voteWeekRestaurantAPI.persons();
        Person person = personList.iterator().next();
        assertEquals("nickName" ,person.getNickName());
    }

    @Test
    public void testRestaurant() throws Exception {
        Restaurant restaurant = new VoteWeekRestaurantAPI().restaurant("La Casona", "http://lacasona.com", "091");
        assertEquals("La Casona", restaurant.getName());
        assertEquals("http://lacasona.com" ,restaurant.getUrl());
        assertEquals("091" ,restaurant.getPhone());
    }

    @Test
    public void testRestaurants() throws Exception {
        VoteWeekRestaurantAPI voteWeekRestaurantAPI = new VoteWeekRestaurantAPI();
        voteWeekRestaurantAPI.restaurant("La Casona", "http://lacasona.com", "091");
        List<Restaurant> restaurantList = voteWeekRestaurantAPI.restaurants();
        Restaurant restaurant = restaurantList.iterator().next();
        assertEquals("La Casona" ,restaurant.getName());
        }

    public void testVote() throws Exception {
        VoteWeekRestaurantAPI voteWeekRestaurantAPI = new VoteWeekRestaurantAPI();
        Person person = voteWeekRestaurantAPI.person("nikname");
        Restaurant restaurant = voteWeekRestaurantAPI.restaurant("La Casona", "http://lacasona.com", "091");
        Vote vote = voteWeekRestaurantAPI.vote(new Long(person.getId()).toString(),new Long(restaurant.getId()).toString());
        assertEquals("La Casona" , vote.getRestaurant().getName());
        assertEquals("nickName" , vote.getPerson().getNickName());
    }
    }








