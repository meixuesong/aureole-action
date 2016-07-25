import React from 'react';
import { Link } from 'react-router';

class Main extends React.Component {
  render() {
    return (
      <div className="main">
        <div className="main__menu">
          <Link to="zip-code-to-barcode">1. Zip Code To Barcode</Link>
          &nbsp;
          <Link to="barcode-to-zip-code">2. Barcode To Zip Code</Link>
        </div>
        <div className="main__container">
          {this.props.children}
        </div>
      </div>
    );
  }
}

Main.contextTypes = {
  router: React.PropTypes.object.isRequired
};

Main.defaultProps = {};

export default Main;
