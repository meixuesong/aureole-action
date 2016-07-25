package core;

import enumeration.CodeType;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by zbwang on 16/7/25.
 */
public class CoreServicesTest {

    CoreServices coreServices = new CoreServices();

    @Test
    public void should_return_correct_barcode_when_translate_5_length_zipcode(){
        Assert.assertThat(coreServices.translation("45056", CodeType.ZIP), CoreMatchers.is("|:|::|:|:|:||::::|:|::||::||:::|"));
    }

    @Test
    public void should_return_error_msg_when_translate_lt_5_length_zipcode(){
        Assert.assertThat(coreServices.translation("4506", CodeType.ZIP), CoreMatchers.is("Please give right input"));
    }

    @Test
    public void should_return_error_msg_when_translate_gt_5_lt_9_length_zipcode(){
        Assert.assertThat(coreServices.translation("444506", CodeType.ZIP), CoreMatchers.is("Please give right input"));
    }

    @Test
    public void should_return_correct_barcode_when_translate_9_length_zipcode(){
        Assert.assertThat(coreServices.translation("444565406", CodeType.ZIP), CoreMatchers.is("|:|::|:|::|:|::|:|:|::||:::|:|::|::|||::::||::::|:||"));
    }

    @Test
    public void should_return_correct_barcode_when_translate_10_length_zipcode(){
        Assert.assertThat(coreServices.translation("44456-5406", CodeType.ZIP), CoreMatchers.is
            ("|:|::|:|::|:|::|:|:|::||:::|:|::|::|||::::||::::|:||"));
    }

    @Test
    public void should_return_cd_0_barcode_when_translate_zipcode_450561234(){
        Assert.assertThat(coreServices.translation("450561234", CodeType.ZIP), CoreMatchers.is
            ("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|"));
    }

    @Test
    public void should_return_cd_9_barcode_when_translate_zipcode_450561235(){
        Assert.assertThat(coreServices.translation("450561235", CodeType.ZIP), CoreMatchers.is
            ("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|:|:|:|::|"));
    }

    @Test
    public void should_return_cd_3_barcode_when_translate_zipcode_450561231(){
        Assert.assertThat(coreServices.translation("450561231", CodeType.ZIP), CoreMatchers.is
            ("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::::||::||:|"));
    }

    @Test
    public void should_return_correct_zipcode_when_translate_barcode(){
        Assert.assertThat(coreServices.translation("|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|", CodeType.BAR), CoreMatchers.is
            ("450561234"));
    }

    @Test
    public void should_return_error_msg_when_translate_10_length_zipcode_with_error_hyphen_index(){
        Assert.assertThat(coreServices.translation("4445-65406", CodeType.ZIP), CoreMatchers.is
            ("Please give right input"));
    }

    @Test
    public void should_return_error_msg_when_translate_not_all_number_zipcode(){
        Assert.assertThat(coreServices.translation("44df56-5406", CodeType.ZIP), CoreMatchers.is
            ("Please give right input"));
    }
}