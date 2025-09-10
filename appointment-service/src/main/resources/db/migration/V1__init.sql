CREATE TABLE IF NOT EXISTS mt_appointments (
  id SERIAL PRIMARY KEY,
  patient_id INTEGER NOT NULL,
  doctor_id INTEGER NOT NULL,
  time TIMESTAMP,
  status VARCHAR(50)
);
