package cn.com.gatico.Tree;

import com.alibaba.fastjson.JSONObject;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        /**
         * root0
         * |-A1
         * | |-D4
         * | |-E5 √
         * |   |-F6
         * |-B2 √
         * |-C3
         */
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
        nodeBEntity.setUpstreamId(0);

        TreeNodeEntity nodeCEntity = new TreeNodeEntity();
        nodeCEntity.setId(3);
        nodeCEntity.setName("nodeC");
        nodeCEntity.setUpstreamId(0);

        TreeNodeEntity nodeDEntity = new TreeNodeEntity();
        nodeDEntity.setId(4);
        nodeDEntity.setName("nodeD");
        nodeDEntity.setUpstreamId(1);

        TreeNodeEntity nodeEEntity = new TreeNodeEntity();
        nodeEEntity.setId(5);
        nodeEEntity.setName("nodeE");
        nodeEEntity.setUpstreamId(1);

        TreeNodeEntity nodeFEntity = new TreeNodeEntity();
        nodeFEntity.setId(6);
        nodeFEntity.setName("nodeF");
        nodeFEntity.setUpstreamId(5);

        TreeNode root = new TreeNode(rootEntity);
        TreeNode nodeA = new TreeNode(nodeAEntity);
        nodeA.getTreeNodeList().add(new TreeNode(nodeDEntity));
        TreeNode nodeE = new TreeNode(nodeEEntity);
        nodeE.getTreeNodeList().add(new TreeNode(nodeFEntity));
        nodeA.getTreeNodeList().add(nodeE);
        TreeNode nodeB = new TreeNode(nodeBEntity);
        TreeNode nodeC = new TreeNode(nodeCEntity);
        root.getTreeNodeList().add(nodeA);
        root.getTreeNodeList().add(nodeB);
        root.getTreeNodeList().add(nodeC);

        Map<Long, TreeNodeEntity> nodeEntityMap = new ConcurrentHashMap<>();
        nodeEntityMap.put(nodeBEntity.getId(), nodeBEntity);
        nodeEntityMap.put(nodeCEntity.getId(), nodeCEntity);
        nodeEntityMap.put(nodeEEntity.getId(), nodeEEntity);

        // getNode(root, nodeEntityMap);
        System.out.println(JSONObject.toJSON(root));
        Flux.create((t) -> {
            t.next("create");
            t.next("create1");
            t.complete();
        }).subscribe(System.out::println);

        //单个元素
        Flux.just("just").subscribe(System.out::println);
        //多个元素
        Flux.just("just", "just1", "just2").subscribe(System.out::println);
        Flux.fromArray(new String[]{"arr", "arr", "arr", "arr"})
                .subscribe(System.out::println);

        Flux.defer(() -> Flux.just("just", "just1", "just2"))
                .subscribe(System.out::println);

        Flux.interval(Duration.of(500, ChronoUnit.MILLIS)).subscribe(aLong -> {
            System.out.println(aLong);
        });
    }


    public static void getNode(TreeNode root, Map<Long, TreeNodeEntity> nodeEntityMap) {
        root.getTreeNodeList().forEach(treeNode -> {
            if (treeNode.getTreeNodeList().isEmpty() && !nodeEntityMap.containsKey(treeNode.getId())) {
                root.getTreeNodeList().remove(treeNode);
            } else {
                getNode(treeNode, nodeEntityMap);
            }
        });
    }
}
