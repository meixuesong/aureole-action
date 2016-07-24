package shell;

import org.junit.Before;
import org.junit.Test;
import shell.command.*;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by Xuesong Mei on 7/24/16.
 */
public class RouterTest {
    private Router router;
    private Map<String, PostNetCommand> routeMap;
    private HomeCommand homeCommand = mock(HomeCommand.class);
    private Zip2BarcodeCommand zip2BarcodeCommand = mock(Zip2BarcodeCommand.class);
    private Barcode2ZipCommand barcode2ZipCommand = mock(Barcode2ZipCommand.class);
    private ExitCommand exitCommand = mock(ExitCommand.class);

    @Before
    public void setUp() throws Exception {
        routeMap = new HashMap<>();
        routeMap.put("HOME", homeCommand);
        routeMap.put("HOME1", zip2BarcodeCommand);
        routeMap.put("HOME2", barcode2ZipCommand);
        routeMap.put("HOME3", exitCommand);
        routeMap.put("ZIP2BARCODE", zip2BarcodeCommand);
        routeMap.put("BARCODE2ZIP", barcode2ZipCommand);
        router = new Router(routeMap);
    }

    @Test
    public void should_run_HomeCommand_given_HOME() throws Exception {
        router.route(CommandType.HOME, "");
        verify(homeCommand, times(1)).execute("");
    }

    @Test
    public void should_run_Zip2BarcodeCommand_given_HOME1() throws Exception {
        router.route(CommandType.HOME, "1");
        verify(zip2BarcodeCommand, times(1)).execute("");
    }

    @Test
    public void should_run_Barcode2ZipCommand_given_HOME2() throws Exception {
        router.route(CommandType.HOME, "2");
        verify(barcode2ZipCommand, times(1)).execute("");
    }

    @Test
    public void should_run_ExitCommand_given_HOME3() throws Exception {
        router.route(CommandType.HOME, "3");
        verify(exitCommand, times(1)).execute("");
    }

    @Test
    public void should_run_Zip2BarcodeCommand_given_ZIP2BARCODE() throws Exception {
        router.route(CommandType.ZIP2BARCODE, "");
        verify(zip2BarcodeCommand, times(1)).execute("");
    }

    @Test
    public void should_run_Barcode2ZipCommand_given_BARCODE2ZIP() throws Exception {
        router.route(CommandType.BARCODE2ZIP, "");
        verify(barcode2ZipCommand, times(1)).execute("");
    }
}
