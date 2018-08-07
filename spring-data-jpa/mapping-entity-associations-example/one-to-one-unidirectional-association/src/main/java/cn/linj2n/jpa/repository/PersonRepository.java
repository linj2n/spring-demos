package cn.linj2n.jpa.repository;

import cn.linj2n.jpa.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{
    Person findPersonByName(String name);
    List<Person> findByAddressCity(String city);
}
