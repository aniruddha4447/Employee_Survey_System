/*
  on clicking button new frame open to create and update record
 */
package AdminPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Pattern;


public class MainAdminGUI extends JFrame {
    JFrame frame;
    JPanel buttonPanel, topPanel, mainPanel, editPanel, createPanel;
    JButton Create, Editprofile;
    JLabel title;
    Statement s;
    /**********************************************************/
    JPasswordField p1, p2;
    JPanel panel1;
    JLabel title1, IDLabel, NameLabel, PhnoLabel, RoleLabel, gender, AddressLabel, Password, Confirmpass, EmailLabel;
    JTextArea Add;
    JTextField ID, Namet, Phno, Email;
    JRadioButton male, female;
    JComboBox Rolecb;
    JFrame f;
    JPanel panel;
    private JButton submit, deleteb, reset, update, cancelb, cancel, showdetails;

    String Username, Addr, Pno, Rolval, Gval, pwd, email, Cpwd;
    String outputString, inputString, em, nm, pass, phno, rnm, gen, addr, passw;


    PreparedStatement p;
    Connection con;

    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);

    public void connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_portfolio", "root", "pkha14@21");
        System.out.println("Connected to Database");
    }

    public void PanelExample() {
        frame = new JFrame("Admin Module");
        buttonPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        editPanel = new JPanel();
        createPanel = new JPanel();
        JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        Create = new JButton("Create User");
        Editprofile = new JButton("Edit Profile");

        // Panels Code
        frame.setLayout(null);
        frame.setExtendedState(MAXIMIZED_BOTH);
        scroll.setBounds(1421, 120, 20, 629);
        topPanel.setBounds(250, 0, 1450, 120);
        topPanel.setBackground(Color.GRAY);
        buttonPanel.setBounds(0, 0, 250, 710);
        buttonPanel.setBackground(Color.GRAY);
        buttonPanel.setLayout(null);
        mainPanel.setBounds(251, 120, 1451, 629);
        mainPanel.setBackground(Color.WHITE);

        // Buttons Code
        Create.setBounds(30, 150, 180, 40);
        Create.setBackground(Color.lightGray);
        Editprofile.setBounds(30, 230, 180, 40);
        Editprofile.setBackground(Color.lightGray);

        //Label Code
        title = new JLabel("ADMIN ");
        title.setBounds(50, 100, 100, 30);
        title.setFont(new Font("Verdana", Font.PLAIN, 32));
        title.setForeground(new Color(255, 255, 255));

        // Add Panels and Buttons on the Frame
        buttonPanel.add(Create);
        buttonPanel.add(Editprofile);
        topPanel.add(title);
        frame.add(mainPanel);
        frame.add(editPanel);
        frame.add(createPanel);
        frame.add(topPanel);
        frame.add(buttonPanel);
        frame.add(scroll);
        frame.setSize(400, 400);
        frame.pack();
        frame.setVisible(true);

        Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Createu();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
        Editprofile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Editu();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        MainAdminGUI gui = new MainAdminGUI();
        gui.PanelExample();
        gui.connection();
    }


   public void Createu() throws SQLException, ClassNotFoundException {
        Createuser cu=new Createuser();
        cu.connection();

    }

    public void Editu() throws SQLException, ClassNotFoundException {
          Editprofile ep=new Editprofile();
           ep.connection();
    }


}