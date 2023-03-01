import axios from 'axios';
export const add_order = async (farmerLogin, deliveryAddress, plantAmount, plantId) => {
    try {
        const response = await axios.post('http://localhost:8190/order/add', {
            farmerLogin,
            deliveryAddress,
            plantId,
            plantAmount,

        })
        if (response.ok) {
            alert('ГУД!')
        }
        else {
            alert('Беда')
        }
    }
    catch (e) {
        if (e.response && e.response.status === 409) {
            alert('Пользователь с такими данными уже зарегистрирован')
        }
    }
}
export const get_orders = async (id) => {
    try {
        const response = await axios.post(`http://localhost:8190/order/${id}`, {
            id,
        })
    }
    catch (e) {
        if (e.response && e.response.status === 409) {
            alert('Пользователь с такими данными уже зарегистрирован')
        }
    }
}