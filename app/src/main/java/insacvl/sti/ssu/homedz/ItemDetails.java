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
    public int getVal() {
        return val;
    }
    public void setVal(int valeur) {
        this.val = valeur;
    }
    public String getDesc() {
        return description;
    }
    public void setDesc(String desc){
        description = desc;
    }
    public int getImageNumber() {
        return imageNumber;
    }
    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    private String description;
    private String name ;
    private String id;
    private int imageNumber;
    private int val;
}