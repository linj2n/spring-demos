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

        Person john = personRepository.findOne(1L);
        LOGGER.info(String.format("John{Id: %d, addrId: %d}",john.getId(),john.getAddress().getId()));
        Assert.assertEquals(john.getId(),john.getAddress().getId());

    }
}