package integration.commons;

import dev.przbetkier.routemesh.domain.obstacle.Obstacle;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleBuilder;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleType;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacle;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacleType;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile;

import static dev.przbetkier.routemesh.domain.obstacle.height.HeightObstacleType.*;
import static dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile.*;
import static integration.commons.helpers.LatitudeHelper.randomPolishLatitude;
import static integration.commons.helpers.LatitudeHelper.randomPolishLongitude;

public class ObstacleFactory {

    public static Obstacle simpleWithName(String name) {
        return ObstacleBuilder.builder()
                .withName(name)
                .withCity("Warszawa")
                .immovable(true)
                .obstructingRoad(RoadFactory.simple())
                .withComment("This is a simple comment")
                .withLatitude(randomPolishLatitude())
                .withLongitude(randomPolishLongitude())
                .withMilestone(100.11)
                .withObstacleType(ObstacleType.HEIGHT)
                .withUrl("https://simpleurl.com")
                .build();
    }

    public static HeightObstacle simpleHeightObstacle(String name) {
        return new HeightObstacle(simpleWithName(name), 5000, LINE, 300, PIPE);
    }
}
