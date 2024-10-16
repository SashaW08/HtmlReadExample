import javax.swing.*;
import java.awt.*;
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
    private JPanel controlPanel;
    private JPanel panelpanel;
    private int WIDTH=1000;
    private int HEIGHT=500;
    private JTextArea ta;
    private JTextArea tb;


    private void prepareGUI() {
        mainFrame = new JFrame("Sasha Learning SWING");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        panelpanel = new JPanel();
        controlPanel.setLayout(new GridLayout(1,2));
        panelpanel.setLayout(new GridLayout(1,2));
        mainFrame.setVisible(true);
    }
    private void showEventDemo() {
        JButton button1 = new JButton("Search!");
        JLabel label1 = new JLabel ("Input URL here", JLabel.CENTER);
        JLabel label2 = new JLabel ("Input search word here", JLabel.CENTER);
        button1.setActionCommand("Button 1");
        mainFrame.add(button1, BorderLayout.NORTH);
        controlPanel.add(label1);
        controlPanel.add(label2);
        ta = new JTextArea();
        tb = new JTextArea();
        panelpanel.add(ta);
        panelpanel.add(tb);
        mainFrame.add(controlPanel, BorderLayout.SOUTH);
        mainFrame.add(panelpanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    public HtmlProject() {
        prepareGUI();
        try {
            URL url = new URL("https://www.milton.edu/");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {
                if((line.contains("href"))) {
                    int indexHREF = line.indexOf("href")+6;
                    String newLine = line.substring(indexHREF);
                    int end = newLine.indexOf("\"");
                    int oend = newLine.indexOf("'");

                    if(end>-1 && oend>-1 && end<oend){
                        System.out.println(newLine.substring(0, end));
                    }
                    if(end>-1 && oend>-1 && end>oend) {
                        System.out.println(newLine.substring(0, oend));
                    }
                    if(end>-1 && oend==-1){
                        System.out.println(newLine.substring(0, end));
                    }
                    if(oend>-1 && end==-1){
                        System.out.println(newLine.substring(0,oend));
                    }
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }


}
