package application.andrei.kukshinov.entitiy;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role /*implements GrantedAuthority*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role")
    private String authority;
    @ManyToMany(mappedBy = "authorities", cascade = { CascadeType.ALL })
    @JsonBackReference
    public List<User> users;
}
