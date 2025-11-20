# TimeWarp - Architettura dell'App

## Panoramica

TimeWarp è un'app Android nativa sviluppata in Kotlin che permette di gestire le timbrature degli orari di lavoro.

## Componenti Principali

### 1. Data Layer

#### TimeEntry (Data Model)
- `id`: Identificativo univoco della timbratura
- `entryTime`: Timestamp dell'orario di entrata
- `exitTime`: Timestamp dell'orario di uscita (opzionale)
- `department`: Reparto di lavoro
- `date`: Data della timbratura (formato yyyy-MM-dd)

Metodi:
- `getHoursWorked()`: Calcola le ore lavorate
- `getFormattedEntryTime()`: Formatta l'orario di entrata (HH:mm)
- `getFormattedExitTime()`: Formatta l'orario di uscita (HH:mm)
- `isActive()`: Verifica se la timbratura è ancora attiva

#### TimeEntryManager
Gestisce la persistenza e le operazioni CRUD delle timbrature.

- **Persistenza**: SharedPreferences + Gson per serializzazione JSON
- **Metodi principali**:
  - `clockIn(department)`: Crea una nuova timbratura di entrata
  - `clockOut(entryId)`: Registra l'uscita per una timbratura attiva
  - `getActiveEntry()`: Recupera l'eventuale timbratura attiva
  - `getTodayEntries()`: Recupera tutte le timbrature del giorno corrente
  - `getEntriesForDate(date)`: Recupera le timbrature di una data specifica

### 2. Presentation Layer

#### MainActivity
Activity principale che gestisce l'interfaccia utente.

**Funzionalità**:
- Selezione del reparto tramite dropdown
- Pulsante "Timbratura Entrata" per iniziare un nuovo turno
- Pulsante "Timbratura Uscita" per terminare il turno corrente
- Visualizzazione delle timbrature del giorno in una RecyclerView
- Esportazione delle timbrature in Excel

**Gestione permessi**:
- Richiede permessi di scrittura per Android < 10 (API 29)
- Per Android 10+ usa Scoped Storage (Download directory)

#### TimeEntryAdapter
Adapter per RecyclerView che visualizza le timbrature.

**Visualizza per ogni entry**:
- Reparto di lavoro
- Orario di entrata
- Orario di uscita
- Ore lavorate
- Indicatore "In corso" per timbrature attive

### 3. Export Layer

#### ExcelExporter
Gestisce l'esportazione dei dati in formato Excel (.xlsx).

**Funzionalità**:
- Crea un file Excel con Apache POI
- Struttura del file:
  - Header: Reparto | Orario Entrata | Orario Uscita | Ore Lavorate
  - Righe dati: Una per ogni timbratura
  - Riga totale: Somma delle ore lavorate
- Formattazione: Header in grassetto, colonne auto-dimensionate
- Salvataggio: Directory Download del dispositivo

## Flusso dell'Applicazione

```
1. Apertura App
   ↓
2. Caricamento timbrature da SharedPreferences
   ↓
3. Visualizzazione timbrature del giorno
   ↓
4. Utente seleziona reparto
   ↓
5a. TIMBRATURA ENTRATA                    5b. TIMBRATURA USCITA
    - Crea TimeEntry con timestamp        - Trova entry attiva
    - exitTime = null                     - Aggiorna exitTime
    - Salva in SharedPreferences          - Salva in SharedPreferences
    - Aggiorna UI                         - Aggiorna UI
    ↓
6. ESPORTA EXCEL
   - Recupera timbrature del giorno
   - Genera file .xlsx con Apache POI
   - Salva in Download/Timbrature_YYYY-MM-DD.xlsx
```

## Dipendenze Tecniche

### AndroidX
- `core-ktx`: Estensioni Kotlin per Android
- `appcompat`: Compatibilità con versioni precedenti
- `constraintlayout`: Layout flessibile
- `recyclerview`: Lista scrollabile
- `cardview`: Card Material Design

### Material Components
- `material`: Componenti Material Design (Button, TextInputLayout, Card)

### Apache POI
- `poi:5.2.3`: Core library per manipolazione file Office
- `poi-ooxml:5.2.3`: Supporto per formato .xlsx

### Gson
- `gson:2.10.1`: Serializzazione/Deserializzazione JSON

## Requisiti di Sistema

- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 34 (Android 14)
- **Kotlin**: 1.9.0
- **Gradle Plugin**: 8.1.0

## Localizzazione

L'app è completamente localizzata in italiano:
- Tutte le stringhe UI in `res/values/strings.xml`
- Formati data/ora usando `Locale.ITALIAN`
- Numeri decimali formattati con convenzioni italiane (virgola come separatore)

## Sicurezza e Privacy

- **Dati locali**: Tutti i dati sono salvati localmente sul dispositivo
- **Nessuna connessione internet**: L'app funziona completamente offline
- **Permessi minimi**: Solo permessi di scrittura per esportazione Excel
- **Nessuna telemetria**: Nessun dato viene inviato a server esterni
