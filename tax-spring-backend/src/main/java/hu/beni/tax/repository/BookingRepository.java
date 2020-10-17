package hu.beni.tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.beni.tax.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
