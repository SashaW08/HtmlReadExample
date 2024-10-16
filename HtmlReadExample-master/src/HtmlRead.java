import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class HtmlRead {

    public static void main(String[] args) {
        HtmlRead html = new HtmlRead();
    }

    public HtmlRead() {
        try {
            System.out.println();
            System.out.print("hello \n");
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