package graph.build;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 使用set集合代替链表提高查找操作,构建图以及求相邻节点的时间复杂度降低
 */
public class AdjSet {
    private int V;

    private int E;

    private TreeSet<Integer>[] adj;

    public int getV() {
        return V;
    }

    public int getE() {
        return E;
    }

    public boolean hasEdge(int v,int w){
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    /**
     * 返回该点连接的所有点
     * @param v
     * @return
     */
    public Iterable<Integer> adj(int v){
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v){
        validateVertex(v);
        return adj[v].size();
    }

    public AdjSet(String filename){
        File file=new File(filename);
        try(Scanner scanner=new Scanner(file)) {
            V=scanner.nextInt();
            if(V<0) throw new IllegalArgumentException("V must be non-negative");
            adj=new TreeSet[V];
            for(int i=0;i<V;i++)
                adj[i]=new TreeSet<>();

            E=scanner.nextInt();
            if(E<0) throw new IllegalArgumentException("E must be non-negative");
            for(int i=0;i<E;i++){
                int a=scanner.nextInt();
                validateVertex(a);
                int b=scanner.nextInt();
                validateVertex(b);
                //判断自环边
                if(a==b) throw new IllegalArgumentException("Self Loop is Detected");
                //判断平行边
                if(adj[a].contains(b)) throw new IllegalArgumentException("Parallel Edges are Detected");
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void validateVertex(int v){
        if(v<0||v>=V) throw new IllegalArgumentException("vertex"+v+"is invalid");
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append(String.format("V=%d,E=%d\n",V,E));
        for(int i=0;i<V;i++){
            sb.append(String.format("%d: ",i));
            for(int w:adj[i]) sb.append(String.format("%d ",w));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjSet adjSet=new AdjSet("g.txt");
        System.out.println(adjSet);
    }
}
