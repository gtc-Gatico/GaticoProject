import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

public class RoadNode {
    public RoadNode() {
        this.roadNodeList = new LinkedList();
    }

    public RoadNode(Point current) {
        this.current = current;
        this.roadNodeList = new LinkedList();
    }

    public Point current;
    public int fromDirection;
    public int top;
    public int bottom;
    public int left;
    public int right;
    public List roadNodeList;

}
