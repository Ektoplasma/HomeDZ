package insacvl.sti.ssu.homedz;


import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import insacvl.sti.ssu.homedz.pahowrapper.ActivityConstants;
import insacvl.sti.ssu.homedz.pahowrapper.Connection;
import insacvl.sti.ssu.homedz.pahowrapper.Connections;

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
    private String clientHandle;
    private Context ctx;



    //////////////////////// CONSTRUCTEUR ////////////////////////////////////
    public JsonDz(JSONObject MyJSONObject, Context ctx) throws JSONException {
        clientHandle = ActivityConstants.currentHandler;
        this.ctx = ctx;

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
            this.idx = this.json.getInt("idx");
        } catch (JSONException e) {
            System.out.println("No idx");
        }
        try {
            this.name = this.json.getString("name");
        } catch (JSONException e) {
            System.out.println("No name");
        }
        try {
            this.id = this.json.getString("id");
        } catch (JSONException e) {
            System.out.println("No id");
        }
        try {
            this.unit = this.json.getInt("unit");
        } catch (JSONException e) {
            System.out.println("No unit");
        }
        try {
            this.dtype = this.json.getString("dtype");
        } catch (JSONException e) {
            System.out.println("No dtype");
        }
        try {
            this.stype = this.json.getString("stype");
        } catch (JSONException e) {
            System.out.println("No stype");
        }
        try {
            this.nvalue = this.json.getInt("nvalue");
        } catch (JSONException e) {
            System.out.println("No nvalue");
        }
        try {
            this.svalue1 = this.json.getInt("svalue1");
        } catch (JSONException e) {
            System.out.println("No svalue1");
        }
        try {
            this.svalue2 = this.json.getInt("svalue2");
        } catch (JSONException e) {
            System.out.println("No svalue2");
        }
        try {
            this.battery = this.json.getInt("battery");
        } catch (JSONException e) {
            System.out.println("No battery value");
        }
        try {
            this.rssi = this.json.getInt("RSSI");
        } catch (JSONException e) {
            System.out.println("No RSSI");
        }
    }


    public void updateView(String dtype){
        //On notera que ce système ne fonctionne pas pour les scènes

        // Les différentes variables à utiliser
        int MyIdx = getIdx();

        Connection connection = Connections.getInstance(ctx).getConnection(clientHandle);

        // Les expressions régulières ça fait hacker
        // Permet de résumer tous les types de lumières dans la même catégorie
        if (dtype.startsWith("Light")){
            dtype = "LightSwitch";
        }
        else if(dtype.startsWith("Therm") || dtype.startsWith("Temp")){
            dtype = "Temp";
        }
        // Switch sur dtype pour savoir quel fragment updater
        switch(dtype){
            case "Temp":
                //Update ThermFragment
                if(connection.isNewTemp(MyIdx)){
                    ItemDetails bleh = new ItemDetails();
                    bleh.setDesc("Je suis un thermometre");
                    bleh.setId(MyIdx);
                    bleh.setName(name);
                    bleh.setVal(Math.round(svalue1));
                    bleh.setImageNumber(R.drawable.therm_64);
                    connection.addItemDetailsTemp(bleh);
                }
                else
                {
                    connection.whichOneTemp(MyIdx, name, Math.round(svalue1));
                }
                break;
            case "LightSwitch":
                //Update LightFragment
                if(connection.isNewLight(MyIdx)){
                    ItemDetails bleh = new ItemDetails();
                    bleh.setDesc("Je suis une lampe");
                    bleh.setId(MyIdx);
                    bleh.setName(name);
                    bleh.setVal(nvalue);
                    if(nvalue > 0) bleh.setImageNumber(R.drawable.lightbulb_icon_on64);
                    else bleh.setImageNumber(R.drawable.lightbulb_icon_off64);
                    connection.addItemDetailsLight(bleh);
                }
                else
                {
                    connection.whichOneLight(MyIdx, name, nvalue);
                }
                break;

        }
    }

    ////////////////////////////// getters //////////////////////////////////


    public int getIdx() {
        return idx;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDtype() {
        return dtype;
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
