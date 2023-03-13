package Model;

import PROV.DM.ProvActivity;
import java.util.Date;

public class ActivityPSS extends ProvActivity {

    private String description;

    public ActivityPSS() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityPSS(String description, Date startTime, Date endTime, Integer idActivity) {
        super(idActivity);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.description = description;
    }

}
