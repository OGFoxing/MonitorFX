package kz.ogfox.monitorfx.readers;

import com.sun.management.OperatingSystemMXBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.management.ManagementFactory;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Ram {
    private OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private ObservableList<Double> list = FXCollections.observableArrayList();

    public ObservableList<Double> getRam() {
        double free = 0;
        double usage = 0;
        double total = 0;

        free = os.getFreePhysicalMemorySize() /1024.0 / 1024.0 /1024.0;
        total = os.getTotalPhysicalMemorySize() / 1024.0 /1024.0 / 1024.0;
        usage = (os.getTotalPhysicalMemorySize() - os.getFreePhysicalMemorySize()) /1024.0 / 1024.0 /1024.0;
        list.clear();

        if(list.isEmpty()) {
            list.addAll(free, total, usage);
        } else {
            list.clear();
            list.addAll(free, total, usage);
        }
        return list;
    }

}

