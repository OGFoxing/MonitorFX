package kz.ogfox.monitorfx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import kz.ogfox.monitorfx.readers.Hdd;
import kz.ogfox.monitorfx.readers.Monitor;
import kz.ogfox.monitorfx.readers.Netsat;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pala4 on 13.02.2017.
 */
public class DataController  implements Initializable {
    @FXML
    PieChart pieChart;
    private XYChart.Series freeHdd;
    private XYChart.Series usageHdd;
    @FXML
    private Label osLabel;
    @FXML
    private Label coresLabel;
    @FXML
    private Label freeLabel;
    @FXML
    private Label usageLabel;
    @FXML
    private Label rootLabel;
    @FXML
    TextArea textArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCharts();
        dataToLabels();
        Thread th = new Thread(new Netsat(textArea));
        th.start();
    }



    private void initCharts() {
        freeHdd = new XYChart.Series();
        freeHdd.setName("Free");
        usageHdd = new XYChart.Series();
        usageHdd.setName("Usage");
        pieChart.setTitle("HDD");
        pieChart.getData().addAll(Hdd.getRootSpace());
    }

    private void dataToLabels() {
        osLabel.setText(Monitor.osLabel);
        coresLabel.setText(Monitor.coresLabel);
        freeLabel.setText(String.format("%.7s",Monitor.freeSpace) + " GB");
        usageLabel.setText(String.format("%.7s",Monitor.usageSpace) + " GB");
        rootLabel.setText(Monitor.root);

    }

}
