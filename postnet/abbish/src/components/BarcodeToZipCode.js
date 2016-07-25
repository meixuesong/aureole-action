import React from 'react';
import * as barcodeToZipCodeCore from '../libs/barcode-to-zip-code';

class BarcodeToZipCode extends React.Component {

  constructor() {
    super();

    this.core = barcodeToZipCodeCore;

    this.state = {
      zipCode: null,
      barcode: null,
      message: null,
    };

    this.takeBarcode = this.takeBarcode.bind(this);
    this.generateZipCode = this.generateZipCode.bind(this);
  }

  takeBarcode(event) {
    this.setState({
      zipCode: null,
      message: null,
      barcode: event.target.value
    });
  }

  generateZipCode() {
    const zipCode = this.core.decodeBarcode(this.state.barcode);

    this.setState({
      zipCode: zipCode,
      message: zipCode ? null : 'Invalid Barcode'
    });
  }

  render() {
    return (
      <div className="barcode-to-zip-code">
        <p>
          <input type="text" placeholder="Barcode" onChange={this.takeBarcode}/>
          <button onClick={this.generateZipCode}>Generate Zip Code</button>
        </p>
        <p>
          <span>{this.state.message ? this.state.message : this.state.zipCode}</span>
        </p>
      </div>
    );
  }
}

BarcodeToZipCode.defaultProps = {};

export default BarcodeToZipCode;
