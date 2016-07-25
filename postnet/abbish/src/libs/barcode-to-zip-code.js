import _ from 'lodash';

export function decodeBarcode(barcode) {
  if(!validateBarcode(barcode))
  {
    return null;
  }

  const zipCodeString = decodeBarcodeString(generateBarcodeString(barcode));

  if(!validateCheckDigit(zipCodeString)) {
    return null;
  }

  return formatZipCode(zipCodeString);
}

export function validateBarcode(barcode) {

  if(barcode.length !== 52)
  {
    return false;
  }

  if(barcode[0] !== '|' || barcode[barcode.length - 1] !== '|') {
    return false;
  }

  return true;
}

export function generateBarcodeString(barcode) {
  return barcode.substr(1, barcode.length -2).match(new RegExp('.{1,5}', 'g'));
}

export function decodeBarcodeString(barcodeString) {

  const mapping = {
    ':::||': '1',
    '::|:|': '2',
    '::||:': '3',
    ':|::|': '4',
    ':|:|:': '5',
    ':||::': '6',
    '|:::|': '7',
    '|::|:': '8',
    '|:|::': '9',
    '||:::': '0'
  };

  return _.chain(barcodeString)
    .map(code => mapping[code])
    .value();
}

export function validateCheckDigit(zipCodeString) {
  const checkDigit = parseInt(zipCodeString[zipCodeString.length -1]);
  const sum = _.chain(zipCodeString)
    .reduce((sum, number) => sum + parseInt(number), 0)
    .value() - checkDigit;

  return sum % 10 === checkDigit;
}

export function formatZipCode(zipCodeString) {
  let zipCode = _.slice(zipCodeString, 0, zipCodeString.length - 1).join('');

  if(zipCode.length === 9) {
    zipCode = zipCode.substr(0, 5) + '-' + zipCode.substr(5, 4);
  }

  return zipCode;
}
