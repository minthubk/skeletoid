package com.mindera.skeletoid.logs.appenders;

import android.content.Context;

import com.mindera.skeletoid.logs.LOG;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class LogFileAppenderUnitTest {

    private String PACKAGE_NAME = "my.package.name";
    private final String FILE_NAME = "FILENAME";


    @Test(expected = IllegalArgumentException.class)
    public void testConstructorAllNull() {
        new LogFileAppender(null, null, false);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorPackageNameNull() {
        new LogFileAppender(null, FILE_NAME, false);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFileNameInvalid() {
        new LogFileAppender(PACKAGE_NAME, "*", false);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFileNameNull() {
        new LogFileAppender(PACKAGE_NAME, null, false);

    }

    @Test
    public void testFileNameIsValidEmpty() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);
        assertFalse(appender.isFilenameValid(""));
    }

    @Test
    public void testFileNameIsValidInvalid() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);
        assertFalse(appender.isFilenameValid("//"));
    }

    @Test
    public void testFileNameIsValid() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);
        assertTrue(appender.isFilenameValid(FILE_NAME));
    }


    @Test
    public void testConstructor() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);

        assertEquals("LogFileAppender", appender.getLoggerId());
    }

    @Test
    public void testEnableAppender() {
        Context context = mock(Context.class);

        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);

        appender.enableAppender(context);

        assertFalse(appender.canWriteToFile());
    }


    @Test
    public void testDisableAppender() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);
        appender.disableAppender();

        assertFalse(appender.canWriteToFile());

    }

    @Test
    public void testSetMaxLineLength() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);


        appender.setLogFileSize(1000);
        assertEquals(1000, appender.getLogFileSize());
    }


    @Test
    public void testSetNumberOfLogFiles() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);

        appender.setNumberOfLogFiles(5);
        assertEquals(5, appender.getNumberOfLogFiles());
    }

    @Test
    public void testSetMinLogLevel() {
        LogFileAppender appender = new LogFileAppender(PACKAGE_NAME, FILE_NAME, false);

        appender.setMinLogLevel(LOG.PRIORITY.DEBUG);
        assertEquals(LOG.PRIORITY.DEBUG, appender.getMinLogLevel());
    }

}
