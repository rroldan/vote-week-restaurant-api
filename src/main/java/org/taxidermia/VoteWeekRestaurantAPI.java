package org.taxidermia;

        import com.google.api.server.spi.config.Api;
        import com.google.api.server.spi.config.ApiMethod;
        import com.google.api.server.spi.config.ApiNamespace;
        import com.google.api.server.spi.config.ApiMethod.HttpMethod;
        import com.google.api.server.spi.config.Named;
        import org.taxidermia.voteweekrestaurant.application.PersonService;
        import org.taxidermia.voteweekrestaurant.application.RestaurantService;
        import org.taxidermia.voteweekrestaurant.application.VoteService;
        import org.taxidermia.voteweekrestaurant.model.Person;
        import org.taxidermia.voteweekrestaurant.model.DomainRegistry;
        import org.taxidermia.voteweekrestaurant.model.Restaurant;
        import org.taxidermia.voteweekrestaurant.model.Vote;

        import java.util.List;




/** An endpoint class we are exposing */
@Api(name = "voteweekrestaurant",
        version = "v1",
        namespace = @ApiNamespace(ownerDomain = "org.taxidermia",
                ownerName = "org.taxidermia",
                packagePath=""))


public class VoteWeekRestaurantAPI {

    DomainRegistry domainRegistry = new DomainRegistry();
    PersonService personService = new  PersonService(domainRegistry.personRepository());
    RestaurantService restaurantService = new RestaurantService(domainRegistry.restaurantRepository());
    VoteService voteService = new VoteService(domainRegistry.voteRepository());

    /** A simple endpoint method that takes a name and says Hi back */


    @ApiMethod(name = "person", path="person", httpMethod = HttpMethod.PUT )
    public Person person(@Named("nickName")String nickName){
        Person person = new Person.Builder().id(domainRegistry.personRepository().nextIdentity()).nickName(nickName).build();
        personService.addPersonToList(person);
        return person;
    }

    @ApiMethod(name = "persons", path="persons" , httpMethod = HttpMethod.GET)
    public List<Person> persons() {
       return personService.personList();
    }

    @ApiMethod(name = "restaurant", path="restaurant", httpMethod = HttpMethod.PUT )
    public Restaurant restaurant(@Named("name")String name, @Named("url") String url, @Named("phone") String phone ){
        Restaurant restaurant = new Restaurant.Builder().id(domainRegistry.restaurantRepository().nextIdentity()).name(name).url(url).phone(phone).build();
        restaurantService.addRestaurantToList(restaurant);
        return restaurant;
    }

    @ApiMethod(name = "restaurants", path="restaurants" , httpMethod = HttpMethod.GET)
    public List<Restaurant> restaurants() {
        return restaurantService.restaurantList();
    }



    @ApiMethod(name = "vote", path="vote", httpMethod = HttpMethod.PUT )
    public Vote vote(@Named("personId")String personId,@Named("restaurantId")String restaurantId){

        Person person = personService.getPerson(Long.parseLong(personId));
        Restaurant restaurant = restaurantService.getRestaurant(Long.parseLong(restaurantId));
        Vote vote = new Vote.Builder().id(domainRegistry.voteRepository().nextIdentity()).person(person).restaurant(restaurant).build();
        voteService.vote(vote);
        return vote;
    }

    @ApiMethod(name = "votes", path="votes", httpMethod = HttpMethod.GET )
    public List<Vote> votes() {
        return voteService.voteList();
    }


}
