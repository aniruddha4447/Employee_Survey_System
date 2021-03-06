package LoginPage;

import AdminPackage.MainAdminGUI;
import ManagerPackage.MainManagerGUI;
import UserPackage.MainUserGUI;
import com.buttons.AllButtonsLoginPage;
import com.framesAndPanels.AllFrameLoginPage;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabelsLoginPage;
import com.textFields.AllTextFieldsLoginPage;
import com.util.InitiateComponents;
import com.util.UtilityFunctions;

import javax.swing.*;
import java.sql.*;

import static com.util.UtilityFunctions.encryptDecrypt;

public class LoginPage implements InitiateComponents
{

private JFrame loginFrame;
private JPanel loginPagePanel;
private JLabel userNameLabel,passwordLabel;
private JTextField userNameField;
private JPasswordField passwordField;
private JButton loginButton,resetButton,exitButton;
private PreparedStatement preparedStatement;
private ResultSet roleNameFetching;
public JFrame queFrame;
public JFrame frame;
protected String LoginUsername;
protected String LoginPassword;


LoginPage() {
    initComponents();
}

@Override
public void initComponents(){

    AllButtonsLoginPage buttonsCallingObj = new AllButtonsLoginPage();
    AllLabelsLoginPage labelsCallingObj=new AllLabelsLoginPage();
    AllTextFieldsLoginPage textFieldsCallingObj = new AllTextFieldsLoginPage();
    AllFramesAndPanels userFramesAndPanelsObj =new AllFramesAndPanels();

    loginFrame=new AllFrameLoginPage().putLoginPageFrame();
    queFrame=userFramesAndPanelsObj.putFrame();

    // Calling created label methods from AllLabelsLoginPage.java

    userNameLabel=labelsCallingObj.putUsernameLabel();
    loginFrame.add(userNameLabel);

    passwordLabel=labelsCallingObj.putPasswordLabel();
    loginFrame.add(passwordLabel);

    //Calling created TextFields methods from AllTextFieldsLoginPage.java

    userNameField=textFieldsCallingObj.putUsernameTextField();
    loginFrame.add(userNameField);

    passwordField=textFieldsCallingObj.putPasswordField();
    loginFrame.add(passwordField);

    // Calling all created button methods from AllButtonsLoginPage.java

    loginButton= buttonsCallingObj.putLoginButton();
    loginFrame.add(loginButton);
    loginButton.addActionListener(e -> {

        //calling login button actionListener method
        loginButtonActionListener();
    });


    resetButton= buttonsCallingObj.putResetButton();
    loginFrame.add(resetButton);
    resetButton.addActionListener(e -> {

        //calling reset button actionListener method
        resetButtonActionListener();
    });


    exitButton= buttonsCallingObj.putExitButton();
    loginFrame.add(exitButton);

    exitButton.addActionListener(e -> {
        //calling exit button actionListener method
        exitButtonActionListener();

    });


}

    private void exitButtonActionListener()
    {
        int a=JOptionPane.showConfirmDialog(loginPagePanel,"Are you sure ?","Warning",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if(a==JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }

    private void resetButtonActionListener()
    {
        userNameField.setText("");
        passwordField.setText("");
    }

    private void loginButtonActionListener()
    {
        try{
            Connection connection= UtilityFunctions.createConnection();

            String username = userNameField.getText();
            LoginUsername=username;

            String password= UtilityFunctions.encryptDecrypt( new String(passwordField.getPassword()));
            LoginPassword=password;

            preparedStatement = connection.prepareStatement("select role from users where username= ? and password= ?");
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            //ResultSet idFetching ;
            roleNameFetching = preparedStatement.executeQuery();

            if(roleNameFetching.next())
            {
                    String role_name= roleNameFetching.getString(1);
                    System.out.println(role_name);
                    if(role_name.equalsIgnoreCase("admin"))
                    {
                        MainAdminGUI mainAdminGUI=new MainAdminGUI();
                        mainAdminGUI.setVisible(true);
                        loginFrame.setVisible(false);
                    }
                    else if(role_name.equalsIgnoreCase("developer"))
                    {
                        MainUserGUI mainUserGUI=new MainUserGUI();
                        mainUserGUI.setVisible(true);
                        loginFrame.setVisible(false);
                        
                    }
                    else if(role_name.equalsIgnoreCase("manager"))
                    {
                        MainManagerGUI mainManagerGUI=new MainManagerGUI();
                        //mainManagerGUI.setVisible(true);
                        loginFrame.setVisible(false);
                    }

            }
            else
            {
                JOptionPane.showMessageDialog(loginPagePanel,"Enter Valid username and password","ERROR",JOptionPane.ERROR_MESSAGE);

            }
        }
        catch (SQLException | ClassNotFoundException exception)
        {
            exception.printStackTrace();
        }
    }


public static void main(String[] arg) throws SQLException, ClassNotFoundException {
    new LoginPage();
}
}