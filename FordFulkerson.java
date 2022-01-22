import java.util.*;
import java.io.File;
 
/**
 * instructed by professor David Becerra
 * big thanks for the last day help!
 */
 
public class FordFulkerson {
 
    private static void  dfshelper (Integer source,Integer destination,WGraph graph,ArrayList<Integer> path,boolean searched[]){
        //ArrayList<Integer> path = new ArrayList<Integer>();
        ArrayList<Integer>adjnodes = new ArrayList<Integer>();
        int num =0;
        while(num< graph.getEdges().size()){
            Edge e = graph.getEdges().get(num);
            num++;
            int node1 = e.nodes[0];
            int node2 = e.nodes[1];
            if (node1 == source){
                adjnodes.add(node2);
            }
        }
        //boolean []searched = new boolean[graph.getNbNodes()];
        searched[source]=true;
        path.add(source);
        int count = 0;
        while ( count< adjnodes.size()){
            int vertice = adjnodes.get(count);
            count++;
            Edge e = graph.getEdge(source,vertice);
            int weight = e.weight;
            if (searched[vertice] != true &&weight > 0) {
                dfshelper(vertice,destination,graph,path,searched);
                int bottomindex = path.size() - 1;
                int bottom = path.get(bottomindex);
                if (bottom !=destination) { path.remove(bottomindex); } }
        }
    }
 
 
    public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
        ArrayList<Integer> path = new ArrayList<Integer>();
        /* YOUR CODE GOES HERE*/
        int nodes = graph.getNbNodes();
        boolean searched[] = new boolean[nodes];
        if(source == destination){
            path.add(source);
            return path;
        }
        dfshelper(source,destination,graph,path,searched);
        int bottom = path.get(path.size()-1);
        if (bottom ==destination){ return path; }
        else if (bottom!=destination){ path.clear(); }
        return path;
    }
 
    public static String fordfulkerson( WGraph graph){
        String answer="";
        int maxFlow = 0;
        WGraph initgrahp = new WGraph(graph);
        int source = initgrahp.getSource();
        int destination = initgrahp.getDestination();
        for(Edge e: initgrahp.getEdges()){
            int node1 = e.nodes[0];
            int node2 = e.nodes[1];
            initgrahp.setEdge(node1,node2,0);
        }
        WGraph changegraph = new WGraph(graph);
        //ArrayList<Integer>augmentpath = pathDFS(source,destination,initgrahp);
        //ArrayList<Integer>augmentpath = new ArrayList<>();
        WGraph residualgraph = new WGraph(graph);
        int min = 0;
        while(pathDFS(source,destination,residualgraph).contains(destination)) {
            //pathDFS(source,destination,initgrahp);
            //System.out.println(augmentpath);
            ArrayList<Integer>augmentpath = pathDFS(source, destination, residualgraph);
            //ArrayList<Edge>=augmentpath.get(i);
            for (int i = 0; i < augmentpath.size() - 1; i++) {
                int node1 = augmentpath.get(i);
                int node2 = augmentpath.get(i + 1);
                Edge e = residualgraph.getEdge(node1, node2);
                int weight = e.weight;
                if(min==0 ||weight<min) {
                    min = weight;
                }
            }
            // WGraph residualgraph = new WGraph(graph);
            for (int j = 0; j < augmentpath.size() - 1; j++) {
                int node1 = augmentpath.get(j);
                int node2 = augmentpath.get(j + 1);
                Edge frontedge = residualgraph.getEdge(node1, node2);
                Edge backedge = residualgraph.getEdge(node2, node1);
                frontedge.weight = frontedge.weight - min;
                //residualgraph.setEdge(node1,node2,weight-min);
                if (backedge == null) {
                    Edge newbackedge = new Edge(node2, node1,min );
                    residualgraph.addEdge(newbackedge);
                }
                if(backedge!= null){
                    int weight = frontedge.weight + min;
                    backedge.weight = weight;
                }
                Edge orifrontedge = initgrahp.getEdge(node1, node2);
                Edge oribackedge = residualgraph.getEdge(node2,node1);
                Edge cfrontedge = changegraph.getEdge(node1,node2);
                    /*if(oribackedge==null){
                        //int weight = frontedge.weight-min;
                        initgrahp.setEdge(node1,node2,0);
                    }*/
                    /*
                    if(oribackedge!=null){
                        int weight = changegraph.getEdge(node2,node1).weight;
                        changegraph.setEdge(node1,node2,oribackedge.weight-min);
                    }*/
                //maxFlow = maxFlow + min;
                //System.out.println(maxFlow);
                //initgrahp = residualgraph;
            }
            ArrayList<Edge>redges= residualgraph.getEdges();
            ArrayList<Edge>cedgeds= changegraph.getEdges();
            for (Edge e : redges){
                for (Edge f : cedgeds){
                    if(issameedge(e,f)){ int weight = f.weight-e.weight;initgrahp.setEdge(f.nodes[0],f.nodes[1],weight); }
                }
            }
            maxFlow = maxFlow + min;
            graph = initgrahp;
 
        }/* YOUR CODE GOES HERE		*/
        answer += maxFlow + "\n" + graph.toString();
        return answer;
    }
    private  static  boolean issameedge(Edge e ,Edge f){
        boolean issame = false;
        int enode1 = e.nodes[0];
        int enode2 = e.nodes[1];
        int fnode1 = f.nodes[0];
        int fnode2 = f.nodes[1];
        if(enode1==fnode1&&enode2==fnode2){
            issame = true;
        }
        return  issame;
    }
 
 
    public static void main(String[] args){
        String file = args[0];
        File f = new File(file);
        WGraph g = new WGraph(file);
        System.out.println(fordfulkerson(g));
    }
}
 
 
 
