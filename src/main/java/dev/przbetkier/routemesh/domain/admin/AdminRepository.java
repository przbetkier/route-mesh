package dev.przbetkier.routemesh.domain.admin;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface AdminRepository extends Neo4jRepository<Admin, Long> {

    List<Admin> findAll();
}
