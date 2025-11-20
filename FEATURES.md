# TimeWarp - Funzionalità Complete

## 🎯 Caratteristiche Principali

### 1. ⏰ Timbratura Orari di Lavoro

L'app permette di registrare con precisione gli orari di entrata e uscita dal lavoro:

- **Timbratura Entrata**: 
  - Un click per registrare l'orario di inizio turno
  - Timestamp preciso al millisecondo
  - Associazione automatica del reparto selezionato
  
- **Timbratura Uscita**:
  - Registra l'orario di fine turno
  - Calcolo automatico delle ore lavorate
  - Aggiornamento istantaneo della visualizzazione

### 2. 🏢 Gestione Reparti

Sistema completo per tracciare il reparto di lavoro:

- **Reparti predefiniti**:
  - Produzione
  - Amministrazione
  - Logistica
  - Manutenzione
  - Qualità
  - Vendite

- **Selezione facile**: Menu a tendina Material Design
- **Memorizzazione**: Ogni timbratura è associata al suo reparto

### 3. 📊 Calcolo Ore Lavorate

Sistema automatico di calcolo delle ore:

- **Calcolo in tempo reale**: Ore lavorate calcolate automaticamente
- **Formato decimale**: Ore mostrate in formato decimale (es: 8.50 ore)
- **Precisione**: Calcolo al minuto con arrotondamento a 2 decimali
- **Totali giornalieri**: Somma automatica di tutte le ore del giorno

### 4. 📈 Visualizzazione Timbrature

Interfaccia chiara e intuitiva:

- **Lista cronologica**: Tutte le timbrature del giorno in ordine inverso
- **Card Material Design**: Ogni timbratura in una card separata
- **Informazioni complete**:
  - Reparto (evidenziato in blu)
  - Orario entrata
  - Orario uscita
  - Ore lavorate
  - Stato "In corso" per timbrature attive

- **Stato vuoto**: Messaggio chiaro quando non ci sono timbrature

### 5. 📑 Esportazione Excel

Funzionalità completa di export in formato Excel:

- **Formato**: File .xlsx (Excel moderno)
- **Contenuto**:
  - Header formattato in grassetto
  - Colonne: Reparto | Orario Entrata | Orario Uscita | Ore Lavorate
  - Una riga per ogni timbratura
  - Riga finale con totale ore giornaliere
  
- **Posizione**: File salvato in Download/Timbrature_YYYY-MM-DD.xlsx
- **Formattazione automatica**: Colonne auto-dimensionate
- **Localizzazione**: Numeri decimali con formato italiano (virgola)

### 6. 🇮🇹 Localizzazione Italiana

Interfaccia completamente in italiano:

- **Tutti i testi**: Pulsanti, messaggi, etichette in italiano
- **Formati data/ora**: Formato italiano (gg/mm/aaaa, HH:mm)
- **Numeri**: Decimali con virgola (8,50 invece di 8.50)
- **Messaggi di feedback**: Conferme e errori in italiano

## 🛠️ Funzionalità Tecniche

### Persistenza Dati
- **SharedPreferences**: Salvataggio locale sicuro
- **Serializzazione JSON**: Formato efficiente con Gson
- **Caricamento automatico**: Dati ripristinati all'avvio

### Gestione Permessi
- **Richiesta dinamica**: Permessi richiesti solo quando necessario
- **Compatibilità**: Supporto Android 7.0+ (API 24+)
- **Scoped Storage**: Utilizzo corretto per Android 10+

### UI/UX
- **Material Design 3**: Interfaccia moderna e coerente
- **Responsive**: Layout adattivo per diverse dimensioni schermo
- **Feedback visivo**: Messaggi toast per ogni azione
- **Stati pulsanti**: Abilitazione/disabilitazione intelligente

### Validazioni
- **Timbratura unica**: Impossibile avere più timbrature attive
- **Selezione reparto**: Controllo reparto selezionato prima della timbratura
- **Controllo dati**: Verifiche prima dell'esportazione Excel

## 📱 Flussi di Utilizzo

### Scenario 1: Giornata Lavorativa Standard

```
Mattino (09:00)
├── Apri app
├── Seleziona "Produzione"
├── Premi "Timbratura Entrata"
└── Conferma: "Entrata registrata"

Pomeriggio (18:00)
├── Apri app
├── Premi "Timbratura Uscita"
└── Conferma: "Uscita registrata"
    └── Visualizzazione: 9.00 ore lavorate

Sera
├── Premi "Esporta Excel"
└── File creato: Download/Timbrature_2025-11-20.xlsx
```

### Scenario 2: Cambio Reparto

```
Mattino (09:00)
├── Seleziona "Produzione"
├── Premi "Timbratura Entrata"
└── Lavoro in produzione

Mezzogiorno (12:30)
├── Premi "Timbratura Uscita"
├── Seleziona "Manutenzione"
├── Premi "Timbratura Entrata"
└── Lavoro in manutenzione

Sera (18:00)
├── Premi "Timbratura Uscita"
└── Totale: 2 timbrature, reparti diversi
```

## 🔒 Sicurezza e Privacy

- ✅ **Dati locali**: Tutto salvato solo sul dispositivo
- ✅ **Nessuna connessione**: Funzionamento 100% offline
- ✅ **Nessuna telemetria**: Zero tracciamento utente
- ✅ **Permessi minimi**: Solo scrittura per export
- ✅ **Open Source**: Codice verificabile

## 📦 Specifiche Tecniche

- **Linguaggio**: Kotlin 1.9.0
- **Min SDK**: 24 (Android 7.0 Nougat)
- **Target SDK**: 34 (Android 14)
- **Architettura**: MVVM-like con Manager pattern
- **UI**: Material Components 3
- **Database**: SharedPreferences + JSON
- **Export**: Apache POI 5.2.3

## 🎨 Design

- **Colori**: Schema blu e verde (Material)
- **Tipografia**: Roboto (system default)
- **Icone**: Material Icons
- **Layout**: LinearLayout e ConstraintLayout
- **Card**: MaterialCardView con elevation

## 📊 Capacità

- **Timbrature**: Illimitate (limitato solo dallo storage)
- **Storage**: ~1KB per 100 timbrature
- **Performance**: Istantaneo anche con migliaia di record
- **Export**: Gestisce file Excel di qualsiasi dimensione

## 🚀 Vantaggi

1. **Semplicità**: Interfaccia intuitiva, nessuna curva di apprendimento
2. **Velocità**: Timbratura in un click, nessun caricamento
3. **Affidabilità**: Dati salvati istantaneamente
4. **Compatibilità**: File Excel apribili su qualsiasi PC
5. **Privacy**: Nessun dato lascia il dispositivo
6. **Gratuito**: Nessun costo, nessun abbonamento
