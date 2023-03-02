import axios from 'axios';
export const add_order = async (driverLogin, orderId, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/add', {
            driverLogin,
            orderId
        }, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const get_orders = async (driverLogin, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/get', {
            driverLogin
        }, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
        return [{ "id": 1, "plant": "Apple", "amount": 2, "address1": "SPB", "address2": "Kazan", "farmerName": "Ashot", "date": "2023-05-23" }]
    }
}
export const add_car = async (driverLogin, mark, number, capacity, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/car_driver/add', {
            driverLogin,
            mark,
            number,
            capacity
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const get_cars = async (driverLogin, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/get', {
            driverLogin
        }, { headers: { "Authorization": auth } })
    }
    catch (e) {
        console.log(e)
    }
}