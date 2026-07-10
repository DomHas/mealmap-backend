# mealmap-backend - Projektdokumentation

Modul 295 - Backend für Applikationen realisieren
Autoren: Dominique Hassenpflug, Marina Tschirky

---

## Inhaltsverzeichnis
1. [Projektidee](#1-projektidee)
2. [Anforderungskatalog — User Stories](#2-anforderungskatalog--user-stories)
3. [Klassendiagramm](#3-klassendiagramm)
4. [Testplan und Testdurchführung](#4-testplan-und-testdurchführung)
5. [Installationsanleitung](#5-installationsanleitung)
6. [Hilfestellungen und Quellen](#6-hilfestellungen-und-quellen)

---

## 1. Projektidee


---

## Anforderungskatalog - User Stories

### User Story 1: Rezept ersetllen
**Als** Nutzer 
**moöchte ich** ein neues Rezept mit seinen Zutaten erfassen
**damit ich** meine eigenen Rezepte digital verwalten kann.

**Akzeptanzkriterien:**
- Rezept kann mit Titel, Beschreibung, Kategorie, Zubereitung und mindestens einer Zutat angelegt werden (CREATE)
- Fehlt ein Pflichtfeld oder ist die Zutatenliste leer, erscheint eine verständliche Fehlermeldung. (Validierung)
- Nach erfolgreichem Anlegen erhält das Rezept eine eindeutige ID.

**API-Ablauf:**
````
POST /api/recipes
````
**Beispiel-Request:**
```json
{
  "title": "Tomatensuppe",
  "description": "Einfache, cremige Tomatensuppe",
  "category": "Suppe",
  "instructions": "Tomaten anschwitzen, mit Bruehe aufkochen, pueriseren.",
  "ingredients": [
    { "name": "Tomaten", "amount": 1000, "unit": "g" },
    { "name": "Gemuesebruehe", "amount": 500, "unit": "ml" }
  ]
}
```
**Beispiel-Response (201 Created):**
```json
{
  "id": 7,
  "title": "Tomatensuppe",
  "description": "Einfache, cremige Tomatensuppe",
  "category": "Suppe",
  "instructions": "Tomaten anschwitzen, mit Bruehe aufkochen, pueriseren.",
  "ingredients": [
    { "id": 15, "name": "Tomaten", "amount": 1000, "unit": "g" },
    { "id": 16, "name": "Gemuesebruehe", "amount": 500, "unit": "ml" }
  ]
}
```
---
### User Story 2: Rezepte durchsuchen

**Als** Nutzer
**moechte ich** alle vorhandenen Rezepte abrufen
**damit ich** einen Ueberblick ueber meine Sammlung erhalte.

**Akzeptanzkriterien:**
- Alle Rezepte werden inklusive ihrer Zutaten in einer Liste
  angezeigt (READ)
- Sind keine Rezepte vorhanden, wird eine leere Liste angezeigt
  statt eines Fehlers

**API-Ablauf:**
```
GET /api/recipes
```

**Beispiel-Response (200 OK):**
```json
[
  {
    "id": 1,
    "title": "Blumenkohlcremesuppe",
    "description": "Klassische cremige Suppe aus Blumenkohl",
    "category": "Suppe",
    "instructions": "...",
    "ingredients": [
      { "id": 1, "name": "Butter", "amount": 30, "unit": "g" }
    ]
  }
]
```

---

### User Story 3: Rezeptdetails ansehen

**Als** Nutzer
**moechte ich** ein bestimmtes Rezept ueber seine ID abrufen
**damit ich** nur die Details eines Rezepts sehe, das mich
interessiert.

**Akzeptanzkriterien:**
- Bei gueltiger ID werden alle Details inklusive Zutaten angezeigt
  (READ)
- Bei ungueltiger ID erscheint eine verstaendliche Fehlermeldung
  (Rezept nicht gefunden)

**API-Ablauf:**
```
GET /api/recipes/{id}
```

**Beispiel-Response (200 OK):**
```json
{
  "id": 1,
  "title": "Blumenkohlcremesuppe",
  "description": "Klassische cremige Suppe aus Blumenkohl",
  "category": "Suppe",
  "instructions": "...",
  "ingredients": [
    { "id": 1, "name": "Butter", "amount": 30, "unit": "g" },
    { "id": 2, "name": "Zwiebeln, geschaelt", "amount": 80, "unit": "g" }
  ]
}
```

**Beispiel-Response bei Fehler (404 Not Found):**
```json
{
  "timestamp": "2026-07-04T18:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Recipe mit ID 99 wurde nicht gefunden"
}
```

---
### User Story 4: Rezept bearbeiten

**Als** Nutzer
**moechte ich** ein bestehendes Rezept bearbeiten
**damit ich** Fehler korrigieren oder Mengenangaben anpassen kann.

**Akzeptanzkriterien:**
- Titel, Beschreibung, Kategorie, Zubereitung und Zutatenliste
  koennen vollstaendig aktualisiert werden (UPDATE)
- Ungueltige Eingaben (z. B. leerer Titel) werden mit einer
  Fehlermeldung abgelehnt (Validierung)
- Bei ungueltiger ID erscheint eine verstaendliche Fehlermeldung

**API-Ablauf:**
```
PUT /api/recipes/{id}
```

**Beispiel-Request:**
```json
{
  "title": "Tomatensuppe (verfeinert)",
  "description": "Cremige Tomatensuppe mit einem Hauch Basilikum",
  "category": "Suppe",
  "instructions": "Tomaten anschwitzen, mit Bruehe aufkochen, pueriseren, mit Basilikum verfeinern.",
  "ingredients": [
    { "name": "Tomaten", "amount": 1000, "unit": "g" },
    { "name": "Gemuesebruehe", "amount": 500, "unit": "ml" },
    { "name": "Basilikum, frisch", "amount": 10, "unit": "g" }
  ]
}
```

**Beispiel-Response (200 OK):**
```json
{
  "id": 7,
  "title": "Tomatensuppe (verfeinert)",
  "description": "Cremige Tomatensuppe mit einem Hauch Basilikum",
  "category": "Suppe",
  "instructions": "Tomaten anschwitzen, mit Bruehe aufkochen, pueriseren, mit Basilikum verfeinern.",
  "ingredients": [
    { "id": 20, "name": "Tomaten", "amount": 1000, "unit": "g" },
    { "id": 21, "name": "Gemuesebruehe", "amount": 500, "unit": "ml" },
    { "id": 22, "name": "Basilikum, frisch", "amount": 10, "unit": "g" }
  ]
}
```

**Beispiel-Response bei Fehler (400 Bad Request):**
```json
{
  "timestamp": "2026-07-04T18:35:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "title: Titel darf nicht leer sein"
}
```

---
### User Story 5: Rezept loeschen

**Als** Nutzer
**moechte ich** ein nicht mehr benoetigtes Rezept loeschen
**damit** meine Sammlung uebersichtlich und aktuell bleibt.

**Akzeptanzkriterien:**
- Rezept kann inklusive aller Zutaten geloescht werden (DELETE)
- Bei ungueltiger ID erscheint eine verstaendliche Fehlermeldung
- Nach dem Loeschen ist das Rezept nicht mehr abrufbar

**API-Ablauf:**
```
DELETE /api/recipes/{id}
```

**Beispiel-Response:**
Status `204 No Content` (kein Body)

**Beispiel-Response bei Fehler (404 Not Found):**
```json
{
  "timestamp": "2026-07-04T18:31:00",
  "status": 404,
  "error": "Not Found",
  "message": "Recipe mit ID 99 wurde nicht gefunden"
}
```

---

## 3. Klassendiagramm

![Klassendiagramm](Klassendiagramm_MealMap_backend.png)

---
## 4. Testplan und Testdurchführung
### 4.1 Manuelle Tests (Insomnia)


### 4.2 Unit-Tests

---

## 5. Installationsanleitung

---

## 6. Hilfestellungen und Quellen
