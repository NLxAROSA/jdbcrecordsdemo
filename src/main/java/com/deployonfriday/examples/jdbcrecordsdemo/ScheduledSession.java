package com.deployonfriday.examples.jdbcrecordsdemo;

import java.time.LocalDateTime;

public record ScheduledSession(String sessionName, String passCode, LocalDateTime startDate) {
    
}
