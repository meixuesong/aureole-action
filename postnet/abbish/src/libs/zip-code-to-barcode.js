import _ from 'lodash';

export function encodeZipCode(zipCode) {

  if (!validateZipCode(zipCode)) {
    return null;
  }

  const parsedZipCode = parseZipCode(zipCode);
  const checkDigit = generateCheckDigit(parsedZipCode);

  return encodeZipCodeString(parsedZipCode, checkDigit);
}

export function validateZipCode(zipCode) {

  if (_.isEmpty(zipCode)) {
    return false;
  }

  const length = zipCode.length;
  const splitPosition = zipCode.indexOf('-');

  if ((length === 5 || length === 9) && splitPosition < 0) {
    return true;
  }

  if (length === 10 && splitPosition === 5) {
    return true;
  }

  return false;
}

export function parseZipCode(zipCode) {
  return zipCode.replace('-', '');
}

export function generateCheckDigit(parsedZipCode) {
  const sum = _.chain(_.split(parsedZipCode, ''))
    .map(code => parseInt(code))
    .reduce((sum, number) => sum + number, 0)
    .value();

  return (sum % 10).toString();
}

export function encodeZipCodeString(parsedZipCode, checkDigit) {
  const mapping = {
    1: ':::||',
    2: '::|:|',
    3: '::||:',
    4: ':|::|',
    5: ':|:|:',
    6: ':||::',
    7: '|:::|',
    8: '|::|:',
    9: '|:|::',
    0: '||:::'
  };

  const barcode = _.chain(_.split(`${parsedZipCode}${checkDigit}`, ''))
    .map(code => mapping[code])
    .value()
    .join('');

  return `|${barcode}|`;
}
