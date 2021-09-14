package CreateSurvey;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import  java.lang.String;
import java.util.regex.Pattern;


class Survey {
    String category;
    String question;

    public Survey(String category, String question) {
        this.category = category;
        this.question = question;
    }
}

public class MainClass extends JFrame implements ItemListener
{
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JComboBox category, question;
    private JButton btnAdd,addCatButton, addQueButton;
    private JLabel lpublishdate, lcloseddate;
    private JTextField publishdateTF, closeddateTF;
    private String pdate, cdate,currentDate;
    private JScrollPane scrollPane;
    private Connection c;
    private Statement s;
    private PreparedStatement p;
    private ResultSet r;

    int row;
    ArrayList<Survey> surveyList;
    DefaultTableModel dtm;
    String header[] = new String[]{"Category", "Question"};

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        MainClass window = new MainClass();
        window.connection();
    }

    public void displaySurveyDetails() {
        dtm.setRowCount(0);
        for (int i = 0; i < surveyList.size(); i++) {
            Object[] obj = {surveyList.get(i).category, surveyList.get(i).question};
            dtm.addRow(obj);
        }
    }

    public MainClass() throws SQLException {
        initialize();
        surveyList = new ArrayList<>();
        dtm = new DefaultTableModel(header, 0);
        table.setModel(dtm);
    }

    public void connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_system", "Aress", "Aress@aress123");
        System.out.println("Connected to Database");
    }

    public void initialize() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(200,120,1570,840);
        panel.setLocation(0,0);
        frame = new JFrame("Survey ");
        frame.setBackground(new Color(250, 250, 250));
        frame.setBounds(200,120,1570,840);
        frame.setLocation(0,0);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.add(panel);
        panel.setLayout(null);

        //Category Label
        JLabel lCategory = new JLabel("Category");
        lCategory.setForeground(new Color(0, 0, 128));
        lCategory.setFont(new Font("Tahoma", Font.BOLD, 13));
        lCategory.setBounds(20, 50, 100, 26);
        panel.add(lCategory);

        //Category JCombobox
        String catagory[] = {};
        category = new JComboBox(catagory);
        category.setBounds(100, 50, 230, 26);
        panel.add(category);
        category_combo();
        panel.add(category);
        category.addItemListener(this);

        //Category add category button
        addCatButton = new JButton("Add Catogery");
        addCatButton.setBounds(100, 80, 111, 20);
        addCatButton.addActionListener(new ActionListener(  ) {
            public void actionPerformed(ActionEvent e) {
                new AddCat();
            }
        });
        panel.add(addCatButton);

        //Question Label
        JLabel lQuestion = new JLabel("Question");
        lQuestion.setForeground(new Color(0, 0, 128));
        lQuestion.setFont(new Font("Tahoma", Font.BOLD, 13));
        lQuestion.setBounds(20, 150, 150, 26);
        panel.add(lQuestion);

        //Question JCombobox
        String questions[] = {};
        question = new JComboBox(questions);
        question.setBounds(100, 150, 380, 20);
        panel.add(question);

        //Question add question button
        addQueButton = new JButton("Add Question");
        addQueButton.setBounds(100, 173, 111, 20);
        addQueButton.addActionListener(new ActionListener(  ) {
            public void actionPerformed(ActionEvent e) {
                new AddQue();
            }
        });
        panel.add(addQueButton);

        //Publish Date Label
        lpublishdate = new JLabel("Publish Date");
        lpublishdate.setForeground(new Color(0, 0, 128));
        lpublishdate.setFont(new Font("Tahoma", Font.BOLD, 13));
        lpublishdate.setBounds(20, 250, 150, 26);
        panel.add(lpublishdate);

        //Publish Date TextField
        publishdateTF = new JTextField();
        publishdateTF.setBounds(130, 250, 150, 30);
        panel.add(publishdateTF);

        //Closed Date Label
        lcloseddate = new JLabel("Closed Date");
        lcloseddate.setForeground(new Color(0, 0, 128));
        lcloseddate.setFont(new Font("Tahoma", Font.BOLD, 13));
        lcloseddate.setBounds(20, 350, 150, 26);
        panel.add( lcloseddate);

        //Closed Date TextField
        closeddateTF = new JTextField();
        closeddateTF.setBounds(130, 350, 150, 30);
        panel.add(closeddateTF);


        //Add Button
        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Survey data = new Survey(category.getItemAt(category.getSelectedIndex()).toString(), question.getItemAt(question.getSelectedIndex()).toString());
                surveyList.add(data);
                displaySurveyDetails();
            }
        });
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnAdd.setBounds(550, 400, 89, 23);
        panel.add(btnAdd);


        //Delete Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, " Do you want to delete ?", "Delete", JOptionPane.YES_NO_OPTION);
                if (choice == 0) {
                    dtm.removeRow(row);
                    surveyList.remove(row);
                    displaySurveyDetails();
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDelete.setBounds(700, 400, 89, 23);
        panel.add(btnDelete);


        //Update Button
        JButton btnUpdate = new JButton("Replace");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                surveyList.get(row).category = category.getItemAt(category.getSelectedIndex()).toString();
                surveyList.get(row).question = question.getItemAt(question.getSelectedIndex()).toString();
                displaySurveyDetails();
            }
        });
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUpdate.setBounds(850, 400, 89, 23);
        panel.add(btnUpdate);


        //Launch Button
        JButton btnLaunch = new JButton("Launch");
        btnLaunch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                if(model.getRowCount()==0){
                    JOptionPane.showMessageDialog(null, "Table is Empty");
                }
                else
                {
                    String category, question;
                    try {
                        String data = "truncate table temporary";
                        p.execute(data);

                        for (int i = 0; i < model.getRowCount(); i++) {
                            category = model.getValueAt(i, 0).toString();
                            question = model.getValueAt(i, 1).toString();
                            String query = "insert into temporary(category, question) values(?,?)";
                            p = c.prepareStatement(query);
                            p.setString(1, category);
                            p.setString(2, question);
                            p.execute();
                        }

                    } catch (SQLException ex)
                    {
                        ex.printStackTrace();
                    }
                }


                //Current Date
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                currentDate = dateFormat.format(now);
                //System.out.println(currentDate);

                //Publish Date
                pdate = publishdateTF.getText();
                Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
                cdate = closeddateTF.getText();
                try
                {
                    if (DATE_PATTERN.matcher(pdate).matches())
                    {
                        if (DATE_PATTERN.matcher(cdate).matches())
                        {
                            System.out.println("valid date");

                            String query = "insert into survey(survey_name,creation_date,publish_date, close_date, category_category_id) values('"+category.getSelectedItem()+"','"+currentDate+"','" + pdate + "', '" + cdate + "', '"+category.getSelectedIndex()+"')";
                            p = c.prepareStatement(query);
                            p.execute(query);
                            JOptionPane.showMessageDialog(frame, "Survey Launch Successfully");
                            model.setRowCount(0);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(frame, "Please Enter Correct Date", "Check date", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (SQLException ex)
                {
                    ex.printStackTrace();
                }
            }

        });
        btnLaunch.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnLaunch.setBounds(1000, 400, 89, 23);
        panel.add(btnLaunch);



        //Table related
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 36, 650, 339);
        panel.add(scrollPane);

        table = new JTable();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                row = table.getSelectedRow();

                category.setName(dtm.getValueAt(row, 0).toString());
                question.setName(dtm.getValueAt(row, 1).toString());
            }
        });
        scrollPane.setViewportView(table);
        frame.setVisible(true);
    }


    //Question JCombobox related
    public void  itemStateChanged(ItemEvent ie)
    {
        if(ie.getSource()==category)
        {
            int cat= category.getSelectedIndex();
            try {
                   /*Class.forName("com.mysql.cj.jdbc.Driver");
                   c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "9822761213");
                   s = c.createStatement();*/
                connection();
                p=c.prepareStatement("select question from questions, category where category_id = '"+cat+"' and category_category_id = '"+cat+"'");
                r = p.executeQuery();
                question.removeAllItems();
                while (r.next()) {

                    String quesname = r.getString("question");
                    //  System.out.println(quesname);
                    question.addItem(quesname);

                }
                // c.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    //Category JCombobox related
    public void category_combo()
    {
        try {
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "9822761213");
            s = c.createStatement();*/
            connection();
            p=c.prepareStatement("select category from category");
            r = p.executeQuery();

            while (r.next()) {

                String catname = r.getString("category");
                System.out.println(catname);
                category.addItem(catname);
            }
            //   c.close();
        } catch (Exception e) {
            // JOptionPane.showMessageDialog(null, "Failed to Connect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.println(e);
        }
    }

}




