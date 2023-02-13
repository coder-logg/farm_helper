import axios from 'axios';

export const registration = async (login, phone, mail, password, role) => {
    // try {
    //     const response = await axios.post('http://localhost:8190/registation', {
    //         login,
    //         phone,
    //         mail,
    //         password,
    //         role
    //     })
    //     console.log(response.data.message)
    // }

    // catch (e) {
    //     alert(e)
    // }
    axios.post("http://localhost:8190/registation", {
        login,
        phone,
        mail,
        password,
        role
    })
        .then(function (response) {
            console.log(response);
        })
        .catch(function (error) {
            console.log(error);
        });
}