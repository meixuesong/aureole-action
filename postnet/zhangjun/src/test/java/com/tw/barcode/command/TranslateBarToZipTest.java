package com.tw.barcode.command;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TranslateBarToZipTest {

    @Test
    public void should_validate_error_length() throws Exception {
        TranslateBarToZip toZip = new TranslateBarToZip();
        assertEquals("length error", toZip.validate("|:::||::|:|::||::|::|:|:|::|:|"));
    }

    @Test
    public void should_check_cd() throws Exception {
        TranslateBarToZip toZip = new TranslateBarToZip();
        assertEquals("error cd", toZip.translate("|:::||::|:|::||::|::|:|:|:||:::|").reply);
    }

    @Test
    public void should_translate_contain_hyphon() throws Exception {
        TranslateBarToZip toZip = new TranslateBarToZip();
        assertEquals("12345-6789", toZip.translate("|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|:::|:|:|").reply);
    }
}
