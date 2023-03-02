import axios from 'axios';
export const add_order = async (driverLogin, orderId) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/add', {
            driverLogin,
            orderId
        })
        if (response.ok) {
            alert('ГУД!')
        }
        else {
            alert('Беда')
        }
    }
    catch (e) {
        console.log(e)
    }
}
export const get_orders = async (driverLogin) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/get', {
        })
        if (response.ok) {
            alert('ГУД!')
        }
        else {
            alert('Беда')
        }
    }
    catch (e) {
        console.log(e)
        return [{ "id": 1, "plant": "Apple", "amount": 2, "address1": "SPB", "address2": "Kazan", "farmerName": "Ashot", "date": "2023-05-23" }]
    }
}
export const add_car = async (driverLogin, mark, number, capacity) => {
    try {
        const response = await axios.post('http://localhost:8190/car_driver/add', {
            driverLogin,
            mark,
            number,
            capacity
        })
        if (response.ok) {
            alert('ГУД!')
        }
        else {
            alert('Беда')
        }
    }
    catch (e) {
        console.log(e)
    }
}
export const get_cars = async (driverLogin) => {
    try {
        const response = await axios.post('http://localhost:8190/order_driver/get', {
            driverLogin
        })
        if (response.ok) {
            alert('ГУД!')
        }
        else {
            alert('Беда')
        }
    }
    catch (e) {
        console.log(e)
        return [{ "id": 0, "mark": "Mersedes", "number": "E167HC", "capacity": 3000 },
        { "id": 1, "mark": "BMW", "number": "A132CB", "capacity": 2000 }]
    }
}