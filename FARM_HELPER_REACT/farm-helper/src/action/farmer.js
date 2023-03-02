import axios from 'axios';
export const add_order = async (farmerLogin, deliveryAddress, plantAmount, plantId, deliveryDate, customerId, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/orders/add', {
            status: { location: "SPB" },
            farmerLogin,
            deliveryAddress,
            plantId,
            plantAmount,
            deliveryDate,
            customerId
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const add_driver = async (farmerLogin, orderId, driverId, auth) => {
    try {
        const response = await axios.post(`http://localhost:8190/choose_driver`, {
            farmerLogin,
            orderId,
            driverId
        })
        window.location.reload()
    }
    catch (e) {
        console.log(e)
    }
}
export const add_farm = async (farmerLogin, location, square, soil_type, sunlight, price) => {
    try {
        const response = await axios.post(`http://localhost:8190/create_farm`, {
            farmerLogin,
            location,
            square,
            soil_type,
            sunlight,
            price
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
export const add_review = async (login, loginToReview, rate, message) => {
    try {
        const response = await axios.post(`http://localhost:8190/add_review`, {
            login,
            loginToReview,
            rate,
            message
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
export const get_orders = async (id, auth) => {
    try {
        const response = await axios.get(`http://localhost:8190/farmer/${id}/orders`, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const get_drivers = async () => {
    try {
        const response = await axios.get(`http://localhost:8190/drivers/`, {
        })
        // return response.data;
        return [
            { "id": 0, "plant": "Apple", "id_driver": "1", "amount": 2, "address": "SPB", "customer": "Artur", "driver": "NULL" }]
    }
    catch (e) {
        console.log(e)
        return [
            { "id": 0, "name": "Artur", "rate": 10, "car_capacity": 400 },
            { "id": 1, "name": "Anton", "rate": 8, "car_capacity": 500 },
            { "id": 2, "name": "Roman", "rate": 9, "car_capacity": 100 }]

    }
}
export const get_reviews = async (id) => {
    try {
        const response = await axios.get(`http://localhost:8190/reviews/`, {
            id
        })
        // return response.data;
        return []
    }
    catch (e) {
        console.log(e)
        return [
            { "id": 0, "login": "Artur", "rate": 10, "message": "GOOD" },
            { "id": 1, "login": "Anton", "rate": 8, "message": "NICE" },
            { "id": 2, "login": "Roman", "rate": 9, "message": "PERFECT" }]
    }
}
export const get_farms = async (id) => {
    try {
        const response = await axios.get(`http://localhost:8190/farms/`, {
            id
        })
        // return response.data;
        return []
    }
    catch (e) {
        console.log(e)
        return [
            { "id": 0, "location": "SPB", "square": 2000, "soil_type": "почва", "sunlight": 10, "price_per_month": 1000 },
            { "id": 1, "location": "USA", "square": 3000, "soil_type": "чернозем", "sunlight": 15, "price_per_month": 2000 },
            { "id": 2, "location": "China", "square": 1500, "soil_type": "глина", "sunlight": 16, "price_per_month": 500 }]
    }
}