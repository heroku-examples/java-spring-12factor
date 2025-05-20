package com.twelvefactor.examples.twelvefactornotepad.service;

import com.twelvefactor.examples.twelvefactornotepad.config.RedisConfig;
import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import com.twelvefactor.examples.twelvefactornotepad.dto.WebSocketMessage;
import com.twelvefactor.examples.twelvefactornotepad.exception.NoteNotFoundException;
import com.twelvefactor.examples.twelvefactornotepad.repository.NoteRepository;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {
    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);
    private final NoteRepository noteRepository;
    private final Optional<RedisTemplate<String, Object>> redisTemplate;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NoteService(
            NoteRepository noteRepository,
            @Autowired(required = false) RedisTemplate<String, Object> redisTemplate,
            SimpMessagingTemplate simpMessagingTemplate) {
        this.noteRepository = noteRepository;
        this.redisTemplate = Optional.ofNullable(redisTemplate);
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Transactional
    public Note create(Note note) {
        Note savedNote = noteRepository.save(note);
        publishEvent("CREATE", savedNote);
        return savedNote;
    }

    @Transactional(readOnly = true)
    public List<Note> getAll() {
        return noteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Note> getById(Long id) {
        return noteRepository.findById(id);
    }

    @Transactional
    public Note update(Long id, Note noteDetails) {
        Note note = noteRepository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());
        note.setColor(noteDetails.getColor());
        note.setPositionX(noteDetails.getPositionX());
        note.setPositionY(noteDetails.getPositionY());
        Note updatedNote = noteRepository.save(note);
        publishEvent("UPDATE", updatedNote);
        return updatedNote;
    }

    @Transactional
    public void delete(Long id) {
        Note note = noteRepository
                .findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
        noteRepository.delete(note);
        publishEvent("DELETE", note);
    }

    private void publishEvent(String action, Note note) {
        String hostname = "Unknown";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            logger.warn("Could not determine hostname for WebSocketMessage: {}", e.getMessage());
        }
        WebSocketMessage message = new WebSocketMessage(action, note, hostname);
        if (redisTemplate.isPresent()) {
            try {
                redisTemplate.get().convertAndSend(RedisConfig.REDIS_CHANNEL_NOTEPAD_UPDATES, message);
                logger.info(
                        "Published {} event to Redis channel {}: {}",
                        action,
                        RedisConfig.REDIS_CHANNEL_NOTEPAD_UPDATES,
                        message);
            } catch (Exception e) {
                logger.error(
                        "Error publishing {} event to Redis for note id {}: {}",
                        action,
                        (note != null ? note.getId() : "null"),
                        e.getMessage(),
                        e);
            }
        } else {
            try {
                simpMessagingTemplate.convertAndSend("/topic/notes", message);
                logger.info("Published {} event to STOMP topic /topic/notes: {}", action, message);
            } catch (Exception e) {
                logger.error(
                        "Error publishing {} event to STOMP for note id {}: {}",
                        action,
                        (note != null ? note.getId() : "null"),
                        e.getMessage(),
                        e);
            }
        }
    }
}
