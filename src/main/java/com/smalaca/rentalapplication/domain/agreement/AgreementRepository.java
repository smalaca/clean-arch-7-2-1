package com.smalaca.rentalapplication.domain.agreement;

import java.util.UUID;

public interface AgreementRepository {
    UUID save(Agreement agreement);

    Agreement findById(UUID agreementId);
}
