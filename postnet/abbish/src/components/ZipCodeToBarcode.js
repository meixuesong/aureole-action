import React from 'react';
import * as zipCodeToBarcodeCore from '../libs/zip-code-to-barcode';

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

  takeZipCode(event) {
    this.setState({
      zipCode: event.target.value,
      message: null,
      barcode: null
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
        <p>
          <input type="text" placeholder="Zip Code" onChange={this.takeZipCode}/>
          <button onClick={this.generateBarcode}>Generate Barcode</button>
        </p>
        <p>
          <span>{this.state.message ? this.state.message : this.state.barcode}</span>
        </p>
      </div>
    );
  }
}

ZipCodeToBarcode.defaultProps = {};

export default ZipCodeToBarcode;
