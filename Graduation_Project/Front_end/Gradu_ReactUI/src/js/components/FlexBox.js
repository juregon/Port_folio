import React from 'react';

import styles from 'stylesdir/components/FlexBox.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const FlexBox = ({row, column, center, children}) => {
	return (
		<div className={ cn('flex-box',{row, column, center}) } >
			{children}
		</div>
		);
}

export default FlexBox;