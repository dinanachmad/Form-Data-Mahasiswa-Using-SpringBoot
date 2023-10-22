package com.form.dataMahasiswa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.form.dataMahasiswa.model.Mahasiswa;
import com.form.dataMahasiswa.service.MahasiswaService;

@Controller
public class MahasiswaController {
	
	@Autowired
	private MahasiswaService mahasiswaService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		Mahasiswa mahasiswa = new Mahasiswa();
		model.addAttribute("mahasiswa", mahasiswa);
		
		return "inputData";
	}
	
	@GetMapping("/saveData")
	public String saveData(@ModelAttribute("mahasiswa") Mahasiswa mahasiswa, Model model) {
		model.addAttribute("mahasiswa", mahasiswa);
		mahasiswaService.saveData(mahasiswa);
		
		return "previewData";
	}
	
	@GetMapping("/lihatData")
	public String lihatData(Model model) {
//		model.addAttribute("listMahasiswa", mahasiswaService.getAllMahasiswa());
//		return "lihatTable";
		return findPaginated(1, "nama", "asc", model);
	}
	
	@GetMapping("/hapusData/{id}")
	public String deleteMahasiswa(@PathVariable (value = "id") long id) {
		this.mahasiswaService.deleteMahasiswaById(id);
		return "redirect:/lihatData";
	}
	
	@GetMapping("/editData/{id}")
	public String editMahasiswa(@PathVariable (value = "id") long id, Model model) {
		Mahasiswa mahasiswa = mahasiswaService.getMahasiswaId(id);
		model.addAttribute("mahasiswa", mahasiswa);
		
		return "editMahasiswa";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Mahasiswa> page = mahasiswaService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Mahasiswa> listMahasiswa = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listMahasiswa", listMahasiswa);
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		return "lihatTable";
		
	}
	
}
