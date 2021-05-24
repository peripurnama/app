package id.co.iteacode.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.User;

public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

	Page<Pegawai> findByDeletedFalseAndNpwpContainingIgnoreCaseOrStatusPekerjaanContainingIgnoreCaseOrPendidikanTerakhirContainingIgnoreCase(
			String npwp, String statusPekerjaan, String pendidikanTerakhir, Pageable pageable);
	
	Page<Pegawai> findByDeletedFalse(Pageable pageable);
	
	
	List<Pegawai> findByStatusPekerjaanContainingIgnoreCase(String statusPekerjaan);
	
	List<Pegawai> findByUserIsNull();
}
