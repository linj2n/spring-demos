package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Address;
import cn.linj2n.jpa.entity.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressRepository addressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Before
    public void init() {

        Address addressA = new Address("street1","ShenZhen","GuangDong","CN");
        Address addressB = new Address("street2","GuangZhou","GuangDong","CN");
        Address addressC = new Address("street3","ZhuHai","GuangDong","CN");

        Person john = new Person("John",18);
        john.getAddresses().addAll(Arrays.asList(addressA,addressB));

        Person sam = new Person("Sam",22);
        sam.getAddresses().addAll(Arrays.asList(addressB,addressC));

        personRepository.save(Arrays.asList(sam,john));
    }

    @Test
    public void findAll() {
        assertEquals(2,personRepository.findAll().size());
        assertEquals(3,addressRepository.findAll().size());
    }

    @Test
    public void addAddress() {
        Person sam = personRepository.findPersonByName("Sam");
        assertNotNull(sam);

        sam.getAddresses().add(new Address("street4","ShenZhen","GuangDong","CN"));
        personRepository.save(sam);

        assertEquals(3,personRepository.findPersonByName("Sam").getAddresses().size());
        assertEquals(4,addressRepository.findAll().size());
    }
}