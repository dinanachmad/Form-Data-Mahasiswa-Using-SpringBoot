package com.form.dataMahasiswa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.form.dataMahasiswa.model.Mahasiswa;
import com.form.dataMahasiswa.repository.MahasiswaRepo;

@Service
public class MahasiswaServiceImp implements MahasiswaService{
	
	@Autowired
	private MahasiswaRepo  mahasiswaRepo;
	
	@Override
	public void saveData(Mahasiswa mahasiswa) {
		// TODO Auto-generated method stub
		this.mahasiswaRepo.save(mahasiswa);
	}

	@Override
	public List<Mahasiswa> getAllMahasiswa() {
		return mahasiswaRepo.findAll();
	}

	@Override
	public void deleteMahasiswaById(long id) {
		// TODO Auto-generated method stub
		this.mahasiswaRepo.deleteById(id);
	}

	@Override
	public Mahasiswa getMahasiswaId(long id) {
		Optional<Mahasiswa> optional = mahasiswaRepo.findById(id);
		Mahasiswa mahasiswa = null;
		if(optional.isPresent()) {
			mahasiswa = optional.get();
		}else {
			throw new RuntimeException("Mahasiswa not found for id :: " + id);
		}
		
		return mahasiswa;
	}

	@Override
	public Page<Mahasiswa> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.mahasiswaRepo.findAll(pageable);
	}

}
