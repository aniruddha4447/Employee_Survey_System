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

public class MainClass extends JFrame implements ItemListener {
    private JFrame frame;
    private JPanel panel;
    private JTable table;
    private JComboBox category, question, maneger;
    private JButton btnAdd, addCatButton, addQueButton;
    private JLabel lpublishdate, lcloseddate, lmaneger;
    private JTextField publishdateTF, closeddateTF;
    private String pdate, cdate, currentDate, sql, sql1,mannm;
    private int cat, que, id;

    private JScrollPane scrollPane;
    private Connection c;
    private Statement s;
    private PreparedStatement p;
    private ResultSet r;
    public int fetchingCatId;
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
        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/survey_mgmt", "Aress", "Aress@aress123");
        System.out.println("Connected to Database");
    }

    public void initialize() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setBounds(200, 120, 1570, 840);
        panel.setLocation(0, 0);
        frame = new JFrame("Survey ");
        frame.setBackground(new Color(250, 250, 250));
        frame.setBounds(200, 120, 1570, 840);
        frame.setLocation(0, 0);
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
        addCatButton.addActionListener(new ActionListener() {
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
        addQueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    new AddQue();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        panel.add(addQueButton);

        //Maneger Label
        JLabel lmaneger = new JLabel("Maneger");
        lmaneger.setForeground(new Color(0, 0, 128));
        lmaneger.setFont(new Font("Tahoma", Font.BOLD, 13));
        lmaneger.setBounds(20, 230, 100, 26);
        panel.add(lmaneger);

        //Maneger JCombobox
        String manegers[] = {};
        maneger = new JComboBox(manegers);
        maneger.setBounds(100, 230, 230, 20);
        maneger_combo();
        panel.add(maneger);



        //Publish Date Label
        lpublishdate = new JLabel("Publish Date");
        lpublishdate.setForeground(new Color(0, 0, 128));
        lpublishdate.setFont(new Font("Tahoma", Font.BOLD, 13));
        lpublishdate.setBounds(20, 290, 150, 26);
        panel.add(lpublishdate);

        //Publish Date TextField
        publishdateTF = new JTextField();
        publishdateTF.setBounds(130, 290, 150, 30);
        panel.add(publishdateTF);

        //Closed Date Label
        lcloseddate = new JLabel("Closed Date");
        lcloseddate.setForeground(new Color(0, 0, 128));
        lcloseddate.setFont(new Font("Tahoma", Font.BOLD, 13));
        lcloseddate.setBounds(20, 350, 150, 26);
        panel.add(lcloseddate);

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
                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Table is Empty");
                }



                //que = question.getSelectedIndex() + 1;
               // cat = category.getSelectedIndex() + 1;



                //Current Date
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();
                currentDate = dateFormat.format(now);
                //System.out.println(currentDate);

                //Publish Date
                pdate = publishdateTF.getText();
                Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
                cdate = closeddateTF.getText();
                try {
                    if (DATE_PATTERN.matcher(pdate).matches()) {
                        if (DATE_PATTERN.matcher(cdate).matches()) {
                            if (pdate.compareTo(cdate) > 0) {
                                JOptionPane.showMessageDialog(frame, " Enter valid Date", "Check date", JOptionPane.ERROR_MESSAGE);
                            }
                            //  System.out.println("valid date");
                            else {
                                String query = "insert into survey_responses(question_id,cat_id,creation_date,publish_date, close_date) values('" + que + "','" + cat + "','" + currentDate + "','" + pdate + "', '" + cdate + "')";
                                p = c.prepareStatement(query);
                                p.execute(query);
                                JOptionPane.showMessageDialog(frame, "Survey Launch Successfully");
                                model.setRowCount(0);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Please Enter Correct Date", "Check date", JOptionPane.ERROR_MESSAGE);
                    }




                } catch (SQLException ex) {
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
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getSource() == category) {
            String que = (String) category.getSelectedItem();
            try {
                connection();
                p = c.prepareStatement("select questions.question,categories.category_name from questions,category_question,categories where questions.question_id=category_question.question_id and categories.category_id=category_question.category_id having (categories.category_name)='" + que + "'");
                r = p.executeQuery();
                question.removeAllItems();
                while (r.next()) {
                    String quesname = r.getString("question");
                    question.addItem(quesname);
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }


    //Category JCombobox related
    public void category_combo() {
        try {
            connection();
            p = c.prepareStatement("select category_id,category_name from categories");
            r = p.executeQuery();

            while (r.next()) {
                fetchingCatId = r.getInt("category_id");
                String catname = r.getString("category_name");
                category.addItem(catname);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void maneger_combo()
    {
        try {
                    connection();
                    String sql = "Select username from users where role = 'manager'";
                    p = c.prepareStatement(sql);
                    r = p.executeQuery();
                    while (r.next()) {
                        String nm = r.getString("username");
                        maneger.addItem(nm);
                        //System.out.println(nm);
                    }
            insert_id_maneger_combo();

        } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
        }
    }


    public void insert_id_maneger_combo()
    {
        maneger.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event) {
              if (event.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        mannm= (String) maneger.getSelectedItem();
                        System.out.println(mannm);
                    sql = "select user_id from users where username = '" + mannm + "'";
                    p = c.prepareStatement(sql);
                    r = p.executeQuery();
                    while (r.next()) {
                        id = r.getInt("user_id");
                        maneger.addItem(id);
                        System.out.println(id);
                        //System.out.println(nm);
                    }
                    sql1 = "insert into survey_responses (manager_id) values ('"+id+"')";
                    p = c.prepareStatement(sql1);
                    p.execute();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
              }

            }
        });
    }

}









