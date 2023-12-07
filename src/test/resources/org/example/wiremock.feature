#language: ru

Функционал: Wiremock

  @wiremock
  Сценарий: Проверка стаба всех курсов
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу со списком курсов
    Тогда Список курсов актуален JSON

  @wiremock
  Сценарий: Проверка стаба всех курсов
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу со списком курсов
    Тогда Список курсов актуален XML

  @wiremock
  Сценарий: Проверка стаба всех пользователей
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу со списком пользователей
    Тогда Список пользователей актуален JSON

  @wiremock
  Сценарий: Проверка стаба всех пользователей
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу со списком пользователей
    Тогда Список пользователей актуален XML

  @wiremock
  Сценарий: Проверка стаба оценки пользователя
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу с оценкой пользователя 1
    Тогда Оценка пользователя 1 актуальна JSON

  @wiremock
  Сценарий: Проверка стаба оценки пользователя
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу с оценкой пользователя 23
    Тогда Оценка пользователя 23 актуальна XML