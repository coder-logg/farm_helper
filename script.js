function Mock() { }

Mock.mobile_prefix = ["134", "135", "136", "137", "138", "139", "150", "151",
    "152", "157", "158", "159", "130", "131", "132", "155", "156", "133", "153"];

Mock.numeric = "0123456789";

Mock.lowerCase = "abcdefghijklmnopqrstuvwxyz"
Mock.upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

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
    return Math.floor(Math.random() * max);
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
// Получаем случайный ключ массива
for (var i = 0; i < 10000; i++) {
    console.log("INSERT INTO Farmer (id,farmer_inf_login,balance,farm_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ")");
    console.log("INSERT INTO Order_for_drive (id,driver_id,farmer_id,cost) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ")");
    console.log("INSERT INTO Arbitration (id,order_id,driver_id,fermer_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ")");
    console.log("INSERT INTO Farm (id,location) VALUES (" + getRandomInt(100) + "," + getRandomInt(7) + ")")
}
for (var i = 0; i < 20000; i++) {
    console.log("INSERT INTO Order_for_drive (id,driver_id,farmer_id,cost) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ")");
    console.log("INSERT INTO Arbitration (id,order_id,driver_id,fermer_id) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + "," + getRandomInt(100) + ")");
}
for (i = 0; i < 15000; i++) {
    var login = pass_gen(10);
    var rand1 = Math.floor(Math.random() * message_enum.length);
    console.log("INSERT INTO User (login, phone, mail, password) VALUES ('" + login + "'," + Mock.getMobile() + ",'" + Mock.getEmail() + "','" + pass_gen(10) + "')");
    console.log("INSERT INTO Review_List  (id, review_id,user_login) VALUES (" + getRandomInt(100) + "," + getRandomInt(100) + ",'" + login + "')")
    console.log("INSERT INTO Review (id, sendler_id, message, rate) VALUES (" + getRandomInt(100) + "," + login + ",'" + message_enum[rand1] + "'," + getRandomInt(10) + ")")
};
for (i = 0; i < 10000; i++) {
    console.log("INSERT INTO Customer (id, name, phone, mail) VALUES (" + getRandomInt(100) + ",'" + pass_gen(10) + "'," + Mock.getMobile() + ",'" + Mock.getEmail() + "','" + pass_gen(10) + "')")
}
for (i = 0; i < 25; i++) {
    var rand = Math.floor(Math.random() * country.length);
    console.log("INSERT INTO Location (id,counry_name, price_per_month, square) VALUES (" + getRandomInt(7) + "," + country[rand] + "," + getRandomInt(100) + "," + getRandomInt(100) + ")")
}
for (i = 0; i < 7; i++) {
    var rand1 = Math.floor(Math.random() * soil_type.length);
    console.log("INSERT INTO Country (name,soil_type, sunlight_amount) VALUES ('" + country[i] + "','" + soil_type[rand1] + "'," + getRandomInt(100) + ")")
}

equipment = ["культиватор бензиновый", "ножницы садовые", "аккумуляторный опрыскиватель", "газонокосилка бензиновая",
    "шланг поливочный 100м", "шланг поливочный 50м", "шланг поливочный 30м", "лопата штыковая", "грабли",
    "садовый трактор", "кусторез", "комплект садовых инструметов", "система полива"]
brends = ["PATRIOT","GLORIA", "ЗУБР", "GARDENA", "KARCHER", "DAEWOO", "ВИХРЬ"]
customer_number = 100

for (e in equipment){
    for (b in brends){
        var name = "\"" + e + " " + b + "\""
        console.log("INSERT INTO equipment (name, cost, location) VALUES (" + name + ", " + getRandomInt(10000) + "," + country[getRandomInt(country.length)] + ");")
    }
}
progress = ['started', 'cultivation','delivery','finished','arbitration']

for (i = 0; i < 50; i++){
    console.log("INSERT INTO status (location, progress) VALUES ("+ country[getRandomInt(country.length)] + ", " + progress[getRandomInt(progress.length)] +");")
    console.log("INSERT INTO status (customer_id, order_detail_id, status_id) VALUES ("+ country[getRandomInt(country.length)] + ", " + progress[getRandomInt(progress.length)] +");")
}

for (i = 0; i < 100; i++){
    console.log("INSERT INTO admin (admin_inf_login) VALUES (i)")
}