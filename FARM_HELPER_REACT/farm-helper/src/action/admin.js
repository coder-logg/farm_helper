import axios from 'axios';
export const add_plant = async (name, cost, timeForCompleted, requiredEquipmentId) => {
    try {
        const response = await axios.post('http://localhost:8190/admin/add_plant', {
            name,
            cost,
            timeForCompleted,
            requiredEquipmentId
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
export const get_plants = async () => {
    try {
        const response = await axios.get('http://localhost:8190/admin/get_plants', {
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
        return [{ "id": 1, "plant": "Apple", "cost": 1000, "timeForCompleted": 2 }]
    }
}
export const add_equipment = async (name, cost, location) => {
    try {
        const response = await axios.post('http://localhost:8190/admin/equipment', {
            name,
            cost,
            location
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
export const get_equipments = async () => {
    try {
        const response = await axios.get('http://localhost:8190/admin/get_equipments', {
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
        return [{ "id": 1, "name": "Супер лампа", "cost": 1000, "location": "SPB" }]
    }
}
export const get_arbitration = async () => {
    try {
        const response = await axios.get('http://localhost:8190/admin/get_arbitrarion', {
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
        return [{ "id": 1, "admin_id": 3, "order_id": 1000, "driver_id": 2, "farmer_id": 2, "order_for_drive_id": 2 }]
    }
}
export const add_arbitration = async (loginAdmin, orderId, balance) => {
    try {
        const response = await axios.post('http://localhost:8190/admin/add_arbitrarion', {
            loginAdmin,
            orderId,
            balance
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
        return [{ "id": 1, "admin_id": 3, "order_id": 1000, "driver_id": 2, "farmer_id": 2, "order_for_drive_id": 2 }]
    }
}