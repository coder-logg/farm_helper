import axios from 'axios';

export const registration = async (login, phone, mail, password, role, history) => {
    try {
        const response = await axios.post('http://localhost:8190/registration', {
            login,
            phone,
            mail,
            password,
            role
        })
        console.log(response.data.message)
        if (response.data.login == response.data.login) {
            const state = response.data;
            const auth = 'Basic ' + btoa(login + ':' + password)
            console.log(state)
            if (response.data.role == "FARMER") {
                history(`/farmer/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return true;
            }
            if (response.data.role == "DRIVER") {
                history(`/driver/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return true;
            }
            if (response.data.role == "ADMIN") {
                history(`/admin/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return true;
            }
        }
    }
    catch (e) {
        if (e.response && e.response.status === 409) {
            alert('Пользователь с такими данными уже зарегистрирован')
        }
    }
}
export const Log = async (login, password, history) => {
    try {
        const response = await axios.get(`http://localhost:8190/login`,
            {
                headers: { 'Authorization': 'Basic ' + btoa(login + ':' + password) }
            }
        )
        const auth = 'Basic ' + btoa(login + ':' + password)
        const state = response.data;
        if (response.data.login == login) {
            if (response.data.role == "FARMER") {
                history(`/farmer/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return true;
            }
            if (response.data.role == "DRIVER") {
                history(`/driver/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return response;
            }
            if (response.data.role == "ADMIN") {
                history(`/admin/${response.data.login}`, { state: { response: response.data, auth: auth } });
                return true;
            }
        }
        console.log(response.data.message)
    }

    catch (e) {
        alert(e)
    }
}