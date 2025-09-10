-- create users and roles
CREATE TABLE IF NOT EXISTS mt_users (id SERIAL PRIMARY KEY, username VARCHAR(150) UNIQUE NOT NULL, password VARCHAR(200) NOT NULL);
CREATE TABLE IF NOT EXISTS mt_user_roles (user_id INTEGER REFERENCES mt_users(id) ON DELETE CASCADE, role VARCHAR(50) NOT NULL);
INSERT INTO mt_users (username, password) VALUES ('alice', '$2a$10$u1qQZ0r6yZ0pQJ9oYzdP9eZx1fJq2G1y8d3sKQ8mZ6Y8eFh1qOa2u') ON CONFLICT (username) DO NOTHING;
