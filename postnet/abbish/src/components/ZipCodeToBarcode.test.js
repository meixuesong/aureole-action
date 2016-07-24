import React from 'react';
import { assert } from 'chai';
import { shallow } from 'enzyme';
import sinon from 'sinon';
import ZipCodeToBarcode from '../components/ZipCodeToBarcode';

describe('ZipCodeToBarcode component', () => {

  var setup;

  beforeEach(() => {
    setup = (props = {}, options = {}) => {
      return shallow(<ZipCodeToBarcode {...props} />, Object.assign({}, {
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
    it('should has input for take user input zip code', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Zip Code');

      assert.equal(input.length, 1);
      assert.equal(input.prop('onChange').name, 'takeZipCode');
    });

    it('should has button for trigger barcode generator', () => {
      const component = setup();
      const button = component.find('button').filterWhere(i => i.prop('children') === 'Generate Barcode');

      assert.equal(button.length, 1);
      assert.equal(button.prop('onClick').name, 'generateBarcode');
    });
  });

  describe('takeZipCode', () => {
    it('should set input value to state zip code when input zip code', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Zip Code');

      input.prop('onChange')('12345');

      assert.equal(component.state('zipCode'), '12345');
    });

    it('should clear state message when input zip code', () => {
      const component = setup();
      const input = component.find('input').filterWhere(i => i.prop('placeholder') === 'Zip Code');

      component.setState({
        message: 'test'
      });

      assert.equal(component.state('message'), 'test');

      input.prop('onChange')('12345');

      assert.equal(component.state('message'), null);
    });
  });

  describe('generateBarcode', () => {

    it('should set generated barcode to state barcode when call encodeZipCode', () => {
      const component = setup();
      const instance = component.instance();

      instance.core = {
        encodeZipCode: sinon.stub().returns('barcode')
      };

      instance.generateBarcode();

      assert.equal(component.state('barcode'), 'barcode');
    });

    it('should set error message to state message when call function encodeZipCode and return value is empty', () => {
      const component = setup();
      const instance = component.instance();

      instance.core = {
        encodeZipCode: sinon.stub().returns(null)
      };

      instance.generateBarcode();

      assert.equal(component.state('barcode'), null);
      assert.equal(component.state('message'), 'Invalid Zip Code');
    });

    it('should clear state message when generated valid barcode', () => {
      const component = setup();
      const instance = component.instance();

      component.setState({
        message: 'test'
      });

      assert.equal(component.state('message'), 'test');

      instance.core = {
        encodeZipCode: sinon.stub().returns('barcode')
      };

      instance.generateBarcode();

      assert.equal(component.state('message'), null);
    });
  });

});
