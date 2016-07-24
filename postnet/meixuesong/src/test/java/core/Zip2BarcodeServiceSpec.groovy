package core

import core.utils.CheckDigitUtil
import core.utils.ZipUtil
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 7/24/16.
 */
class Zip2BarcodeServiceSpec extends Specification {
    @Shared
            service = new Zip2BarcodeService(new ZipUtil(), new CheckDigitUtil());

    def "should transfer zip to barcode correctly"() {
        expect:
        this.service.getBarcode(value).barcode == barcode

        where:
        value         | barcode
        "95713"      || "||:|:::|:|:|:::|:::||::||::|:|:|"
        "123456789"  || "|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|:::|:|:|"
        "12345-1234" || "|:::||::|:|::||::|::|:|:|::::||::|:|::||::|::|:|:|:|"
        "57890"      || "|:|:|:|:::||::|:|:|::||::::::|||"
    }

    def "should return correct response code when transfer zip to barcode"() {
        expect:
        this.service.getBarcode(value).responseCode == code

        where:
        value         | code
        "12345" || ResponseCode.OK
        "123456" || ResponseCode.ERROR
    }

}
