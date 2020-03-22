package integration.commons;

import dev.przbetkier.routemesh.api.request.HeightObstacleRequest;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleSubtype;
import dev.przbetkier.routemesh.domain.obstacle.ObstacleType;
import dev.przbetkier.routemesh.domain.obstacle.height.HeightProfile;
import integration.commons.helpers.LatitudeHelper;

public class HeightObstacleRequestFactory {

    public static HeightObstacleRequest simpleWithRoadId(Long roadId) {
        return new HeightObstacleRequest(roadId,
                                         "Obstacle 1",
                                         "City",
                                         LatitudeHelper.randomPolishLatitude(),
                                         LatitudeHelper.randomPolishLongitude(),
                                         true,
                                         123.0,
                                         "http://url.com",
                                         "comment",
                                         ObstacleType.HEIGHT.toString(),
                                         ObstacleSubtype.TUNNEL.toString(),
                                         5000,
                                         HeightProfile.LINE,
                                         200);
    }
}
