import React from 'react';
import { assert } from 'chai';
import { shallow } from 'enzyme';
import sinon from 'sinon';
import BarcodeToZipCode from '../components/BarcodeToZipCode';

describe('BarcodeToZipCode component', () => {

  var setup;

  beforeEach(() => {
    setup = (props = {}, options = {}) => {
      return shallow(<BarcodeToZipCode {...props} />, Object.assign({}, {
        context: {
          router: {
            push: sinon.spy()
          }
        }
      }, options));
    }
  });

  it('should defined zipCode state with default value null', () => {
    const component = setup();

    assert.equal(component.state('zipCode'), null);
  });

  it('should defined barcode state with default value null', () => {
    const component = setup();

    assert.equal(component.state('barcode'), null);
  });

  it('should defined message state with default value null', () => {
    const component = setup();

    assert.equal(component.state('message'), null);
  });

  describe('render', () => {
    it('should has input for take user input barcode', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Barcode');

      assert.equal(input.length, 1);
      assert.equal(input.prop('onChange').name, 'takeBarcode');
    });

    it('should has button for trigger zip code generator', () => {
      const component = setup();
      const button = component.find('button').filterWhere(i => i.prop('children') === 'Generate Zip Code');

      assert.equal(button.length, 1);
      assert.equal(button.prop('onClick').name, 'generateZipCode');
    });

    it('should display message when state message is not empty', () => {
      const component = setup();
      component.setState({
        message: 'test message'
      });

      const span = component.find('span').filterWhere(s => s.prop('children') === 'test message');
      assert.equal(span.length, 1);
    });

    it('should display zip code when state zip code is not empty', () => {
      const component = setup();
      component.setState({
        zipCode: 'zip code'
      });

      const span = component.find('span').filterWhere(s => s.prop('children') === 'zip code');
      assert.equal(span.length, 1);
    });

    it('should not display zip code when state message is not empty', () => {
      const component = setup();
      component.setState({
        zipCode: 'zip code',
        message: 'test'
      });

      const span = component.find('span').filterWhere(s => s.prop('children') === 'test');
      assert.equal(span.length, 1);
    });
  });

  describe('takeBarcode', () => {
    it('should set input value to state barcode when input barcode', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Barcode');

      input.prop('onChange')({
        target: {
          value: 'barcode'
        }
      });

      assert.equal(component.state('barcode'), 'barcode');
    });

    it('should clear state message and zip code when input barcode', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Barcode');

      component.setState({
        message: 'test',
        zipCode: 'zip code'
      });

      assert.equal(component.state('message'), 'test');
      assert.equal(component.state('zipCode'), 'zip code');

      input.prop('onChange')({
        target: {
          value: 'barcode'
        }
      });

      assert.equal(component.state('message'), null);
      assert.equal(component.state('zipCode'), null);
    });
  });

  describe('generateZipCode', () => {

    it('should set generated zip code to state zipCode when call decodeBarcode', () => {
      const component = setup();
      const instance = component.instance();

      instance.core = {
        decodeBarcode: sinon.stub().returns('zip code')
      };

      instance.generateZipCode();

      assert.equal(component.state('zipCode'), 'zip code');
    });

    it('should set error message to state message when call function decodeBarcode and return value is empty', () => {
      const component = setup();
      const instance = component.instance();

      instance.core = {
        decodeBarcode: sinon.stub().returns(null)
      };

      instance.generateZipCode();

      assert.equal(component.state('zipCode'), null);
      assert.equal(component.state('message'), 'Invalid Barcode');
    });

    it('should clear state message when generated valid zip code', () => {
      const component = setup();
      const instance = component.instance();

      component.setState({
        message: 'test'
      });

      assert.equal(component.state('message'), 'test');

      instance.core = {
        decodeBarcode: sinon.stub().returns('zip code')
      };

      instance.generateZipCode();

      assert.equal(component.state('message'), null);
    });
  });

});
