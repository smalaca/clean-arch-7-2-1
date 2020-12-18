package com.smalaca.rentalapplication.application.apartmentbookinghistory;

import com.smalaca.rentalapplication.domain.apartment.ApartmentBooked;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBooking;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistory;
import com.smalaca.rentalapplication.domain.apartmentbookinghistory.ApartmentBookingHistoryRepository;
import org.springframework.context.event.EventListener;

public class ApartmentBookingHistoryEventListener {
    private final ApartmentBookingHistoryRepository apartmentBookingHistoryRepository;

    public ApartmentBookingHistoryEventListener(ApartmentBookingHistoryRepository apartmentBookingHistoryRepository) {
        this.apartmentBookingHistoryRepository = apartmentBookingHistoryRepository;
    }

    @EventListener
    public void consume(ApartmentBooked apartmentBooked) {
        ApartmentBookingHistory apartmentBookingHistory = getApartmentBookingHistoryFor(apartmentBooked.getApartmentId());

        apartmentBookingHistory.add(ApartmentBooking.start(
                apartmentBooked.getOwnerId(), apartmentBooked.getTenantId(), apartmentBooked.getPeriodStart(), apartmentBooked.getPeriodEnd()));

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
