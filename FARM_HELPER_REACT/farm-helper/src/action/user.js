import axios from 'axios';

export const registration = async (email = "", login, password) => {
    try {
        const response = await axios.post('http://localhost/5000/api/auth/registation', {
            email,
            login,
            password
        })
        alert(response.data.message)
    }

    catch (e) {
        alert(e)


    }
}