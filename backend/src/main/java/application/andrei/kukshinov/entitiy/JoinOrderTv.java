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
@Table(name = "orders_tv")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class JoinOrderTv implements Comparable<JoinOrderTv>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")

    //
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_id")

    //
    private TvSet tv;
    @Column(name = "tv_model_price")
    private int tvModelPrice;
    @Column(name = "tv_quantity")
    private byte tvQuantity;


    @Override
    public int compareTo(JoinOrderTv o) {
        if (this.id == o.id) return 0;
        else if (this.id > o.id) return 1;
        else return 0;
    }
}
