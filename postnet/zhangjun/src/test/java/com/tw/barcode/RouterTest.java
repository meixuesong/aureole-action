package com.tw.barcode;

import com.tw.barcode.command.AppUseage;
import com.tw.barcode.command.TranslateZipToBar;
import com.tw.barcode.command.ZipToBar;
import org.junit.Test;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RouterTest {
    @Test
    public void should_return_apptips_router() throws Exception {
        Router router = new Router();
        String tips = router.request(null);

        assertTrue(router.command instanceof AppUseage);

        assertEquals("1 zip to bar\n2 bar to zip\n3 exit", tips);
    }

    @Test
    public void should_check_input_1() throws Exception {
        Router router = new Router();
        String tips = router.request("1");

        assertTrue(router.command instanceof ZipToBar);

        assertEquals("input zip", tips);
    }

}
