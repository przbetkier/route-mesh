package integration.commons;

import dev.przbetkier.routemesh.api.request.ObstacleRequest;
import dev.przbetkier.routemesh.domain.obstacle.obstructions.Obstructions;
import integration.commons.helpers.LatitudeHelper;

public class ObstacleRequestFactory {
    public static ObstacleRequest simple(Long roadId, Obstructions obstructions) {
        return new ObstacleRequest(roadId,
                                   "Obstacle 1",
                                   "City",
                                   LatitudeHelper.randomPolishLatitude(),
                                   LatitudeHelper.randomPolishLongitude(),
                                   true,
                                   123.0,
                                   "http://url.com",
                                   "comment",
                                   obstructions);
    }
}
