package com.buttons;

import javax.swing.*;
import java.awt.*;

public class AllUserInputButtons extends JButton {
    static int BUTTONWIDTH = 120;
    static int BUTTONHEIGHT =30;

    JButton feedbackButton=new JButton("Fill Feedback");
    JButton submitButton = new JButton("Submit");
    JButton resetButton = new JButton("Clear");
    JButton nextButton = new JButton("Next");
    JButton prevButton = new JButton("Previous");

    public JButton putFeedbackButton(){
        feedbackButton.setBounds(50,110,BUTTONWIDTH,BUTTONHEIGHT);
        feedbackButton.setBackground(Color.lightGray);
        return feedbackButton;
    }

    public JButton putNextButton(){
        nextButton.setBounds(900,500,BUTTONWIDTH,BUTTONHEIGHT);
        nextButton.setBackground(Color.lightGray);
        return nextButton;
    }

    public JButton putPrevButton(){
        nextButton.setBounds(750,500,BUTTONWIDTH,BUTTONHEIGHT);
        nextButton.setBackground(Color.lightGray);
        return prevButton;
    }

    public JButton putSubmitButton(){
        submitButton.setBounds(500,750,BUTTONWIDTH,BUTTONHEIGHT);
        feedbackButton.setBackground(Color.lightGray);
        return submitButton;
    }

    public JButton putResetButton(){
        resetButton.setBounds(1200,750,BUTTONWIDTH,BUTTONHEIGHT);
        feedbackButton.setBackground(Color.lightGray);
        return resetButton;
    }
}
