import React from 'react';

import FlexBox from './FlexBox';

import styles from 'stylesdir/components/TableBox.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const TableBox = ({children}) => {
	return (
		<div className={ cn('table-box') }>
			{children}	
		</div>				
		);
}

export default TableBox;