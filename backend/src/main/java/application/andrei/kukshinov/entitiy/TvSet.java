package application.andrei.kukshinov.entitiy;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tv")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class TvSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String producer;
    private String model;
    @Column(name = "img_URL")
    private String imgURl;
    private float diagonal;
    private String resolution;
    private boolean is3d = false;
    private String wifi;
    private String bluetooth;
    @OneToMany(mappedBy = "tv", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
   //@JsonView(Views.Internal.class)
    private List<JoinOrderTv> orderTv;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tvModel")
   //@JsonView(Views.Internal.class)
    private WarehouseOfTvs warehouse;
    @Override
    public String toString() {
        return "TvSet{ model:" + producer + " " + model + " " + '}';
    }

}

