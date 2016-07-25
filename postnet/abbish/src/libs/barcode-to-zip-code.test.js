import {
  decodeBarcode,
  validateBarcode,
  generateBarcodeString,
  decodeBarcodeString,
  validateCheckDigit,
  formatZipCode
} from '../libs/barcode-to-zip-code';

describe.only('barcode-to-zip-code', () => {
  describe('decodeBarcode', () => {
    it('should return zipCode when given barcode', () => {
      assert.equal(decodeBarcode('|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|'), '45056-1234');
    });
  });

  describe('validateBarcode', () => {
    it('should return true when given valid barcode', () => {
      assert.equal(validateBarcode('|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|'), true);
    });

    it('should return false when given invalid barcode', () => {
      assert.equal(validateBarcode(':|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::'), false);
      assert.equal(validateBarcode('::|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||::::'), false);
    });
  });

  describe('generateBarcodeString', () => {
    it('should return array contains every word of barcode when given barcode', () => {
      assert.deepEqual(generateBarcodeString('|:|::|:|:|:||::::|:|::||:::::||::|:|::||::|::|||:::|'), [
        ':|::|', ':|:|:', '||:::', ':|:|:', ':||::', ':::||', '::|:|', '::||:', ':|::|', '||:::'
      ]);
    });
  });

  describe('decodeBarcodeString', () => {
    it('should return array contains every word of zip code decoded from barcode when given barcode', () => {
      assert.deepEqual(decodeBarcodeString([
        ':|::|', ':|:|:', '||:::', ':|:|:', ':||::', ':::||', '::|:|', '::||:', ':|::|', '||:::'
      ]), ['4', '5', '0', '5', '6', '1', '2', '3', '4', '0']);
    });
  });

  describe('validateCheckDigit', () => {
    it('should return true when given valid zip code string', () => {
      assert.equal(validateCheckDigit(['4', '5', '0', '5', '6', '1', '2', '3', '4', '0']), true);
    });

    it('should return false when given invalid zip code string', () => {
      assert.equal(validateCheckDigit(['4', '5', '0', '5', '6', '1', '2', '3', '4', '5']), false);
    });
  });

  describe('formatZipCode', () => {
    it('should return formatted zip code when given zip code string', () => {
      assert.equal(formatZipCode(['1','2','3','4','5','0']), '12345');
      assert.equal(formatZipCode(['1','2','3','4','5','6','7','8','9','5']), '12345-6789');
    });
  });
});
