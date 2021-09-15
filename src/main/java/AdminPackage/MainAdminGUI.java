/*
  on clicking button new frame open to create and update record
 */
package AdminPackage;

import AdminPackage.pendingTask.AdminPendingTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.regex.Pattern;


public class MainAdminGUI extends JFrame {
    JFrame frame;
    JPanel buttonPanel, topPanel, mainPanel, editPanel, createPanel, pendingPanel;
    JButton Create, Editprofile,Createsurvey,Pendingtask;
    JLabel title;

    public MainAdminGUI()
    {
        PanelExample();
    }





    Connection conn;

    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);

   /* public void connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/survey_portfolio", "Aress", "Aress@aress123");
        System.out.println("Connected to Database");
    }*/

    public void PanelExample() {
        frame = new JFrame("Admin Module");
        buttonPanel = new JPanel();
        topPanel = new JPanel();
        mainPanel = new JPanel();
        editPanel = new JPanel();
        createPanel = new JPanel();
        pendingPanel=new JPanel();
        JScrollBar scroll = new JScrollBar(JScrollBar.HORIZONTAL);
        Create = new JButton("Create User");
        Editprofile = new JButton("Edit Profile");
        Createsurvey = new JButton("Create Survey");
        Pendingtask=new JButton("Pending Task");


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

        //pending panel
        pendingPanel.setBounds(251, 120, 1451, 629);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        // Buttons Code
        Create.setBounds(30, 150, 180, 40);
        Create.setBackground(Color.lightGray);
        Editprofile.setBounds(30, 230, 180, 40);
        Editprofile.setBackground(Color.lightGray);
        Createsurvey.setBounds(30, 310, 180, 40);
        Createsurvey.setBackground(Color.lightGray);
        Pendingtask.setBounds(30, 390, 180, 40);
       Pendingtask.setBackground(Color.lightGray);



        //Label Code
        title = new JLabel("ADMIN ");
        title.setBounds(50, 100, 100, 30);
        title.setFont(new Font("Verdana", Font.PLAIN, 32));
        title.setForeground(new Color(255, 255, 255));

        // Add Panels and Buttons on the Frame
        buttonPanel.add(Create);
        buttonPanel.add(Editprofile);
        buttonPanel.add(Createsurvey);
        buttonPanel.add(Pendingtask);
        topPanel.add(title);
        frame.add(pendingPanel);
        pendingPanel.setVisible(false);
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
        Editprofile
                .addActionListener(new ActionListener() {
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

        Pendingtask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AdminPendingTask adminPendingTask=new AdminPendingTask();
                    pendingPanel.setVisible(true);

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
       // gui.connection();
    }


   public void Createu() throws SQLException, ClassNotFoundException {
        Createuser cu=new Createuser();
       // cu.connection();

    }

    public void Editu() throws SQLException, ClassNotFoundException {
          Editprofile ep=new Editprofile();
          // ep.connection();
    }


}