package setupandUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * LoginUI.java
 * Build the LoginUI
 * @author Shuxuan Zhao, Jax Wang, Khoi Nguyen Vu, Jason Yiyuan Cao
 * 
 */
public class LoginUI extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private static JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();

    private static JLabel usernameLabel = new JLabel("Username:");
    private static JLabel passwordLabel = new JLabel("Password:");

    private static JTextField username = new JTextField();
    private static JPasswordField password = new JPasswordField();

    private static JButton loginBtn = new JButton("Submit!");

    private static LoginUI instance;

    public static LoginUI getInstance() {
        if (instance == null)
            instance = new LoginUI();
        return instance;
    }

    private static void setFrame(){
        panel.setLayout(null);

        frame.setTitle("Login");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(430, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private static void setElements(){
        usernameLabel.setBounds(25, 25, 200, 20);
        username.setBounds(100, 25, 200, 30);
        passwordLabel.setBounds(25, 60, 200, 20);
        password.setBounds(100, 60, 200, 30);
        loginBtn.setBounds(300, 120, 90, 25);
    }

    private static void addElements(){
        panel.add(usernameLabel);
        panel.add(username);
        panel.add(passwordLabel);
        panel.add(password);
        panel.add(loginBtn);
        setAction();
    }

    private static void setAction(){
        loginBtn.addActionListener((ActionListener) new LoginUI());
    }

    public static void launchLogInUI(){
        setFrame();
        setElements();
        addElements();
        getInstance();
    }

    /**
     * listen the action performed
     * check the username and password is valid
     * if the password is true, update to main class so that we can run the mainUI
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginBtn){
            String Username = username.getText().toLowerCase();
            String Password = String.valueOf(password.getPassword());
            if(Username.equals(""))
            {
            	JOptionPane.showMessageDialog(this, "Please enter username");
            }
            if(Password.equals(""))
            {
            	JOptionPane.showMessageDialog(this, "Please enter password");
            }
            else {
                if (LoginValidator.validate(Username, Password)) {
                    frame.dispose();
                    MainUI.launch();
                }
                else {
                    JOptionPane.showMessageDialog(this, "The account does not exist");
                    return;
                }
            }
        }
    }
}