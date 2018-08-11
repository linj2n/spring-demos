package cn.linj2n.jpa.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(age, person.age) &&
                Objects.equals(addresses, person.addresses);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, age, addresses);
    }
}
