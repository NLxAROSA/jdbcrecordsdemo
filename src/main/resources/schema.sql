CREATE TABLE scheduled_session (
  session_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  start_date TIMESTAMP NOT NULL,
  session_name VARCHAR(200) NOT NULL,
  passcode VARCHAR(50) NOT NULL
);
