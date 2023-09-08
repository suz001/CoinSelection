package utils;
import java.util.Hashtable;
import java.io.*;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
public class UserDatabase {

    public static Hashtable<String, String> userMap; 
    
    /**
     * Will not read first line of login.txt, but the rest of the lines will be treated as the password/username combos
     */
    private static void setUserData() throws IOException {
        try {
        	userMap = new Hashtable<>();
            File file = new File("login.txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String readLine = bufferedReader.readLine();
            while((readLine = bufferedReader.readLine()) != null){
                String[] split = readLine.split(":");
                userMap.put(split[0].toLowerCase().replaceAll(" ", ""),
                        split[1].replaceAll(" ", ""));
            }
            bufferedReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * get the user's information
     * @return userData the hash tables contains user information
     * @throws IOException
     */
    public static Hashtable<String, String> getDatabase() throws IOException{
        setUserData(); 
        if(!userMap.isEmpty())
            return userMap; 
        else
            return null;
    }
}