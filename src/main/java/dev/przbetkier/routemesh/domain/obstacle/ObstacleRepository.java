package dev.przbetkier.routemesh.domain.obstacle;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface ObstacleRepository extends Neo4jRepository<Obstacle, Long> {

    List<Obstacle> findAll();

    @Query("MATCH (o:Obstacle)-[h:HAS]-(ob:Obstruction) where id(o)=$id DETACH DELETE o,h,ob")
    void deleteById(Long id);
}
