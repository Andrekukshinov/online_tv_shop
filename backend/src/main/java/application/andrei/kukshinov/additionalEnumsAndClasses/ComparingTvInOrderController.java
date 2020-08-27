package application.andrei.kukshinov.additionalEnumsAndClasses;

import application.andrei.kukshinov.entitiy.TvSet;

public class ComparingTvInOrderController {
    public static int compareTv(TvSet tv1, TvSet tv2)  {
        return Long.compare(tv1.getId(), tv2.getId());
    }
}
