package Model;

import PROV.DM.ProvEntity;

public class EntityPSS extends ProvEntity {
    private String title;
    private int price;

    public EntityPSS() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
