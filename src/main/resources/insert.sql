-- enth‰lt testdaten um die funktionalit‰t des prototypen vorzuf¸hren
-- categories are static defiend by given list
-- Technik, Moebel, Lebensmittel, Kleidung
INSERT INTO CATEGORY (Category_ID, Name) VALUES (0, 'alle');
INSERT INTO CATEGORY (Category_ID, Name) VALUES (1, 'technik');
INSERT INTO CATEGORY (Category_ID, Name) VALUES (2, 'moebel');
INSERT INTO CATEGORY (Category_ID, Name) VALUES (3, 'lebensmittel');
INSERT INTO CATEGORY (Category_ID, Name) VALUES (4, 'bekleidung');

-- sub categories are static defiend by given list
-- sub category: fernseher, laptop
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (0, 0, 'alle');
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (1, 1, 'fernseher');
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (2, 1, 'notebook');
-- sub category: tisch, bett
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (3, 2, 'tisch');
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (4, 2, 'bett');
-- sub category: getraenk, fleisch, backware
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (5, 3, 'getraenk');
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (6, 3, 'fleisch');
-- sub category: t-shirt, hose
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (7, 4, 't-shirt');
INSERT INTO SUB_CATEGORY (Sub_C_ID, Category_ID, Name) VALUES (8, 4, 'hose');

-- dummy dealers entries are static 
INSERT INTO DEALER (Dealer_ID, Name) VALUES (0, 'alle');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (1, 'aldi', 'teststraﬂe 1', '12345', 'teststadt1');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (2, 'lidl', 'teststraﬂe 2', '12345', 'teststadt2');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (3, 'rewe', 'teststraﬂe 3', '12345', 'teststadt3');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (4, 'edeka', 'teststraﬂe 4', '12345', 'teststadt4');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (5, 'saturn', 'teststraﬂe 5', '12345', 'teststadt5');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (6, 'media markt', 'teststraﬂe 6', '12345', 'teststadt6');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (7, 'h&m', 'teststraﬂe 7', '12345', 'teststadt7');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (8, 'karstadt', 'teststraﬂe 8', '12345', 'teststadt8');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (9, 'sport check', 'teststraﬂe 9', '12345', 'teststadt9');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (10, 'c&a', 'teststraﬂe 10', '12345', 'teststadt10');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (11, 'Knapstein', 'teststraﬂe 11', '12345', 'teststadt11');
INSERT INTO DEALER (Dealer_ID, Name, Street, Zip, City) VALUES (12, 'Roller', 'teststraﬂe 12', '12345', 'teststadt12');

-- brands are too various but still static defiend
INSERT INTO BRAND (Brand_ID, Name) VALUES (0, 'alle');
INSERT INTO BRAND (Brand_ID, Name) VALUES (1, 'cola');
INSERT INTO BRAND (Brand_ID, Name) VALUES (2, 'fanta');
INSERT INTO BRAND (Brand_ID, Name) VALUES (3, 'capri-sonne');
INSERT INTO BRAND (Brand_ID, Name) VALUES (4, 'granini');
INSERT INTO BRAND (Brand_ID, Name) VALUES (5, 'samsung');
INSERT INTO BRAND (Brand_ID, Name) VALUES (6, 'lg');
INSERT INTO BRAND (Brand_ID, Name) VALUES (7, 'sony');
INSERT INTO BRAND (Brand_ID, Name) VALUES (8, 'toschiba');
INSERT INTO BRAND (Brand_ID, Name) VALUES (9, 'acer');
INSERT INTO BRAND (Brand_ID, Name) VALUES (10, 'nike');
INSERT INTO BRAND (Brand_ID, Name) VALUES (11, 'puma');
INSERT INTO BRAND (Brand_ID, Name) VALUES (12, 'adidas');
INSERT INTO BRAND (Brand_ID, Name) VALUES (13, 'mondo');
INSERT INTO BRAND (Brand_ID, Name) VALUES (14, 'h¸lsta');

-- dummy offer entries for comparison
-- TECHNIK
-- fernsher
-- criteria for fernseher: 'zoll', 'hz', 'typ', 'hd'
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (1, 1, 5, 5, 'samsung syncmaster', 200, 300, '2010-12-10', '32', '100', 'lcd', 'ready');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (2, 1, 5, 6, 'samsung UE40E', 100, 200, '2010-12-10', '24', '100', 'lcd', 'ready');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (3, 1, 5, 6, 'samsung UE60E', 300, 350, '2010-10-10', '40', '100', 'lcd', 'full');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (4, 1, 6, 5, 'lg electronics v1', 350, 400, '2010-09-10', '42', '200', 'lcd', 'full');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (5, 1, 6, 6, 'lg electronics v2', 450, 500, '2010-09-10', '46', '100', 'led', 'full');

-- laptop
-- criteria for laptop: 'zoll', 'ram', 'prozessor', 'festplatte'
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (7, 2, 7, 5, 'sony vaio', 400, 500, '2010-12-10', '15', '4', '2.3', '300');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (8, 2, 8, 6, 'toschiba satelite pro', 420, 480, '2010-11-10', '13', '4', '2', '250');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (9, 2, 9, 6, 'acer aspire', 500, 540, '2010-12-10', '15', '4', '3', '1000');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (10, 2, 5, 6, 'samsung laptop v1', 250, 300, '2010-11-10', '11', '2', '2.3', '350');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (11, 2, 5, 5, 'samsung laptop v2', 350, 400, '2010-05-10', '13', '4', '2.5', '500');

-- MOEBEL
-- tisch
-- criteria:'material', 'hoehe', 'laenge', 'breite'
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (24, 3, 13, 11, 'tisch1', 400, 500, '2010-12-10', 'eiche', '5', '3', '2');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (25, 3, 14, 12, 'tisch2', 420, 480, '2010-11-10', 'buche', '4', '2', '2');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (26, 3, 13, 11, 'tisch3', 500, 540, '2010-12-10', 'fichte', '4', '3', '1');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (27, 3, 14, 12, 'tisch4', 250, 300, '2010-11-10', 'eiche', '2', '5', '3');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (28, 3, 13, 12, 'tisch5', 350, 400, '2010-05-10', 'buche', '4', '4', '2');
-- bett
-- criteria:'material', 'typ', 'laenge', 'breite'
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (29, 4, 14, 11, 'bett1', 400, 500, '2010-12-10', 'fichte', '4', '2', '3');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (30, 4, 13, 11, 'bett2', 420, 480, '2010-11-10', 'eiche', '4', '3', '2');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (31, 4, 14, 12, 'bett3', 500, 540, '2010-12-10', 'buche', '4', '2', '1');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (32, 4, 13, 11, 'bett4', 250, 300, '2010-11-10', 'fichte', '2', '3', '3');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3, Criteria4) VALUES (33, 4, 14, 12, 'bett5', 350, 400, '2010-05-10', 'eiche', '4', '2', '5');
-- LEBENSMITTEL werden nicht im prototypen behandelt, da diese sich anders verhalten

-- KLEIDUNG
-- t-shirt
-- criteria:'groesse', 'material', 'farbe'
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (13, 7, null, 7, 't-shirt v ausschnitt', 1, 3, '2012-12-10', 'm', 'baumwolle', 'blau');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (14, 7, null, 7, 't-shirt u ausschnitt', 2, 5, '2012-12-10', 'l', 'baumwolle', 'gr¸n');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (15, 7, 10, 8, 'nike t-shirt', 20, 30, '2012-10-10', 's', 'stoff', 'schwarz');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (16, 7, 11, 9, 'puma t-shirt', 35, 40, '2012-09-10', 'xxl', 'stoff', 'braun');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (17, 7, 11, 8, 'puma t-shirt', 22, 25, '2012-10-10', 'm', 'stoff', 'rot');

-- hose
-- criteria:'groesse', 'material', 'farbe
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (19, 8, 11, 8, 'jeans', 33, 50, '2012-12-10', 'm', 'jeans', 'blau');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (20, 8, 10, 9, 'short', 20, 30, '2012-11-10', 'l', 'baumwolle', 'gr¸n');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (21, 8, 12, 8, 'latzhose', 30, 40, '2012-11-10', 's', 'baumwolle', 'schwarz');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (22, 8, 10, 9, 'jeans', 30, 40, '2012-06-10', 'xxl', 'jeans', 'braun');
INSERT INTO OFFER (Offer_ID, Sub_C_ID, Brand_ID, Dealer_ID, Name, old_Price, Price, End_Date, Criteria1, Criteria2, Criteria3) VALUES (23, 8, 11, 10, 'lederhose', 30, 50, '2012-06-10', 'm', 'leder', 'rot');
