-- die tabellen wurden analog zu dem datenbankmodell(siehe abbildung: datenmodell) definiert
CREATE TABLE DEVICE (
	Reg_ID VARCHAR(255) NOT NULL
);

ALTER TABLE DEVICE ADD CONSTRAINT Reg_ID_PK PRIMARY KEY (Reg_ID);

CREATE TABLE CATEGORY (
	Category_ID BIGINT NOT NULL,
	Name VARCHAR(50) NOT NULL
);

ALTER TABLE CATEGORY ADD CONSTRAINT Category_ID_PK PRIMARY KEY (Category_ID);
ALTER TABLE CATEGORY ADD CONSTRAINT Category_checkDomain CHECK (
    Name IN ('alle', 'technik', 'moebel', 'lebensmittel', 'bekleidung')
);

CREATE TABLE SUB_CATEGORY (
	Sub_C_ID BIGINT NOT NULL,
	Category_ID BIGINT NOT NULL,
	Name VARCHAR(50) NOT NULL
);

ALTER TABLE SUB_CATEGORY ADD CONSTRAINT Sub_Category_ID_PK PRIMARY KEY (Sub_C_ID);
ALTER TABLE SUB_CATEGORY ADD FOREIGN KEY (Category_ID) REFERENCES CATEGORY (Category_ID);

CREATE TABLE DEALER (
	Dealer_ID BIGINT NOT NULL,
	Name VARCHAR(50) NOT NULL,
	Street VARCHAR(50),
	Zip VARCHAR(50),
	City VARCHAR(50)
);

ALTER TABLE DEALER ADD CONSTRAINT Dealer_ID_PK PRIMARY KEY (Dealer_ID);

CREATE TABLE BRAND (
	Brand_ID BIGINT NOT NULL,
	Name VARCHAR(50) NOT NULL
);

ALTER TABLE BRAND ADD CONSTRAINT Brand_ID_PK PRIMARY KEY (Brand_ID);

CREATE TABLE OFFER (
	Offer_ID BIGINT NOT NULL,
	Sub_C_ID BIGINT,
	Brand_ID BIGINT,
	Dealer_ID BIGINT,
	Name VARCHAR(50) NOT NULL,
	old_Price DECIMAL,
	Price DECIMAL NOT NULL,
	Start_Date VARCHAR(50),
	End_Date VARCHAR(50),
	Criteria1 VARCHAR(50),
	Criteria2 VARCHAR(50),
	Criteria3 VARCHAR(50),
	Criteria4 VARCHAR(50)
);

ALTER TABLE OFFER ADD CONSTRAINT Offer_ID_PK PRIMARY KEY (Offer_ID);
ALTER TABLE OFFER ADD FOREIGN KEY (Sub_C_ID) REFERENCES SUB_CATEGORY (Sub_C_ID);
ALTER TABLE OFFER ADD FOREIGN KEY (Brand_ID) REFERENCES BRAND (Brand_ID);
ALTER TABLE OFFER ADD FOREIGN KEY (Dealer_ID) REFERENCES DEALER (Dealer_ID);

-- topic subscribtion --
CREATE TABLE SUBSCRIPTION(
	Subscription_ID BIGINT NOT NULL,
	Reg_ID VARCHAR(255) NOT NULL,
	Category_ID BIGINT NOT NULL,
	Sub_C_ID BIGINT,
	Dealer_ID BIGINT,
	Brand_ID BIGINT
);
ALTER TABLE SUBSCRIPTION ADD CONSTRAINT S_ID_PK PRIMARY KEY (Subscription_ID);
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY (Reg_ID) REFERENCES DEVICE (Reg_ID);
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY (Category_ID) REFERENCES CATEGORY (Category_ID);
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY (Sub_C_ID) REFERENCES SUB_CATEGORY (Sub_C_ID);
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY (Dealer_ID) REFERENCES DEALER (Dealer_ID);
ALTER TABLE SUBSCRIPTION ADD FOREIGN KEY (Brand_ID) REFERENCES BRAND (Brand_ID);

-- secuences --
CREATE TABLE DUAL(VALUE INT);
INSERT INTO DUAL VALUES (1);

CREATE SEQUENCE Offer_SEQ START WITH 90 INCREMENT BY 1;
CREATE SEQUENCE Subscription_SEQ START WITH 1 INCREMENT BY 1;
