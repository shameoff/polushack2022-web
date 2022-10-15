# polushack2022-web
Бэкенд и фронтэнд с хакатона. Мобилка в другом репозитории, ссылку на который оставим позже



### База данных
Как БД мы используем PostgreSQL 14 версии. В папке database/ лежат скрипты для инициализации БД и volume из docker-контейнера, дабы сохранять данные на хостовой машине, а не только в докере. 

Помимо БД в docker-контейнере крутится adminer, который доступен на localhost по порту, указываемому в .env <br>
*Как сервер в adminer нужно указывать db, иначе зайти вы не сможете*

Все настройки БД тянет из конфига .env <br>
**При ребилде docker-compose** <br>
нужно прописывать `sudo rm -rf ./database/postgres_data`, иначе же база данных останется в прежнем положении и переменные из .env и скрипты из ./database/initial_scripts работать **не будут!**

