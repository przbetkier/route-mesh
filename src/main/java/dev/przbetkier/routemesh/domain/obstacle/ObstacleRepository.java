package dev.przbetkier.routemesh.domain.obstacle;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ObstacleRepository extends Neo4jRepository<Obstacle, Long> {

    List<Obstacle> findAll();
}
