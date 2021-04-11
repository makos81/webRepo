CREATE TABLE BOOKS_AUD (
                           EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                           EVENT_DATE DATETIME NOT NULL,
                           EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                           BOOK_ID INT(11) NOT NULL,
                           OLD_TITLE VARCHAR(255),
                           NEW_TITLE VARCHAR(255),
                           OLD_PUBYEAR VARCHAR(255),
                           NEW_PUBYEAR VARCHAR(255),
                           OLD_bestseller tinyint(1),
                           NEW_bestseller tinyint(1),
                           PRIMARY KEY (EVENT_ID)
);

DELIMITER $$

CREATE TRIGGER BOOKS_INSERT AFTER INSERT ON BOOKS
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID, NEW_TITLE, NEW_PUBYEAR
    ,NEW_bestseller)
        VALUE(CURTIME(), "INSERT", NEW.BOOK_ID, NEW.TITLE, NEW.PUBYEAR, NEW.bestseller);
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER BOOKS_DELETE AFTER DELETE ON BOOKS
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID)
        VALUE(CURTIME(), "DELETE", OLD.BOOK_ID);
END $$

DELIMITER ;

DELIMITER $$
CREATE TRIGGER BOOKS_UPDATE AFTER UPDATE ON BOOKS
    FOR EACH ROW
BEGIN
    INSERT INTO BOOKS_AUD (EVENT_DATE, EVENT_TYPE, BOOK_ID, NEW_TITLE, OLD_TITLE,
                           NEW_PUBYEAR, OLD_PUBYEAR, NEW_bestseller, OLD_bestseller)
        VALUE(CURTIME(), "UPDATE", NEW.BOOK_ID, NEW.TITLE, OLD.TITLE, NEW.PUBYEAR,
              OLD.PUBYEAR, NEW.bestseller, OLD.bestseller);
END $$
DELIMITER ;


CREATE TABLE READERS_AUD (
                             EVENT_ID INT(11) NOT NULL AUTO_INCREMENT,
                             EVENT_DATE DATETIME NOT NULL,
                             EVENT_TYPE VARCHAR(10) DEFAULT NULL,
                             READER_ID INT(11) NOT NULL,
                             OLD_FIRSTNAME VARCHAR(255),
                             NEW_FIRSTNAME VARCHAR(255),
                             OLD_LASTNAME VARCHAR(255),
                             NEW_LASTNAME VARCHAR(255),
                             OLD_PESELID VARCHAR(11),
                             NEW_PESELID VARCHAR(11),
                             OLD_VIP_LEVEL VARCHAR(20),
                             NEW_VIP_LEVEL VARCHAR(20),
                             PRIMARY KEY (EVENT_ID)
);


DELIMITER $$

CREATE TRIGGER READERS_INSERT AFTER INSERT ON READERS
    FOR EACH ROW
BEGIN
    INSERT INTO READERS_AUD (EVENT_DATE, EVENT_TYPE, READER_ID, NEW_FIRSTNAME, NEW_LASTNAME,
                             NEW_PESELID, NEW_VIP_LEVEL)
        VALUE(CURTIME(), "INSERT", NEW.READER_ID, NEW.FIRSTNAME, NEW.LASTNAME, NEW.PESELID, NEW.VIP_LEVEL);
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER READERS_DELETE AFTER DELETE ON READERS
    FOR EACH ROW
BEGIN
    INSERT INTO READERS_AUD (EVENT_DATE, EVENT_TYPE, READER_ID)
        VALUE(CURTIME(), "DELETE", OLD.READER_ID);
END $$

DELIMITER ;

DELIMITER $$

CREATE TRIGGER READERS_UPDATE AFTER UPDATE ON READERS
    FOR EACH ROW
BEGIN
    INSERT INTO readers_aud (EVENT_DATE, EVENT_TYPE, READER_ID, NEW_FIRSTNAME, OLD_FIRSTNAME, NEW_LASTNAME,
                             OLD_LASTNAME, NEW_PESELID, OLD_PESELID, NEW_VIP_LEVEL, OLD_VIP_LEVEL)
        VALUE(CURTIME(), "UPDATE", NEW.READER_ID, NEW.FIRSTNAME, OLD.FIRSTNAME, NEW.LASTNAME,
              OLD.LASTNAME, NEW.PESELID, OLD.PESELID, NEW.VIP_LEVEL, OLD_VIP_LEVEL);
END $$
DELIMITER ;