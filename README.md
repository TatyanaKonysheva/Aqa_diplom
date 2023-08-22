# Инструкция по запуску тестов

1. Выполнить клонирование проекта `git clone git@github.com:TatyanaKonysheva/Aqa_diplom.git`.
2. Открыть проект в IntelliJ IDEA.
3. В терминале запустить контейнер командой `docker-compose up --build`.
4. Выполнить запуск приложения `java -jar artefacts/aqa-shop.jar` в терминале.
5. Sut открывается по адресу `http://localhost:8080/`.
6. В командной строке запустить тесты `gradlew clean test --info`.
7. После прохождения тестов в командной строке выполнть команду `gradlew allureServe` для формирования отчета Allure.
8. После заврешения тестирования в терминале остановить контейнер командой `docker-compose down`.
9. Закрыть приложение IntelliJ IDEA.
