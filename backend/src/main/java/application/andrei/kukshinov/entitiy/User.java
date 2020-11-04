package application.andrei.kukshinov.entitiy;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, name = "email")
    private String username;
    @Column(name = "is_account_active")
    private boolean isAccountNonExpired;
    @Column(name = "is_account_not_locked")
    private boolean isAccountNonLocked;
    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "is_credentials_not_expired")
    private boolean isCredentialsNonExpired;
    private String password;
    private String phone;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "avatar_URL")
    private String avatarURL;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
		  CascadeType.DETACH, CascadeType.REFRESH})
    private List<Order> userOrders = new LinkedList<>();
    @OneToMany(mappedBy = "user"/*, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH }*/)
    private Set<CreditCard> creditCards = new TreeSet<>();
    @OneToMany(mappedBy = "user"/*, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH }*/)
    private Set<Location> location;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "users_roles", joinColumns = {
		  @JoinColumn(name = "role_id")}, inverseJoinColumns = {
		  @JoinColumn(name = "user_id")})
    public Set<Role> authorities;

}
