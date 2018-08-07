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
public class RepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);

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

        List<Address> samAddresses = addressRepository.findByPersonName("Sam");
        LOGGER.info("Sam's Addresses found with findByPersonName(...): ");
        LOGGER.info("------------------------------");

        for (Address samAddress: samAddresses) {
            LOGGER.info(samAddress.toString());
        }
        Assert.assertEquals(1,samAddresses.size());

        LOGGER.info("Address found with findByCity(...): ");
        LOGGER.info("------------------------------");
        List<Address> addresses = addressRepository.findByCity("GuangZhou");

        for (Address address1: addresses) {
            LOGGER.info(address1.toString());
        }
        Assert.assertEquals(2,addresses.size());

        List<Person> allPersonFromGuangZhou = personRepository.findByAddressCity("GuangZhou");
        LOGGER.info("Person found with findByAddressCity(...): ");
        LOGGER.info("------------------------------");
        for (Person person: allPersonFromGuangZhou) {
            LOGGER.info(person.toString());
        }
        Assert.assertNotNull(allPersonFromGuangZhou);

    }
}