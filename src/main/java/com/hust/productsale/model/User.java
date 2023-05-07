package com.hust.productsale.model;

import com.hust.productsale.validation.annotation.NullOrNotBlank;
import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users_usr")
public class User {
    private static final long serialVersionUID = 74597879236597717L;

    @Id
    @Column(name = "id_usr")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", unique = true)
    @NullOrNotBlank(message = "Username can not be blank")
    private String username;

    @Column(name = "name_usr", length = 50)
    private String name;

    @NaturalId
    @Column(name = "email_usr", unique = true)
    @NotBlank(message = "User email cannot be null")
    private String email;

    @Column(name = "password_usr")
    @NotNull(message = "Password cannot be null")
    private String password;

    @Column(name = "date_create")
    @NullOrNotBlank(message = "Last name can not be blank")
    private Date createdAt = new Date();

    @Column(name = "date_update")
    @NullOrNotBlank(message = "Last name can not be blank")
    private Date updatedAt = new Date();

    @Column(name = "usr_enabled", columnDefinition = "TINYINT(1)")
    private Boolean active = true;

    @Column(name = "usr_token", nullable = false)
    private String usrToken;

    @Column(name = "type_usr", insertable = true, updatable = true, nullable = true)
    private Short type;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "auth_user_authority", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "id_usr") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
    private Set<Role> roles = new HashSet<>();

    @Column(name = "gender")
    private Short gender;

    @Column(name = "birth_day")
    private Date birthday;

    @Column(name = "phone")
    private String phone;

    @Column(name = "city")
    private String city;

    @Column(name = "province")
    private String province;

    @Column(name = "ward")
    private String ward;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "is_anonymous")
    private Boolean anonymous;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    private List<User> employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    public User() {
    }

    public User(User user) {
        id = user.getId();
        username = user.getUsername();
        if (user.getUsrToken() != null) {
            password = user.getPassword() + "®®" + user.getUsrToken();
        } else {
            password = user.getPassword();
        }
        email = user.getEmail();
        usrToken = user.getUsrToken();
        roles = user.getRoles();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUsrToken() {
        return usrToken;
    }

    public void setUsrToken(String usrToken) {
        this.usrToken = usrToken;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public RefreshToken getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        this.anonymous = anonymous;
    }

    public List<User> getEmployee() {
        return employee;
    }

    public void setEmployee(List<User> employee) {
        this.employee = employee;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}
