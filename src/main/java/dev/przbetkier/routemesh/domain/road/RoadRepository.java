package dev.przbetkier.routemesh.domain.road;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadRepository extends Neo4jRepository<Road, Long> {

    List<Road> findAll();

    List<Road> findAllByDirection(RoadDirection roadDirection);
}
