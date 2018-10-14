package cn.linj2n.jpa.service;

import cn.linj2n.jpa.entity.Address;
import cn.linj2n.jpa.entity.Person;
import cn.linj2n.jpa.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public void deleteAddressById(Long id) {
        Address address = addressRepository.findOne(id);
        List<Person> people = address.getPersonList();
        for (int i = 0; i < people.size(); i ++) {
            people.get(i).removeAddress(address);
        }
        addressRepository.delete(address);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findOne(id);
    }
}
