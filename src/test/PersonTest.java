import myApp.entity.AddressesEntity;
import myApp.entity.PersonsEntity;
import myApp.services.AddressService;
import myApp.services.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;

@Transactional
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("deprecation")
public class PersonTest {
    @Resource
    private PersonService personService;

    private PersonsEntity person = new PersonsEntity("Vasya", "Pupkin", new Date(new java.util.Date().getTime()), "vasya@mail.ru", "qwerty", 1);

    @Test
    public void create() {
        personService.create(person);
    }

    @Test
    public void find() {
        PersonsEntity person2 = personService.create(person);
        Assert.assertEquals(person2, personService.find(person2.getId()));
    }

    @Test(expected = NullPointerException.class)
    public void delete() {
        PersonsEntity person2 = personService.create(person);
        personService.delete(person2.getId());
        personService.find(person2.getId()).getName();
    }

    @Test
    public void createWithParams() {
        int personId = personService.createWithParams("Vasya", "Pupkin", new Date(new java.util.Date().getTime()),
                "vasya@mail.ru", "qwerty");
        Assert.assertEquals("Vasya", personService.find(personId).getName());
    }

    @Test
    public void getPersonByEmail() {
        PersonsEntity person2 = personService.create(person);
        Assert.assertEquals("Vasya", personService.getPersonByEmail("vasya@mail.ru").getName());
    }

    @Test
    public void hasPerson() {
        PersonsEntity person2 = personService.create(person);
        Assert.assertTrue(personService.hasPerson(person2.getEmail()));
        Assert.assertFalse(personService.hasPerson("qwerty@google.com"));
    }
}
