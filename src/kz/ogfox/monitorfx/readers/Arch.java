package kz.ogfox.monitorfx.readers;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.util.HashMap;

/**
 * Created by pala4 on 28.01.2017.
 */
public class Arch {
    private static OperatingSystemMXBean os = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();

    public static HashMap<String,String> getArch() {
        String name = System.getProperty("os.name");
        String arch = null;
        double totalRam = os.getTotalPhysicalMemorySize() /1024.0 / 1024.0 / 1024.0;

        if(totalRam <= 3200) {
            arch = "x64";
        } else {
            arch = "x32";
        }
        HashMap<String,String> map = new HashMap<>();
        map.put(name,arch);
        return map;
    }

}
