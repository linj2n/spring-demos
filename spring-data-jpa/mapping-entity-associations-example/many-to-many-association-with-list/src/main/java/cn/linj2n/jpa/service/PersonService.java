package cn.linj2n.jpa.service;

import cn.linj2n.jpa.entity.Address;
import cn.linj2n.jpa.entity.Person;
import cn.linj2n.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Person createPersonInformation(String name, Integer age, List<Address> addresses) {
        Person newPerson = new Person(name,age);
        for (Address addr: addresses) {
            newPerson.addAddress(addr);
        }
        return personRepository.save(newPerson);
    }

    public Person findOneById(Long id) {
        Person person = personRepository.findOne(id);
        person.getAddresses().size();
        return person;
    }
}
