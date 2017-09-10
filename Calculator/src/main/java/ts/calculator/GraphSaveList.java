package ts.calculator;

import com.handstudio.android.hzgrapherlib.vo.Graph;

/**
 * Created by user on 2017-02-21.
 */
public class GraphSaveList {
    private String maxX;
    private String maxY;
    private String GraphSaveName;
    private String Graph1;
    private String Graph2;
    private String Graph3;
    private String Graph1Val;
    private String Graph2Val;
    private String Graph3Val;

    public GraphSaveList(String maxX, String maxY, String GraphSaveName, String Graph1, String Graph2, String Graph3, String Graph1Val, String Graph2Val, String Graph3Val) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.GraphSaveName = GraphSaveName;
        this.Graph1 = Graph1;
        this.Graph2 = Graph2;
        this.Graph3 = Graph3;
        this.Graph1Val = Graph1Val;
        this.Graph2Val = Graph2Val;
        this.Graph3Val = Graph3Val;
    }


    public String getMaxX() {
        return this.maxX;
    }

    public String getMaxY() {
        return this.maxY;
    }

    public String getGraphName() {
        return this.GraphSaveName;
    }

    public String getGraph1() {
        return this.Graph1;
    }

    public String getGraph2() {
        return this.Graph2;
    }

    public String getGraph3() {
        return this.Graph3;
    }

    public String getGraph1Val() {
        return this.Graph1Val;
    }

    public String getGraph2Val() {
        return this.Graph2Val;
    }

    public String getGraph3Val() {
        return this.Graph3Val;
    }

}