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
SELECT @cost = cost  FROM delivery_order Where fermer_id=@fermer AND driver_id=@driver
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
    SELECT @cost=cost from delivery_order Where id=@id
    SELECT @driver_balance=balance from Driver Where id=(SELECT id from delivery_order Where id=@id)
    SELECT @farmer_balance=balance from Farmer Where id=(SELECT id from delivery_order Where id=@id)
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
