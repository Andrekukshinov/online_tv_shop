package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "credit_cards")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CreditCard implements Comparable<CreditCard> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "credit_card_number")
    private long creditCardNumber;
    private short ccv;
    @Column(name = "expiration_date")
    private String expirationDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    //
    private User user;

    @Override
    public int compareTo(CreditCard o) {
        return Long.compare(this.id, o.id);
    }
}


