package com.hust.productsale.model;

import com.hust.productsale.validation.annotation.NullOrNotBlank;
import javax.persistence.*;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users_usr")
public class User {
    private static final long serialVersionUID = 74597879236597717L;

    @Id
    @Column(name = "id_usr", unique = true)
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
    private Date createdAt;

    @Column(name = "date_update")
    @NullOrNotBlank(message = "Last name can not be blank")
    private Date updatedAt;

//    @Column(name = "usr_enabled")
//    private Boolean active;

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

    public User() {
    }

    public User(User user) {
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

//    public Boolean getActive() {
//        return active;
//    }
//
//    public void setActive(Boolean active) {
//        this.active = active;
//    }

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
}
