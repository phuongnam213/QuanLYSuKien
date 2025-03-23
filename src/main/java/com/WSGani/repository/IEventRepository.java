package com.WSGani.repository;

import com.WSGani.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event, Long> {
}
