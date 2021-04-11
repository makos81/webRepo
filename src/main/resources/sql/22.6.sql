/*
 Napisz skrypt SQL dodający indeksy do tabel BOOKS oraz READERS, które przyspieszą wyszukiwanie po imieniu i
 nazwisku czytelnika oraz po tytule książki. W skrypcie zawrzyj również instrukcję wyświetlającą plan zapytania
 wybierającego z tabeli READERS czytelników o imieniu "John". Wyświetl plan zapytania przed dodaniem indeksu
 oraz po dodaniu indeksu.
 */

explain
select * from READERS
where FIRSTNAME = "John";

CREATE INDEX BOOK_INDX ON BOOKS (TITLE);
CREATE INDEX READERS_INDX ON READERS(FIRSTNAME, LASTNAME);

explain
select * from READERS
where FIRSTNAME = "John";
