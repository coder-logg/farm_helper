# FalmHelper
#### Регистрация
URL: `/registration` \
Пример  ожидаемого `application/json`

    {
        "login": "HD5hkP2sm",
        "phone": "15864202965",
        "mail": "c9HZuoD5cL@qq.com",
        "password": "AAAAAA",
        "role": "ADMIN"
    }
Возможные значения поля `role`: `ADMIN`, `FARMER`, `DRIVER` \
В ответе присылается редирект (поле `Location` в заголовках ответа) на главную страницу пользователя.
####  Вход в аккаунт
URL: `/login` \
В заголовках запроса должено быть поле `Authorization` со значением зашифрованных согласно
HTTP Basic Authorization именем и паролем пользователя.\
В ответе присылается редирект (поле `Location` в заголовках ответа) на главную страницу пользователя.

#### Запуск Swagger
URL: http://localhost:8192/swagger-ui.html \
Запускается вместе со Spring'ом 


