#language: ru

  Функционал: Домашняя работа 2

    @homework2
    Сценарий: Chrome Выбор специализации по названию
      Пусть Я открываю браузер Chrome
      Если Я открываю главную страницу
      И Я выбираю специализацию QA Automation Engineer
      Тогда Открылась страница курса QA Automation Engineer

    @homework2
    Сценарий: Firefox Выбор специализации по названию
      Пусть Я открываю браузер Mozilla Firefox
      Если Я открываю главную страницу
      И Я выбираю специализацию Системный аналитик
      Тогда Открылась страница курса Системный аналитик

    @homework2
    Сценарий: Chrome Вывод в консоль специализаций по дате
      Пусть Я открываю браузер Google Chrome
      Если Я открываю главную страницу
      Тогда Вывести в консоль специализации, стартующие 1 ноября или позже

    @homework2
    Сценарий: Firefox Вывод в консоль специализаций по дате
      Пусть Я открываю браузер Firefox
      Если Я открываю главную страницу
      Тогда Вывести в консоль специализации, стартующие 1 января 2024 года или позже