package dev.przbetkier.routemesh.domain.admin;

import dev.przbetkier.routemesh.api.request.AdminRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> findById(Long id) {
        return adminRepository.findById(id);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Admin createAndSave(AdminRequest adminRequest) {
        Admin admin = new Admin(adminRequest.getName(),
                                adminRequest.getAddress(),
                                adminRequest.getPostalCode(),
                                adminRequest.getTelephone(),
                                adminRequest.getEmail(),
                                adminRequest.getFax(),
                                Collections.emptySet());
        return adminRepository.save(admin);
    }

    public void deleteById(Long adminId) {
        logger.info("Removing admin with ID: [{}]", adminId);
        adminRepository.deleteById(adminId);
        logger.info("Admin with ID: [{}] has been removed successfully", adminId);
    }
}
