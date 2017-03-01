package kz.ogfox.monitorfx.readers;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Cores {
    private static OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static int cores;

    public static int getCores() {
        cores = os.getAvailableProcessors();
        return cores;
    }
}
