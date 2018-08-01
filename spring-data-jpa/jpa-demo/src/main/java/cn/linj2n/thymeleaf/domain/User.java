package cn.linj2n.thymeleaf.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * User Entity
 */
@Entity
public class User implements Serializable {

    @Id                                                 // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增策略
    private Long id;
    private String name;
    private String email;

    protected User() {      // 设置为 protected ，防止直接使用
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(Long id, String name, String email) {
        this.id = id;
        this.name = name;

        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "User[id='%d', name='%s', email='%s']",
                id,name,email);
    }
}
