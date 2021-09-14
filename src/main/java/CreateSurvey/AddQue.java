package CreateSurvey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddQue extends JFrame
{
    JLabel EnterQuestion;
    JTextField EnterQuestionTextField;
    JButton SaveButton;
    JFrame frame;
    JPanel panel;

    public java.sql.Connection c;
    public Statement s;
    public PreparedStatement p;


    public AddQue()
    {

        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(30,30,720,400);
        frame = new JFrame();
        frame.setTitle("Add Question");
        frame.setBounds(100, 100, 800, 500);
        frame.setLocation(0,0);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        frame.add(panel);
        panel.setLayout(null);

        EnterQuestion = new JLabel("Enter Question");
        EnterQuestionTextField = new JTextField();
        SaveButton = new JButton("Save");

        EnterQuestion.setBounds(70, 50, 100, 30);
        EnterQuestionTextField.setBounds(200, 50, 200, 30);
        SaveButton.setBounds(150, 100, 100, 30);

        panel.add(EnterQuestion);
        panel.add(EnterQuestionTextField);
        panel.add(SaveButton);

        SaveButton.addActionListener(new ActionListener(  )
        {
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_system", "Aress", "Aress@aress123");
                    s = c.createStatement();

                    String str = " insert into questions(question) values('" + EnterQuestionTextField.getText() + "')";
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

