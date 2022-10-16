# polushack2022-web
Бэкенд и фронтэнд с хакатона. Мобилка в другом репозитории, ссылку на который оставим позже



### База данных
Как БД мы используем PostgreSQL 14 версии. В папке database/ лежат скрипты для инициализации БД и volume из docker-контейнера, дабы сохранять данные на хостовой машине, а не только в докере. 

Помимо БД в docker-контейнере крутится adminer, который доступен на localhost по порту, указываемому в .env <br>
*Как сервер в adminer нужно указывать db, иначе зайти вы не сможете*

Все настройки БД тянет из конфига .env <br>
**При ребилде docker-compose** <br>
нужно прописывать `sudo rm -rf ./database/postgres_data`, иначе же база данных останется в прежнем положении и переменные из .env и скрипты из ./database/initial_scripts работать **не будут!**

=======
# PolusHackathonProject

*Mobile app for drivers of Polus Company*

### Task Description
The main purpose of this app was showing task for drivers, in order to optimize the work of logistics.</br>
Driver must authorize in application, get a token (btw it can expire).</br>
After authorization (or after launching screen in case he has already authorized), driver'll be redirected to task screen.</br>
The task will arrive from server. He can also look at the maps where he will need to drive and observe his last tasks at history screen

### Technology stack:
- MVVM
- Clean Architecture
- Kotlin Coroutines
- Retrofit
- Gson
- Jetpack Navigation
- RecyclerView
- Yandex MapKit

### Screenshots


<p>
<img src="https://user-images.githubusercontent.com/98749008/196021838-f5ee745d-7d62-4392-9af9-b19c16803c0a.jpg" alt="third" width="181"/>
<img src="https://user-images.githubusercontent.com/98749008/196021563-fcb09544-5b27-48b3-809d-a65228c8aaac.png" alt="second" width="200"/>
<img src="https://user-images.githubusercontent.com/98749008/196021481-edba32b4-6586-482a-934e-0321d0354f30.png" alt="first" width="200"/>
</p>
