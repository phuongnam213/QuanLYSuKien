package com.WSGani.validator;

import com.WSGani.entity.Category;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Category> {
    @Override
    public boolean isValid(Category loaiSuKien, ConstraintValidatorContext constraintValidatorContext){
        return loaiSuKien!=null && loaiSuKien.getId() != null;
    }
}
