package id.co.iteacode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.Jabatan;

public interface JabatanRepository extends JpaRepository<Jabatan, Long> {

	Page<Jabatan> findByNamaContainingIgnoreCaseOrDeskripsiContainingIgnoreCaseOrBagianNamaContainingIgnoreCase(String nama, String deskripsi, String bagianNama,
			Pageable pageable);
	
}
