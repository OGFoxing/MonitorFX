package kz.ogfox.monitorfx.readers;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Info implements Runnable{
    protected String line = "";
    protected TextArea textArea;
    final protected String CHARSET = "IBM866";

    public Info(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void run() {
        getSystemInfo();
    }



    public synchronized void getSystemInfo() {
        try {
            Process proc = Runtime.getRuntime().exec("systeminfo");
            BufferedReader bf = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while((line = bf.readLine()) != null) {
                line = new String(line.getBytes(),CHARSET);
                Platform.runLater(() -> {
                    this.textArea.appendText(line + "\n");
                });
                Thread.sleep(10);
            }
        }
        catch (Exception ex) {

        }
    }

}
