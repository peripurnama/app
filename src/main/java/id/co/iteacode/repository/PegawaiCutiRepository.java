package id.co.iteacode.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.iteacode.model.Cuti;
import id.co.iteacode.model.Pegawai;
import id.co.iteacode.model.PegawaiCuti;

public interface PegawaiCutiRepository extends JpaRepository<PegawaiCuti, Long> {

	Optional<PegawaiCuti> findByPegawaiAndCuti(Pegawai pegawai, Cuti cuti);
}
