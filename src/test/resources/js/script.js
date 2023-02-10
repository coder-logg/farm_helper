function Mock() { }

Mock.mobile_prefix = ["134", "135", "136", "137", "138", "139", "150", "151",
    "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153"];

Mock.numeric = "0123456789";

Mock.lowerCase = "abcdefghijklmnopqrstuvwxyz"
Mock.upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

const users_number = 15000
const customer_number = 10000
const farmer_number = 10000
const farm_number = 1000
const driver_number = 4000
const admin_number = 1000
const order_number = 10000
const plant_number = 150
const car_number = 500
const review_number = 15000
const location_number = 5000
const order_for_drive_number = 10000

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

function getRandomIdx(max){
    return Math.floor(Math.random() * max);
}

function randomBetween(min, max) {
    return min + Math.floor(Math.random() * (max - min))
}


function genName(){
    names = ['Александр','Сергей','Владимир','Елена','Татьяна','Андрей','Алексей','Ольга','Николай','Наталья','Анна','Иван',
        'Дмитрий','Ирина','Мария','Михаил','Светлана','Екатерина','Евгений','Виктор','Анастасия','Юрий','Юлия','Валентина',
        'Галина','Людмила','Василий','Игорь','Анатолий','Олег','Надежда','Марина','Павел','Максим','Александра','Нина',
        'Виталий','Валерий','Виктория','Любовь','Роман','Оксана','Денис','Дарья','Константин','Вячеслав','Вера','Евгения',
        'Петр','Антон','Илья','Владислав','Лариса','Лидия','Ксения','Вадим','Наталия','Никита','Григорий','Геннадий','Тамара',
        'Алина','Леонид','Руслан','Борис','Кристина','Елизавета','Кирилл','Артем','Валерия','Полина','Станислав','Инна',
        'Раиса','Алла','Яна','Антонина','Валентин','Егор','Федор','Артём','Георгий','Зинаида','Алена','Степан','Олеся',
        'Маргарита','Лилия','Диана','Эдуард','Даниил','Евдокия','Зоя','Алёна','Вероника','Пётр','Артур','Софья','Клавдия',
        'София']
    surnames = ['Иванов','Иванова','Кузнецова','Кузнецов','Попов','Попова','Петров','Коваленко','Бондаренко','Шевченко',
        'Смирнова','Ткаченко','Васильева','Кравченко','Петрова','Васильев','Смирнов','Новикова','Новиков','Бойко','Волков',
        'Морозова','Козлов','Волкова','Захаров','Соколова','Михайлов','Захарова','Соколов','Морозов','Козлова','Михайлова',
        'Макаров','Макарова','Павлова','Павлов','Мельник','Савченко','Никитина','Никитин','Андреева','Андреев','Марченко',
        'Орлова','Егоров','Степанов','Степанова','Фролов','Зайцева','Зайцев','Алексеева','Егорова','Романова','Сергеев',
        'Фролова','Орлов','Лебедева','Лебедев','Сергеева','Руденко','Яковлева','Ким','Сорокин','Юрченко','Алексеев',
        'Кузьмин','Николаева','Григорьев','Поляков','Борисов','Григорьева','Яковлев','Василенко','Борисова','Романов',
        'Кузьмина','Сорокина','Клименко','Николаев','Нестеренко','Полякова','Медведева','Сидоренко','Тарасов','Мороз',
        'Баранов','Данилов','Медведев','Левченко','Чернов','Филиппов','Петренко','Колесник','Матвеев','Тарасова','Мищенко',
        'Белова','Назаренко','Данилова','Гончарова']
    return names[getRandomIdx(names.length)] + " " + surnames[getRandomIdx(surnames.length)]
}

function getMark(){
    marks = ['Ural', 'KRAZ', 'MAZ', 'GAZ', 'ZIL', 'KAMAZ', 'MAN', 'VOLVO', 'SCANIA', 'MERCEDES', 'ISUZU', 'NISSAN', 'IVECO']
    return marks[getRandomIdx(marks.length)]
}

function string_gen(len) {
    var chrs = 'abdehkmnpswxzABDEFGHKMNPQRSTWXZАБВГДЕЁЖЗИЙКЛМНОПРСТфХЦЧШЩЫЭЮЯ ';
    var str = '';
    for (var i = 0; i < len; i++) {
        var pos = Math.floor(Math.random() * chrs.length + 1);
        str += chrs.substring(pos, pos + 1);
    }
    return str;
}

function pass_gen(len) {
    var chrs = 'abdehkmnpswxzABDEFGHKMNPQRSTWXZ123456789';
    var str = '';
    for (var i = 0; i < len; i++) {
        var pos = Math.floor(Math.random() * chrs.length + 1);
        str += chrs.substring(pos, pos + 1);
    }
    return str;
}

function gen_date(){
    month = "" + Mock.randomInt(1, 12);
    if (month.length === 1)
        month = "0" + month
    day = "" + Mock.randomInt(1, 28)
    if (day.length === 1)
        day = "0" + day
    return Mock.randomInt(2020, 2030)+ "-" + month + "-" + day;
}

car_numbers = []
function gen_car_number(){
    var abc = 'АБВГДЕЁЖЗИЙКЛМНОПРСТфХЦЧШЩЫЭЮЯ';
    res = abc.charAt(getRandomIdx(abc.length)) + getRandomIdx(9) + getRandomIdx(9) + getRandomIdx(9) + abc.charAt(getRandomIdx(abc.length)) + abc.charAt(getRandomIdx(abc.length))
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

// заплнения таблицы car
for (i = 0; i < car_number; i++) {
    console.log("INSERT INTO car (mark, number, capacity) VALUES ('" + getMark() + "', '" + gen_car_number() + "', " + Mock.randomInt(20, 100)+ ");");
}

for (i = 0; i < customer_number; i++) {
    console.log("INSERT INTO Customer (name, phone, mail) VALUES ('" + genName() + "', " + Mock.getMobile() + ", '" + Mock.getEmail() + "');")
}

for (i = 0; i < country.length; i++) {
    console.log("INSERT INTO Country (name,soil_type, sunlight_amount) VALUES ('" + country[i] + "', '" + soil_type[getRandomIdx(soil_type.length)] + "'," + getRandomInt(100) + ");")
}

for (i = 0; i < location_number; i++) {
    console.log("INSERT INTO Location (country_name, price_per_month, square) VALUES ('" + country[getRandomIdx(country.length)] + "'," + getRandomInt(100) + "," + getRandomInt(100) + ");")
}

for (i = 0; i < farm_number; i++) {
    console.log("INSERT INTO Farm (location_id) VALUES (" + getRandomInt(location_number) + ");")
}

a = 0
b = 0
c = 0
logins = []

function loginGen(){
    login = pass_gen(10);
    if (logins.includes(login))
        return loginGen()
    else return login
}
// заполнение _user, farmer, driver, admin
for (i = 1; i <= users_number; i++) {
    login = loginGen()
    logins.push(login)
    console.log("INSERT INTO _user (login, phone, mail, password) VALUES ('" + login + "', " + Mock.getMobile() + ", '" + Mock.getEmail() + "', '" + pass_gen(10) + "');");
    num = getRandomInt(100)
    if (num % 3 === 1 && b < driver_number) {
        console.log("INSERT INTO driver (driver_inf_login, car_id, balance) VALUES ('" + login + "', " + getRandomInt(car_number) + ", " + Mock.randomInt(-100, 10000000) + ");");
        b++
    }
    else if (num % 3 === 2 && c < admin_number){
        console.log("INSERT INTO admin (admin_inf_login) VALUES ('" + login + "');");
        c++
    }
    else if (a < farmer_number) {
        console.log("INSERT INTO Farmer (farmer_inf_login, balance,farm_id) VALUES ('" + login + "', " + Mock.randomInt(1, 10000000) + "," + getRandomInt(farm_number) + ");");
        a++
    }
}

for (i = 1; i <= review_number; i++){
    console.log("INSERT INTO Review (sendler_login, message, rate) VALUES ('" + logins[getRandomIdx(logins.length)] + "', '" + message_enum[getRandomIdx(message_enum.length)] + "', " + getRandomIdx(6) + ");")
    console.log("INSERT INTO Review_List  (review_id, user_login) VALUES (" + getRandomInt(i) + ", '" + logins[getRandomIdx(logins.length)] + "');")
}

equipment = ["культиватор бензиновый", "ножницы садовые", "аккумуляторный опрыскиватель", "газонокосилка бензиновая",
    "шланг поливочный 100м", "шланг поливочный 50м", "шланг поливочный 30м", "лопата штыковая", "грабли",
    "садовый трактор", "кусторез", "комплект садовых инструметов", "система полива"]
brends = ["PATRIOT", "GLORIA", "ЗУБР", "GARDENA", "KARCHER", "DAEWOO", "ВИХРЬ", "SOLO"]

for (i in equipment){
    for (k in brends){
        var name = "\"" + i + " " + k + "\""
        console.log("INSERT INTO equipment (name, cost, location) VALUES ('" + name + "', " + getRandomInt(10000) + ", '" + country[getRandomIdx(country.length)] + "');")
    }
}

for (i = 1; i <= plant_number; i++){
    console.log("INSERT INTO plant (name, cost, time_for_completed) VALUES ('"+ string_gen(30) + "', " + Mock.randomInt(20, 1000) + ", " + Mock.randomInt(500, 2000) + ");")
    console.log("INSERT INTO required_equipment (plant_id, equipment_id, amount) VALUES ("+ i + ", " + getRandomInt(equipment.length * brends.length) + ", " + getRandomInt(20) + ");")

}

progress = ['started', 'cultivation','delivery','finished','arbitration']

for (i = 1; i <= order_number; i++){
    console.log("INSERT INTO status (location, progress) VALUES ('"+ country[getRandomIdx(country.length)] + "', '" + progress[getRandomIdx(progress.length)] +"');")
    console.log("INSERT INTO order_detail (plant_id, amount, delivery_date, delivery_address) VALUES ("+ getRandomInt(plant_number) + ", " + Mock.randomInt(500, 2000) + ", '" + gen_timestamp() + "', '" + string_gen(30) +"');")
    console.log("INSERT INTO _order (farmer_id, customer_id, order_detail_id, status_id, cost) VALUES ("+ getRandomInt(a) + ", " + getRandomInt(customer_number) + ", " + i + ", " + i + ", " + Mock.randomInt(10000, 10000000) + ");")
}


for (i = 1; i <= order_for_drive_number; i++) {
    console.log("INSERT INTO Order_for_drive (driver_id, farmer_id, cost) VALUES (" + getRandomInt(driver_number) + "," + getRandomInt(a) + "," + getRandomInt(100) + ");");
    console.log("INSERT INTO Arbitration (order_id, driver_id, farmer_id) VALUES (" + i + "," + getRandomInt(driver_number) + "," + getRandomInt(a) + ");");
    console.log("INSERT INTO arbitration_list (arbitration_id, admin_id) VALUES (" + i + ", " + getRandomInt(admin_number) +");");
}

console.log("--- a=" + a)
console.log("--- b=" + b)
console.log("--- c=" + c)