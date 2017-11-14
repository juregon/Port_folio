import React from 'react';

import styles from 'stylesdir/components/DialogBtn.scss';
import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const DialogBtn = ({children,className,btnClick}) => {	
	return (
		<div className={cn('box',className)} onClick={btnClick}>
			{children}
		</div>
		);	
}

export default DialogBtn;