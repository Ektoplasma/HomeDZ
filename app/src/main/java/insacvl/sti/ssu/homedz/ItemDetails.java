package insacvl.sti.ssu.homedz;

/**
 * Created by Rudy on 30/01/2017.
 */

public class ItemDetails {
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String itemDescription) {
        this.id = itemDescription;
    }

    public int getImageNumber() {
        return imageNumber;
    }
    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    private String name ;
    private String id;
    private int imageNumber;
}
