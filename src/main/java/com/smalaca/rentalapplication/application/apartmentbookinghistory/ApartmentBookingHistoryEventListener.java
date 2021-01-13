package com.smalaca.rentalapplication.application.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import com.smalaca.rentalapplication.domain.period.Period;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApartmentBookingHistoryEventListener {
    private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
        this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
    }

    @EventListener
    public void consume(ApartmentBooked apartmentBooked) {
        ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());
        Period period = new Period(apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd());

        apartmentBookingHistory.addBookingStart(
                apartmentBooked.getEventCreationDateTime(), apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), period);

        apartmentBookingHistoryRepository.save(apartmentBookingHistory);
    }

    private ApartmentBookingHistory getApartmentBookingHistoryFor(String apartmentId) {
        if (apartmentBookingHistoryRepository.existsFor(apartmentId)) {
            return apartmentBookingHistoryRepository.findFor(apartmentId);
        } else {
            return new ApartmentBookingHistory(apartmentId);
        }
    }
}
