package cn.com.gatico.mib;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.percederberg.mibble.Mib;
import net.percederberg.mibble.MibLoader;
import net.percederberg.mibble.MibLoaderException;
import net.percederberg.mibble.MibValue;
import net.percederberg.mibble.MibValueSymbol;
import net.percederberg.mibble.snmp.SnmpObjectType;


public class MibFileParse {
    @SuppressWarnings({"rawtypes"})
    public List<MmMib> getMib(String filePath) throws IOException, MibLoaderException {
        File file = new File(filePath);
        MibLoader ml = new MibLoader();
        Mib mib = ml.load(file);
        String mibName = mib.getName();
        System.err.println("mibName===" + mibName);
        System.err.println("-------------------------------------");
        String syntax = "";
        String access = "";
        String status = "";
        String description = "";
        List<MmMib> list = new ArrayList<MmMib>();
        Collection c = mib.getAllSymbols();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof MibValueSymbol) {
                MmMib mo = new MmMib();
                MibValueSymbol mvs = (MibValueSymbol) obj;
                SnmpObjectType sot = null;
                if (mvs.getType() instanceof SnmpObjectType) {
                    sot = (SnmpObjectType) mvs.getType();
                }
                if (sot != null) {
                    syntax = sot.getSyntax().getName();
                    access = sot.getAccess().toString();
                    status = sot.getStatus().toString();
                    description = sot.getDescription();
                }
                //是否为表的列
                boolean isTableColumn = mvs.isTableColumn();
                String name = mvs.getName();
                MibValue value = mvs.getValue();
                MibValueSymbol parent = mvs.getParent();
                String parentValue = "";
                System.err.println("name==" + name);
                System.err.println("value==" + value);
                System.err.println("isTableColumn==" + isTableColumn);
                if (parent != null) {
                    parentValue = parent.getValue().toString();
                    if (parent.getParent() == null) {
                        System.err.println("supperParentName======" + mibName);
                        System.err.println("supperParentValue=====" + parentValue);
                    }
                    System.err.println("parentName=" + parent.getName());
                    System.err.println("parentValue=" + parent.getValue());
                } else {
                }
                System.err.println("syntax=" + syntax);
                System.err.println("access=" + access);
                System.err.println("status=" + status);
                System.err.println("status=" + status);
                System.err.println("description=" + description);
                System.err.println("-------------------------------------");
                mo.setName(name);
                mo.setValue(value.toString());
                mo.setParent(parentValue);
                mo.setSyntax(syntax);
                mo.setAccess(access);
                mo.setStatus(status);
                mo.setDescription(description);
                list.add(mo);
            }
        }
        /*
        MibValueSymbol mvs = mib.getSymbolByOid("1.3.6.1.2.1.10");
        System.err.println("mvs.getName()=" + mvs.getName());
        System.err.println("mvs.getValue()=" + mvs.getValue());
        MibValueSymbol parent = mvs.getParent();
        System.err.println("parent=" + parent);  */
        return list;
    }
    static String rfcMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\RFC1289.MIB";
    public static void main(String[] args) throws IOException, MibLoaderException {
        MibFileParse t = new MibFileParse();
        char a = File.separatorChar;
        String abPath = System.getProperty("user.dir");
        String filePath = abPath + a + "src" + a + "main" + a + "webapp" + a + "mib" + a + "ISM-HUAWEI-MIB.mib";
//        filePath = "c:\\Users\\48909\\Desktop\\7X-networks-MIB.my";
        filePath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\CISCO-MIB";
        t.getMib(rfcMibPath);
    }

}
