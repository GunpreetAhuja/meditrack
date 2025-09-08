# MediTrack 

MediTrack — Healthcare Resource & Appointment Management System.

Services: user-service, appointment-service, inventory-service, billing-service, auth-service, notification-service + dashboard

Quick start (local):
1. Build and run with Docker Compose: `docker compose up --build`
2. Create a user (POST to user-service) and login via auth-service to get JWT.
3. Use `/api/appointment/book` to queue appointments and exercise async billing/notifications.

Security note: demo only — replace secrets and harden for production.


Generated starter includes core Java services, messaging, observability, CI/CD examples, and deployment artifacts. Replace demo placeholders, implement JPA entities and frontend authentication flows for production.
