import React from 'react';

import styles from 'stylesdir/components/ItemListBox.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const ItemListBox = ({children}) => {
	return (
		<div className={cn('list-box')}>
			{children}
		</div>
		);

}

export default ItemListBox;