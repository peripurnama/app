package id.co.iteacode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.PengajuanCuti;

public interface PengajuanCutiRepository extends JpaRepository<PengajuanCuti, Long> {

	Page<PengajuanCuti> findByTitleContainingIgnoreCaseOrBodyContainingIgnoreCaseOrPegawaiNamaContainingIgnoreCase(
			String title, String body, String nama, Pageable pageable);
}
