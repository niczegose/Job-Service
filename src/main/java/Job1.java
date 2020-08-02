public class Job1 {

    /*
    1)
Stworz Klase OfertaPracy
klasa zawiera następujące pola:
- miasto
- oferowana pensja
- oczekiwane minimalne doświadczenie
- kolekcja oczekiwanych umiejętności (stringi)
Stworz listę OfertPracy a następnie
- wypisz ofertę która oferuje największą pesję
- ile jest ofert z warszawy
- z jakich miast pochodzą oferty?
- jakie jest średnie minimalne doświadzenie
- która z oczekiwanych umiejętności jest najpopularniejsza?
(możliwośc zdobycia 6 punktów)

2) Stworz klasę Zgłoszenie
klasa zawiera następujące pola
- imię i nazwisko kandydata
- jego oczekiwania finansowe
- miasto skąd pochodzi
- telefon kontaktowy
- chętny do relokacji (true/false)
- email z którego nadeszło zgłoszenie
- posiadane doświadczenie
- kolekcja posiadanych umiejętności
Stwórz listę Zgłoszeń a następnie
- ile jest zgłoszeń dla każdego z miast?
- ile jest zgłoszeń dla każdego z email? (być może kandydat z jednego adresu email wysłał kilka aplikacji)
- która z umiejętnośc jest posiadana najczęściej?
- wykonaj wstępnej weryfikacji listy zgłoszeń, tzn: jeśli z tego samego adresu email przyszły zgłoszenia dwóch różnnych osób (różne imie i nazwisko) to znaczy że coś jest nie tak, wypisz wszystkie adresy email które sie nie zgadzają.
(możliwośc zdobycia 6 ptk, ostatnie jest za 2 punkty)

3)
Dla OfertPracy może przyjść wiele zgłoszeń, a jedno zgłoszenie może byc
dla wielu ofert pracy. Będąc rekruterem masz nielada gratkę aby ogarnąc 5 tyś CV na skrzynce pocztowej, dlatego zostałeś poproszony o napisanie prostego algorytmu który by dla każdej oferty wybrał by JEDNEGO (póki co) najlepszego kandydata.
Najlepsze dopasowanie będzie na podstawie ilości punktów oraz pewnych warunów "must have"
a) oferty z miasta X mogą brać pod uwagę tylko kandydatów z miasta X lub kandydatów z innego miasta jeśli kandydat bierze pod uwagę relokację.
b) oczekiwania kandydata nie mogą być wyższe niż stawka w ofercie pracy, chyba że kandydat posiada wszystkie wymagane umiejętności w swoim zgłoszeniu, wtedy możemy dopóścić limit 120% wynagrodzenia z ogłoszenia, ale nie więcej.
c) dodatkowe punkty przyznawane są za każdo razowe dopasowanie technologii
tzn: jeśli oferta wymaga: Java, Spring, Hibernate, Junit, a kandydat zna: Java, Spring, Hibernate to mamy 75% dopasowania.
napisz metodę public Zgloszenie znajdzNajlepsze(OfertaPracy oferta, List<Zgloszenie> zgloszenia) ktora dla podanej oferty znajdze najlepiej dopasowane zgloszenie lub zroci null jesli nie ma zadego dopasowanego zgloszenia*/
}
