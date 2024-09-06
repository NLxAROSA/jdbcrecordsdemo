package com.deployonfriday.examples.jdbcrecordsdemo;

import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class ScheduledSessionRepo {

    private static final String INSERT_SQL = "insert into scheduled_session (start_date, session_name, passcode) values (:startDate, :sessionName, :passCode)";
    private static final String SELECT_SQL = "select session_name, start_date, passcode from scheduled_session where session_name = :sessionName";

    private final JdbcClient jdbcClient;

    private final RowMapper<ScheduledSession> rowMapper = (rs, rowNum) -> new ScheduledSession(
            rs.getString("session_name"),
            rs.getString("passCode"),
            rs.getTimestamp("start_date").toLocalDateTime());

    public ScheduledSessionRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public int insertScheduledSession(ScheduledSession scheduledSession) {
        return jdbcClient
                .sql(INSERT_SQL)
                .param("startDate", scheduledSession.startDate())
                .param("sessionName", scheduledSession.sessionName())
                .param("passCode", scheduledSession.passCode())
                .update();
    }

    public Optional<ScheduledSession> getScheduledSession(String sessionName) {
        return jdbcClient
                .sql(SELECT_SQL)
                .param("sessionName", sessionName)
                .query(rowMapper).optional();
    }

}
