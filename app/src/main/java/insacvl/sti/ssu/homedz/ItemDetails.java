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
    public int getId() {
        return id;
    }
    public void setId(int itemDescription) {
        this.id = itemDescription;
    }
    public int getVal() {
        return val;
    }
    public void setVal(int valeur) {
        this.val = valeur;
    }
    String getDesc() {
        return description;
    }
    void setDesc(String desc){
        description = desc;
    }
    int getImageNumber() {
        return imageNumber;
    }
    public void setImageNumber(int imageNumber) {
        this.imageNumber = imageNumber;
    }

    private String description;
    private String name ;
    private int id;
    private int imageNumber;
    private int val;
}