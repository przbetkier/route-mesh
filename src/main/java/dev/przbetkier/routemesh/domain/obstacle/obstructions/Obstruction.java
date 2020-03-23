package dev.przbetkier.routemesh.domain.obstacle.obstructions;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Obstruction {

    @Id
    @GeneratedValue
    private Long id;

}
