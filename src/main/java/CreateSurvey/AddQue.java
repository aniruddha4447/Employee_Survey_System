package CreateSurvey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import static java.lang.Integer.parseInt;

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
    long tempQuesLong=0L;

    public AddQue() throws SQLException {
    MainClass mainClass=new MainClass();
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

        SaveButton.addActionListener(new ActionListener()
        {
            ResultSet resultset;
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_mgmt", "Aress", "Aress@aress123");
                    s = c.createStatement();


                    String str = " insert into questions(question) values('" + EnterQuestionTextField.getText() + "')";

                    p = c.prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
                    int rownum = p.executeUpdate();
                    resultset = p.getGeneratedKeys();
                    if(  resultset.next()) {
                        System.out.println("Record inserted successfully");
                        tempQuesLong = resultset.getInt(1);
                    }
                    //PreparedStatement p = c.prepareStatement(str);



                    boolean x = false;
                    if (x == p.execute())
                    {

                        String insertDataIntoCatQue="insert into category_question values (category_id="+ mainClass.fetchingCatId +", question_id="+(int)tempQuesLong+")";
                        PreparedStatement pAddRelationalTable=c.prepareStatement(insertDataIntoCatQue);
                        if(pAddRelationalTable.execute())
                        {
                            System.out.println("Successfull");
                        }
                        else
                            System.out.println("not successfull");
                    }
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

