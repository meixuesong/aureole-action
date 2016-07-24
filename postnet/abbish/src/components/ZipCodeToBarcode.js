import React from 'react';
import zipCodeToBarcodeCore from '../libs/zip-code-to-barcode';

class ZipCodeToBarcode extends React.Component {

  constructor() {
    super();

    this.core = zipCodeToBarcodeCore;

    this.state = {
      zipCode: null,
      barcode: null,
      message: null,
    };

    this.takeZipCode = this.takeZipCode.bind(this);
    this.generateBarcode = this.generateBarcode.bind(this);
  }

  takeZipCode(value) {
    this.setState({
      zipCode: value,
      message: null
    });
  }

  generateBarcode() {
    const barcode = this.core.encodeZipCode(this.state.zipCode);

    this.setState({
      barcode: barcode,
      message: barcode ? null : 'Invalid Zip Code'
    });
  }

  render() {
    return (
      <div className="zip-code-to-barcode">
        <input type="text" placeholder="Zip Code" onChange={this.takeZipCode} />
        <button onClick={this.generateBarcode}>Generate Barcode</button>
      </div>
    );
  }
}

ZipCodeToBarcode.defaultProps = {
};

export default ZipCodeToBarcode;
