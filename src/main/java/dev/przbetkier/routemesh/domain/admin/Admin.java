package dev.przbetkier.routemesh.domain.admin;

import dev.przbetkier.routemesh.domain.road.Road;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Admin {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String address;
    private String postalCode;
    private String telephone;
    private String email;
    private String fax;

    @Relationship(type = "MANAGES")
    private Set<Road> roads;

    public Admin() {
    }

    public Admin(String name, String address, String postalCode, String telephone, String email, String fax, Set<Road> roads) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
        this.roads = roads;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }

    public Set<Road> getRoads() {
        return roads;
    }
}
