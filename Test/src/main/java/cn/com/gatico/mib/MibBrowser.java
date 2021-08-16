package cn.com.gatico.mib;

import com.alibaba.fastjson.JSONObject;
import com.ireasoning.util.gb;
import com.ireasoning.util.hb;
import com.ireasoning.util.ld;

import java.util.List;


public class MibBrowser {
    static String sevenMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\7X-networks-MIB.my";
    static String ciscoMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\CISCO-MIB";
    static String otherMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\test.mib";
    static String rfcMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\RFC1289.MIB";
    static String oracleMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\ORADB-MIB";

    public static void main(String[] args) throws Exception {
        ld ld1 = ld.loadMib(ciscoMibPath);
        hb root = ld1.getRoot();
        MibRoot mibRoot = new MibRoot();
        mibRoot.setName(root.getName().toString());
        getMibTree(root, mibRoot.getChildren());
        String res = JSONObject.toJSON(mibRoot).toString();
        System.out.println(res);
    }

    public static void getMibTree(gb gb1, List<MibTree> mibTrees) {
        if (gb1.getChildNodes() != null) {
            gb1.getChildNodes().forEach(o -> {
                hb hb2 = (hb) o;
                MibTree mibTree = new MibTree();
                mibTree.setName(hb2.getName().toString());
                mibTree.setObjectId(hb2.getFullName());
                mibTree.setSyntax(hb2.getSyntax() != null ? hb2.getSyntax().toString() : "");
                mibTree.setStatus(hb2.getStatus());
                mibTree.setAccess(hb2.getAccess());
                if (hb2.getIndice() != null) {
                    mibTree.setIndex(String.join(",", hb2.getIndice()));
                }
                mibTree.setOid(hb2.getOID().toString());
                mibTree.setDescription(hb2.getDescription());
                mibTrees.add(mibTree);
                System.out.println(o.toString());
                getMibTree((gb) o, mibTree.getChildren());
            });
        }
    }
}
