require('normalize.css/normalize.css');

import React from 'react';
import styles from './App.scss';

class AppComponent extends React.Component {
  render() {
    return (
      <div className="app">
        abbish is a good boy
      </div>
    );
  }
}

AppComponent.defaultProps = {
};

export default AppComponent;
