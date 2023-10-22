package com.form.dataMahasiswa.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import com.form.dataMahasiswa.model.Mahasiswa;

@Repository
public interface MahasiswaRepo extends JpaRepository<Mahasiswa, Long>{
	
}
