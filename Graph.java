//package Graph;
import java.util.*;

public class Graph {
    private int vertices;

    private int matrix[][];

    private int counted_matrix[][];

    private int grid[][];


    private Set<Integer> gridEdges; 



    // init graph frm ground up
    public Graph(int vertex) {
        this.vertices = vertex;
        this.matrix = new int[vertex][vertex];
    }

    // copy from an existed graph
    public Graph(int[][] graph, int size){
        this.matrix = graph;
        this.vertices = size;
    }

    // copy the grid and turn it into graph
    public Graph(int[][] grid) {
        this.grid = grid;
    }


    public void addEdge(int source, int destination, int weight) {
        //add edge
        matrix[source][destination]=weight;

        //add back edge for undirected graph
        matrix[destination][source] = weight;
    }

    public int[][] getAdjacentMatrix() {
        return this.matrix;
    }


    public void printMatrix(String type){
        
        int[][] copyMatrix;
        if( type.toLowerCase().compareTo("grid") == 0 )
            copyMatrix = this.grid;
        else if( type.toLowerCase().compareTo("count") == 0 )
            copyMatrix = this.counted_matrix;
        else if ( type.toLowerCase().compareTo("adjacent") == 0  )
            copyMatrix = this.matrix;
        else 
            return ;


        System.out.print("\t");
        for (int j = 1 ; j<= copyMatrix[0].length ;j++) 
            System.out.print(j + "  ");


            System.out.print("\n");
        for (int j = 0 ; j< copyMatrix.length ;j++) {
            System.out.print(j+1 + "\t");
            for(int i = 0; i< copyMatrix[0].length ; i++)
                System.out.print(copyMatrix[j][i] + "  ");

            System.out.println();
        }

        






    }

    // This function take the maze grid as an argument and return adjacent matrix 
    public void convertToAdjacency() {
        int[][] A = this.grid;
        int count = 0;

        counted_matrix = new int[A.length][A[0].length];

        // trying to identify every free cell 
        for( int i = 0; i < A.length; i++ ){
            for (int j = 0 ; j< A[0].length ; j++) {
                if( A[i][j] == 0 || A[i][j] == 2 )
                    counted_matrix[i][j] = ++count;
            }
        }







        // now we are working on adjacent matrix
        int[][] MA = new int[count][count];

        // init the matrix
        for(int i = 0; i< count ; i++)
            for(int j =0 ; j<count; j++) 
                MA[i][j] = 0;


        for(int i = 0; i< counted_matrix.length ; i++){
            for(int j =0 ; j< counted_matrix[0].length -1 ; j++) {
                if( counted_matrix[i][j] > 0 ){
                    // check next row element
                    if( counted_matrix[i][j+1] > 0 )
                        MA[ counted_matrix[i][j]-1  ] [ counted_matrix[i][j+1]-1 ] = 1;
                    



                    // check next column element
                    if( i != counted_matrix.length -1  && counted_matrix[i+1][j] > 0 )
                        MA[ counted_matrix[i][j]  -1] [ counted_matrix[i+1][j] -1] = 1;

                }


            }
        }


        // make the matrix symetric
        for(int i = 0; i< MA.length ; i++) {
            for(int j = 0; j< MA.length ; j++) {
                if( MA[i][j] == 1 )
                    MA[j][i] = 1;
            }
        }


        gridEdgeDetecteor();


        // save it into the graph itself
        this.matrix = MA;
        this.vertices = MA.length;

    }


    // return a vector of the edges of the grid 
    
    private void gridEdgeDetecteor(){
        gridEdges = new LinkedHashSet<>(); 

        // columns
        for(int i =0; i<counted_matrix.length ; i++){
            if(counted_matrix[i][0]> 0 )
                gridEdges.add(Integer.valueOf(counted_matrix[i][0]));

            if( counted_matrix[i][counted_matrix[0].length -1]> 0  )
                gridEdges.add(Integer.valueOf(counted_matrix[i][ counted_matrix[0].length -1 ] ));
        }



        // rows
        for(int i =0; i<counted_matrix[0].length ; i++){
            if(counted_matrix[0][i]> 0 )
                gridEdges.add(Integer.valueOf(counted_matrix[0][i]));

            if( counted_matrix[counted_matrix.length -1][i]> 0  )
                gridEdges.add(Integer.valueOf(counted_matrix[ counted_matrix.length -1][i]));

        }

        
    }

    public Set<Integer> getGridEdges() {
        return this.gridEdges;
    }


}
