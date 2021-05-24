package id.co.iteacode.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.Cuti;

public interface CutiRepository extends JpaRepository<Cuti, Long> {

	Page<Cuti> findByTypeContainingIgnoreCase(String type, Pageable pageable);

	Page<Cuti> findByJumlahCutiTahunanOrTahun(Integer jumlahCutiTahunan, Integer tahun, Pageable pageable);

	Optional<Cuti> findByTypeAndTahun(String type, Integer tahun);
}
