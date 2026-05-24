# Security Policy

## Repository Purpose

This is a **public interview/demo repository** maintained by CatalystOne. It contains intentionally incomplete Spring Boot + Java code used as a technical exercise for candidates.

- No production data, customer data, or internal infrastructure details are intentionally included.
- The database is an in-memory H2 instance with synthetic seed data only.
- No credentials, API keys, or secrets are present in the codebase.

## Reporting a Vulnerability

If you discover a security issue in this repository (e.g. accidental credential exposure, sensitive data leak), please **do not open a public GitHub issue**.

Instead, report it responsibly via email:

**security@catalystone.com**

Please include:
- A description of the issue
- Steps to reproduce or the specific file/commit reference
- Your assessment of the potential impact

We aim to acknowledge reports within **5 business days**.

## Scope

This repository is intentionally public and is used solely for interview and demonstration purposes. It is **not connected to any production system or internal infrastructure**.

## Known Limitations

- Git commit history contains real employee email addresses (from the repository's development history) as commit author metadata. These are not secrets but are personal data. If this is a concern, please contact the maintainers.
- The `spring.h2.console.enabled=true` setting is intentional for the local/interview development experience. The H2 console is **not exposed in any production deployment**.

