# Studiehanterare

En enkel Java-applikation för att spåra studietid för olika kurser. Programmet låter användaren registrera studietid, visa total tid för enskilda kurser, och sammanställa statistik för den aktuella veckan. Data sparas automatiskt till en fil baserat på veckan och laddas in automatiskt när programmet startas igen.

## Funktioner
- **Registrera studietid**: Lägg till studietid för olika kurser.
- **Veckostatistik**: Laddar och sparar studietid baserat på den aktuella veckan.
- **Filhantering**: Data sparas i en textfil med veckospecifika namn.
- **Kursöversikt**: Visa sammanställd studietid för alla kurser.

---

## Installation och körning

### Steg 1: Klona projektet
Kopiera projektet från GitHub genom att använda följande kommando:
```bash
git clone https://github.com/padthe/StudyTracker.git
```
### Steg 2: Navigera till projektmappen
Gå till projektmappen där koden ligger genom att köra:
```bash
cd StudyTracker
```
### Steg 3: Lägg till egna kurser
Applikationen använder en enum för att definiera de tillgängliga kurserna. För att anpassa applikationen till dina behov:

Öppna filen Courses.java i valfri textredigerare eller IDE.
Lägg till dina egna kurser i Courses-listan. Till exempel:
```bash
public enum Courses {
    Matematik,
    Programmering,
    Historia
}
```
Spara filen.

### Steg 4: Kompilera filerna
För att köra applikationen behöver du först kompilera. Kör följande kommando:

```bash
javac *.java
```
Detta kompilerar alla .java - filer i katalogen och går sedan att köras.

### Steg 5: Kör programmet
Starta sedan applikationen genom att skriva:
```bash
java Main
```

