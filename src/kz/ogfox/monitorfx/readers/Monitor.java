package kz.ogfox.monitorfx.readers;

/**
 * Created by pala4 on 28.01.2017.
 */
public abstract class Monitor {
    public static final String osLabel = Arch.getArch().toString();
    public static final String coresLabel = String.valueOf(Cores.getCores());
    public static final double freeSpace =  Hdd.getRootSpace().get(0).getPieValue();
    public static final double usageSpace = Hdd.getRootSpace().get(1).getPieValue();
    public static final String root = Root.getRoot();
}
