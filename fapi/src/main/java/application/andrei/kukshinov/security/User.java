package application.andrei.kukshinov.security;

import application.andrei.kukshinov.models.Authorities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User  {
    private String username;
    private String password;
    private List<Authorities> authorities;
    private Date registrationDate;
    private String phone;

    public User() {
        registrationDate = new Date(new java.util.Date().getTime());
    }
}
