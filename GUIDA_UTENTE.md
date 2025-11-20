# TimeWarp - Guida Utente

## Installazione

1. Scarica il file APK dell'app
2. Abilita l'installazione da "Fonti Sconosciute" nelle impostazioni del dispositivo
3. Installa l'app
4. Al primo avvio, concedi i permessi richiesti per l'esportazione Excel

## Come Utilizzare l'App

### 1. Timbratura Entrata (Inizio Turno)

1. Apri l'app TimeWarp
2. Nel menu a tendina **"Seleziona Reparto"**, scegli il tuo reparto di lavoro:
   - Produzione
   - Amministrazione
   - Logistica
   - Manutenzione
   - Qualità
   - Vendite

3. Premi il pulsante **"TIMBRATURA ENTRATA"**
4. Vedrai un messaggio di conferma "Entrata registrata"
5. La tua timbratura apparirà nella lista sottostante con:
   - Nome del reparto
   - Orario di entrata
   - Indicazione "In corso..." (perché non hai ancora timbrato l'uscita)

### 2. Timbratura Uscita (Fine Turno)

1. Quando hai finito il turno, apri nuovamente l'app
2. Premi il pulsante **"TIMBRATURA USCITA"**
3. Vedrai un messaggio di conferma "Uscita registrata"
4. La timbratura nella lista verrà aggiornata mostrando:
   - Orario di uscita
   - Ore lavorate (calcolate automaticamente)
   - L'indicazione "In corso..." scomparirà

**Nota**: Puoi avere solo una timbratura attiva alla volta. Il pulsante "Timbratura Entrata" sarà disabilitato finché non registri l'uscita.

### 3. Visualizzare le Timbrature

La schermata principale mostra tutte le timbrature del giorno corrente in ordine cronologico inverso (la più recente in alto).

Per ogni timbratura vedrai:
- **Reparto**: Il reparto in cui hai lavorato
- **Orario Entrata**: L'ora in cui hai timbrato l'entrata
- **Orario Uscita**: L'ora in cui hai timbrato l'uscita (o "-" se ancora attivo)
- **Ore Lavorate**: Il totale delle ore lavorate (o "-" se ancora attivo)

### 4. Esportare in Excel

1. Alla fine della giornata, premi il pulsante **"ESPORTA EXCEL"**
2. L'app creerà un file Excel con tutte le timbrature del giorno
3. Il file verrà salvato nella cartella **Download** del tuo dispositivo
4. Nome del file: `Timbrature_YYYY-MM-DD.xlsx` (esempio: Timbrature_2025-11-20.xlsx)

**Contenuto del file Excel**:
- Colonna 1: Reparto
- Colonna 2: Orario Entrata (formato HH:mm)
- Colonna 3: Orario Uscita (formato HH:mm)
- Colonna 4: Ore Lavorate (formato decimale, es: 8.50)
- Ultima riga: **TOTALE** con la somma totale delle ore lavorate nella giornata

### 5. Condividere il File Excel

Dopo l'esportazione:
1. Apri l'app **File** o **Gestione File** del tuo dispositivo
2. Vai nella cartella **Download**
3. Trova il file `Timbrature_YYYY-MM-DD.xlsx`
4. Puoi:
   - Inviarlo via email
   - Caricarlo su Google Drive
   - Condividerlo via WhatsApp
   - Trasferirlo su PC tramite USB

## Domande Frequenti (FAQ)

### Cosa succede se dimentico di timbrare l'uscita?

La timbratura rimarrà "attiva" e mostrerà l'indicazione "In corso...". Non potrai creare nuove timbrature finché non completi quella attiva. Timbra l'uscita appena possibile.

### Posso modificare una timbratura dopo averla creata?

No, attualmente l'app non supporta la modifica delle timbrature. Assicurati di timbrare correttamente.

### Posso vedere le timbrature dei giorni precedenti?

Nella versione attuale, la schermata principale mostra solo le timbrature del giorno corrente. Tuttavia, tutte le timbrature sono salvate e possono essere esportate in Excel.

### Cosa succede se cambio reparto durante la giornata?

Puoi creare più timbrature nello stesso giorno con reparti diversi. Ogni coppia entrata/uscita rappresenta un turno separato.

### I miei dati sono al sicuro?

Sì! Tutti i dati sono salvati solo sul tuo dispositivo. L'app non invia nessuna informazione a server esterni e funziona completamente offline.

### Posso usare l'app su più dispositivi?

L'app salva i dati localmente, quindi ogni dispositivo avrà il proprio set di timbrature. Per sincronizzare i dati, dovrai esportare e condividere manualmente i file Excel.

### Quanto spazio occupa l'app?

L'app è molto leggera, circa 5-10 MB. I dati delle timbrature occupano uno spazio minimo (pochi KB).

## Risoluzione Problemi

### L'esportazione Excel non funziona

1. Verifica di aver concesso i permessi di scrittura all'app
2. Controlla che ci sia spazio disponibile sul dispositivo
3. Verifica che la cartella Download sia accessibile

### Il pulsante "Timbratura Entrata" è disabilitato

C'è già una timbratura attiva. Devi prima completarla timbrandone l'uscita.

### Non vedo le mie timbrature

Le timbrature mostrate sono solo quelle del giorno corrente. Verifica la data del dispositivo.

## Supporto

Per assistenza o segnalazione bug, contatta il supporto o apri una issue su GitHub.

---

**Versione App**: 1.0  
**Data**: Novembre 2025
