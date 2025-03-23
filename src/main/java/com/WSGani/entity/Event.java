package com.WSGani.entity;

import com.WSGani.validator.ValidCategoryId;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tên sự kiện không được để trống")
    @Size(max = 256)
    @Column(name = "name_event")
    private String nameEvent;

    @NotBlank(message = "Mô tả không được để trống")
    @Size(max = 256)
    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "image")
    private String image;

    @Column(name = "so_luong")
    private int soLuong;

    @Column(name = "date_bat_dau")
    private LocalDate dateBatDau;

    @Column(name = "time_bat_dau")
    private LocalTime timeBatDau;

    @Column(name = "date_ket_thuc")
    private LocalDate dateKetThuc;

    @Column(name = "time_ket_thuc")
    private LocalTime timeKetThuc;

    @NotBlank(message = "Địa điểm không được để trống")
    @Size(max = 256)
    @Column(name = "dia_diem")
    private String diaDiem;

    @NotBlank(message = "Tên người đăng ký không được để trống")
    @Size(max = 256)
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Tên tổ chức không được để trống")
    @Size(max = 256)
    @Column(name = "company")
    private String company;

    @NotBlank(message = "Email không được để trống")
    @Size(max = 256)
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 256)
    @Column(name = "phone")
    private String phone;

    @Min(value = 1000, message = "Giá phải lớn hơn hoặc bằng 1000")
    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}