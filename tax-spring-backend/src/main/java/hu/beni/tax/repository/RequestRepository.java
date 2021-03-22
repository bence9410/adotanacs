package hu.beni.tax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.beni.tax.entity.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {

}
