package com.smalaca.rentalapplication.infrastructure.persistence.jpa.agreement;

import com.smalaca.rentalapplication.domain.agreement.Agreement;
import com.smalaca.rentalapplication.domain.agreement.AgreementRepository;
import org.springframework.stereotype.Repository;

@Repository
class JpaAgreementRepository implements AgreementRepository {
    @Override
    public void save(Agreement agreement) {

    }
}
