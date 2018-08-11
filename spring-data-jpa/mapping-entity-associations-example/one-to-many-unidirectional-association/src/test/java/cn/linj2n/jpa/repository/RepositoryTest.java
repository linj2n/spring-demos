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

        Person john = new Person("John",18);
        john.getAddresses().add(new Address("XLL","ShenZhen","GuangDong","CN"));
        john.getAddresses().add(new Address("XLL","GuangZhou","GuangDong","CN"));
        personRepository.save(john);

        john = personRepository.findPersonByName("John");

        List<Address> johnsAddress = john.getAddresses();
        LOGGER.info("John's Addresses found with findByPersonName(...): ");
        LOGGER.info("------------------------------");

        for (Address johnAddress: johnsAddress) {
            LOGGER.info(johnAddress.toString());
        }
        Assert.assertEquals(2,johnsAddress.size());

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

    @Test
    public void testCascadeOperations() {
        Person john = new Person("John",18);
        Address johnsFirstAddress = new Address("XLL","ShenZhen","GuangDong","CN");
        john.getAddresses().add(johnsFirstAddress);
        personRepository.saveAndFlush(john);

        // Create operation
        Assert.assertNotNull(addressRepository.findOne(1L));

        // Update operation
        john = personRepository.findPersonByName("John");
        johnsFirstAddress = john.getAddresses().get(0);
        johnsFirstAddress.setStreet("DLL");
        personRepository.saveAndFlush(john);

        johnsFirstAddress = addressRepository.findOne(1L);
        Assert.assertEquals("DLL",johnsFirstAddress.getStreet());

        johnsFirstAddress.setStreet("ALL");
        addressRepository.saveAndFlush(johnsFirstAddress);

//        Assert.assertEquals("ALL",personRepository.findPersonByName("john").getAddresses().get(0).getStreet());

        // Remove operation
        john = personRepository.findPersonByName("John");
        john.getAddresses().remove(johnsFirstAddress);
        Assert.assertEquals(0,john.getAddresses().size());
        personRepository.saveAndFlush(john);
        Assert.assertNull(addressRepository.findOne(1L));
    }

}