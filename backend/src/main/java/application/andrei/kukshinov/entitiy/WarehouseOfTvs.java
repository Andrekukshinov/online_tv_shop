package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "warehouse_tv_amounts")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class WarehouseOfTvs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //
    private long id;
    @Column(name = "amount")
    //
    private int tvAmount;
    //
    private float price;
    //
    @Column(name = "is_active")
    private boolean isActive;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tv_id")

    //
    private TvSet tvModel;
}
