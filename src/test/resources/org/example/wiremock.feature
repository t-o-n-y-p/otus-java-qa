#language: ru

Функционал: Wiremock

  @wiremock
  Сценарий: Проверка стаба всех пользователей
    Пусть Я открываю браузер Chrome
    Если Я открываю страницу со списком пользователей
    Тогда Список пользователей актуален
