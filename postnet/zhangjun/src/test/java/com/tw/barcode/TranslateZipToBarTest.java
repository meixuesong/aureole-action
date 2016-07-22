package com.tw.barcode;

import com.tw.barcode.command.TranslateZipToBar;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TranslateZipToBarTest {
    @Test
    public void should_translate_5zip_to_6bar() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("|:::||::|:|::||::|::|:|:|::|:|:|", toBar.translate("12345"));
    }

    @Test
    public void should_translate_9zip_to_10bar() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|:::|:|:|", toBar.translate("12345-6789"));
    }


    @Test
    public void should_translate_zip_to_bar_error() throws Exception {
        TranslateZipToBar toBar = new TranslateZipToBar();
        assertEquals("length error", toBar.validate("1234"));
        assertEquals("character error", toBar.validate("abcd"));

    }
}
