# Учебный проект "Сетевое хранилище (NetFileWarehouse)"

Клиент-серверное приложение для простой организации облачного хранения файлов.

Сервер выполняет backend-функции, по обработке запросов клиентской программы - прием/отправка файлов, создание папок и удаление файлов/папок в облаке.

Клиентская часть позволяет конкретным пользователям отправлять/загружать файлы в/из облака, создавать папки и удалять файлы/папки в облаке и локальных папках.

Реализована система прав доступа (полный или ограниченный доступ, блокирование).

Пользователи и информация об их правах хранится в БД.

Имеется возможность регистрации новых пользователей.

При создании использованы:

    OracleJDK 18,
    Netty
    JavaFX

Для запуска сервера: ./ru/gb/cloudserver/CloudServer

Для запуска клиента: ./ru/gb/client/NetFileWarehouse

