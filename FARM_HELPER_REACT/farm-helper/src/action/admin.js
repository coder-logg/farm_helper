import axios from 'axios';
export const add_plant = async (name, cost, timeForCompleted, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/plants', {
            name,
            cost,
            timeForCompleted,
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const get_plants = async (auth) => {
    try {
        const response = await axios.get('http://localhost:8190/plants/all', { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const add_equipment = async (name, cost, location, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/equipments', {
            name,
            cost,
            location
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const get_equipments = async (auth) => {
    try {
        const response = await axios.get('http://localhost:8190/equipments/all', { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const get_arbitration = async (auth) => {
    try {
        const response = await axios.get('http://localhost:8190/admin/get_arbitrarion', {
        }, { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}
export const add_arbitration = async (loginAdmin, orderId, balance, auth) => {
    try {
        const response = await axios.post('http://localhost:8190/admin/add_arbitrarion', {
            loginAdmin,
            orderId,
            balance
        }, { headers: { "Authorization": auth } })
        window.location.reload();
    }
    catch (e) {
        console.log(e)
    }
}
export const get_customers = async (auth) => {
    try {
        const response = await axios.get('http://localhost:8190/customers', { headers: { "Authorization": auth } })
        return response.data;
    }
    catch (e) {
        console.log(e)
    }
}