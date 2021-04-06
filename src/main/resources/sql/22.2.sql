/*
 Napisz skrypt, który do tabeli BOOKS doda kolumnę BESTSELLER typu BOOLEAN. Kolumna ta powinna przyjmować wartość true,
 jeżeli liczba wypożyczeń książki przekroczy 2. Pamiętaj o tym, że początkowa wartość dla kolumny BESTSELLER powinna
 wynosić 0.
Do skryptu dodaj procedurę UpdateBestsellers(), która zaktualizuje kolumnę BESTSELLER przy każdej książce w tabeli
 BOOKS na podstawie danych zawartych w tabeli RENTS. Użyj w tym celu kursora.
 */

alter table books add bestseller boolean;

delimiter $$

drop procedure if exists UpdateBestsellers;

create procedure UpdateBestsellers()
begin
    declare rentCounts, book int;
    DECLARE FINISHED INT DEFAULT 0;
    DECLARE ALL_READERS CURSOR FOR SELECT book_id FROM books;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET FINISHED = 1;
    OPEN ALL_READERS;
    WHILE (FINISHED = 0) DO
    FETCH ALL_READERS INTO book;
    IF (FINISHED = 0) THEN
        SELECT COUNT(*) FROM rents
        WHERE book_id = book
        INTO rentCounts;

        if rentCounts > 2 then
            update books set books.bestseller = TRUE
            where book_id = book;
            commit;
        else
            update books set books.bestseller = FALSE
            where book_id = book;
            commit;
        end if;
    END IF;
        END WHILE;

    CLOSE ALL_READERS;
end $$

delimiter ;

call UpdateBestsellers();

select * from books;