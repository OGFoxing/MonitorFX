package kz.ogfox.monitorfx.readers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

import java.io.File;
import java.util.HashMap;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Hdd {
    public static ObservableList<PieChart.Data> getRootSpace() {
        double free;
        double usage;

        File root = new File("c:");
        free = root.getFreeSpace() / 1024.0 / 1024.0 / 1024.0;
        usage = (root.getTotalSpace() - root.getFreeSpace()) / 1024.0 / 1024.0 / 1024.0;
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Free",free),
                new PieChart.Data("Usage",usage)
        );

        return list;
    }
}