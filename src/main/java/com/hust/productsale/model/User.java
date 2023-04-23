package com.hust.productsale.model;

import com.hust.productsale.validation.annotation.NullOrNotBlank;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users_usr")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private static final long serialVersionUID = 74597879236597717L;

    @Id
    @Column(name = "id_usr", unique = true)
    @NullOrNotBlank(message = "Username can not be blank")
    private String username;

    @Column(name = "name_usr", length = 50)
    private String name;

//    @Column(name = "NS_ID")
//    private Long nsId;
//
//    @Column(name = "id_org_usr")
//    private Integer donviId;

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

    @Column(name = "usr_enabled", nullable = false)
    private Boolean active;

    @Column(name = "usr_token", nullable = false)
    private String usrToken;

    @Column(name = "type_usr", insertable = true, updatable = true, nullable = true)
    private Short type;

//    @Column(name = "Alias_CA", insertable = true, updatable = true, nullable = true)
//    private String aliasCA;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "auth_user_authority", joinColumns = {
            @JoinColumn(name = "USER_ID", referencedColumnName = "id_usr") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
    private Set<Role> roles = new HashSet<>();


	public User(User user) {
            // id = user.getId(); firstName
            name = user.getName();
            username = user.getUsername();
            password = user.getPassword();
            email = user.getEmail();
            active = user.getActive();
            usrToken = user.getUsrToken();
            roles = user.getRoles();
//            nsId = user.getNsId();
//            donviId = user.getDonviId();
    }
}
