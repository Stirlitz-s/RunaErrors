package com.stsoft.runaerrors;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class RemoveSignatureTest {
    private final int MAX_NUM_ERRORS = 100;

    @Test
    void test() {
        for (int i = 0; i < MAX_NUM_ERRORS; i++) {
            Throwable tg = new Throwable();
            Errors.addSystemError(tg, "mess" + i, new Date(123456789 + i));
        }
        
        for (int i = 0; i < MAX_NUM_ERRORS; i++) {
            if (i % 2 == 0) {
                Errors.removeSystemError("mess" + i);
            }
        }
        
        List<SystemError> expected = Errors.getSystemErrors();
        
        Errors.clearSystemErrorsSet();
        
        for (int i = 0; i < MAX_NUM_ERRORS; i++) {
            Throwable tg = new Throwable();
            Errors.addSystemError(tg, "mess" + i, new Date(123456789 + i));
        }
        
        for (int i = 0; i < MAX_NUM_ERRORS; i++) {
            if (i % 2 == 0) {
                //Errors.removeSystemErrorOptimized("mess" + i);
                Errors.removeSystemError("mess" + i);
            }
        }
        
        List<SystemError> actual = Errors.getSystemErrorsOptimized();
        
        for (int i = 0; i < MAX_NUM_ERRORS/2; i++) {
            System.out.println("expected: " + expected.get(i).getMessage()
                    + "; actual: " + actual.get(i).getMessage() + ";");
        }

        assertEquals(actual, expected);
    }

}
