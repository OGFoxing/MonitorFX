package kz.ogfox.monitorfx.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kz.ogfox.monitorfx.readers.Info;
import kz.ogfox.monitorfx.readers.Ping;
import kz.ogfox.monitorfx.readers.Ram;
import kz.ogfox.monitorfx.readers.ShowNotifications;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private static final String titlePing= "Ping test";
    private static final String titleRam = "Ram test";
    private static final String titleNet = "Network information";

    private static final String startTextPing = "ping test with google.com launched";
    private static final String stopTextPing = "ping test stop";
    private static final String updateTextPing = "data from test ping update";
    private static final String updateTextRam = "data from ram test update";
    private static final String textNet = "network test start";


    @FXML
    LineChart<Number,Number> lineChart;
    private XYChart.Series freeMemory;
    private XYChart.Series usageMemory;
    private XYChart.Series totalMemory;

    @FXML
    LineChart<Number,Number> pingChart;
    private XYChart.Series pingValue;
    @FXML
    TextArea textArea;


    Thread ping = new Thread(new iPing());
    @FXML
    private void testPing(ActionEvent event) {
        switch (((MenuItem)event.getSource()).getId()) {
            case "startTest":
                ping.start();
                new ShowNotifications().showNotifications(titlePing,startTextPing);
                break;
            case "stopTest":
                ping.stop();
                new ShowNotifications().showNotifications(titlePing,stopTextPing);
                break;
            case "clearData":
                pingChart.getData().clear();
                new iPing().initPingChart();
                new ShowNotifications().showNotifications(titlePing,updateTextPing);
                break;
        }
    }

    @FXML
    private void testRam(ActionEvent event) {
        switch (((MenuItem)event.getSource()).getId()) {
            case "updateRam" :
                lineChart.getData().clear();
                iCharts iChar = new iCharts();
                iChar.initCharts();
                iChar.time = 0;
                new ShowNotifications().showNotifications(titleRam,updateTextRam);
                break;
        }
    }
    @FXML
    private void about() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/About.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:icon/icon.png"));
            stage.setTitle("Saber FX About");
            stage.setResizable(false);


            stage.show();
        } catch (Exception ex) {

        }
    }

    @FXML
    private void showData() {
        try {
            new ShowNotifications().showNotifications(titleNet,textNet);
            Parent parent = FXMLLoader.load(getClass().getResource("../fxml/Data.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.getIcons().add(new Image("file:icon/icon.png"));
            stage.setTitle("Saber FX Networking");
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new iPing().initPingChart();
        new iCharts().initCharts();

        Thread info = new Thread(new Info((TextArea) textArea));
        info.start();

        Thread charts = new Thread(new iCharts());
        charts.start();
    }

    private class iCharts implements Runnable{
        double freeRam;
        double usedRam;
        double totalRam;
        int time;
        Ram ram = new Ram();

            public void initCharts() {
                freeMemory = new XYChart.Series();
                freeMemory.setName("RAM free");
                usageMemory = new XYChart.Series();
                usageMemory.setName("RAM usage");
                totalMemory = new XYChart.Series();
                totalMemory.setName("RAM total");

                lineChart.setTitle("Ram monitor");
                lineChart.getData().addAll(freeMemory,usageMemory,totalMemory);
            }

            public void getData() {
                freeRam = ram.getRam().get(0);
                usedRam = ram.getRam().get(2);
                totalRam = ram.getRam().get(1);
            }

            public void addDataToMemoryChart() {
                int counter = 0;
                try {
                    while (true) {
                        time+=2;
                        counter++;
                        getData();
                        if(counter < 20) {
                            Platform.runLater(() -> {
                                freeMemory.getData().add(new XYChart.Data(time + " ", freeRam));
                                usageMemory.getData().add(new XYChart.Data(time + " ", usedRam));
                                totalMemory.getData().add(new XYChart.Data(time + " ", totalRam));
                            });
                            Thread.sleep(2000);
                        } else {
                            Platform.runLater(() -> {
                                lineChart.getData().clear();
                                new iCharts().initCharts();
                            });
                            counter = 0;
                        }
                    }
                }
                catch (Exception ex) {
                }
            }

            @Override
            public void run() {
                addDataToMemoryChart();
            }
        }

    private class iPing implements Runnable{
        int pack;
        int ms;

        public void initPingChart() {
            pingValue = new XYChart.Series();
            pingValue.setName("Ping to Google");
            pingChart.setTitle("Ping monitor");
            pingChart.getData().addAll(pingValue);
        }
        public void addDataToPingChart() {
            try {
                while (true) {
                    pack++;
                    ms = new Ping().getPing();
                    /*er th*/
                    Platform.runLater(() -> {
                        pingValue.getData().add(new XYChart.Data(pack + " ",ms));
                    });
                    Thread.sleep(1000);
                }
            } catch (Exception ex) {

            }
        }

        @Override
        public void run() {
            addDataToPingChart();
        }
    }
    }


