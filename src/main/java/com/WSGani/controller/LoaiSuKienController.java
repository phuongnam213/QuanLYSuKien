package com.WSGani.controller;

import com.WSGani.entity.Category;
import com.WSGani.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RestController
@RequestMapping("/loaisk")
public class LoaiSuKienController {

    @Autowired
    private CategoryServices loaiSuKienServices;

    @GetMapping
    public List<Category> getAllLoaiSK() {
        return loaiSuKienServices.getAllLoaiSK();
    }

    @PostMapping
    public Category createLoaiSK(@RequestBody Category loaiSuKien) {
        return loaiSuKienServices.addLoaiSK(loaiSuKien);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getLoaiSKById(@PathVariable Long id) {
        Category loaiSuKien = loaiSuKienServices.getLoaiSKById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại sự kiện :: " + id));
        return ResponseEntity.ok().body(loaiSuKien);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateLoaiSK(@PathVariable Long id, @RequestBody Category loaiSKdata) {
        Category loaiSuKien = loaiSuKienServices.getLoaiSKById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại sự kiện :: " + id));
        loaiSuKien.setName(loaiSKdata.getName());
        final Category updatedLoaiSK = loaiSuKienServices.addLoaiSK(loaiSuKien);
        return ResponseEntity.ok(updatedLoaiSK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoaiSK(@PathVariable Long id) {
        Category loaiSuKien = loaiSuKienServices.getLoaiSKById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy loại sự kiện:: " + id));
        loaiSuKienServices.deleteLoaiSK(id);
        return ResponseEntity.ok().build();
    }
}
