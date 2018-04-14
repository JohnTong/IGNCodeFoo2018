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

        int randStart = 0;
        boolean validStartPoint = false;

        while(!validStartPoint) {
            int rand = r.nextInt(4);
            randStart = 4 * rand;
            if(!road.getPotholeLocations().contains(randStart))
                validStartPoint = true;
        }

        for(int i = 0; i < 4; i++) {
            int end = (4 * i) + 3;
            road.printValidPaths(randStart, end);
        }

        road.illustratePath();
    }
}
