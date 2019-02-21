CREATE TABLE IF NOT EXISTS m_user (
	user_id VARCHAR(50) PRIMARY KEY,
	password VARCHAR(100),
	user_name VARCHAR(50),
	birthday DATE,
	age INT,
	marriage BOOLEAN,
	role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS article_list (
	article_id VARCHAR(50) PRIMARY KEY,
	title VARCHAR(50),
	memo TEXT,
	category VARCHAR(50),
	post_date DATE,
);