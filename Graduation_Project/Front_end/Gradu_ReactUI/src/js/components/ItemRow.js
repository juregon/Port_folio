import React from 'react';

import styles from 'stylesdir/components/ItemRow.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const boxSvg = (
	<svg fill="#000000" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
	    <path d="M0 0h24v24H0z" fill="none"/>
	    <path d="M18 4H6c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 14H6V6h12v12z"/>
	</svg>
	);

const fillBoxSvg = (
	<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
	    <path d="M0 0h24v24H0z" fill="none"/>
	    <path d="M18 4H6c-1.1 0-2 .9-2 2v12c0 1.1.9 2 2 2h12c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 14H6V6h12v12z"/>
	</svg>
);

const ItemRow = ({isChecked, name, description, clickCheckbox, index}) => {
	return (
		<div className={cn('item-row')}>
			<div className={cn('check-box')} onClick={()=>clickCheckbox(name)}>
				<div className={cn({'disable':isChecked})}>
					{boxSvg}
				</div>
				<div className={cn({'disable':isChecked})}>
					{fillBoxSvg}
				</div>				
			</div>
			<div className={cn('name')}>
				{name}
			</div>
			<div className={cn('description')}>
				{description}
			</div>
		</div>
		);

};

export default ItemRow;