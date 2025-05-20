package com.twelvefactor.examples.twelvefactornotepad.repository;

import com.twelvefactor.examples.twelvefactornotepad.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {}
