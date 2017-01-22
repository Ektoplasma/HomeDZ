package insacvl.sti.ssu.homedz;

import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Marthin on 22/01/2017.
 */

public class JsonDz {

    private JSONObject json;
    private String title;

    JsonDz(JSONObject MyJSONObject) throws JSONException {
        this.json = MyJSONObject;
        this.setTitle();
    }

    /* Get title section from response & set accordingly*/
    public String getTitle() throws JSONException {
        JSONObject jsonTitle = this.json.getJSONObject("title");
        System.out.println("json title : " + this.title);
        return jsonTitle.getString("title");
    }

    public void setTitle() throws JSONException {
        this.title = this.getTitle();
    }

    public void handleSunset(){
        System.out.println("jsonTitle = 'GetSunRiseSet'");
    }
    /* Switch through titles to determine app behaviour */
    /*TODO Implement handle methods for light switch, scenes, sensors, meteo*/
    public void switchIt(){
        switch(this.title){
            case "GetSunRiseSet":
                this.handleSunset();
                break;
            case "GetLightSwitches":
                System.out.println("jsonTitle = 'GetLightSwitches'");
                //this.handleLightSwitches();
                break;
            case "SwitchLight":
                System.out.println("jsonTitle = 'SwitchLight'");
              //  this.handleLightSwitchState();
                break;
            case "Scenes":
                System.out.println("jsonTitle = 'Scenes'");
               // this.handleScenes();
                break;
            case "SwitchScenes":
                System.out.println("jsonTitle = 'SwitchScenes'");
               // this.handleSwitchScenes();
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
