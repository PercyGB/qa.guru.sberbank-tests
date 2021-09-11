# Проект по автоматизации тестирования ресурса "Частным клиентам — СберБанк" (https://www.sberbank.ru/ru/person)

### Используемые технологии и инструменыт:
<p align="left">
<img height="40" width="40" src="src/test/resources/images/java-logo.svg" alt=""java">
<img height="40" width="40" src="src/test/resources/images/gradle-logo.svg" alt="gradle">
<img height="40" width="40" src="src/test/resources/images/git-logo.svg" alt="git">
<img height="40" width="40" src="src/test/resources/images/junit5-logo.svg" alt="junit5">
<img height="40" width="40" src="src/test/resources/images/selenide-logo.svg" alt="selenide">
<img height="40" width="40" src="src/test/resources/images/jenkins-logo.svg" alt="jenkins">
<img height="40" width="40" src="src/test/resources/images/selenoid-logo.svg" alt="selenoid">
<img height="40" width="40" src="src/test/resources/images/allure-Report-logo.svg" alt="allure">
<img height="40" width="40" src="src/test/resources/images/allure-ee-logo.svg" alt="allure-testops">
</p>

### Реализованы следующие UI проверки:
- [X] Проверка мининимального значения величины вклада
- [X] Проверка величины дохода по вкладу
- [X] Проверка величины процента по вкладу
- [X] Проверка работы ссылки "Все вопросы и ответы"
- [X] "Проверка работы ссылки "Все новости" 
- [X] Проверка заголовка страницы
- [X] Проверка консоли браузера на наличие ошибок

Run tests with filled remote.properties:
```bash
gradle clean test
```

Run tests with not filled remote.properties:
```bash
gradle clean -DremoteDriverUrl=https://%s:%s@selenoid.autotests.cloud/wd/hub/ -DvideoStorage=https://selenoid.autotests.cloud/video/ -Dthreads=1 test
```

Serve report:
```bash
allure serve build/allure-results
```


###### For further development there are some example tests in src/test/java/cloud.autotests/tests/demowebshop
* remove @Disabled("...") annotation to run tests
```bash
gradle clean demowebshop
```

:heart: <a target="_blank" href="https://qa.guru">qa.guru</a><br/>
:blue_heart: <a target="_blank" href="https://t.me/qa_automation">t.me/qa_automation</a>
