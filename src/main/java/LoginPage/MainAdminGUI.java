package LoginPage;

import javax.swing.*;
import java.awt.*;

public class MainAdminGUI extends JFrame {

    JMenuBar menuBar;
    JMenu menu1,menu2,menu3,menu4;
    JMenu submenu1,submenu2;
    JMenuItem m1,m2,m3,m4;

    public MainAdminGUI()
    {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400,400);

        setTitle("MenuBarDemo");
        setLayout(new BorderLayout());
        menuBar=new JMenuBar();
        setJMenuBar(menuBar);
        menu1=new JMenu("Menu 1");
        menu2=new JMenu("Menu 2");
        menu3=new JMenu("Menu 3");
        menu4=new JMenu("Menu 4");
        submenu1=new JMenu("submenu1");
        submenu2=new JMenu("submenu2");
        m1=new JMenuItem("m1");
        m2=new JMenuItem("m2");
        m3=new JMenuItem("m3");
        m4=new JMenuItem("m4");

        menuBar.add(menu1);
        menu1.add(submenu1);
        menu1.add(submenu2);
        submenu1.add(m1);
        submenu1.add(m2);
        submenu2.add(m3);
        submenu2.add(m4);
        menuBar.add(menu2);
        menuBar.add(menu3);
        menuBar.add(menu4);

        setVisible(true);

    }
}
