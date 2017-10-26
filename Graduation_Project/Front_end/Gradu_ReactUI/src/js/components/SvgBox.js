import React from 'react';

import styles from 'stylesdir/components/SvgBox.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const SvgBox = ({width, height, color, children}) => {

	const remWidth = width + 'rem';
	const remHeight = height + 'rem';

	const svgStyle = { 
		width: remWidth,
		height: remHeight,
		color: color
	};

	return (
		<div className={ cn('svg-box') } style={svgStyle}>
			{children}
		</div>
		);
}

export default SvgBox;