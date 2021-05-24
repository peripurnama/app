package id.co.iteacode.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.Bagian;

public interface BagianRepository extends JpaRepository<Bagian, Long>{
	
	Page<Bagian> findByNamaContainingIgnoreCaseOrDeskripsiContainingIgnoreCaseOrDepartementNamaContainingIgnoreCase(String nama, String deskripsi, String departementNama,
			Pageable pageable);
}
