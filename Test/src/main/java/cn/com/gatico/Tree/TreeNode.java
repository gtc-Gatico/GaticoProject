package cn.com.gatico.Tree;

import java.util.LinkedList;
import java.util.List;

public class TreeNode {
    private long id;
    private String name;
    private List<TreeNode> treeNodeList = new LinkedList<>();

    public TreeNode() {
    }

    public TreeNode(TreeNodeEntity treeNodeEntity) {
        this.id = treeNodeEntity.getId();
        this.name = treeNodeEntity.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TreeNode> getTreeNodeList() {
        return treeNodeList;
    }

    public void setTreeNodeList(List<TreeNode> treeNodeList) {
        this.treeNodeList = treeNodeList;
    }
}
