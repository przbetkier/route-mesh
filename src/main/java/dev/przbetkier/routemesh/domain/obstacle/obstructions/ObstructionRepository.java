package dev.przbetkier.routemesh.domain.obstacle.obstructions;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ObstructionRepository extends Neo4jRepository<Obstruction, Long> {
}
