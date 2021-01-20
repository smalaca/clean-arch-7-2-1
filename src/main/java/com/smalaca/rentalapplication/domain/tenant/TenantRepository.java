package com.smalaca.rentalapplication.domain.tenant;

public interface TenantRepository {
    boolean existById(String tenantId);
}
