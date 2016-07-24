package shell;

import core.ResponseCode;
import org.junit.Test;
import shell.command.HomeCommand;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class HomeCommandTest {

    @Test
    public void should_return_responseCode_OK() throws Exception {
        HomeCommand command = new HomeCommand();
        PostNetResponse response = command.execute("");

        assertEquals(ResponseCode.OK, response.getResponseCode());
    }
}
