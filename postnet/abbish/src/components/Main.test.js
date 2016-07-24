import React from 'react';
import { assert } from 'chai';
import { shallow } from 'enzyme';
import sinon from 'sinon';
import { Link } from 'react-router';
import Main from '../components/Main';

describe('Main component', () => {

  var setup;

  beforeEach(() => {
    setup = (props = {}, options = {}) => {
      return shallow(<Main {...props} />, Object.assign({}, {
        context: {
          router: {
            push: sinon.spy()
          }
        }
      }, options));
    }
  });

  it('should has link for zip code convert to barcode page', () => {
    const main = setup();
    const link = main.find(Link).filterWhere(l => l.prop('to') === 'zip-code-to-barcode');

    assert.equal(link.length, 1);
  });

  it('should has link for barcode convert to zip code page', () => {
    const main = setup();
    const link = main.find(Link).filterWhere(l => l.prop('to') === 'barcode-to-zip-code');

    assert.equal(link.length, 1);
  });
});
