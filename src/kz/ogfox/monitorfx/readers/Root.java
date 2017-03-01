package kz.ogfox.monitorfx.readers;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Root {
    private static OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static String root = null;

    public static String getRoot() {
        root = System.getenv("SystemRoot");
        return root;
    }

}
