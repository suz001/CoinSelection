package setupandUI;
/**
* @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
* 
*/
import proxy.ProxyValidator;
import proxy.ValidateInterface;

public class LoginValidator  {

    public static Boolean validate(String user, String pass){
        ValidateInterface valid = new ProxyValidator();
        return valid.validation(user, pass);
    }
}