import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Project2Driver {
    static String path = "";
    public static void main(String[] args) {
        String[][] maze = null;
        String[] size = new String[2];
        String filename = "";
        Scanner scan = new Scanner(System.in);
        BufferedReader reader = null;

        System.out.print("Please enter a maze file: ");
        filename = scan.nextLine();

        try {
            System.out.println("Using the maze in " + filename);

            reader = new BufferedReader(new FileReader(filename));

            size = reader.readLine().split(" ");
            maze = new String[Integer.parseInt(size[0])] [Integer.parseInt(size[1])];

            for (int i = 0; i < Integer.parseInt(size[0]); i++) {
                maze[i] = reader.readLine().split("");
            }
            reader.close();
        }
        catch (IOException e) {
            System.err.println("Error opening " + filename);
        }

        printMaze(maze);
        String answer =solveMaze(maze, 0, 1, "S");
        System.out.println(answer);

        if (solveMaze(maze, 0, 1, "S").compareTo("!") == 0) {
            System.out.println("Solved! Direct path from entrance to exit: " + path);
        }
        else {
            System.out.println("Unsolvable!");
        }

        printMaze(maze);
    }

    public static void printMaze(String[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }

    public static String solveMaze(String[][] maze, int row, int column, String direction) {
        if (row == 0 && column == 3 && maze[row][column].compareTo(" ") == 0) {
            maze[row][column] = "!";
            return "!";
        }
        if (checkForX(maze, row, column)) {
            maze[row][column] = "X";
            return "";
        }

        if (maze[row][column].compareTo("#") == 0) {
            return "";
        }

        else {
            if (row == 0 && column == 1) {
                path += "S";
                maze[row][column] = "S";
                solveMaze(maze, row + 1, column, "S");
            }
            else {
                if (maze[row][column].compareTo(" ") == 0) {
                    path += direction;
                    maze[row][column] = direction;
                    solveMaze(maze, row + 1, column, "S");
                    solveMaze(maze, row - 1, column, "N");
                    solveMaze(maze, row, column + 1, "E");
                    solveMaze(maze, row, column - 1, "W");
                }
                return "";
            }
            return "";
        }
    }

    public static boolean checkForX(String[][] maze, int row, int column) {
        if (maze[row][column].compareTo(" ") == 0) {
            if (maze[row + 1][column].compareTo(" ") == 0) {
                return false;
            }
            else if (maze[row - 1][column].compareTo(" ") == 0) {
                return false;
            }
            else if (maze[row][column + 1].compareTo(" ") == 0) {
                return false;
            }
            else if (maze[row][column - 1].compareTo(" ") == 0) {
                return false;
            }
            return true;
        }
        return false;
    }
}
