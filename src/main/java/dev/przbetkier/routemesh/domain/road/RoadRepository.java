package dev.przbetkier.routemesh.domain.road;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadRepository extends Neo4jRepository<Road, Long> {

    List<Road> findAll();

    Page<Road> findAll(Pageable pageable);

    Page<Road> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

}
