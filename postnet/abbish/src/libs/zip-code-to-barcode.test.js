import {
  encodeZipCode,
  validateZipCode,
  parseZipCode,
  generateCheckDigit,
  encodeZipCodeString
} from '../libs/zip-code-to-barcode';

describe('zip-code-to-barcode', () => {
  describe('encodeZipCode', () => {
    it('should return barcode when given zip code', () => {
      assert.equal(encodeZipCode('45056-1234'), '|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|');
    });
  });

  describe('validateZipCode', () => {
    it('should return true when given valid zip code', () => {
      assert.equal(validateZipCode('12345'), true);
      assert.equal(validateZipCode('12345-1234'), true);
      assert.equal(validateZipCode('123451234'), true);
    });

    it('should return false when given invalid zip code', () => {
      assert.equal(validateZipCode(''), false);
      assert.equal(validateZipCode(null), false);
      assert.equal(validateZipCode('1234'), false);
      assert.equal(validateZipCode('1234567890'), false);
      assert.equal(validateZipCode('1234-56789'), false);
      assert.equal(validateZipCode('12345-678'), false);
    });
  });

  describe('parseZipCode', () => {
    it('should return parsedZipCode when given zip code', () => {
      assert.equal(parseZipCode('12345'), '12345');
      assert.equal(parseZipCode('12345-1234'), '123451234');
    });
  });

  describe('generateCheckDigit', () => {
    it('should return check digit when given parsed zip code', () => {
      assert.equal(generateCheckDigit('450561234'), '0');
      assert.equal(generateCheckDigit('12345'), '5');
    });
  });

  describe('encodeZipCodeString', () => {
    it('should return barcode when given parsed zip code and check digit', () => {
      assert.equal(encodeZipCodeString('450561234', '0'), '|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|')
    });
  });
});
