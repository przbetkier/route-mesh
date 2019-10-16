package dev.przbetkier.routemesh.api.response;

import dev.przbetkier.routemesh.domain.admin.Admin;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class AdminResponse {

    private final Long id;
    private final String name;
    private final String address;
    private final String postalCode;
    private final String telephone;
    private final String email;
    private final String fax;
    private final Set<SimpleRoad> managesRoads;

    private AdminResponse(Long id, String name, String address, String postalCode, String telephone, String email,
                          String fax, Set<SimpleRoad> managesRoads) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
        this.managesRoads = managesRoads;
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

    public Set<SimpleRoad> getManagesRoads() {
        return managesRoads;
    }

    public static AdminResponse fromAdmin(Admin admin) {
        return new AdminResponse(admin.getId(),
                                 admin.getName(),
                                 admin.getAddress(),
                                 admin.getPostalCode(),
                                 admin.getTelephone(),
                                 admin.getEmail(),
                                 admin.getFax(),
                                 hasRoads(admin) ? admin
                                         .getRoads()
                                         .stream()
                                         .map(r -> new SimpleRoad(r.getId(), r.getName()))
                                         .collect(Collectors.toSet()) : Collections.emptySet());
    }

    private static boolean hasRoads(Admin admin) {
        return admin.getRoads() != null && !admin.getRoads().isEmpty();
    }

    public static class SimpleRoad {
        private final Long id;
        private final String name;

        SimpleRoad(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
