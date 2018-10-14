package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Address;
import cn.linj2n.jpa.entity.Person;
import cn.linj2n.jpa.service.AddressService;
import cn.linj2n.jpa.service.PersonService;
import org.junit.Before;
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
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Before
    public void insertData() {
        Person john = new Person("John",18);
        Address addressA = new Address("XLL","ShenZhen","GuangDong","CN");
        Address addressB = new Address("XLL","GuangZhou","GuangDong","CN");
        List<Address> addresses = new ArrayList<>();
        addresses.add(addressA);
        addresses.add(addressB);
        personService.createPersonInformation("John", 18, addresses);
    }

    @Test
    public void test_Cascade_Delete_Operation() {
        addressService.deleteAddressById(1L);
        assertEquals(personService.findOneById(1L).getAddresses().size(),1);
    }

}