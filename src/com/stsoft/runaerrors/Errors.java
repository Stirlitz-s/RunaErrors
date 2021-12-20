package com.stsoft.runaerrors;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.ForwardingSortedSet;
import com.google.common.collect.Lists;
//import com.google.common.io.ByteStreams;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.function.Function;

public class Errors {
    private static Set<SystemError> systemErrors = Collections.synchronizedSet(new LinkedHashSet<>());


    /* ���� ����� ������� ��� ���������. 
     * ��������������� ��������� Collection.sort O(n*log(n)).
     * �������������� ��������� new ArrayList<>(systemErrors) O(n)
     * ��� ��� ��������� ������ ������ �� ��������� � ������� ���������.
     * */
    public static List<SystemError> getSystemErrors() {
        synchronized (systemErrors) {
            System.out.println("Excepted tree size - " + systemErrors.size());
            List<SystemError> list = new ArrayList<>(systemErrors);
            Collections.sort(list);
            return list;
        }
    }
    public static List<SystemError> getSystemErrorsOptimized() {
        synchronized (systemErrors) {
            System.out.println("Actual tree size - " + systemErrors.size());
            List<SystemError> list = new ArrayList<>(systemErrors);
            list = Lists.reverse(list);
     //       Collections.sort(list);
            return list;
        }
    }

    //���� ����� ������� ��� ���������. ��������������� ��������� ��� ����� O(1)
    public static void addSystemError(Throwable throwable) {
        SystemError systemError = new SystemError(throwable);
        boolean alreadyExists = systemErrors.add(systemError);
        if (!alreadyExists) {
            System.out.println(systemErrors);
         //   sendEmailNotification(systemError);
        }
    }

    /* ������������ ����� - ����������� ������ �� ���� ���������
     * � ����� ������ ��������� ������������� �������
     * � ����������� �� �������. �������������� ��� 
     * ��������������� ��������� O(n)
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
    
    /* ���������������� ����� - ������������ ����������� ��������
     * �� HashSet ��� ��������������� ��������� O(1)
    */
    public static void removeSystemErrorOptimized(String errorMessage) {
        SystemError toDeleteSystemError = new SystemError();
        toDeleteSystemError.setMessage(errorMessage);
        synchronized (systemErrors) {
            systemErrors.remove(toDeleteSystemError);
        }
    }

    //����� �������� ��� ������������
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
