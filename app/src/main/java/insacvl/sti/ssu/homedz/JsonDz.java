package insacvl.sti.ssu.homedz;

import android.hardware.camera2.params.StreamConfigurationMap;
import android.widget.Toast;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marthin on 22/01/2017.
 */

public class JsonDz {

    private JSONObject json;
    // Pour capteurs
    private int idx;
    private String name;
    private String id;
    private int unit;
    private String dtype;
    private String stype;
    private int nvalue;
    private float svalue1;
    private float svalue2;
    private int battery;
    private int rssi;
    //Pour scenes
    //on recycle le name et le idx des capteurs
    private String title;
    private int favorite;
    private int hardwareID;


    private String lastUpdate;
    private String status;
    private String timers;
    private String type;
    //Pour sunrise/sunset
    private String serverTime;
    private String sunrise;
    private String sunset;
    //Pour lights-switches
    // !! tableau !!
    //on recycle name, type et idx
    private String isDimmer;
    private String subtype;



    //////////////////////// CONSTRUCTEUR ////////////////////////////////////
    public JsonDz(JSONObject MyJSONObject) throws JSONException {
        this.json = MyJSONObject;
        try {
            this.setTitle();
            switchIt();
        }
        catch(JSONException e){
            this.title = "";
            filler();
        }

    }

    /////////////////// Filler ///////////////////////////////////////
    /* remplit les différents attributs */
    private void filler() throws JSONException {
        try {
            JSONObject jsonIdx = this.json.getJSONObject("idx");
            this.idx = jsonIdx.getInt("idx");
        } catch (JSONException e) {
            System.out.println("No idx");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("name");
            this.name = jsonIdx.getString("name");
        } catch (JSONException e) {
            System.out.println("No name");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("id");
            this.id = jsonIdx.getString("id");
        } catch (JSONException e) {
            System.out.println("No id");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("unit");
            this.unit = jsonIdx.getInt("unit");
        } catch (JSONException e) {
            System.out.println("No unit");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("dtype");
            this.dtype = jsonIdx.getString("dtype");
        } catch (JSONException e) {
            System.out.println("No dtype");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("stype");
            this.stype = jsonIdx.getString("stype");
        } catch (JSONException e) {
            System.out.println("No stype");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("nvalue");
            this.nvalue = jsonIdx.getInt("nvalue");
        } catch (JSONException e) {
            System.out.println("No nvalue");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("svalue1");
            this.svalue1 = jsonIdx.getInt("svalue1");
        } catch (JSONException e) {
            System.out.println("No svalue1");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("svalue2");
            this.svalue2 = jsonIdx.getInt("svalue2");
        } catch (JSONException e) {
            System.out.println("No svalue2");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("battery");
            this.battery = jsonIdx.getInt("battery");
        } catch (JSONException e) {
            System.out.println("No battery value");
        }
        try {
            JSONObject jsonIdx = this.json.getJSONObject("RSSI");
            this.rssi = jsonIdx.getInt("RSSI");
        } catch (JSONException e) {
            System.out.println("No RSSI");
        }
    }

    ////////////////////////////////// TODO RUDY ///////////////////////////

    public void updateView(String dtype){
        //On notera que ce système ne fonctionne pas pour les scènes
        /*Todo : Partie pour Rudy (mettre à jour les différents fragments avec les infos*/

        // Les différentes variables à utiliser
        int MyIdx = getIdx();
        String MyName = getName();
        String MyId = getId();
        int MyUnit = getUnit();
        String MyStype = getStype();
        int MyNvalue = getNvalue();
        float MySvalue1 = getSvalue1();
        float MySvalue2 = getSvalue2();
        int MyBattery = getBattery();
        int MyRssi = getRssi();

        // Les expressions régulières ça fait hacker
        // Permet de résumer tous les types de lumières dans la même catégorie
        if (Pattern.matches("Light*", dtype) == true){
            dtype = "LightSwitch";
        }
        // Switch sur dtype pour savoir quel fragment updater
        switch(dtype){
            case "Temp":
                //Update ThermFragment

                break;
            case "LightSwitch":
              /*  //Update LightFragment
                      //si nouveau
                ItemDetails bleh = new ItemDetails();
                //...
                ((LightFragment) TabLayoutActivity.tabsPagerAdapter.getItem(1)).addItemDetails(bleh);*/
                break;

        }
    }

    ////////////////////////////// getters //////////////////////////////////

    public int getHardwareID() {
        return hardwareID;
    }

    public String getIsDimmer() {
        return isDimmer;
    }

    public JSONObject getJson() {
        return json;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getServerTime() {
        return serverTime;
    }

    public String getStatus() {
        return status;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public String getTimers() {
        return timers;
    }

    public String getType() {
        return type;
    }

    public int getFavorite() {

        return favorite;
    }


    public int getRssi() {
        return rssi;
    }

    public int getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getUnit() {
        return unit;
    }

    public String getDtype() {
        return dtype;
    }

    public String getStype() {
        return stype;
    }

    public int getNvalue() {
        return nvalue;
    }

    public float getSvalue1() {
        return svalue1;
    }

    public float getSvalue2() {
        return svalue2;
    }

    public int getBattery() {
        return battery;
    }

    /* Structure foire du trone : le getter récupère le titre dans le json, le setter set en fonction */
    /* Get title section from response & set accordingly*/
    public String getTitle() throws JSONException {
        JSONObject jsonTitle = this.json.getJSONObject("title");
        String fonzie = jsonTitle.getString("title");
        System.out.println("json title : " + fonzie);
        return fonzie;
    }

    public void setTitle() throws JSONException {
        this.title = this.getTitle();
    }

    ////////////////////////////////////////////////////////////////////////////////

    /* Handlers en fonction des commandes utilisées (avec title) */

    public void handleSunset(){ //TODO
        System.out.println("jsonTitle = 'GetSunRiseSet'");
    }

    public void handleLightSwitches(){ //TODO plein de sous objets avec le tableau puis updateView() sur chacun
        System.out.println("jsonTitle = 'GetLightSwitches'");
    }

    public void handleLightSwitchState(){ //TODO
        System.out.println("jsonTitle = 'SwitchLight'");
    }

    public void handleScenes(){
        //TODO
        System.out.println("jsonTitle = 'Scenes'");
    }

    public void handleSwitchScenes(){
        //TODO
        System.out.println("jsonTitle = 'SwitchScenes'");
    }



    /* Switch through titles to determine app behaviour */
    /*TODO Implement handle methods for light switch, scenes, sensors, meteo*/
    public void switchIt(){
        switch(this.title){
            case "GetSunRiseSet":
                this.handleSunset();
                break;
            case "GetLightSwitches":
                this.handleLightSwitches();
                break;
            case "SwitchLight":
                this.handleLightSwitchState();
                break;
            case "Scenes":
                this.handleScenes();
                break;
            case "SwitchScenes":
                this.handleSwitchScenes();
                break;
            case "SceneTimers":
                System.out.println("jsonTitle = 'SceneTimers'");
                break;
            case "SystemShutdown":
                System.out.println("jsonTitle = 'System Shutdown'");
                break;
            case "SystemReboot":
                System.out.println("jsonTitle = 'System Reboot'");
                break;
        }
    }

}
