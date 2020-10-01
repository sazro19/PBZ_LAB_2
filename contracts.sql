CREATE TABLE contracts (
 id VARCHAR(10) PRIMARY KEY,
 organization_id VARCHAR(10),
 agent_name VARCHAR(50),
 start_date DATE,
 end_date DATE,
 sum_category DECIMAL(5,2),
 sum_case DECIMAL(5,2),
 staff_name VARCHAR(50)
 )