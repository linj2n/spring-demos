package cn.linj2n.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Person")
@Table(name = "person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },fetch = FetchType.LAZY)
    @JoinTable(name = "person_address",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses = new HashSet<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.getPersons().add(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.getPersons().remove(this);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id != null && id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    protected Person() { // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("Person{id=%d,name='%s',age=%d}",id,name,age);
    }
}
