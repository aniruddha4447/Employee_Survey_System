package UserPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.buttons.AllUserInputButtons;
import com.framesAndPanels.AllFramesAndPanels;
import com.labels.AllLabels;
import com.util.*;

import static com.util.UtilityFunctions.createConnection;

public class MainUserGUI extends JFrame implements ActionListener, ItemListener {

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public JButton feedbackButton;
    public JButton nextButton;
    public JButton prevButton;
    public JButton submitButton;
    public JButton resetButton;

    public JFrame queFrame;
    public JPanel mainPanel;
    public JPanel topPanel;
    public JPanel bottomPanel;
    public JPanel buttonPanel;

    public JLabel createDateLabel;
    public JLabel publishDateLabel;
    public JLabel closeDateLabel;
    public JLabel closeDate;
    public JLabel createDate;
    public JLabel publishDate;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainUserGUI();
    }

    public  MainUserGUI(){
        AllUserInputButtons userInputButtonsCallingObj = new AllUserInputButtons();
        AllFramesAndPanels userFramesAndPanelsObj =new AllFramesAndPanels();
        //  AllActionListeners action = new AllActionListeners();
        AllLabels labelsObj = new AllLabels();

        // Frames and panels code
        queFrame=userFramesAndPanelsObj.putFrame();
        mainPanel=userFramesAndPanelsObj.putMainPanel();
        topPanel=userFramesAndPanelsObj.putTopPanel();
        bottomPanel=userFramesAndPanelsObj.putBottomPanel();
        buttonPanel=userFramesAndPanelsObj.putButtonPanel();

        // Buttons Code
        feedbackButton=userInputButtonsCallingObj.putFeedbackButton();
        nextButton=userInputButtonsCallingObj.putNextButton();
        prevButton=userInputButtonsCallingObj.putPrevButton();
        submitButton=userInputButtonsCallingObj.putSubmitButton();
        resetButton=userInputButtonsCallingObj.putResetButton();

        // Labels Code
        createDateLabel=labelsObj.putCreateDate();
        publishDateLabel=labelsObj.putPublishDate();
        closeDateLabel=labelsObj.putCloseDate();
        createDate=labelsObj.createDate();
        closeDate=labelsObj.closeDate();
        publishDate=labelsObj.publishDate();

        // Add Panels and Buttons on the Frame
        queFrame.add(mainPanel);
        queFrame.add(topPanel);
        queFrame.add(bottomPanel);
        queFrame.add(buttonPanel);

        buttonPanel.add(feedbackButton);
        bottomPanel.add(submitButton);
        bottomPanel.add(resetButton);
        mainPanel.add(nextButton);
        mainPanel.add(prevButton);

        topPanel.add(createDateLabel);
        topPanel.add(publishDateLabel);
        topPanel.add(closeDateLabel);

        topPanel.add(createDate);
        topPanel.add(publishDate);
        topPanel.add(closeDate);
        topPanel.add(createDate);
        topPanel.add(closeDate);
        topPanel.add(publishDate);

        queFrame.setSize(400,400);
        queFrame.pack();
        queFrame.setVisible(true);

        feedbackButton.addActionListener(e -> {
            try {
                Connection con = createConnection();
                /// Current Date Code
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                String currentDate = dateFormat.format(now);



                //  action.feedbackButtonAction(con);
                // ResultSet fetchDates = con.createStatement().executeQuery("select creation_date,publish_date,close_date from survey,users where users_user_id = 2");
                ResultSet fetchQuestions = con.createStatement().executeQuery("select ques_id,question from questions,survey where '"+currentDate+"' between publish_date AND close_date" );
                //publish_date < '"+currentDate+"' and close_date > '"+currentDate+"'
                while(fetchQuestions.next()){
                    int lQuesId = fetchQuestions.getInt(1);
                    String lQuestion= fetchQuestions.getString(2);
                    System.out.println(lQuesId);
                    System.out.println(lQuestion);
                }
               /* fetchDates.next();
                String lcreateDate = fetchDates.getString(1);
                String lpublishDate=fetchDates.getString(2);
                String lcloseDate=fetchDates.getString(3);*/
                // AllLabels labelImpl = new AllLabels();

                topPanel.setLayout(null);
            /*    createDate.setText(lcreateDate);
                closeDate.setText(lcloseDate);
                publishDate.setText(lpublishDate);*/

                System.out.println("mayurhtht");

            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}

//  int x=30,y=5,width=250,height=25;
