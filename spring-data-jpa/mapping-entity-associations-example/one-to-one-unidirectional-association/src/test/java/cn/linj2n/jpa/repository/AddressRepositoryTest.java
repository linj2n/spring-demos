package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Address;
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
public class AddressRepositoryTest {
    @Autowired
    private AddressRepository addressRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonRepositoryTest.class);

    @Test
    public void testCRUD() {
        addressRepository.save(
                new Address("XLL","ShenZhen","GuangDong","CN"));
        addressRepository.save(
                new Address("XLL","GuangZhou","GuangDong","CN"));
        addressRepository.save(
                new Address("OLO","GuangZhou","GuangDong","CN"));
        addressRepository.save(
                new Address("XLL","DongGuan","GuangDong","CN"));
        addressRepository.save(
                new Address("XLL","ZhuHai","GuangDong","CN"));
        addressRepository.save(
                new Address("XLL","HuiZhou","GuangDong","CN"));

        // fectch all Address
        LOGGER.info("Address found with findAll(): ");
        LOGGER.info("------------------------------");
        List<Address> allAddress = addressRepository.findAll();

        for (Address address:  allAddress) {
            LOGGER.info(address.toString());
        }
        Assert.assertNotNull(allAddress);


        // fectch specificed Address with findOne()
        LOGGER.info("Address found with findOne(): ");
        LOGGER.info("------------------------------");
        Address address = addressRepository.findOne(new Long(1));

        LOGGER.info(address.toString());
        Assert.assertNotNull(address);

        LOGGER.info("------------------------------");
        address.setStreet("DLL");
        addressRepository.save(address);
        LOGGER.info(addressRepository.findOne(1L).toString());
        Assert.assertEquals("DLL",addressRepository.findOne(1L).getStreet());

        LOGGER.info("Address found with findByCity(...): ");
        LOGGER.info("------------------------------");
        List<Address> addresses = addressRepository.findByCity("GuangZhou");

        for (Address address1: addresses) {
            LOGGER.info(address1.toString());
        }
        Assert.assertEquals(2,addresses.size());

    }
}