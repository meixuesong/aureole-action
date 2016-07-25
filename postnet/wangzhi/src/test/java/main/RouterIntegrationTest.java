package main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import core.CoreServices;
import org.junit.Test;

/**
 * Created by zbwang on 16/7/17.
 */
public class RouterIntegrationTest {

    @Test
    public void should_return_translated_barCode_when_given_correct_zipCode_with_short_line() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("1"), is("Please input zip code:"));
        assertThat(router.route("45056-1234"), is("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|"));
    }

    @Test
    public void should_return_translated_barCode_when_given_correct_zipCode_without_short_line() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("1"), is("Please input zip code:"));
        assertThat(router.route("450561234"), is("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|"));
    }

    @Test
    public void should_return_translated_zipCode_when_given_correct_barCode() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("2"), is("Please input bar code:"));
        assertThat(router.route("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|"), is("450561234"));
    }

    @Test
    public void should_return_error_msg_when_translate_error_barCode() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("2"), is("Please input bar code:"));
        assertThat(router.route("|:|::|:|:|:|:|:|::||:::::||::|:|::||::|::|||:::|"), is("Please give right input"));
    }

    @Test
    public void should_return_error_msg_when_translate_error_zipCode() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("1"), is("Please input zip code:"));
        assertThat(router.route("450sdf5612sdfdsf34"), is("Please give right input"));
    }

    @Test
    public void should_return_error_msg_when_input_error_command_in_first_step() {
        Router router = new Router(new CommandFactory(new CommandResponseFactory(),new CoreServices()));
        assertThat(router.route(null), is("1. Translate zip code to bar code\n" +
            "2. Translate bar code to zip code\n" +
            "3. Quit\n" +
            "Please input your choices(1~3)"));
        assertThat(router.route("5"), is("Please give right input"));
    }

}