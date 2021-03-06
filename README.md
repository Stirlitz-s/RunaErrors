# RunaErrors
Class Errors optimization


<h2>Задание</h2>
Оптимизировать алгоритмическую сложность класса https://github.com/processtech/runawfe-free-server/blob/master/wfe-core/src/main/java/ru/runa/wfe/commons/Errors.java в части работы с системными ошибками (SystemError).
Для простоты можно считать, что реализация класса Errors идентична этой - https://pastebin.com/LAU4vQMG . Кроме того, допустимо считать, что класс используется исключительно в однопоточной среде, т.е. можно игнорировать необходимость потокобезопасной реализации и представить, что в классе не используются synchronized  и synchronizedSet конструкции.
 
Перед началом оптимизации необходимо определить алгоритмическую сложность существующих методов getErrors, addError и removeError.
Предлагается начать с оптимизации метода removeError.
 
Решение тестового задания должно содержать в себе:
1. Исходную алгоритмическую сложность методов класса.
2. Алгоритмическую сложность методов, которой удалось добиться.
3. Ссылку на GitHub репозиторий, в котором можно ознакомиться с решением.
4. Юнит-тесты, доказывающие, что семантика метода не изменилась.
 
Алгоритмическую сложность можно указать в readme GitHub репозитория.

<h2>Комментарии к решению</h2>

<h3>removeSystemError</h3>
Оригинальный метод - совершается проход по всей коллекции и время работы алгоритма увеличивается линейно в зависимости от размера. Соответственно его алгоритмическая сложность O(n).
Так как эквальность объектов класса SystemError проверяется по сообщению, то создав экземпляр класса SystemError и установив ему искомое сообщение можно использовать стандартный метод удаление который имеет сложность O(1).

<h3>addSystemError</h3>
Данный метод имеет алгоритмическую сложность O(1) и оптимизировать его не представляется возможным.

<h3>getSystemErrors</h3>
В методе выполняются два действия. Первый со сложностью O(n) - это конструктор класса ArrayList с параметром типа HashSet. И второй метод Collection.sort, где происходит сортировка по дате. Его сложность O(n*log(n)).
Оптимизация выполнена с помощью изменения типа данных на LinkedHashSet. После получения списка его нужно отразить зеркально используя Lists.reverse() (на сколько мне известно этот метод работает быстрее чем Collection.reverse()). 

