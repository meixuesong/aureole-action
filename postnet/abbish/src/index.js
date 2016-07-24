import 'core-js/fn/object/assign';
import 'babel-polyfill';
import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, browserHistory } from 'react-router';
import Main from './components/Main';
import ZipCodeToBarcode from './components/ZipCodeToBarcode';
import BarcodeToZipCode from './components/BarcodeToZipCode';

const app = (
  <Router history={browserHistory}>
    <Route component={Main} path="/">
      <Route component={ZipCodeToBarcode} path="zip-code-to-barcode"/>
      <Route component={BarcodeToZipCode} path="barcode-to-zip-code"/>
    </Route>
  </Router>
);
ReactDOM.render(app, document.getElementById('main'));
