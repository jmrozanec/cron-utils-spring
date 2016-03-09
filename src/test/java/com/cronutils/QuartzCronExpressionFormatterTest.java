package com.cronutils;

import org.junit.Before;
import org.quartz.CronExpression;

import java.util.Locale;

import static org.junit.Assert.*;

public class QuartzCronExpressionFormatterTest {
    private QuartzCronExpressionFormatter formatter;

    @Before
    public void setUp(){
        formatter = new QuartzCronExpressionFormatter();
    }

    @org.junit.Test
    public void testPrintExpressionOK() throws Exception {
        QuartzCronExpressionFormatter formatter = new QuartzCronExpressionFormatter();
        assertNotNull(formatter.print(new CronExpression("* * * ? * * *"), Locale.UK));
        assertNotNull(formatter.print(new CronExpression("* * * ? * *"), Locale.UK));
    }

    @org.junit.Test(expected = UnsupportedOperationException.class)
    public void testParse() throws Exception {
        formatter.parse("every second", Locale.UK);
    }
}