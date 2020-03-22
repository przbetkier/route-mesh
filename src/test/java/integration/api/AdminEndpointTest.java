package integration.api;

import dev.przbetkier.routemesh.api.request.AdminRequest;
import dev.przbetkier.routemesh.api.response.AdminResponse;
import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.node.Node;
import dev.przbetkier.routemesh.domain.road.Road;
import integration.IntegrationTest;
import integration.commons.AdminFactory;
import integration.commons.AdminRequestFactory;
import integration.commons.NodeFactory;
import integration.commons.RoadFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

class AdminEndpointTest extends IntegrationTest {

    @Test
    @DisplayName("should get all admins")
    void shouldGetAllAdmins() {
        // given
        List<Admin> admins = Arrays.asList(AdminFactory.simpleWithName("admin-1"),
                                           AdminFactory.simpleWithName("admin-2"));

        adminRepository.saveAll(admins);

        // when
        ResponseEntity<AdminResponse[]> response = restTemplate.getForEntity(localUrl("/admins"),
                                                                             AdminResponse[].class);

        // then
        assertEquals(OK, response.getStatusCode());
        assertEquals(2, requireNonNull(response.getBody()).length);
        assertTrue(stream(response.getBody()).anyMatch(a -> a.getName().equals("admin-1")));
        assertTrue(stream(response.getBody()).anyMatch(a -> a.getName().equals("admin-2")));
    }

    @Test
    @DisplayName("should get admin with roads")
    void shouldGetAdminWithRoads() {
        // given
        Node start = NodeFactory.simpleWithName("start");
        Node end = NodeFactory.simpleWithName("end");
        nodeRepository.saveAll(Arrays.asList(start, end));
        Road road = roadRepository.save(RoadFactory.simpleFromNodes("road-1", start, end));

        Admin admin = AdminFactory.simpleWithRoads("admin-1", Set.of(road));
        adminRepository.save(admin);

        // when
        ResponseEntity<AdminResponse[]> response = restTemplate.getForEntity(localUrl("/admins"),
                                                                             AdminResponse[].class);

        // then
        assertEquals(OK, response.getStatusCode());
        assertEquals(1, requireNonNull(response.getBody()).length);
        assertTrue(stream(response.getBody()).anyMatch(a -> a.getName().equals(admin.getName())));
        Set<AdminResponse.SimpleRoad> roads = response.getBody()[0].getManagesRoads();
        roads.forEach(r -> assertAll("properties",
                                     () -> assertEquals(road.getId(), r.getId()),
                                     () -> assertEquals(road.getName(), r.getName())));
    }

    @Test
    @DisplayName("should successfully delete admin")
    void shouldDeleteAdmin() {
        // given
        Admin admin = adminRepository.save(AdminFactory.simpleWithName("admin-1"));

        // when
        ResponseEntity<Void> response = restTemplate.exchange(localUrl("/admins/" + admin.getId()),
                                                              DELETE,
                                                              null,
                                                              Void.class);

        // then
        assertEquals(NO_CONTENT, response.getStatusCode());
        assertEquals(0, adminRepository.count());
    }

    @Test
    @DisplayName("should add new admin")
    void shouldAddNewAdmin() {
        // given
        AdminRequest adminRequest = AdminRequestFactory.simple();

        HttpEntity<AdminRequest> entity = new HttpEntity<>(adminRequest, null);

        // when
        ResponseEntity<AdminResponse> response = restTemplate.exchange(localUrl("/admins/"),
                                                                       POST,
                                                                       entity,
                                                                       AdminResponse.class);

        // then
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(1, adminRepository.count());
        Long adminId = requireNonNull(response.getBody()).getId();
        Optional<Admin> savedAdmin = adminRepository.findById(adminId);
        assertTrue(savedAdmin.isPresent());

        savedAdmin.ifPresent(admin -> assertAll("properties",
                                                () -> assertEquals(adminRequest.getName(), admin.getName()),
                                                () -> assertEquals(adminRequest.getAddress(), admin.getAddress()),
                                                () -> assertEquals(adminRequest.getEmail(), admin.getEmail()),
                                                () -> assertEquals(adminRequest.getFax(), admin.getFax()),
                                                () -> assertEquals(adminRequest.getPostalCode(), admin.getPostalCode()),
                                                () -> assertEquals(adminRequest.getTelephone(), admin.getTelephone())));
    }
}
