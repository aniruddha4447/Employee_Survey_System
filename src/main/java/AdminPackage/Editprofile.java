package AdminPackage;


import com.util.UtilityFunctions;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Editprofile extends JFrame implements ActionListener {

    String userstatus;

    JPasswordField p1, p2;
    JPanel panel1;
    JLabel title, IDLabel, NameLabel, PhnoLabel, RoleLabel, gender, AddressLabel, Password, Confirmpass, EmailLabel;
    JTextArea Add;
    JTextField ID, Namet, Phno,Email;
    JRadioButton male, female;
    JComboBox Rolecb;
    JFrame f;
    JPanel panel;
    private JButton  deleteb,reset, update, cancel,showdetails;

    String Username, Addr, Pno, Rolval, Gval, pwd, email, Cpwd;
    String outputString,inputString,em,nm,pass,phno,rnm,gen,addr,passw;


    PreparedStatement p;
    Connection conn;

    String reg = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // boolean result = email.matches(reg);
    String REG = "^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$";
    final Pattern PATTERN = Pattern.compile(REG);

   /* public void connection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/survey_portfolio", "Aress", "Aress@aress123");
        System.out.println("Connected to Database");
    }*/

    public Editprofile() {
        f = new JFrame("Edit Profile");
        panel = new JPanel();
        panel.setBounds(40, 40, 800, 800);
        panel.setBackground(Color.lightGray);
        panel.setLayout(null);

        title = new JLabel("Edit Profile:");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setBounds(100, 30, 400, 30);

        EmailLabel  = new JLabel("Email:");
        EmailLabel .setBounds(80, 70, 200, 30);
        Email = new JTextField();
        Email.setBounds(300, 70, 200, 30);


        RoleLabel = new JLabel("Role:");
        RoleLabel.setBounds(80, 110, 200, 30);
        String user[] = {"Admin", "Manager", "Developer"};
        Rolecb = new JComboBox(user);
        Rolecb.setBounds(300, 110, 200, 30);

        PhnoLabel = new JLabel("Phone No:");
        PhnoLabel.setBounds(80, 150, 200, 30);
        Phno = new JTextField();
        Phno.setBounds(300, 150, 200, 30);

        gender = new JLabel("Gender:");
        gender.setBounds(80, 190, 200, 30);

        male = new JRadioButton("Male");
        male.setBounds(300, 190, 80, 20);
        male.setBackground(Color.lightGray);
        female = new JRadioButton("Female");
        female.setBounds(400, 190, 80, 20);
        female.setBackground(Color.lightGray);

        AddressLabel = new JLabel("Address:");
        AddressLabel.setBounds(80, 230, 200, 30);
        Add = new JTextArea();
        Add.setBounds(300, 230, 200, 50);

       NameLabel= new JLabel("Name:");
        NameLabel.setBounds(80, 300, 200, 30);
        Namet= new JTextField();
        Namet.setBounds(300, 300, 200, 30);

        Password = new JLabel("Password:");
        Password.setBounds(80, 340, 200, 30);
        p1 = new JPasswordField();
        p1.setBounds(300, 340, 200, 30);

        Confirmpass = new JLabel("Confirm Password:");
        Confirmpass.setBounds(80, 390, 200, 30);
        p2 = new JPasswordField();
        p2.setBounds(300, 390, 200, 30);


        //showdetails= new JButton("Show details");
        //showdetails.setBounds(680, 600, 100, 30);
        update= new JButton("Update");
        update.setBounds(80, 600, 100, 30);
        deleteb = new JButton("Delete");
        deleteb.setBounds(230, 600, 100, 30);
        reset = new JButton("Reset");
        reset.setBounds(380, 600, 100, 30);
        cancel = new JButton("Cancel");
        cancel.setBounds(530, 600, 100, 30);
        // cancel = new JButton("Cancle");
        //cancel.setBounds(550, 600, 100, 30);

        panel.add(title);
        panel.add(NameLabel);
        panel.add(Namet);
        panel.add(RoleLabel);
        panel.add(Rolecb);
        panel.add(PhnoLabel);
        panel.add(Phno);
        panel.add(gender);
        panel.add(male);
        panel.add(female);
        panel.add(AddressLabel);
        panel.add(Add);
        panel.add(EmailLabel);
        panel.add(Email);

        panel.add(Password);
        panel.add(p1);
        panel.add(Confirmpass);
        panel.add(p2);
        // panel.add(sub);
        //sub.addActionListener(this);


       // panel.add(showdetails);
       // showdetails.addActionListener(this);
        Email.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                showdetails();
            }
            @Override
            public void keyReleased(KeyEvent e) {  }
            @Override
            public void keyTyped(KeyEvent e) {}

        });
        panel.add(update);
        update.addActionListener(this);
        panel.add(deleteb);
        deleteb.addActionListener(this);
        panel.add(reset);
        reset.addActionListener(this);
        panel.add(cancel);
        cancel.addActionListener(this);

        f.add(panel);
        f.setSize(900, 900);
        f.setLayout(null);
        f.setVisible(true);
       // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void actionPerformed(ActionEvent event) {

        if(event.getSource()==update)
        {
            updatemethod();
        }
         else if (event.getSource() == deleteb) {
           deletemethod();
        }
        /*else if(event.getSource()==showdetails)
        {
           showdetails();
        }*/
        else if (event.getSource()==cancel)
        {
            cancelmethod();
        }
        else if(event.getSource()==reset)
        {
            Rolecb.setSelectedIndex(0);
            male.setSelected(false);
            female.setSelected(false);
            Email.setText("");
            Namet.setText("");
            Add.setText("");
            Phno.setText("");
            p1.setText("");
            p2.setText("");
        }



    }


    public void cancelmethod() {
        int res = JOptionPane.showConfirmDialog(null,
                "Do you want to Exit?", "Select an Option...", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        else
        {
            JOptionPane.showMessageDialog(f, "Please enter the correct details",
                    "Swing Tester", JOptionPane.ERROR_MESSAGE);
        }

    }
   /* public void encryptdecryptpwd(String str) {
        // Define XOR key
        // Any character value will work
        char xorKey = 'P';

        // Define String to store encrypted/decrypted String
        outputString = "";
        //inputString = pwd;
        inputString=str;
        System.out.println("instr is:"+inputString);
        // calculate length of input string
        int len = inputString.length();

        // perform XOR operation of key
        // with every character in string
        for (int i = 0; i < len; i++) {
            outputString = outputString + Character.toString((char) (inputString.charAt(i) ^ xorKey));
        }

        System.out.println(outputString);

    }*/

    public void getValuefromGui() {

        Username = Namet.getText();
        Pno = Phno.getText();
        Addr = Add.getText();
        email = Email.getText();
        System.out.println(email);

        if (male.isSelected())
            Gval = "Male";
        else if (female.isSelected())
            Gval = "Female";

        Rolval = Rolecb.getSelectedItem().toString();

        pwd = String.valueOf(p1.getPassword());
        Cpwd = String.valueOf(p2.getPassword());

    }
    public void setvaluestogui()
    {
        Namet.setText(nm);;
        Phno.setText(phno);
        Add.setText(addr);
        Email.setText(em);
        Rolecb.setSelectedItem(rnm);
        if (gen.equals("Female")){
            male.setSelected(false);
            female.setSelected(true);
        } else {
            female.setSelected(false);
            male.setSelected(true);
        }
         passw=String.valueOf(pass);
        outputString=UtilityFunctions.encryptDecrypt(passw);
        p1.setText(outputString);

    }

    public void showdetails() {
       getValuefromGui();

        try {
            Connection connection= UtilityFunctions.createConnection();
            p = connection.prepareStatement("select user_email from users where user_email='" + email+ "'    ");
            ResultSet rs = p.executeQuery();

            while (rs.next()) {
                em = rs.getString("user_email");

                System.out.println(em);

            }
            if(email.equals(em))
            {

                p = connection.prepareStatement("select * from users where user_email='" + email + "'    ");
                ResultSet rs1 = p.executeQuery();

                while (rs1.next()) {
                   nm=rs1.getString("user_name");
                    em = rs1.getString("user_email");
                    pass = rs1.getString("password");
                    phno = rs1.getString("phone_no");
                    rnm = rs1.getString("role_name");
                    gen=rs1.getString("gender");
                    addr=rs1.getString("address");

                  setvaluestogui();

                    /* System.out.println(nm + "\t\t" + em
                            + "\t\t"+"   " + pass + "\t\t " + phno+"\t\t"+"  "+rnm+"\t\t"+"  "+gen+"\t\t"+"  "+addr);*/

                }

            }


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updatemethod()
    {
        getValuefromGui();
        System.out.println("pwd is:"+pwd);
        if (PATTERN.matcher(pwd).matches()) {
            System.out.print("The Password " + pwd + " is valid" + "\n");

            if (pwd.equals(Cpwd)) {

                if (email.matches(reg)) {
                    System.out.println("Given email-id is valid");

                    if (Pno.matches("\\d{10}")) {
                        System.out.println("Valid Mobile NO");


                        try {
                            if(Rolval.equals("Manager"))
                            {
                                userstatus="null";
                            }
                            else if(Rolval.equals("Admin"))
                            {
                                userstatus="null";
                            }
                            else
                            {
                                userstatus="Pending";
                            }
                            Connection connection= UtilityFunctions.createConnection();
                            outputString=UtilityFunctions.encryptDecrypt(pwd);
                            //encryptdecryptpwd(pwd);
                            String up = "update users set user_name='" + Namet.getText() + "',user_email='" + Email.getText() + "' ,password='" + outputString + "',phone_no='" + Phno.getText() + "',role_name='" + Rolecb.getSelectedItem().toString() + "',gender='" + Gval + "',address='" + Add.getText() + "',status='"+userstatus+"' where user_Email='" +  Email.getText() + "' ";
                            p = connection.prepareStatement(up);
                            p.execute();
                            JOptionPane.showMessageDialog(f,
                                    "User Details Updated Successfully");
                            System.out.println("Record updated Successfully ");


                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please Enter Correct Mobile no",
                                "Check Password", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Please Enter Correct Email ID",
                            "Check Password", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                System.out.println("password not matched");
                JOptionPane.showMessageDialog(f, "Password not match to Confirm Password",
                        "Confirm Password", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            JOptionPane.showMessageDialog(f, "Please enter correct Password",
                    "Check Password", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void deletemethod() {
        showdetails();

        getValuefromGui();

        if (PATTERN.matcher(pwd).matches()) {
            System.out.print("The Password " + pwd + " is valid" + "\n");

            if (pwd.equals(Cpwd)) {

                if (email.matches(reg)) {
                    System.out.println("Given email-id is valid");

                    if (Pno.matches("\\d{10}")) {
                        System.out.println("Valid Mobile NO");


                        try {
                            Connection connection= UtilityFunctions.createConnection();
                            String del = "delete from users where user_Email='" +Email.getText() + "' ";

                            p =connection.prepareStatement(del);
                            p.execute();
                            JOptionPane.showMessageDialog(f,
                                    "User Details Deleted Successfully");
                            System.out.println("Record Deleted Successfully ");


                        } catch (SQLException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "Please Enter Correct Mobile no",
                                "Check Password", JOptionPane.ERROR_MESSAGE);
                    }

                } else {
                    JOptionPane.showMessageDialog(f, "Please Enter Correct Email ID",
                            "Check Password", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                System.out.println("password not matched");
                JOptionPane.showMessageDialog(f, "Password not match to Confirm Password",
                        "Confirm Password", JOptionPane.ERROR_MESSAGE);
            }


        } else {
            JOptionPane.showMessageDialog(f, "Please enter correct Password",
                    "Check Password", JOptionPane.ERROR_MESSAGE);
        }

    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Editprofile e=new Editprofile();
       // e.connection();

    }



}
