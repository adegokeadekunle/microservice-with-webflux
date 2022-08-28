package com.adekunle.commons.events;

import java.util.Date;
import java.util.UUID;

public interface Event {
    UUID getEventId();
    Date eventDate();
}
