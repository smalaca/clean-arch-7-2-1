package com.smalaca.rentalapplication.domain.period;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Embeddable
@SuppressWarnings("PMD.UnusedPrivateMethod")
public class Period {
    private LocalDate periodStart;
    private LocalDate periodEnd;

    private Period() {}

    public Period(LocalDate periodStart, LocalDate periodEnd) {
        this.periodStart = periodStart;
        this.periodEnd = periodEnd;
    }

    public List<LocalDate> asDays() {
        List<LocalDate> dates = periodStart.datesUntil(periodEnd).collect(toList());
        dates.add(periodEnd);

        return dates;
    }

    public LocalDate getPeriodStart() {
        return periodStart;
    }

    private void setPeriodStart(LocalDate periodStart) {
        this.periodStart = periodStart;
    }

    public LocalDate getPeriodEnd() {
        return periodEnd;
    }

    private void setPeriodEnd(LocalDate periodEnd) {
        this.periodEnd = periodEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Period period = (Period) o;

        return new EqualsBuilder().append(periodStart, period.periodStart).append(periodEnd, period.periodEnd).isEquals();
    }

    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(periodStart).append(periodEnd).toHashCode();
    }
}
