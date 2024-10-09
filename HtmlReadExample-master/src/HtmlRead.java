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
                if((line.contains("http") && line.contains("//"))) {
                    int indexHTTP = line.indexOf("http");
                    String newLine = line.substring(indexHTTP);

                    System.out.println(newLine);

//                    if(line.indexOf("http")==-1){
//                        newLine = line.substring(indexWWW);
//                    }

                }
                if(line.contains("www")) {
                    int indexWWW = line.indexOf("www");
                    String newnewLine = line.substring(indexWWW);

                    System.out.println(newnewLine);
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}