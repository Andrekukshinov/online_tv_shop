package application.andrei.kukshinov.entitiy;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User /*implements UserDetails */{
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
    public boolean isAccountNonExpired;
    @Column(name = "is_account_not_locked")
    public boolean isAccountNonLocked;
    @Column(name = "is_enabled")
    public boolean isEnabled;
    @Column(name = "is_credentials_not_expired")
    public boolean isCredentialsNonExpired;
    private String password;
    private long phone;
    @Column(name = "registration_date")
    private Date registrationDate;
    @Column(name = "avatar_URL")
    private String avatarURL;
    @OneToMany(mappedBy = "user"/*, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH}*/)
    @JsonManagedReference
    private List<Order> userOrders = new LinkedList<>();
    @OneToMany(mappedBy = "user"/*, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH }*/)
    @JsonManagedReference
    private Set<CreditCard> creditCards = new TreeSet<>();
    @OneToMany(mappedBy = "user"/*, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH }*/)
    @JsonManagedReference
    private Set<Location> location;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                    @JoinColumn(name = "role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id")
            }
    )
    @JsonManagedReference
    public Set<Role> authorities;

}
