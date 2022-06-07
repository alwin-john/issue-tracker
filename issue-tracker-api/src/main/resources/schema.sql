CREATE TABLE ISSUE_DETAILS(
    id INT NOT NULL auto_increment,
    issue_type VARCHAR(15) NOT NULL,
    description VARCHAR(1000),
    created_date TIMESTAMP,
    developer_id INT,
    issue_status VARCHAR(20),
    story_points INT,
    issue_severity VARCHAR(20),
    updated_date TIMESTAMP
);

CREATE TABLE DEVELOPER_DETAILS(
    id BIGINT NOT NULL auto_increment,
    name VARCHAR(10) NOT NULL,
    team_id INT,
    created_date TIMESTAMP,
    updated_date TIMESTAMP
);