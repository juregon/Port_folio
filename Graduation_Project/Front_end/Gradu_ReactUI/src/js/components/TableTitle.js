import React from 'react';

import styles from 'stylesdir/components/TableTitle.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const TableTitle = ({children}) => {
	return (
		<div className={ cn('table-title') }>
			{children}
		</div>
		);
}

export default TableTitle;