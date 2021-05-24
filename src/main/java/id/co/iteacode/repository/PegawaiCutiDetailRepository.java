package id.co.iteacode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.PegawaiCuti;
import id.co.iteacode.model.PegawaiCutiDetail;
import id.co.iteacode.model.PengajuanCuti;

public interface PegawaiCutiDetailRepository extends JpaRepository<PegawaiCutiDetail, Long>{

	List<PegawaiCutiDetail> findByPegawaiCuti(PegawaiCuti pegawaiCuti);
	List<PegawaiCutiDetail> findByPengajuanCuti(PengajuanCuti pengajuanCuti);
	
	Page<PegawaiCutiDetail> findAllByPegawaiCutiPegawaiUserUsername(String username, Pageable pageable);
	
	List<PegawaiCutiDetail> findByStatus(String status);
}
