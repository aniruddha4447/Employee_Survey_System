package com.framesAndPanels;

import javax.swing.*;
import java.awt.*;

public class AllFramesAndPanels extends JFrame {

    JFrame queFrame= new JFrame("Survey");
    JPanel buttonPanel=new JPanel();
    JPanel topPanel = new JPanel();
    JPanel bottomPanel=new JPanel();
    JPanel mainPanel = new JPanel();

    public JFrame putFrame(){
        queFrame.setLayout(null);
        queFrame.setExtendedState(MAXIMIZED_BOTH);
        return queFrame;
    }

    public JPanel putMainPanel(){
        mainPanel.setLayout(null);
        mainPanel.setBounds(251,120,1450,629);
        mainPanel.setBackground(Color.WHITE);
        return mainPanel;
    }

    public JPanel putTopPanel() {
        topPanel.setLayout(null);
        topPanel.setBounds(0,0,1450,119);
        topPanel.setBackground(Color.lightGray);
        return topPanel;
    }

    public JPanel putBottomPanel(){
        bottomPanel.setBounds(0,750,1450,300);
        bottomPanel.setBackground(Color.lightGray);
        return bottomPanel;
    }

    public JPanel putButtonPanel(){
        buttonPanel.setBounds(0,120,250,629);
        buttonPanel.setBackground(Color.gray);
        return buttonPanel;
    }
}
