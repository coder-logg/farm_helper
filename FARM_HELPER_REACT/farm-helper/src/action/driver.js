import axios from 'axios';
export const completed_order = async (driverLogin, orderId, auth) => {
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
export const get_orders_driver = async (driverLogin, auth) => {
    try {
        const response = await axios.get(`http://localhost:8190/driver/${driverLogin}/orders-for-drive`, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
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