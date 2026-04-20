import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';

export default class CostPriceRange extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      editMode: false,
      value: this.props.value,
      valueTo: this.props.valueTo,
    };
  }

  updateFromValue = (e) => {
    const { onChange } = this.props;
    const _value = e.target.value;
    this.setState({ value: _value }, () => {
      onChange(_value, this.state.valueTo);
    });
  };

  updateToValue = (e) => {
    const { onChange } = this.props;
    const _valueTo = e.target.value;
    this.setState({ valueTo: _valueTo }, () => {
      onChange(this.state.value, _valueTo);
    });
  };

  render() {
    const { className, precision } = this.props;
    const { value, valueTo, editMode } = this.state;

    console.log('CostPriceRange *** render', {
      value,
      valueTo,
      precision,
      editMode,
    });

    return (
      <div className="input-range-container">
        <div className="input-range-from">
          <input
            className="input-field js-input-field"
            value={value}
            onChange={this.updateFromValue}
            type="number"
          />
        </div>

        <div className="input-range-separator">-</div>

        <div className="input-range-to">
          <input
            className="input-field js-input-field"
            value={valueTo}
            onChange={this.updateToValue}
            type="number"
          />
        </div>
      </div>
    );
  }
}

CostPriceRange.propTypes = {
  onChange: PropTypes.func.isRequired,
  onBlur: PropTypes.func.isRequired,
  value: PropTypes.string,
  valueTo: PropTypes.string,
  precision: PropTypes.number,
};
