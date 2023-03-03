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
        }, { headers: { "Authorization": auth } })
        window.location.reload()
    }
    catch (e) {
        console.log(e)
    }
}
export const add_farm = async (farmerLogin, location, square, soil_type, sunlight, price, auth) => {
    try {
        const response = await axios.post(`http://localhost:8190/create_farm`, {
            farmerLogin,
            location,
            square,
            soil_type,
            sunlight,
            price
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const add_review = async (senderLogin, recipientLogin, rate, message, auth) => {
    try {
        const response = await axios.post(`http://localhost:8190/reviews`, {
            senderLogin,
            recipientLogin,
            rate,
            message
        }, { headers: { "Authorization": auth } })
        window.location.reload();
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
export const get_drivers = async (auth) => {
    try {
        const response = await axios.get(`http://localhost:8190/drivers/`, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const get_reviews = async (id, auth) => {
    try {
        const response = await axios.get(`http://localhost:8190/${id}/reviews-for-me`, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const get_farms = async (id, auth) => {
    try {
        const response = await axios.get(`http://localhost:8190/farms/`, {
            id
        }, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}