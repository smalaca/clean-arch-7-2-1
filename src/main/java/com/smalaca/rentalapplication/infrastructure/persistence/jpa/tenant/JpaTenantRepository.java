package com.smalaca.rentalapplication.infrastructure.persistence.jpa.tenant;

import com.smalaca.rentalapplication.domain.tenant.TenantRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaTenantRepository implements TenantRepository {
    @Override
    public boolean existById(String tenantId) {
        return true;
    }
}
