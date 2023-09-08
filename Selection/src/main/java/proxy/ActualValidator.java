package proxy;
import java.io.IOException;
import java.util.Hashtable;

import utils.UserDatabase;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class ActualValidator implements ValidateInterface{

    /**
     * Real Validation Class (not the proxy)
     */
    @Override
    public Boolean validation(String username, String password){
        try {
            Hashtable<String, String> userData = UserDatabase.getDatabase();
            if( !userData.isEmpty() && userData != null){
                if(userData.get(username) != null){
                    return userData.get(username).equals(password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
