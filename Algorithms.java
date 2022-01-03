import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Algorithms {
    
    private Graph graph;

    public void setGraph(Graph _graph){
        this.graph = _graph;
    }


    int getMinimumVertex(boolean [] mst, int [] key){
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < graph.getAdjacentMatrix().length ; i++) {
            if(mst[i]==false && minKey>key[i]){
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }


    private int[] dijkstra_GetMinDistances(int sourceVertex){
        int[][] matrix = graph.getAdjacentMatrix();
        int vertices = matrix.length;

        boolean[] spt = new boolean[vertices];
        int [] distance = new int[vertices];



        int INFINITY = Integer.MAX_VALUE;

        //Initialize all the distance to infinity
        for (int i = 0; i <vertices ; i++) {
            distance[i] = INFINITY;
        }

        //start from the vertex 0
        distance[sourceVertex] = 0;

        //create SPT
        for (int i = 0; i <vertices ; i++) {

            //get the vertex with the minimum distance
            int vertex_U = getMinimumVertex(spt, distance);

            if( vertex_U <0 )
                continue;

            //include this vertex in SPT
            spt[vertex_U] = true;

            //iterate through all the adjacent vertices of above vertex and update the keys
            for (int vertex_V = 0; vertex_V <vertices ; vertex_V++) {
                //check of the edge between vertex_U and vertex_V
                if(matrix[vertex_U][vertex_V]>0){
                    //check if this vertex 'vertex_V' already in spt and
                    // if distance[vertex_V]!=Infinity

                    if(spt[vertex_V]==false && matrix[vertex_U][vertex_V]!=INFINITY){
                        //check if distance needs an update or not
                        //means check total weight from source to vertex_V is less than
                        //the current distance value, if yes then update the distance

                        int newKey =  matrix[vertex_U][vertex_V] + distance[vertex_U];
                        if(newKey<distance[vertex_V]){
                            distance[vertex_V] = newKey;
                        }
                    }
                }
            }
        }

        // map the distances
        // Key : vertex
        // Value : distance from source vertex
        return distance;
    }

    public int getShortestPath(int sourceVertex){

        // subtraction of one so it can match distances list 
        Set<Integer> gridEdges = graph.getGridEdges();
        int value = Integer.MAX_VALUE;

        System.out.println(gridEdges);

        int[] distances = dijkstra_GetMinDistances(sourceVertex);


        for(int i =0; i< distances.length ; i++)
        System.out.println((i+1) + " -> " +distances[i]);

        for(Integer e : gridEdges){

            if( distances[ e.intValue() - 1 ] < value )
                value = distances[ e.intValue() -1 ];
        }
        

        return value;
    }

    public void printDijkstra(int sourceVertex, int [] key){
        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
        for (int i = 0; i <graph.getAdjacentMatrix().length ; i++) {
            System.out.println("Source Vertex: " + (sourceVertex +1)+ " to vertex " +   + (i+1) +
                    " distance: " + key[i]);
        }
    }

}
