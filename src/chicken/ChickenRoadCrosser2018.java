package chicken;

import java.util.Random;

public class ChickenRoadCrosser2018 {

    public static void main(String[] args) {

        RoadMatrix road = new RoadMatrix(16);

        road.setSpecificPotholes(4);
        road.setSpecificPotholes(10);
        road.setSpecificPotholes(13);
        road.setSpecificPotholes(15);

        road.createAdjLists();

        Random r = new Random();
        int rand = r.nextInt(4);
        int randStart = 4 * rand;
        
        for(int i = 0; i < 4; i++) {
            int end = (4 * i) + 3;
            road.printValidPaths(randStart, end);
        }
    }
}
