package cn.com.gatico.json;

import cn.com.gatico.Tree.TreeNode;
import cn.com.gatico.Tree.TreeNodeEntity;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.zson.JsonSerializer;

public class ZsonTest {

    public static void main(String[] args) {
        TreeNodeEntity rootEntity = new TreeNodeEntity();
        rootEntity.setId(0);
        rootEntity.setName("root");

        TreeNodeEntity nodeAEntity = new TreeNodeEntity();
        nodeAEntity.setId(1);
        nodeAEntity.setName("nodeA");
        nodeAEntity.setUpstreamId(0);

        TreeNodeEntity nodeBEntity = new TreeNodeEntity();
        nodeBEntity.setId(2);
        nodeBEntity.setName("nodeB");
        nodeBEntity.setUpstreamId(1);

        TreeNodeEntity nodeCEntity = new TreeNodeEntity();
        nodeCEntity.setId(3);
        nodeCEntity.setName("nodeC");
        nodeCEntity.setUpstreamId(2);

        TreeNodeEntity nodeDEntity = new TreeNodeEntity();
        nodeDEntity.setId(4);
        nodeDEntity.setName("nodeD");
        nodeDEntity.setUpstreamId(3);

        TreeNodeEntity nodeEEntity = new TreeNodeEntity();
        nodeEEntity.setId(5);
        nodeEEntity.setName("nodeE");
        nodeEEntity.setUpstreamId(4);

        TreeNodeEntity nodeFEntity = new TreeNodeEntity();
        nodeFEntity.setId(6);
        nodeFEntity.setName("nodeF");
        nodeFEntity.setUpstreamId(5);

        TreeNode root = new TreeNode(rootEntity);
        TreeNode nodeA = new TreeNode(nodeAEntity);
        TreeNode nodeB = new TreeNode(nodeBEntity);
        TreeNode nodeC = new TreeNode(nodeCEntity);
        TreeNode nodeD = new TreeNode(nodeDEntity);
        TreeNode nodeE = new TreeNode(nodeEEntity);
        TreeNode nodeF = new TreeNode(nodeFEntity);
        nodeE.getTreeNodeList().add(nodeF);
        nodeD.getTreeNodeList().add(nodeE);
        nodeC.getTreeNodeList().add(nodeD);
        nodeB.getTreeNodeList().add(nodeC);
        nodeA.getTreeNodeList().add(nodeB);
        root.getTreeNodeList().add(nodeA);
        int count = 1000000;
        JsonSerializer jsonSerializer = new JsonSerializer();
        long b = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            jsonSerializer.serialize(root);
        }
        long e = System.currentTimeMillis();
        System.out.println(e - b);

        b = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            JSONObject.toJSON(root);
        }
        e = System.currentTimeMillis();
        System.out.println(e - b);

        Gson gson = new Gson();
        b = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            gson.toJson(root);
        }
        e = System.currentTimeMillis();
        System.out.println(e - b);

    }
}
