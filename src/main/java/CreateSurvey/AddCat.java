package CreateSurvey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddCat extends JFrame {

    JLabel EnterCategory;
    JTextField EnterCategoryTextField;
    JButton SaveButton;
    JFrame frame;

    public java.sql.Connection c;
    public Statement s;
    public PreparedStatement p;


    public AddCat() {

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(30,30,720,400);
        frame = new JFrame();
        setTitle("Add Category");
        frame.setBounds(100, 100, 800, 500);
        frame.setLocation(0,0);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(panel);
        panel.setLayout(null);

        EnterCategory = new JLabel("Enter Category");
        EnterCategoryTextField = new JTextField();
        SaveButton = new JButton("Save");

        EnterCategory.setBounds(70, 50, 100, 30);
        EnterCategoryTextField.setBounds(200, 50, 200, 30);
        SaveButton.setBounds(150, 100, 100, 30);

        panel.add(EnterCategory);
        panel.add(EnterCategoryTextField);
        panel.add(SaveButton);

        SaveButton.addActionListener(new ActionListener(  )
        {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_system", "Aress", "Aress@aress123");
                    s = c.createStatement();

                    String str = " insert into category(category) values('" + EnterCategoryTextField.getText() + "')";
                    PreparedStatement p = c.prepareStatement(str);

                    boolean x = false;
                    if (x == p.execute())
                        System.out.println("Record Successfully Inserted");
                    else
                        System.out.println("Insert Failed");
                } catch (SQLException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                JOptionPane.showMessageDialog(frame, "Successfull Insertion");
                try {
                    new MainClass();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }        });
        panel.setVisible(true);
        frame.setVisible(true);
    }


}




