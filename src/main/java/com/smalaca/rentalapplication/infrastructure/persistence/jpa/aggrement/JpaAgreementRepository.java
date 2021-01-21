package com.smalaca.rentalapplication.infrastructure.persistence.jpa.aggrement;

import com.smalaca.rentalapplication.domain.aggrement.Agreement;
import com.smalaca.rentalapplication.domain.aggrement.AgreementRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaAgreementRepository implements AgreementRepository {
    @Override
    public void save(Agreement agreement) {

    }
}
