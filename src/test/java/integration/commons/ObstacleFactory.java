package integration.commons;

import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleBuilder;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightObstruction;

import static dev.przbetkier.routemesh.domain.obstacle.obstructions.HeightProfile.LINE;
import static dev.przbetkier.routemesh.domain.obstacle.obstructions.ObstacleHeightSubtype.PIPE;
import static integration.commons.helpers.LatitudeHelper.randomPolishLatitude;
import static integration.commons.helpers.LatitudeHelper.randomPolishLongitude;

public class ObstacleFactory {

    public static Obstacle simpleWithName(String name) {
        return ObstacleBuilder.builder()
                .withName(name)
                .withCity("Warszawa")
                .immovable(true)
                .obstructingRoad(RoadFactory.simple())
                .withHeightObstruction(sampleHeightObstruction())
                .withComment("This is a simple comment")
                .withLatitude(randomPolishLatitude())
                .withLongitude(randomPolishLongitude())
                .withMilestone(100.11)
                .withUrl("https://simpleurl.com")
                .build();
    }

    private static HeightObstruction sampleHeightObstruction() {
        return new HeightObstruction(1111, LINE, 300, PIPE);
    }
}
