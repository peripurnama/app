package id.co.iteacode.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import id.co.iteacode.model.Role;

import java.lang.String;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findByName(String name);
}