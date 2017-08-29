package SBRM.RM;

import java.util.Collection;

public class TreeNode {

    private Collection<TreeNode> children;
    private String caption;

    public TreeNode(Collection<TreeNode> children, String caption) {
        super();
        this.children = children;
        this.caption = caption;
    }

    public Collection<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(Collection<TreeNode> children) {
        this.children = children;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

}

