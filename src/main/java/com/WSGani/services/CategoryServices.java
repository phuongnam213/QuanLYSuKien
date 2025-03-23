package com.WSGani.services;

import com.WSGani.entity.Category;
import com.WSGani.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {

    @Autowired
    private ICategoryRepository loaiSuKienRepository;

    public List<Category> getAllLoaiSK() {
        return loaiSuKienRepository.findAll();
    }

    public Optional<Category> getLoaiSKById(Long id) {
        return loaiSuKienRepository.findById(id);
    }

    public Category addLoaiSK(Category loaiSuKien) {
        Category saveLoaiSK = loaiSuKienRepository.save(loaiSuKien);
        return saveLoaiSK;
    }

    public void deleteLoaiSK(Long id) {
        loaiSuKienRepository.deleteById(id);
    }
}
