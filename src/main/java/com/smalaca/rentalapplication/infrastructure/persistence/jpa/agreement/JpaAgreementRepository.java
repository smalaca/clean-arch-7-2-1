package com.smalaca.rentalapplication.infrastructure.persistence.jpa.agreement;

import com.smalaca.rentalapplication.domain.agreement.Agreement;
import com.smalaca.rentalapplication.domain.agreement.AgreementRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class JpaAgreementRepository implements AgreementRepository {
    private final SpringJpaAgreementRepository repository;

    JpaAgreementRepository(SpringJpaAgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID save(Agreement agreement) {
        return repository.save(agreement).getId();
    }

    @Override
    public Agreement findById(UUID agreementId) {
        return repository.findById(agreementId).get();
    }
}
