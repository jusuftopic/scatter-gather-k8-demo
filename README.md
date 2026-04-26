# Scatter / Gather Kubernetes Demo

This project demonstrates the **Scatter / Gather pattern** using a simple microservice system deployed on Kubernetes.

It is designed as a learning and blog example (CKA/Kubernetes practice), not a production-ready system.

---

## 🧠 Architecture Overview

The system consists of 5 Spring Boot services:

- **aggregator-service** → Entry point (API composition layer)
- **product-service** → Product domain data
- **pricing-service** → Pricing information
- **inventory-service** → Stock information
- **review-service** → Product reviews

---

## ⚙️ Pattern: Scatter / Gather

The aggregator service:

1. Sends parallel requests to multiple microservices (Scatter phase)
2. Collects responses
3. Combines them into a single aggregated response (Gather phase)

This reduces response latency compared to sequential calls.

---

## ☸️ Kubernetes Setup

Each service is deployed as a Kubernetes **Deployment** with a corresponding **ClusterIP Service**.

The aggregator is exposed externally using a **NodePort Service**.

---

## 🌐 Networking

- Internal communication between services uses Kubernetes DNS:
  - `http://product-service`
  - `http://pricing-service`
  - `http://inventory-service`
  - `http://review-service`

- External access is provided via:
  - `http://<node-ip>:30080` (aggregator)

---

## ⚠️ Limitations (Intentionally Simplified)

This project is simplified for learning purposes:

- Dockerfiles are omitted (images are assumed to exist)
- No CI/CD pipeline
- NodePort is used instead of Ingress / LoadBalancer
- No security, TLS, or authentication
- No observability (logging/metrics/tracing)

---

## 🎯 Purpose

This project is meant to:

- Demonstrate the Scatter / Gather pattern
- Practice Kubernetes Deployments and Services
- Understand service-to-service communication in a cluster
- Serve as a blog-ready architecture example

---

## 🚀 Tech Stack

- Java 21
- Spring Boot
- WebClient (Reactor)
- Kubernetes (Deployments + Services)
- NodePort (external access)

---
