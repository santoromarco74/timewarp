# TimeWarp - Panoramica dell'App

## 📱 Interfaccia Utente

### Schermata Principale

```
┌─────────────────────────────────────────┐
│  TimeWarp                            ⋮  │
├─────────────────────────────────────────┤
│                                         │
│  📋 Timbrature di Oggi                  │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ Seleziona Reparto              ▼ │ │
│  │ Produzione                       │ │
│  └───────────────────────────────────┘ │
│                                         │
│  ┌──────────────────┐ ┌──────────────┐ │
│  │ ⏱  TIMBRATURA   │ │ ⏹ TIMBRATURA │ │
│  │    ENTRATA      │ │    USCITA    │ │
│  └──────────────────┘ └──────────────┘ │
│                                         │
│  ┌───────────────────────────────────┐ │
│  │ 💾 ESPORTA EXCEL                 │ │
│  └───────────────────────────────────┘ │
│                                         │
│  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━  │
│                                         │
│  ╔═══════════════════════════════════╗ │
│  ║ 🏢 Produzione                     ║ │
│  ║                                   ║ │
│  ║ Orario Entrata    09:00          ║ │
│  ║ Orario Uscita     18:00          ║ │
│  ║ Ore Lavorate      9.00 ore       ║ │
│  ╚═══════════════════════════════════╝ │
│                                         │
│  ╔═══════════════════════════════════╗ │
│  ║ 🏢 Manutenzione                   ║ │
│  ║                                   ║ │
│  ║ Orario Entrata    14:30          ║ │
│  ║ Orario Uscita     -              ║ │
│  ║ Ore Lavorate      -              ║ │
│  ║ ⚡ In corso...                    ║ │
│  ╚═══════════════════════════════════╝ │
│                                         │
└─────────────────────────────────────────┘
```

## 🎯 Flusso di Utilizzo

### 1. Inizio Giornata

```
Utente apre l'app
       ↓
Seleziona reparto "Produzione"
       ↓
Preme "TIMBRATURA ENTRATA"
       ↓
✅ Sistema registra timestamp 09:00
       ↓
Mostra card con:
- Reparto: Produzione
- Entrata: 09:00
- Uscita: -
- Stato: "In corso..."
```

### 2. Fine Turno

```
Utente apre l'app
       ↓
Preme "TIMBRATURA USCITA"
       ↓
✅ Sistema registra timestamp 18:00
       ↓
Calcola ore: 18:00 - 09:00 = 9.00 ore
       ↓
Aggiorna card con:
- Uscita: 18:00
- Ore: 9.00 ore
- Rimuove "In corso..."
```

### 3. Esportazione Excel

```
Utente preme "ESPORTA EXCEL"
       ↓
Sistema richiede permessi (se necessario)
       ↓
Crea file Excel:
┌──────────────────────────────────────┐
│ Timbrature_2025-11-20.xlsx          │
├──────────┬──────────┬──────────┬────┤
│ Reparto  │ Entrata  │ Uscita   │ Ore│
├──────────┼──────────┼──────────┼────┤
│ Produz.  │ 09:00    │ 18:00    │9.00│
│ Manut.   │ 14:30    │ 16:00    │1.50│
├──────────┴──────────┴──────────┼────┤
│                    TOTALE:     │10.5│
└────────────────────────────────┴────┘
       ↓
Salva in Download/
       ↓
✅ Mostra messaggio: "Excel esportato con successo"
```

## 🎨 Elementi Visivi

### Colori Principali
- **Primario**: Blu #2196F3 (pulsanti, header)
- **Accent**: Verde #4CAF50 (indicatori attivi, ore)
- **Sfondo**: Bianco #FFFFFF
- **Testo**: Nero #000000

### Tipografia
- **Titolo**: 24sp, grassetto
- **Reparto**: 18sp, grassetto, blu
- **Etichette**: 12sp, grigio
- **Valori**: 16sp, grassetto

### Componenti Material
- **MaterialButton**: Pulsanti con elevazione
- **MaterialCardView**: Card per ogni timbratura
- **TextInputLayout**: Dropdown reparti
- **RecyclerView**: Lista scrollabile

## 📊 Stati dell'Interfaccia

### Stato 1: Nessuna Timbratura
```
┌─────────────────────────────┐
│                             │
│    📋 Nessuna timbratura    │
│       registrata            │
│                             │
└─────────────────────────────┘
```

### Stato 2: Timbratura Attiva
```
┌─────────────────────────────┐
│ Pulsante "Entrata": ⛔ OFF  │
│ Pulsante "Uscita":  ✅ ON   │
└─────────────────────────────┘
```

### Stato 3: Nessuna Timbratura Attiva
```
┌─────────────────────────────┐
│ Pulsante "Entrata": ✅ ON   │
│ Pulsante "Uscita":  ⛔ OFF  │
└─────────────────────────────┘
```

## 📁 File Excel Generato

### Struttura

```
Timbrature_2025-11-20.xlsx
│
└── Sheet: "Timbrature 2025-11-20"
    │
    ├── Header Row (Bold)
    │   ├── Reparto
    │   ├── Orario Entrata
    │   ├── Orario Uscita
    │   └── Ore Lavorate
    │
    ├── Data Rows
    │   ├── Produzione │ 09:00 │ 18:00 │ 9.00
    │   ├── Logistica  │ 08:30 │ 12:30 │ 4.00
    │   └── Qualità    │ 13:00 │ 17:30 │ 4.50
    │
    └── Total Row (Bold)
        └── (blank) │ (blank) │ TOTALE: │ 17.50
```

### Esempio Reale

| Reparto        | Orario Entrata | Orario Uscita | Ore Lavorate |
|----------------|----------------|---------------|--------------|
| Produzione     | 08:00          | 12:00         | 4.00         |
| Produzione     | 13:00          | 17:00         | 4.00         |
| Manutenzione   | 09:00          | 11:30         | 2.50         |
|                |                | **TOTALE:**   | **10.50**    |

## 🔔 Messaggi di Feedback

### Successo
- ✅ "Entrata registrata"
- ✅ "Uscita registrata"
- ✅ "Excel esportato con successo"

### Errori
- ⚠️ "Seleziona un reparto"
- ⚠️ "C'è già una timbratura attiva"
- ⚠️ "Nessuna timbratura attiva"
- ⚠️ "Nessuna timbratura da esportare"
- ❌ "Errore durante l'esportazione"

### Permessi
- 🔒 "Permesso di scrittura richiesto"

## 🏗️ Architettura Visuale

```
┌─────────────────────────────────────────┐
│         MainActivity (UI Layer)         │
│  ┌─────────────┐  ┌──────────────────┐ │
│  │   Buttons   │  │   RecyclerView   │ │
│  └──────┬──────┘  └────────┬─────────┘ │
└─────────┼──────────────────┼───────────┘
          │                  │
          ↓                  ↓
┌─────────────────────────────────────────┐
│      TimeEntryManager (Data Layer)      │
│  ┌─────────────────────────────────┐   │
│  │     TimeEntry (Data Model)      │   │
│  └─────────────────────────────────┘   │
└─────────┬───────────────────────────────┘
          │
          ↓
┌─────────────────────────────────────────┐
│     SharedPreferences + Gson (DB)       │
└─────────────────────────────────────────┘

┌─────────────────────────────────────────┐
│    ExcelExporter (Export Layer)         │
│         Apache POI Library              │
└─────────┬───────────────────────────────┘
          │
          ↓
┌─────────────────────────────────────────┐
│    Download/Timbrature_*.xlsx           │
└─────────────────────────────────────────┘
```

## 💡 Vantaggi dell'Interfaccia

1. **Semplicità**: Due pulsanti principali, chiara distinzione
2. **Feedback Immediato**: Ogni azione mostra un risultato
3. **Stato Visibile**: Facile vedere quali timbrature sono attive
4. **Navigazione Zero**: Tutto in una schermata
5. **Material Design**: Interfaccia moderna e familiare
6. **Responsive**: Funziona su tutti i dispositivi Android

## 📐 Dimensioni

- **Min Width**: 320dp (smartphone piccoli)
- **Optimal Width**: 360dp+ (smartphone moderni)
- **Card Height**: ~120dp per timbratura
- **Button Height**: 48dp (touch target standard)
- **Padding**: 16dp (standard Material)

## 🎯 Accessibilità

- ✅ Pulsanti con dimensioni touch target minime (48dp)
- ✅ Contrasto colori conforme WCAG
- ✅ Etichette descrittive
- ✅ Supporto TalkBack (screen reader)
- ✅ Testi leggibili (min 12sp)
