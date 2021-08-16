package cn.com.gatico.mib;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class 解析MIB {
    static String sevenMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\7X-networks-MIB.my";
    static String ciscoMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\CISCO-MIB";
    static String otherMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\test.mib";
    static String rfcMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\RFC1289.MIB";
    static String oracleMibPath = "D:\\Tool\\MIB\\ManageEngine\\MibBrowser Free Tool\\mibs\\ORADB-MIB";
    static String outPath = "D:\\test\\test.json";

    static String splitKeyWords = "::= {";
    static String orgOid = ".1.3";
    static String orgObjectId = ".iso.org";

    static String dodOid = ".1.3.6";
    static String dodObjectId = ".iso.org.dod";

    static String internetOid = ".1.3.6.1";
    static String internetObjectId = ".iso.org.dod.internet";

    static String privateOid = ".1.3.6.1.4";
    static String privateObjectId = ".iso.org.dod.internet.private";

    static String enterprisesOid = ".1.3.6.1.4.1";
    static String enterprisesObjectId = ".iso.org.dod.internet.private.enterprises";

    static MibTree mibTree = new MibTree();
    static MibRoot mibRoot = new MibRoot();
    static String mibInfo = "";


    public static void main(String[] args) throws Exception {
        mibTree.setName("enterprises");
        mibTree.setTitle("enterprises");
        mibTree.setOid(enterprisesOid);
        mibTree.setObjectId(enterprisesObjectId);
        List<String> lines = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(oracleMibPath)));
        StringBuffer cache = new StringBuffer();
        bufferedReader.lines().forEach(line -> {
            line = trimAll(line);
            if (!line.trim().startsWith("--")) {
                if (line.contains("--")) {
                    line = line.substring(0, line.indexOf("--"));
                }
                if (line.endsWith(";")) {
                    cache.append(line);
                    mibInfo = cache.toString().trim();
                    Pattern pattern = Pattern.compile("(.*?)((\\{.*)| )DEFINITIONS");
                    Matcher matcher = pattern.matcher(mibInfo);
                    if (matcher.find()) {
                        mibRoot.setName(matcher.group(1));
                    }
                    cache.setLength(0);
                } else if (line.contains(splitKeyWords)) {
                    cache.append(line);
                    lines.add(cache.toString().trim());
                    cache.setLength(0);
                } else {
                    cache.append(line);
                }
            }
        });

        System.out.println("节点个数：" + lines.size());
        System.out.println("原信息：");
        lines.forEach(System.out::println);

        List<MibNode> mibNodeList = new ArrayList<>();
        lines.forEach(line -> {
            line = trimAll(line);
            String nodeInfo = line.substring(0, line.indexOf(splitKeyWords));
            if (nodeInfo.contains("::= SEQUENCE")) {
                nodeInfo = nodeInfo.substring(nodeInfo.indexOf("} ") + 2);
            }
            if (nodeInfo.contains("MODULE-IDENTITY")) {
                Pattern pattern = Pattern.compile("LAST-UPDATED \"(.*?)\"");
                Matcher matcher = pattern.matcher(nodeInfo);
                if (matcher.find()) {
                    mibRoot.setLastUpdate(matcher.group(1));
                }
                pattern = Pattern.compile("ORGANIZATION \"(.*?)\"");
                matcher = pattern.matcher(nodeInfo);
                if (matcher.find()) {
                    mibRoot.setOrganization(matcher.group(1));
                }
                pattern = Pattern.compile("CONTACT-INFO \"(.*?)\"");
                matcher = pattern.matcher(nodeInfo);
                if (matcher.find()) {
                    mibRoot.setContactInfo(matcher.group(1));
                }
            }
            String[] treeInfo = line.substring(line.indexOf(splitKeyWords)).split(" ");
            MibNode mibNode = new MibNode();
            mibNode.setInfo(nodeInfo);
            mibNode.setParent(treeInfo[2]);
            mibNode.setIndex(Integer.parseInt(treeInfo[3]));
            Pattern pattern = Pattern.compile("([A-Za-z0-9-]+) (OBJECT-TYPE|OBJECT IDENTIFIER)");
            Matcher matcher = pattern.matcher(nodeInfo);
            if (matcher.find()) {
                mibNode.setName(matcher.group(1));
            }
            if (mibNode.getName() == null || mibNode.getName().length() <= 0) {
                mibNode.setName(nodeInfo.substring(0, nodeInfo.indexOf(" ")));
            }
            mibNodeList.add(mibNode);
        });

        getMibTree(mibTree, mibNodeList);
        mibRoot.getChildren().add(mibTree);
        List<MibNode> tmpMibNodeList = mibNodeList.stream().filter(mibNode -> !mibNode.isRemove()).collect(Collectors.toList());
        String tmpRes = JSONObject.toJSON(tmpMibNodeList).toString();
        System.out.println("未解析：");
        System.out.println(tmpRes);

        System.out.println("已解析：");
        String res = JSONObject.toJSON(mibNodeList).toString();
        System.out.println(res);

        System.out.println("MIB库信息：");
        System.out.println(mibInfo);
        System.out.println("MIB库名：" + mibRoot.getName());
        System.out.println("更新时间：" + mibRoot.getLastUpdate());
        System.out.println("组织机构：" + mibRoot.getOrganization());
        System.out.println("联系方式：" + mibRoot.getContactInfo());
        res = JSONObject.toJSON(mibRoot).toString();
        System.out.println("序列化（json）信息：");
        System.out.println(res);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outPath), false));
        bufferedWriter.write(res);
        bufferedReader.close();
    }

    public static String trimAll(String str) {
        str = str.replace("{", " { ").replace("}", " } ").replace("\r", " ").replace("\n", " ").replace("\t", " ");
        while (str.contains("  ")) {
            str = str.replace("  ", " ");
        }
        return str;
    }

    public static void printArray(Object[] arr) {
        printArray(arr, ",");
    }

    public static void printArray(Object[] arr, String join) {
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + join);
            }
        }
        System.out.println();
    }

    public static void getMibTree(MibTree root, List<MibNode> mibNodeList) {
        List<MibNode> tmpMibNodeList = mibNodeList.stream().filter(mibNode -> !mibNode.isRemove()).collect(Collectors.toList());
        for (int i = 0; i < tmpMibNodeList.size(); i++) {
            MibNode mibNode = tmpMibNodeList.get(i);
            if (mibNode.getParent().equals(root.getName())) {
                MibTree mibTree = new MibTree();
                mibTree.setName(mibNode.getName());
                mibTree = getProperty(mibNode.getInfo(), mibTree);
                mibTree.setObjectId(root.getObjectId() + "." + mibNode.getName());
                mibTree.setOid(root.getOid() + "." + mibNode.getIndex());
                root.getChildren().add(mibTree);
                mibNode.setRemove(true);
            }
        }
        if (!root.getChildren().isEmpty()) {
            root.getChildren().forEach(mibTree -> {
                getMibTree(mibTree, tmpMibNodeList);
            });
        }
    }

    public static MibTree getProperty(String info, MibTree mibTree) {
        String keys[] = new String[]{"SYNTAX", "ACCESS", "STATUS", "DESCRIPTION", "INDEX"};
        Pattern pattern;
        Matcher matcher;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i].equals("INDEX")) {
                pattern = Pattern.compile(keys[i] + " \\{ (.*?) }");
            } else if (keys[i].equals("DESCRIPTION")) {
                pattern = Pattern.compile(keys[i] + " \"(.*?)\"");
            } else if (keys[i].equals("SYNTAX")) {
                pattern = Pattern.compile(keys[i] + " (.*?\\) }|.*?) ");
            } else {
                pattern = Pattern.compile(keys[i] + " (.*?) ");
            }
            matcher = pattern.matcher(info.substring(info.indexOf(mibTree.getName())));
            if (matcher.find()) {
                if (keys[i].equals("SYNTAX")) {
                    mibTree.setSyntax(matcher.group(1));
                } else if (keys[i].equals("ACCESS")) {
                    mibTree.setAccess(matcher.group(1));
                } else if (keys[i].equals("STATUS")) {
                    mibTree.setStatus(matcher.group(1));
                } else if (keys[i].equals("DESCRIPTION")) {
                    mibTree.setDescription(matcher.group(1));
                } else if (keys[i].equals("INDEX")) {
                    mibTree.setIndex(matcher.group(1));
                }
            }
        }
        return mibTree;
    }
}
