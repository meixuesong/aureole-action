package core

import core.utils.CheckDigitUtil
import core.utils.ZipUtil
import core.exception.Barcode2ZipException
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 7/24/16.
 */
class Barcode2ZipServiceSpec extends Specification {
    @Shared
            service = new Barcode2ZipService(new CheckDigitUtil(), new ZipUtil());

    def "check all barcode are valid number"() {
        expect:
        this.service.isValidNumber(barcode) == isValid

        where:
        barcode                                                      | isValid
        "|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|::||:::||:::|" || true
        "|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|::||:::||::|"  || false  //barcode少1位
        "|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|::||:::||||||" || false //不在0-9之间的barcode
    }

    def "should return correct zip"() {
        expect:
        this.service.barcode2Zip(barcode) == zip

        where:
        barcode                                                 | zip
        "||:|:::|:|:|:::|:::||::||::|:|:|"                     || "95713"
        "|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|:::|:|:|" || "12345-6789"
        "|:::||::|:|::||::|::|:|:|::::||::|:|::||::|::|:|:|:|" || "12345-1234"
        "|:|:|:|:::||::|:|:|::||::::::|||"                     || "57890"
    }

    def "should throw core.exception when checkDigit is not correct"() {
        when:
        this.service.barcode2Zip("|:::||::|:|::||::|::|:|:|::||::|:::||::|:|:|::||::::::|||")

        then:
        thrown Barcode2ZipException
    }

    def "should return correct response"() {
        expect:
        this.service.getZip(barcode).responseCode == responseCode

        where:
        barcode                                                 | responseCode
        "|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|" || ResponseCode.OK
        "|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::"  || ResponseCode.ERROR
    }
}
