import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//import Graph;

public class Main {

    public static List<String> ReadFile(String filename) {
        Vector<String> maze = new Vector<String>();

        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                maze.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }



        return  maze.stream().filter(e -> !e.isBlank()).collect(Collectors.toList());
    }

    public static int[][] gridBuilder(String filename) {

        List<String> maze = ReadFile(filename);
        Vector<Integer> size = new Vector< Integer> (); 

        Arrays.asList( maze.remove(0).split(" ")).forEach(e -> size.add( Integer.parseInt(e) ));



        Vector< Vector<String> > gridTemp = new Vector< Vector<String>>();

        maze.forEach( e -> {
            Vector<String> tempLine = new Vector<>();

            // split the string + convert it to intger + add to vector of integers
            Arrays.asList( e.split(" ") ).forEach( k -> tempLine.add( k ));

            gridTemp.add(tempLine);


        } );

        // check if the size is correct
        if( gridTemp.size() != size.elementAt(0) ||  gridTemp.stream().filter( e -> e.size() != size.elementAt(1) ).collect(Collectors.toList()).size() > 0  ){
            return new int[0][0]; 
        }



        int[][] grid = new int[size.elementAt(0)][size.elementAt(1)];

        for( int i = 0 ; i< grid.length ; i++ ){
            for (int j  = 0 ; j < grid[0].length ; j++) {
                grid[i][j] = Integer.parseInt( gridTemp.elementAt(i).elementAt(j)); 
            }
        }

        return grid;
    }

    public static void main(String []args) {

        int M[][] = gridBuilder("file");





    }
}
