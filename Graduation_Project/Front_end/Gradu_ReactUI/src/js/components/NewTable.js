import React from 'react';

import styles from 'stylesdir/components/NewTable.scss';
import classNames from 'classnames/bind';

const cn = classNames.bind(styles);

const NewTable = ({title,children,btnClick,btnText}) => {
	return (
		<div className={cn('page')}>
			<div className={cn('table-header')}>
				<div className={cn('title-text')}>
					{title}
				</div>
				<div className={cn('button-4')} onClick={btnClick}>
					<div className={cn('eff-4')}></div>
					<a href="#"> {btnText} </a>
				</div>
			</div>
			
			<div className={cn('border')}>
				{children}
			</div>
			
		</div>
		);
}

export default NewTable;