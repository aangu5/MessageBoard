package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardUtilTest {

    @Test
    public void inputFormattingTest1() {
        String input1 = "ANGUS";
        assertEquals(BoardUtil.inputFormatting(input1), "angus");
    }

    @Test
    public void inputFormattingTest2() {
        String input2 = "ANGUs's";
        assertEquals(BoardUtil.inputFormatting(input2), "angus's");
    }

}
