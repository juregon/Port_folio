import React from 'react';

import styles from 'stylesdir/components/Input.scss';
import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const Input = ({className,placeholder,type,name,value,onChangeInput}) => {
	
	return (
		<div className={cn('box',className)}>
			<input type={type} placeholder={placeholder} name={name} value={value} onChange={onChangeInput}/>
		</div>
		);
	
}

export default Input;