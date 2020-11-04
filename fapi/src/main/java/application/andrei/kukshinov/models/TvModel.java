package application.andrei.kukshinov.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class TvModel {
    private long id;
    private String modelName;
    private String producer;
    private float diagonal;
    private String resolution;
    private boolean is3d = false;
    private String wifi;
    private String bluetooth;
}
