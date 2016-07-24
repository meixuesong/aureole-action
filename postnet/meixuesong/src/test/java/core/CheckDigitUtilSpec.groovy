package core

import core.utils.CheckDigitUtil
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 7/24/16.
 */
class CheckDigitUtilSpec extends Specification {
    @Shared
            checkDigitUtil = new CheckDigitUtil();

    def "check #zip #checkDigit"() {
        expect:
        this.checkDigitUtil.calc(value) == checkDigit

        where:
        value         | checkDigit
        "12345"      || 5
        "11119"      || 7
        "57890"      || 1
        "56890-0001" || 1
    }
}
