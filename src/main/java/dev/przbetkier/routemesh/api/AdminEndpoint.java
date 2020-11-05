package dev.przbetkier.routemesh.api;

import dev.przbetkier.routemesh.api.request.AdminRequest;
import dev.przbetkier.routemesh.api.response.AdminResponse;
import dev.przbetkier.routemesh.domain.admin.AdminService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/admins")
class AdminEndpoint {

    private final AdminService adminService;

    AdminEndpoint(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminResponse> findAll() {
        return adminService.findAll().stream().map(AdminResponse::fromAdmin).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public AdminResponse create(@RequestBody AdminRequest adminRequest) {
        return AdminResponse.fromAdmin(adminService.createAndSave(adminRequest));
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("/{adminId}")
    public void deleteById(@PathVariable Long adminId) {
        adminService.deleteById(adminId);
    }
}
