package kz.ogfox.monitorfx.readers;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pala4 on 13.02.2017.
 */
public class Ping {
    private String line="";
    int ms;


    public int getPing() {
        try {
            Pattern p = Pattern.compile("Среднее = ..");
            Process process = Runtime.getRuntime().exec("ping google.com");
            BufferedReader bf = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((line = bf.readLine())!= null) {
                Matcher m = p.matcher(line);
                if(m.find()) {
                    ms = Integer.parseInt(m.group().substring(10));
                }
            }
        } catch (Exception ex) {

        }
        return ms;
    }


}
