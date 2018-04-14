package chicken;

import java.util.Random;

public class ChickenRoadCrosser2018 {

    public static void main(String[] args) {
        int n = 4;
        int matrixSize = n * n;
        RoadMatrix road = new RoadMatrix(matrixSize);

        road.setRandomPotholes(5);


        road.createAdjLists();

        Random r = new Random();

        int randStart = 0;
        boolean validStartPoint = false;

        while(!validStartPoint) {
            int rand = r.nextInt(n);
            randStart = n * rand;
            if(!road.getPotholeLocations().contains(randStart))
                validStartPoint = true;
        }

        for(int i = 0; i < n; i++) {
            int end = (n * i) + (n - 1);
            road.printValidPaths(randStart, end);
        }

        road.illustratePath();
    }
}
