package com.smalaca.rentalapplication.infrastructure.persistence.jpa.agreement;

import com.smalaca.rentalapplication.domain.agreement.Agreement;
import com.smalaca.rentalapplication.domain.agreement.AgreementRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaAgreementRepository implements AgreementRepository {
    @Override
    public void save(Agreement agreement) {

    }

    @Override
    public Agreement findById(UUID agreementId) {
        return null;
    }
}
