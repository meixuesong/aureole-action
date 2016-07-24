package core

import core.utils.ZipUtil
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Xuesong Mei on 7/24/16.
 */
class ZipUtilSpec extends Specification {
    @Shared
            zipUtil = new ZipUtil();

    def "check #zip #isValid"() {
        expect:
        this.zipUtil.isValid(zip) == isValid

        where:
        zip            | isValid
        "12345"       || true
        "123456789"   || true
        "12345-1234"  || true
        "1"           || false
        "12"          || false
        "123"         || false
        "1234"        || false
        "A1234"       || false
        "123456"      || false
        "1234567"     || false
        "12345678"    || false
        "12345678-9"  || false
        "12345678901" || false
    }

}
