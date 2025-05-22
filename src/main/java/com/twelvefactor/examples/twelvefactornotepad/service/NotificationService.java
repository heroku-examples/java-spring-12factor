package com.twelvefactor.examples.twelvefactornotepad.service;

import com.twelvefactor.examples.twelvefactornotepad.domain.Note;

public interface NotificationService {
    void publishEvent(String action, Note note);
}
