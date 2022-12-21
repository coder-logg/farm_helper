function Mock() { }

Mock.mobile_prefix = ["134", "135", "136", "137", "138", "139", "150", "151",
    "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153"];

Mock.numeric = "0123456789";

Mock.lowerCase = "abcdefghijklmnopqrstuvwxyz"
Mock.upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

const users_number = 15000
const customer_number = 10000
const farmer_number = 10000
const driver_number = 4000
const admin_number = 1000
const order_number = 10000
const plant_number = 150
const car_number = 500

Mock.email_suffix = ["@gmail.com", "@yahoo.com", "@msn.com", "@hotmail.com",
    "@aol.com", "@ask.com", "@live.com", "@qq.com", "@0355.net", "@163.com",
    "@163.net", "@263.net", "@3721.net", "@yeah.net", "@126.com", "@sina.com",
    "@sohu.com", "@yahoo.com.cn"];

Mock.random = function (len, list) {
    if (len <= 1) { len = 1; }
    var s = "";
    var n = list.length;
    if (typeof list === "string") {
        while (len-- > 0) {
            s += list.charAt(Math.random() * n)
        }
    } else if (list instanceof Array) {
        while (len-- > 0) {
            s += list[Math.floor(Math.random() * n)]
        }
    }
    return s;
}
Mock.pick = function (list) {
    if (!list.length) {
        return undefined;
    }
    return list[Math.floor(Math.random() * list.length)]
}
Mock.randomInt = function (min, max) {
    return min + Math.floor(Math.random() * (max - min + 1))
}
Mock.getMobile = function () {
    return Mock.random(1, Mock.mobile_prefix)
        + Mock.random(8, Mock.numeric);
}
Mock.getGrade = function () {
    return Mock.random(1, Mock.grade);
}
Mock.getEmail = function () {
    var opt = Mock.numeric + Mock.lowerCase + Mock.upperCase
    return Mock.random(Mock.randomInt(4, 10), opt) +
        Mock.random(1, Mock.email_suffix);
}
var country = ['Brazil', 'Russia', 'USA', 'France', 'Italy', 'Kavkaz', 'Ukraine'];
var soil_type = ['чернозем', 'почва', 'глина'];
var message_enum = ['Класс', 'Все понравилось', 'Мужик огонь', 'Четкий парень', 'За слова отвечает', 'По понятиям живет']
function getRandomInt(max) {
    return Math.floor(Math.random() * max + 1);
}

function randomBetween(min, max) {
    return min + Math.floor(Math.random() * (max - min))
}

function string_gen(len) {
    var chrs = 'abdehkmnpswxzABDEFGHKMNPQRSTWXZАБВГДЕЁЖЗИЙКЛМНОПРСТфХЦЧШЩЫЭЮЯ ';
    var str = '';
    for (var i = 0; i < len; i++) {
        var pos = Math.floor(Math.random() * chrs.length);
        str += chrs.substring(pos, pos + 1);
    }
    return str;
}

function pass_gen(len) {
    var chrs = 'abdehkmnpswxzABDEFGHKMNPQRSTWXZ123456789';
    var str = '';
    for (var i = 0; i < len; i++) {
        var pos = Math.floor(Math.random() * chrs.length);
        str += chrs.substring(pos, pos + 1);
    }
    return str;
}

function gen_date(){
    month = "" + Mock.randomInt(1, 12);
    if (month.length === 1)
        month = "0" + month
    day = "" + Mock.randomInt(1, 30)
    if (day.length === 1)
        day = "0" + day
    return Mock.randomInt(2020, 2030)+ "-" + month + "-" + day;
}

car_numbers = []
function gen_car_number(){
    var abc = 'АБВГДЕЁЖЗИЙКЛМНОПРСТфХЦЧШЩЫЭЮЯ';
    res = abc.charAt(getRandomInt(abc.length)) + getRandomInt(9) + getRandomInt(9) + getRandomInt(9) + abc.charAt(getRandomInt(abc.length)) + abc.charAt(getRandomInt(abc.length))
    if (!car_numbers.includes(res, 0)){
        car_numbers.push(res)
        return res
    }
    else return gen_car_number()
}

function gen_time(){
    hour = "" + randomBetween(0, 23);
    if (hour.length === 1)
        hour = "0" + hour
    minute = "" + randomBetween(0, 60)
    if (minute.length === 1)
        minute = "0" + minute
    sec = "" + randomBetween(0, 60)
    if (sec.length === 1)
        sec = "0" + sec
    return hour + ":" + minute + ":" + sec;
}

function gen_timestamp() {
    return gen_date() + " " + gen_time()
}


for (i = 0; i < car_number; i++) {
    console.log("INSERT INTO car (mark, number, capacity) VALUES (" + string_gen(15) + "," + gen_car_number() + "," + Mock.randomInt(20, 100)+ ");");
}

// Получаем случайный ключ массива
for (var i = 0; i < 10000; i++) {
    console.log("INSERT INTO Order_for_drive (id,driver_id,farmer_id,cost) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ");");
    console.log("INSERT INTO Arbitration (id,order_id,driver_id,farmer_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ");");
    console.log("INSERT INTO Farm (id, location_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(7) + ")")
}
for (var i = 0; i < 20000; i++) {
    console.log("INSERT INTO Order_for_drive (id,driver_id,farmer_id,cost) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ");");
    console.log("INSERT INTO Arbitration (id,order_id,driver_id, farmer_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ");");
}

for (i = 0; i < users_number; i++) {
    var login = pass_gen(10);
    var rand1 = Math.floor(Math.random() * message_enum.length);
    console.log("INSERT INTO _user (login, phone, mail, password) VALUES ('" + login + "'," + Mock.getMobile() + ",'" + Mock.getEmail() + "','" + pass_gen(10) + "');");
    console.log("INSERT INTO Review_List  (id, review_id, user_login) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + ",'" + login + "');")
    console.log("INSERT INTO Review (id, sendler_login, message, rate) VALUES (" + getRandomInt(100) + "," + login + ",'" + message_enum[rand1] + "'," + getRandomInt(10) + ");")
    num = getRandomInt(3)
    a = 0
    b = 0
    c = 0
    if (num % 3 === 0 && a < farmer_number) {
        console.log("INSERT INTO Farmer (id,farmer_inf_login,balance,farm_id) VALUES (" + getRandomInt(100) + "," + login + "," + getRandomInt(100) + "," + getRandomInt(100) + ");");
        a++
    }
    else if (num % 3 === 1 && b < driver_number) {
        console.log("INSERT INTO driver (driver_inf_login, car_id, balance) VALUES (" + login + "," + getRandomInt(car_number) + "," + Mock.randomInt(-100, 10000000) + ");");
        b++
    }
    else if (c < admin_number){
        console.log("INSERT INTO admin (admin_inf_login) VALUES (" + login + ");");
        console.log("INSERT INTO arbitration_list (arbitration_id, admin_id) VALUES (" + getRandomInt(10000) + ", " + getRandomInt(i)+");");
        c++
    }
};

for (i = 0; i < customer_number; i++) {
    console.log("INSERT INTO Customer (id, name, phone, mail) VALUES (" + getRandomInt(100) + ",'" + pass_gen(10) + "'," + Mock.getMobile() + ",'" + Mock.getEmail() + "','" + pass_gen(10) + "');")
}
for (i = 0; i < 25; i++) {
    var rand = Math.floor(Math.random() * country.length);
    console.log("INSERT INTO Location (id, country_name, price_per_month, square) VALUES (" + getRandomInt(7) + "," + country[rand] + "," + getRandomInt(100) + "," + getRandomInt(100) + ");")
}
for (i = 0; i < 7; i++) {
    var rand1 = Math.floor(Math.random() * soil_type.length);
    console.log("INSERT INTO Country (name,soil_type, sunlight_amount) VALUES ('" + country[i] + "','" + soil_type[rand1] + "'," + getRandomInt(100) + ");")
}

equipment = ["культиватор бензиновый", "ножницы садовые", "аккумуляторный опрыскиватель", "газонокосилка бензиновая",
    "шланг поливочный 100м", "шланг поливочный 50м", "шланг поливочный 30м", "лопата штыковая", "грабли",
    "садовый трактор", "кусторез", "комплект садовых инструметов", "система полива"]
brends = ["PATRIOT", "GLORIA", "ЗУБР", "GARDENA", "KARCHER", "DAEWOO", "ВИХРЬ", "SOLO"]

for (e in equipment){
    for (b in brends){
        var name = "\"" + e + " " + b + "\""
        console.log("INSERT INTO equipment (name, cost, location) VALUES (" + name + ", " + getRandomInt(10000) + "," + country[getRandomInt(country.length)] + ");")
    }
}

for (i = 1; i <= plant_number; i++){
    console.log("INSERT INTO plant (name, cost, time_for_completed) VALUES ("+ string_gen(30) + ", " + Mock.randomInt(20, 1000) + ", " + Mock.randomInt(500, 2000) + ");")
    console.log("INSERT INTO required_equipment (plant_id, equipment_id, amount) VALUES ("+ getRandomInt(plant_number)+ ", " + getRandomInt(equipment.length * brends.length) + ", " + getRandomInt(20) + ");")

}

progress = ['started', 'cultivation','delivery','finished','arbitration']

for (i = 1; i <= order_number; i++){
    console.log("INSERT INTO status (location, progress) VALUES ("+ country[getRandomInt(country.length)] + ", " + progress[getRandomInt(progress.length)] +");")
    console.log("INSERT INTO order_detail (plant_id, amount, delivery_date, delivery_address) VALUES ("+ getRandomInt(plant_number) + ", " + Mock.randomInt(500, 2000) + ", " + gen_timestamp() + ", " + string_gen(30) +");")
    console.log("INSERT INTO _order (farmer_id, customer_id, order_detail_id, status_id, cost) VALUES ("+ getRandomInt(farmer_number) + ", " + getRandomInt(customer_number) + ", " + i + ", " + i + ", " + Mock.randomInt(10000, 10000000) + ");")
}