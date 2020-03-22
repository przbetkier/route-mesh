package dev.przbetkier.routemesh.domain.obstacle.height;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface HeightObstacleRepository extends Neo4jRepository<HeightObstacle, Long> {

    List<HeightObstacle> findAll();
}
