package com.WSGani.controller;

import com.WSGani.entity.Event;
import com.WSGani.repository.ICategoryRepository;
import com.WSGani.services.EventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EventController {

    private static final String UPLOAD_DIR = "src/main/resources/static/img/";

    @Autowired
    private EventServices eventService;

    @Autowired
    private ICategoryRepository categoryRepository;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Optional<Event> event = eventService.getEventById(id);
        return event.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Event> createEvent(@RequestPart("event") Event event, @RequestPart("image") MultipartFile image) {
        try {
            String imagePath = saveImage(image);
            event.setImage(imagePath); // Set the image path to event
            Event newEvent = eventService.addEvent(event);
            return ResponseEntity.ok(newEvent);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @Validated @RequestBody Event eventData) {
        Optional<Event> optionalEvent = eventService.getEventById(id);
        if (optionalEvent.isPresent()) {

            Event event = optionalEvent.get();
            event.setNameEvent(eventData.getNameEvent());
            event.setMoTa(eventData.getMoTa());
            event.setImage(eventData.getImage());
            event.setSoLuong(eventData.getSoLuong());
            event.setDateBatDau(eventData.getDateBatDau());
            event.setTimeBatDau(eventData.getTimeBatDau());
            event.setDateKetThuc(eventData.getDateKetThuc());
            event.setTimeKetThuc(eventData.getTimeKetThuc());
            event.setDiaDiem(eventData.getDiaDiem());
            event.setName(eventData.getName());
            event.setCompany(eventData.getCompany());
            event.setEmail(eventData.getEmail());
            event.setPhone(eventData.getPhone());
            event.setPrice(eventData.getPrice());
            event.setCategory(eventData.getCategory());
            Event updatedEvent = eventService.addEvent(event);
            return ResponseEntity.ok(updatedEvent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        Optional<Event> optionalEvent = eventService.getEventById(id);
        if (optionalEvent.isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private String saveImage(MultipartFile image) throws IOException {
        String imagePath =image.getOriginalFilename();
        Path path = Paths.get(imagePath);
        Files.write(path, image.getBytes());
        return imagePath;
    }
}