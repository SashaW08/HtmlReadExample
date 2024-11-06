import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlProject {

    public static void main(String[] args) {
        HtmlProject html = new HtmlProject();
        html.showEventDemo();
    }


    private JFrame mainFrame;
    private JPanel newpanel;
    private JPanel controlPanel;
    private JPanel panelpanel;
    private JPanel panel2;
    private int WIDTH=1000;
    private int HEIGHT=700;
    private JTextArea ta;
    private JTextArea tb;
    private JTextArea tc;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private JScrollPane scroll3;
    private int linkcheck = 0;
    private Font font = new Font("Times New Roman", Font.BOLD, 15);
    private Color lightpurple = new Color (215, 197, 245);
    private Color lightyellow = new Color (255,255,186);


    private void prepareGUI() {
        mainFrame = new JFrame("Link Key Word Finder");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        controlPanel = new JPanel();
        panelpanel = new JPanel();
        newpanel = new JPanel();
        panel2 = new JPanel();
        controlPanel.setLayout(new GridLayout(1,2));
        panelpanel.setLayout(new GridLayout(1,2));
        newpanel.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        JButton button1 = new JButton("Search!");
        JButton button2 = new JButton("Reset");
        JLabel label1 = new JLabel ("Input URL here", JLabel.CENTER);
        JLabel label2 = new JLabel ("Input search word here", JLabel.CENTER);
        JLabel label3 = new JLabel ("Outputted links:", JLabel.CENTER);

        button1.setActionCommand("Search!");
        button1.addActionListener(new ButtonClickListener());
        button1.setFont(font);
        button1.setBackground(lightpurple);
        button2.setActionCommand("Reset");
        button2.addActionListener(new ButtonClickListener());
        button2.setFont(font);
        button2.setBackground(lightpurple);

        label1.setFont(font);
        label1.setOpaque(true);
        label1.setBackground(lightyellow);
        label2.setFont(font);
        label2.setOpaque(true);
        label2.setBackground(lightyellow);
        label3.setFont(font);
        label3.setOpaque(true);
        label3.setBackground(lightyellow);

        newpanel.add(button1, BorderLayout.SOUTH);
        controlPanel.add(label1);
        controlPanel.add(label2);

        ta = new JTextArea();
        tb = new JTextArea();
        tc = new JTextArea();

        scroll1 = new JScrollPane(tc);
        scroll2 = new JScrollPane(tb);
        scroll3 = new JScrollPane(ta);

        panelpanel.add(scroll3);
        panelpanel.add(scroll2);

        newpanel.add(controlPanel, BorderLayout.NORTH);
        newpanel.add(panelpanel, BorderLayout.CENTER);

        panel2.add(scroll1);
        panel2.add(label3, BorderLayout.NORTH);
        panel2.add(button2, BorderLayout.SOUTH);

        mainFrame.add(newpanel);
        mainFrame.add(panel2);
        mainFrame.setVisible(true);
    }

    public HtmlProject() {
        prepareGUI();
    }

    public void method(){
        try {
            URL url = new URL(ta.getText());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                while((line.contains("href"))) {
                    String tbstring = tb.getText();
                    int indexHREF = line.indexOf("href")+6;
                    String newLine = line.substring(indexHREF);
                    int end = newLine.indexOf("\"");
                    int oend = newLine.indexOf("'");
                    int Indextbstring = newLine.indexOf(tbstring);

                    if(end>-1 && oend>-1 && end<oend && Indextbstring>-1){
                            tc.append(newLine.substring(0, end)+"\n");
                            linkcheck++;
                    }
                    if(end>-1 && oend>-1 && end>oend && Indextbstring>-1) {
                            tc.append(newLine.substring(0, oend)+"\n");
                            linkcheck++;
                    }
                    if(end>-1 && oend==-1 && Indextbstring>-1 ){
                            tc.append(newLine.substring(0, end)+"\n");
                            linkcheck++;
                    }
                    if(oend>-1 && end==-1 && Indextbstring>-1){
                            tc.append(newLine.substring(0, oend)+"\n");
                            linkcheck++;
                    }
                    line = line.substring(indexHREF);
                }
            }

            if(linkcheck == 0){
                tc.append("No links with search word found");
            }

            linkcheck = 0;

            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Search!")) {
                tc.setText("");
                method();
            }
            if(command.equals("Reset")){
                ta.setText("");
                tb.setText("");
                tc.setText("");
            }

        }
    }

}
