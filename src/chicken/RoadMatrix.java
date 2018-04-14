package chicken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoadMatrix {

    //num vertices in the road matrix.
    private int vertices;
    //Road is an NxN matrix.
    private int n;
    private int potholes;

    //Array holding list of reachable segments from each other segment.
    private ArrayList<Integer>[] adjSegList;
    private ArrayList<Integer> potholeLocations = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();

    public RoadMatrix(int vertices) {
        this.vertices = vertices;
        n = (int)Math.sqrt((double)vertices);
        initAdjSegList();
    }

    //Creates an ArrayList for each segment in the road. Left variable so it can be used for more than a 4x4 matrix.
    private void initAdjSegList() {
        adjSegList = new ArrayList[vertices];

        for(int i = 0; i < vertices; i++) {
            adjSegList[i] = new ArrayList<>();
        }
    }

    public void setPotholes(int potholes) {
        this.potholes = potholes;
        createPotholes();
    }

    public void setSpecificPotholes(int pothole) {
        potholeLocations.add(pothole);
    }

    //Create random potholes.
    private void createPotholes() {
        Random r = new Random(System.currentTimeMillis());
        int pothole;

        for(int i = 0; i < potholes; i++) {
            pothole = r.nextInt(vertices);

            while(potholeLocations.contains(pothole))
                pothole++;

            potholeLocations.add(pothole);
        }
    }

    public ArrayList<Integer> getPotholeLocations() {
        return potholeLocations;
    }

    /*
        Create all adjacent road segments programmatically, taking care to ignore potholes, segments that are out of bounds,
        and adjacency for exit segments(so HennyPenny doesn't waste time walking up and down the road when he's already reached
        the end).
         */
    public void createAdjLists() {
        int upSegment, downSegment, rightSegment;

        for(int i = 0; i < vertices; i++) {
            if(i % n != n -1) {
                upSegment = i + n;
                if (upSegment < vertices && !potholeLocations.contains(upSegment))
                    adjSegList[i].add(upSegment);

                downSegment = i - n;
                if (downSegment >= 0 && !potholeLocations.contains(downSegment))
                    adjSegList[i].add(downSegment);

                rightSegment = i + 1;
                if (rightSegment < vertices && !potholeLocations.contains(rightSegment))
                    adjSegList[i].add(rightSegment);
            }
        }
    }

    public void printValidPaths(int start, int end) {
        boolean[] isVisited = new boolean[vertices];

        ArrayList<Integer> pathList = new ArrayList<>();

        pathList.add(start);

        pathFinderUtil(start, end, isVisited, pathList);
    }

    public void illustratePath() {
        Random r = new Random();
        int rand = r.nextInt(allPaths.size());
        ArrayList<Integer> randPath = allPaths.get(rand);
        System.out.print("\n");
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(randPath.contains((n * i) + j))
                    System.out.print("C ");
                else if(potholeLocations.contains((n * i) + j))
                    System.out.print("X ");
                else
                    System.out.print("O ");
            }
            System.out.print("\n");
        }
        System.out.println("Legend: X = Pothole, C = HennyPenny, O = Passable Road Square");
        System.out.print("Path: ");
        printAsCoordinates(randPath);

    }

    private void printAsCoordinates(List<Integer> path) {
        for(int i : path) {
            int x = i / n;
            int y = i % n;
            System.out.print("(" + x + " , " + y + ")");
            System.out.print(" ");
        }
        System.out.print("\n");

    }

    private void pathFinderUtil(Integer u, Integer v, boolean[] isVisited, List<Integer> currentPathList) {
        // Mark the current node
        isVisited[u] = true;

        if (u.equals(v))
        {
            printAsCoordinates(currentPathList);
            allPaths.add(new ArrayList<>(currentPathList));
        }

        // Recur for all the vertices
        // adjacent to current vertex
        for (Integer i : adjSegList[u])
        {
            if (!isVisited[i])
            {
                // store current node
                // in path[]
                currentPathList.add(i);
                pathFinderUtil(i, v, isVisited, currentPathList);

                // remove current node
                // in path[]
                currentPathList.remove(i);
            }
        }

        // Mark the current node
        isVisited[u] = false;
    }
}
