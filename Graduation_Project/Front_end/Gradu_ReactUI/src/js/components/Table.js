import React from 'react';

import styles from 'stylesdir/components/Table.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const Table = ({children}) => {
	return (
		<div className={ cn('table') }>
			{children}
		</div>
		);
}

export default Table;