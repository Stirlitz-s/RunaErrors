package com.stsoft.runaerrors;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
//import com.google.common.io.ByteStreams;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class Errors {
    private static Set<SystemError> systemErrors = Collections.synchronizedSet(new HashSet<>());


    /* Этот метод остался без изменений. 
     * Алгоритмическая сложность Collection.sort O(n*log(n)).
     * Алгоритмическя сложность new ArrayList<>(systemErrors) O(n)
     * так как проиходит полный проход по коллекции с помощью итератора.
     * */
    public static List<SystemError> getSystemErrors() {
        synchronized (systemErrors) {
            List<SystemError> list = new ArrayList<>(systemErrors);
            Collections.sort(list);
            return list;
        }
    }

    //Этот метод остался без изменений. Алгоритмическая сложность его также O(1)
    public static void addSystemError(Throwable throwable) {
        SystemError systemError = new SystemError(throwable);
        boolean alreadyExists = systemErrors.add(systemError);
        if (!alreadyExists) {
            System.out.println(systemErrors);
         //   sendEmailNotification(systemError);
        }
    }

    /* Оригинальный метод - совершается проход по всей коллекции
     * и время работы алгоритма увеличивается линейно
     * в зависимости от размера. Соответственно его 
     * алгоритмическая сложность O(n)
    */
    public static void removeSystemError(String errorMessage) {
        synchronized (systemErrors) {
            for (SystemError systemError : systemErrors) {
                if (Objects.equal(systemError.getMessage(), errorMessage)) {
                    systemErrors.remove(systemError);
                    break;
                }
            }
        }
    }
    
    /* Модифицированный метод - используется стандартное удаление
     * из HashSet его алгоритмическая сложность O(1)
    */
    public static void removeSystemErrorOptimized(String errorMessage) {
        SystemError toDeleteSystemError = new SystemError();
        toDeleteSystemError.setMessage(errorMessage);
        synchronized (systemErrors) {
            systemErrors.remove(toDeleteSystemError);
        }
    }

    //Метод добавлен для тестирования
    public static void addSystemError(Throwable throwable, String message, Date occurredDate) {
        SystemError systemError = new SystemError(throwable);
        systemError.setMessage(message);
        systemError.setOccurredDate(occurredDate);
        boolean alreadyExists = systemErrors.add(systemError);
        if (!alreadyExists) {
            System.out.println(systemErrors);
         //   sendEmailNotification(systemError);
        }
    }
    
    public static void clearSystemErrorsSet() {
        systemErrors.clear();
    }


}
