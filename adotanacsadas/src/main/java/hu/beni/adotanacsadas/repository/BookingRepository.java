package hu.beni.adotanacsadas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.beni.adotanacsadas.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}