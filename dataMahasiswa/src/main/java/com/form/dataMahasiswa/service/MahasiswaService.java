package com.form.dataMahasiswa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.form.dataMahasiswa.model.Mahasiswa;

public interface MahasiswaService {
	void saveData(Mahasiswa mahasiswa);
	List<Mahasiswa> getAllMahasiswa();
	Mahasiswa getMahasiswaId(long id);
	void deleteMahasiswaById(long id);
	Page<Mahasiswa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
