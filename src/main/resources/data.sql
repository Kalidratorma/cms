insert into site (id, name, description, base_url)
values(1,'snowmoscow', 'Зимний фестиваль Снега и Льда в Москве', 'https://snowmoscow.ru/');

insert into page (site_id, id, path_name)
values ( 1, 1, 'main');

insert into helmet (page_id, id, title, description, url)
values ( 1, 1, 'Снег и лёд в Москве | Зимний фестиваль',
        'Ждем вас в парке «Музеон». Снежные скульптуры, ледяные горки, мастер-классы и другие активности для всей семьи. Вход свободный.',
        'https://snowmoscow.ru/');

insert into navigation (page_id, id)
values ( 1, 1);

insert into nav_link (navigation_id, id, label, link)
values ( 1, 1, 'О фестивале', 'festival');
insert into nav_link (navigation_id, id, label, link)
values ( 1, 2, 'Локации', '#locations');
insert into nav_link (navigation_id, id, label, link)
values ( 1, 3, 'Зона творчества', 'artzone');

insert into section(PAGE_ID, ID, NAME, TITLE, DESCRIPTION, CONTENT)
values (1, 1, 'hero','Снег и лед в москве','Сезон «Чудеса России»', '"bullits": [
                {
                    "icon": "calendar",
                    "text": "28 декабря 2022 года —<br>28 января 2023 года"
                },
                {
                    "icon": "pin",
                    "text": "Парк искусств «Музеон»"
                },
                {
                    "icon": "clocks",
                    "text": "Ежедневно с 11:00 до 21:00"
                }
            ]');
insert into section(PAGE_ID, ID, NAME, TITLE, DESCRIPTION, CONTENT)
values (1, 2, 'ad','Зимняя сказка с&nbsp;гигантским замком,<br>снежными скульптурами,<br>ледяным лабиринтом и&nbsp;возможностью создать свой шедевр'
,'Зимняя сказка', ' "button": {
                "link": "https://t.me/sneg_led_2023",
                "text": "телеграм-канал фестиваля"
            }');
insert into section(PAGE_ID, ID, NAME, TITLE, DESCRIPTION, CONTENT)
values (1, 3, 'about','О фестивале','НАСТАЛО ВРЕМЯ УВИДЕТЬ НЕПОВТОРИМЫЕ ЧУДЕСА РОССИИ НЕ&nbsp;ПОКИДАЯ МОСКВУ'
,'"paragraphs" : [
                "Хотите увидеть, как изо льда и&nbsp;снега появляются герои любимых сказок, сюжеты народного творчества, красота природы и&nbsp;даже известные люди? Приходите на&nbsp;фестиваль «Снег и&nbsp;лёд в&nbsp;Москве», где прямо на&nbsp;ваших глазах происходят настоящие чудеса.",
                "Познакомьтесь с&nbsp;уникальными ледяными творениями, почувствуйте гордость за&nbsp;нашу страну, загляните в&nbsp;замок чудес с&nbsp;многоуровневыми горками или создайте собственное произведение снежного искусства под руководством скульпторов.",
                "Приходите, чтобы весело провести<br>время всей семьей!"
            ]');
insert into section(PAGE_ID, ID, NAME, TITLE, DESCRIPTION, CONTENT)
values (1, 4, 'locations','Локации','Уважаемые гости фестиваля! Мы&nbsp;можем закрывать некоторые объекты, когда на&nbsp;улице плюсовая температура, чтобы они не&nbsp;испортились.'
,'"slides": [
                {
                    "title": "Арт-Объекты",
                    "text": "Ледяной обелиск высотой 10&nbsp;метров, застывший водяной кролик, главный волшебник страны&nbsp;— а&nbsp;сколько еще арт-объектов вы&nbsp;найдете в&nbsp;парке?",
                    "img": "img/location1.jpg"
                },
                {
                    "title": "Арт-Объекты",
                    "text": "Ледяной обелиск высотой 10&nbsp;метров, застывший водяной кролик, главный волшебник страны&nbsp;— а&nbsp;сколько еще арт-объектов вы&nbsp;найдете в&nbsp;парке?",
                    "img": "img/location1.jpg"
                }
            ]');

