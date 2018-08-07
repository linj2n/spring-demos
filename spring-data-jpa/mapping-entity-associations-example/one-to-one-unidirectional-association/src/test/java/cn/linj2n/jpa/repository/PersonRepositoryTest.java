package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Address;
import cn.linj2n.jpa.entity.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testCRUD() {

        personRepository.save(new Person("John",18,
                new Address("XLL","ShenZhen","GuangDong","CN")));
        personRepository.save(new Person("Sam",20,
                new Address("XLL","GuangZhou","GuangDong","CN")));
        personRepository.save(new Person("Peter",32,
                new Address("XLL","GuangZhou","GuangDong","CN")));
        personRepository.save(new Person("Dick",28,
                new Address("XLL","DongGuan","GuangDong","CN")));
        personRepository.save(new Person("Alice",11,
                new Address("XLL","ZhuHai","GuangDong","CN")));
        personRepository.save(new Person("Tom",9,
                new Address("XLL","HuiZhou","GuangDong","CN")));

        // fectch all Person
        LOGGER.info("Person found with findAll(): ");
        LOGGER.info("------------------------------");
        List<Person> allPerson = personRepository.findAll();

        for (Person person:  allPerson) {
            LOGGER.info(person.toString());
        }

        Assert.assertNotNull(allPerson);

        Person john = personRepository.findOne(1L);
        Assert.assertNotNull(john);

        Person sam = personRepository.findPersonByName("Sam");
        Assert.assertNotNull(sam);

        sam.getAddress().setStreet("OLO");
        personRepository.save(sam);

        sam = personRepository.findPersonByName("Sam");
        LOGGER.info("------------------------------");
        LOGGER.info(sam.toString());
        Assert.assertEquals("OLO",sam.getAddress().getStreet());

        List<Person> allPersonFromGuangZhou = personRepository.findByAddressCity("GuangZhou");
        LOGGER.info("------------------------------");
        for (Person person: allPersonFromGuangZhou) {
            LOGGER.info(person.toString());
        }
        Assert.assertNotNull(allPersonFromGuangZhou);

    }
}