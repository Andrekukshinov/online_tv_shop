package application.andrei.kukshinov.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authorities {
    private long id;
    private String authority;

    @Override
    public String toString() {
        return  authority;
    }
}
