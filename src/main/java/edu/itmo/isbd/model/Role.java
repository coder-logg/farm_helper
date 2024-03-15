package edu.itmo.isbd.model;

import edu.itmo.isbd.repository.AdminRepository;
import edu.itmo.isbd.repository.DriverRepository;
import edu.itmo.isbd.repository.FarmerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {
    FARMER(FarmerRepository.class, Farmer.class),
    DRIVER(DriverRepository.class, Driver.class),
    ADMIN(AdminRepository.class, Admin.class);
    private final Class<? extends CrudRepository<?, ?>> repository;
    private final Class<? extends User> entity;

    public Class<? extends CrudRepository<?, ?>> getRepository() {
        return repository;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
