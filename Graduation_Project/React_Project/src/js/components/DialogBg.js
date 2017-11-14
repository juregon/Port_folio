import React from 'react';

import styles from 'stylesdir/components/DialogBg.scss';
import classNames from 'classnames/bind';
const cn = classNames.bind(styles);


const DialogBg = ({isDialogOn}) => {
	return (
		<div className={cn('box',{'disable':!isDialogOn})}>
		</div>
		);
};

export default DialogBg;