
CREATE TABLE Order_for_drive(
                                id int PRIMARY KEY,
                                farmer_id int NOT NULL,
                                driver_id int NOT NULL,
                                cost int CHECK(cost>0),

                                FOREIGN KEY(driver_id)
                                    REFERENCES driver(id),
                                FOREIGN KEY(farmer_id)
                                    REFERENCES farmer(id)
);
CREATE TABLE Country(
                        name varchar(30) PRIMARY KEY,
                        soil_type varchar(30) NOT NULL,
                        sunlight_amount int,
                        CONSTRAINT CHK_Country CHECK(sunlight_amount>0 AND (soil_type='почва' OR soil_type='чернозем' OR soil_type='глина'))
);
CREATE INDEX idx_sunlight_amount on Country(sunlight_amount);
CREATE TABLE Location(
                         id int PRIMARY KEY,
                         country_name varchar(30),
                         price_per_month int,
                         square int CHECK(square>0),
                         FOREIGN KEY(country_name)
                             REFERENCES country(name)
);
CREATE INDEX idx_price_per_month on Location(price_per_month);
CREATE TABLE Farm(
                     id int PRIMARY KEY,
                     location_id int NOT NULL,
                     FOREIGN KEY(location_id)
                         REFERENCES Location(id)
);

CREATE TABLE Farmer(
                       id int PRIMARY KEY,
                       farmer_inf_login int NOT NULL,
                       balance int CHECK(balance>0),
                       farm_id int,
                       FOREIGN KEY(farmer_inf_login)
                           REFERENCES user(login),
                       FOREIGN KEY(farm_id)
                           REFERENCES farm(id)
);
CREATE TABLE Farmer_orders(
                              id int PRIMARY KEY,
                              fermer_id int NOT NULL,
                              order_id int,
                              FOREIGN KEY(fermer_id)
                                  REFERENCES farmer(id),
                              FOREIGN KEY(order_id)
                                  REFERENCES _order(id)
);
CREATE TABLE Customer(
                         id int PRIMARY KEY,
                         name varchar(10) NOT NULL,
                         phone int NOT NULL UNIQUE,
                         mail varchar(50)
);
CREATE TABLE Review(
                       id int PRIMARY KEY,
                       sendler_id int NOT NULL,
                       message varchar(255) NOT NULL,
                       rate int CHECK(rate>0 AND rate<5)
);
CREATE INDEX idx_rate on Review(rate);
CREATE TABLE User(
                     login varchar(10) PRIMARY KEY,
                     phone int NOT NULL UNIQUE,
                     mail varchar(50) NOT NULL UNIQUE,
                     password varchar(15) NOT NULL
);

CREATE TABLE Review_list(
                            id int PRIMARY KEY,
                            rewiew_id int  NOT NULL,
                            user_login varchar(10) NOT NULL,
                            FOREIGN KEY(rewiew_id)
                                REFERENCES Review(id),
                            FOREIGN KEY(user_login)
                                REFERENCES User(login)
);
CREATE TABLE Arbitration(
                            id int PRIMARY KEY,
                            order_id int NOT NULL,
                            driver_id int  NOT NULL,
                            fermer_id int NOT NULL,
                            FOREIGN KEY(order_id)
                                REFERENCES _order(id),
                            FOREIGN KEY(driver_id)
                                REFERENCES Driver(id),
                            FOREIGN KEY(fermer_id)
                                REFERENCES farmer(id)
);
CREATE FUNCTION Cost_Order(@id INT)
    RETURNS INT
BEGIN
DECLARE @amount_plant INT, @cost_plant INT, @cost_equipment INT,@sum  INT
SELECT @amount_plant = amount FROM Order_Detail WHERE id=(SELECT id FROM ORDER WHERE id=@id)
SELECT @cost_plant = cost FROM PLANT WHERE id=(SELECT plant_id FROM Order_Detail WHERE id=(SELECT id FROM ORDER WHERE id=@id))
SELECT @cost_equipment= cost FROM Equipment WHERE id=(SELECT equipment_id FROM Required_equipment WHERE id=(SELECT id FROM PLANT WHERE id=(SELECT plant_id FROM Order_Detail WHERE id=(SELECT id FROM ORDER WHERE id=@id)))
SET @sum= @amount_plant*cost_plant*@cost_equipment
    RETURN @sum
END


CREATE FUNCTION Transfer_Money_Arbitration(@percent INT, @fermer INT, @driver INT, @thuth INT)
    RETURNS INT
BEGIN
DECLARE @balance1 int, @balance2 int, @cost int
SELECT @cost = cost  FROM Order_for_drive Where fermer_id=@fermer AND driver_id=@driver
SELECT @balance1 = balance FROM User Where id=@fermer
SELECT @balance2 = balance FROM User Where id=@driver
    IF (@thuth=1){
    @balance1= @balance1+@cost*@percent
UPDATE Fermer SET balance=@balance1
    }
IF (@thuth=2){
     @balance2= @balance2+@cost*@percent
UPDATE Driver SET balance=@balance2
    }
RETURN 1
END

CREATE FUNCTION Send_Money(@id INT)
    RETURNS INT
BEGIN
    DECLARE @cost INT, @driver_balance INT,@farmer_balance INT
SELECT @cost=cost from Order_for_drive Where id=@id
SELECT @driver_balance=balance from Driver Where id=(SELECT id from Order_for_drive Where id=@id)
SELECT @farmer_balance=balance from Farmer Where id=(SELECT id from Order_for_drive Where id=@id)
    SET @driver_balance= @driver_balance+cost
SET @farmer_balance= @farmer_balance-cost
UPDATE Fermer SET balance=@farmer_balance
UPDATE Driver SET balance=@driver_balance
    RETURN 1
END

CREATE FUNCTION Find_Customer(@id INT)
    RETURNS TABLE AS
RETURN
SELECT name,phone FROM Customer WHERE id=(SELECT customer_id FROM Order WHERE id=@id);

CREATE FUNCTION Complete_order(@id as INT)
    RETURNS INT
BEGIN
DECLARE @status varchar(20)
SET @status = 'completed'
UPDATE Status SET progress= @status WHERE id=(SELECT status_id FROM Order WHERE id=@id)
    RETURN 1
END;
CREATE FUNCTION Balance_plus(@id as INT, @sum as INT)
    RETURN INT
DECLARE @balance INT
SELECT @balance=balance FROM FERMER WHERE id=@id
    SET @balance = @balance+@sum
UPDATE Fermer SET balance= @balance
    RETURN 1
END;

CREATE FUNCTION Reg(@name varchar(10), @phone as INT,@mail varchar(15), @password varchar(10))
    RETURN INT
BEGIN
INSERT INTO User (login,phone,mail,password) VALUES (@name,@phone,@mail, @password)
    RETURN 1
END;

CREATE FUNCTION Add_Plant(@name varchar(10), @cost as INT,@time_for_completed timestamp, @sunlight int)
    RETURN INT
BEGIN
INSERT INTO Plant (name,cost,time_for_completed,sunlight) VALUES (@name,@cost,@time_for_completed, @sunlight)
    RETURN 1
END;

CREATE FUNCTION Add_Equipment(@name varchar(10), @cost as INT,@location varchar(15))
    RETURN INT
BEGIN
INSERT INTO Equipment (name,cost,location) VALUES (@name,@cost,@location)
    RETURN 1
END;


CREATE FUNCTION Add_Order(@fermer_id INT,@cost INT, @amount as INT,@plant varchar(15),@delivery_date timestamp, @delivery_address varchar(30))
    RETURNS INT
BEGIN
DECLARE @plant_id int ,@order_detail_id int, @status_id int, @location varchar(30)
SELECT @plant_id=id FROM PLANT WHERE name=@plant
    INSERT INTO Equipment (plant_id,amount,delivery_date,delivery_address) VALUES (@plant_id,@amount,@delivery_date,@delivery_address)
SELECT @order_detail_id= id FROM Order_Detail Where plant_id=@plant_id AND amount=@amount AND delivery_date=@delivery_date AND delivery_address=@delivery_address
SELECT @location=country_name FROM Location WHERE id=(SELECT location_id FROM FARM WHERE id=(SELECT farm_id FROM Fermer WHERE id=@id))
    INSERT INTO Status (location,progress) VALUES (@location,'started')
SELECT @status_id=id FROM Status WHERE location=@location AND progress='started'
    INSERT INTO Order (order_detail_id,status_id,cost) VALUES (@order_detail_id,@status,@cost)
    RETURN 1
END;

CREATE FUNCTION Find_Order(@id INT)
    RETURNS TABLE AS
RETURN
SELECT location FROM Status Where id=(Select status_id FROM Order Where id=@id);