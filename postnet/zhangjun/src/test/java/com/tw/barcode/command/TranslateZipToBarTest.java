package com.tw.barcode.command;

import com.tw.barcode.command.TranslateZipToBar;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

public class TranslateZipToBarTest {

    @Test
    public void should_validate_5zip() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        String errorTips = toBar.validate("12345");

        assertNull(errorTips);
    }

    @Test
    public void should_validate_error_length() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("length error", toBar.validate("1234"));

    }

    @Test
    public void should_validate_error_character() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("character error", toBar.validate("abcd"));

    }

    @Test
    public void should_translate_9zip_to_10bar() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|:::|:|:|", toBar.translate("12345-6789").reply);
    }

    @Test
    public void should_translate_5zip_to_6bar() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("|:::||::|:|::||::|::|:|:|::|:|:|", toBar.translate("12345").reply);
    }

}
