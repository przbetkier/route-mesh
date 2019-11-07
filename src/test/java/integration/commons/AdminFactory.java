package integration.commons;

import dev.przbetkier.routemesh.domain.admin.Admin;
import dev.przbetkier.routemesh.domain.road.Road;

import java.util.Collections;
import java.util.Set;

public class AdminFactory {

    private AdminFactory() {
    }

    public static Admin simpleWithName(String name) {
        return new Admin(name,
                         "address",
                         "00-000",
                         "123-456-789",
                         "email@email.com",
                         "111-111-111",
                         Collections.emptySet());
    }

    public static Admin simpleWithRoads(String name, Set<Road> road) {
        return new Admin(name, "address", "00-000", "123-456-789", "email@email.com", "111-111-111", road);
    }
}
