import React from 'react';

import styles from 'stylesdir/components/TableItem.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const TableItem = ({children}) => {
	return (
		<div className={ cn('table-item') }>
			{children}
		</div>
		);
}

export default TableItem;