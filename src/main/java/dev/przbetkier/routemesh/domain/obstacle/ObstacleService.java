package dev.przbetkier.routemesh.domain.obstacle;

import org.springframework.stereotype.Service;

@Service
public class ObstacleService {


    private final ObstacleRepository obstacleRepository;

    public ObstacleService(ObstacleRepository obstacleRepository) {this.obstacleRepository = obstacleRepository;}
}
