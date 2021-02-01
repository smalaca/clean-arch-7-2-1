package com.smalaca.rentalapplication.infrastructure.persistence.jpa.tenant;

import com.smalaca.rentalapplication.domain.tenant.TenantRepository;
import com.smalaca.usermanagement.infrastructure.persistence.jpa.user.SpringJpaUserRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaTenantRepository implements TenantRepository {
    private final SpringJpaUserRepository repository;

    JpaTenantRepository(SpringJpaUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean existById(String tenantId) {
        return repository.existsById(UUID.fromString(tenantId));
    }
}
