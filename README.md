# TimeWarp - App Android per la Gestione delle Timbrature

TimeWarp è un'applicazione Android per la gestione delle timbrature degli orari di lavoro.

## Funzionalità

- ✅ Timbratura entrata e uscita
- ✅ Tracciamento ore lavorate
- ✅ Gestione reparto di lavoro
- ✅ Esportazione dati in Excel giornaliero

## Caratteristiche

- **Timbratura Entrata**: Registra l'orario di inizio lavoro
- **Timbratura Uscita**: Registra l'orario di fine lavoro
- **Calcolo Automatico**: Calcola automaticamente le ore lavorate
- **Reparti**: Seleziona il reparto di lavoro per ogni timbratura
- **Esportazione Excel**: Esporta tutte le timbrature giornaliere in un file Excel (.xlsx)
- **Interfaccia Italiana**: Completamente localizzata in italiano

## Requisiti

- Android 7.0 (API 24) o superiore
- Permessi di scrittura per l'esportazione Excel

## Struttura del Progetto

```
timewarp/
├── app/
│   ├── build.gradle
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/timewarp/app/
│           │   ├── MainActivity.kt
│           │   ├── TimeEntry.kt
│           │   ├── TimeEntryManager.kt
│           │   ├── TimeEntryAdapter.kt
│           │   └── ExcelExporter.kt
│           └── res/
│               ├── layout/
│               ├── values/
│               └── mipmap/
├── build.gradle
└── settings.gradle
```

## Come Usare

1. **Seleziona il Reparto**: Scegli il reparto di lavoro dal menu a tendina
2. **Timbratura Entrata**: Premi "Timbratura Entrata" all'inizio del turno
3. **Timbratura Uscita**: Premi "Timbratura Uscita" alla fine del turno
4. **Visualizza Storico**: Tutte le timbrature del giorno vengono visualizzate nella lista
5. **Esporta Excel**: Premi "Esporta Excel" per salvare il file nella cartella Download

## Dipendenze

- AndroidX Core KTX
- Material Components
- RecyclerView
- Apache POI (per generazione Excel)
- Gson (per persistenza dati)

## Build

Per compilare il progetto:

**Linux/Mac:**
```bash
./gradlew build
```

**Windows:**
```cmd
gradlew build
```

Per installare su dispositivo:

**Linux/Mac:**
```bash
./gradlew installDebug
```

**Windows:**
```cmd
gradlew installDebug
```
