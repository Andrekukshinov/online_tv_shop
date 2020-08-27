package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "credit_cards")
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
//    @JsonIgnore
//    @JsonManagedReference
    @JsonBackReference
    private User user;

    public CreditCard() {
    }

    public CreditCard(long id, long creditCardNumber, short ccv, String expirationDate, User user) {
        this.id = id;
        this.creditCardNumber = creditCardNumber;
        this.ccv = ccv;
        this.expirationDate = expirationDate;
        this.user = user;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public long getId() {
        return id;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public short getCcv() {
        return ccv;
    }

    public void setCcv(short ccv) {
        this.ccv = ccv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(CreditCard o) {
        if (this.id > o.id) return 1;
        else if (this.id == o.id) return 0;
        else return -1;
    }
}
