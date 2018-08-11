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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonRepository personRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepository.class);

    @Test
    public void testCRUD() {

        Person john = new Person("John",18);
        List<Address> johnAddresses = john.getAddresses();
        johnAddresses.add(new Address("XLL","ShenZhen","GuangDong","CN"));
        johnAddresses.add(new Address("XLL","GuangZhou","GuangDong","CN"));
        personRepository.save(john);

        john = personRepository.findPersonByName("John");

        johnAddresses = john.getAddresses();
        LOGGER.info("John's Addresses found with findByPersonName(...): ");
        LOGGER.info("------------------------------");

        for (Address johnAddress: johnAddresses) {
            LOGGER.info(johnAddress.toString());
        }
        Assert.assertEquals(2,johnAddresses.size());

        john.getAddresses().remove(1);
        personRepository.saveAndFlush(john);

        // Remove operation
        john = personRepository.findOne(1L);
        Assert.assertEquals(1,john.getAddresses().size());


        Address johnsFirstAddress = john.getAddresses().get(0);
        johnsFirstAddress.setStreet("DLL");
        personRepository.saveAndFlush(john);

        // Update operation
        john = personRepository.findOne(1L);
        Assert.assertEquals("DLL",personRepository.findPersonByName("john").getAddresses().get(0).getStreet());
    }
}