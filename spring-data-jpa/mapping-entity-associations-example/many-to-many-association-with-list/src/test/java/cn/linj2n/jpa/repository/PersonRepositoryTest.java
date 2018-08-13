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
    public void insertData() {
        Person john = new Person("John",18);
        Address addressA = new Address("XLL","ShenZhen","GuangDong","CN");
        Address addressB = new Address("XLL","GuangZhou","GuangDong","CN");
        john.addAddress(addressA);
        john.addAddress(addressB);
        personRepository.save(john);
    }

    @Test
    public void testCRUD() {
        Person john = personRepository.findPersonByName("John");
        Assert.assertNotNull(john);
        Assert.assertNotNull(addressRepository.findAll());
        Address address = john.getAddresses().get(0);
        address.setStreet("DLL");
        personRepository.saveAndFlush(john);

        Assert.assertNotNull(addressRepository.findByStreet);

        john = personRepository.findPersonByName("John");
        address = john.getAddresses().get(0);
        Assert.assertEquals("DLL",address.getStreet());

        Address address1 = new Address("DLL","ShenZhen","GuangDong","CN");
        john.removeAddress(address1);

        personRepository.saveAndFlush(john);
        john = personRepository.findPersonByName("John");
        Assert.assertEquals(1,john.getAddresses().size());
    }

}