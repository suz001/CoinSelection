package proxy;

public class ProxyValidator implements ValidateInterface{
    private ValidateInterface validator = new ActualValidator();
    /**
     * Proxy (fake) validation method
     */
    @Override
    public Boolean validation(String username, String password){
        return validator.validation(username, password);
    }
}
