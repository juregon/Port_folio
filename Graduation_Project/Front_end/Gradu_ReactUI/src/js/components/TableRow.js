import React from 'react';

import FlexBox from './FlexBox';

import styles from 'stylesdir/components/TableRow.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const TableRow = ({children, header}) => {
	return (
		<div className={ cn('table-row', {header}) }>
			<FlexBox>
				{children}
			</FlexBox>
		</div>		
		);
}

export default TableRow;