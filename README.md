# MediTrack 

MediTrack — Healthcare Resource & Appointment Management System.

Services: user-service, appointment-service, inventory-service, billing-service, auth-service, notification-service + dashboard

Quick start (local):
1. Build and run with Docker Compose: `docker compose up --build`
2. Create a user (POST to user-service) and login via auth-service to get JWT.
3. Use `/api/appointment/book` to queue appointments and exercise async billing/notifications.

Security note: demo only — replace secrets and harden for production.


Generated starter includes core Java services, messaging, observability, CI/CD examples, and deployment artifacts. Replace demo placeholders, implement JPA entities and frontend authentication flows for production.

## Upgrades applied (detailed)
JPA entities & Spring Data repositories for users, appointments, bills, inventory.
Flyway SQL migrations and initial seed for user-service and other services.
Testcontainers integration test skeleton for user-service.
Frontend login page (React) storing JWT in localStorage and setting Authorization header.
Kubernetes manifests for Prometheus and Grafana and sample dashboards in observability.
Helm chart expanded with values-prod.yaml and templated deployments per service.


-Final Edit-
This bundle includes:
- Java Spring Boot microservices (user, appointment, inventory, billing, notification) with JPA, Flyway, ActiveMQ, Prometheus metrics.
- React frontend with Patient and Doctor portals and OAuth2 login via Keycloak.
- Keycloak docker-compose + k8s manifests to run locally or in cluster.
- Prometheus & Grafana Helm charts and manifests with sample dashboards.
- Testcontainers integration test skeletons and Cypress e2e skeleton.
- Docker Compose to run everything locally (ActiveMQ, Postgres, Keycloak, services, dashboard).

Security: demo only. Replace secrets and follow production checklist in README.


## How to run locally (quick)
1. Start docker compose: `docker compose up --build`
2. Visit Keycloak at http://localhost:8080 (admin/admin). Create a realm `meditrack`, a client `meditrack-client` (public) with redirect URI `http://localhost:3000/callback`, and users with roles PATIENT/DOCTOR.
3. Start services and dashboard; use dashboard login to authenticate via Keycloak.