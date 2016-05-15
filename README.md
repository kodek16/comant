# comant
A course management tool that aims to be easy, beautiful and non-intrusive.

## Goals

Helps various course organizers to manage their students, lections and tests.
Is flexible enough to be used in small short-term courses as well as in
ordinary schools.

## Features

- User accounts for students and teachers.
- Student groups for convenient progress tracking.
- Lections CRUD support (Markdown?)
- Online tests CRUD support:
  - Closed questions, open questions with fuzzy answer matching, open questions
   graded manually by teacher.
  - Anti-cheat measures, primarily focus watching.
- Classes history support
- Grading support: grades can be given for classes, for tests, and for any
 other work manually.
- Individual student pages for multiple courses progress tracking.

## Philosophy

- Non-intrusive: doesn't try to take control of ALL your work, you choose what
 features you use and how you use them.
- Simple: less controls by default is better (of course you can enable more).
- Beautiful: pleasant to look at.
- Decentralized: can be installed to any local server.
- Open-source: duh.

## Building
Use `./gradlew :comant-site:build` for building executable JAR.