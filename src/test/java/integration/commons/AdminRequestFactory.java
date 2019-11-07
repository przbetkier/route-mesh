package integration.commons;

import dev.przbetkier.routemesh.api.request.AdminRequest;

public class AdminRequestFactory {

    public static AdminRequest simple() {
        return new AdminRequest("admin-1", "address", "00-000", "123-456-789", "email@email.com", "111-111-111");
    }
}
