package cn.linj2n.spring.security.formlogin.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
//@Table(name = "user")
public class User implements UserDetails, Serializable {


    private static final long serialVersionUID = -507280633716305798L;

    /**
     * 主键
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自增长策略
    private Long id;
    /**
     * 用户名
     *
     */
    @Column(nullable = false)
    private String username;
    /**
     * 密码
     *
     */
    @Column(nullable = false)
    private String password;

    /**
     * 邮箱
     *
     */
    private String email;

    /**
     * 创建时间
     *
     */
    @Column(nullable = false)
    private Date createdTime;

    /**
     * 最后登录的时间
     *
     */
    private Date lastLoginTime;

    /**
     * 权限列表
     *
     */
    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private List<Authority> authorities;

    public User(String username, String password, String email, Date createdTime, Date lastLoginTime, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdTime = createdTime;
        this.lastLoginTime = lastLoginTime;
        this.authorities = authorities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    protected User() {  // JPA 的规范要求无参构造函数；设为 protected 防止直接使用
    }


    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();
        for(GrantedAuthority authority : this.authorities){
            simpleAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return simpleAuthorities;
    }


    @Override
    public String getPassword() {
        return this.password;
    }


    @Override
    public String getUsername() {
        return this.username;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }


    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }
}
