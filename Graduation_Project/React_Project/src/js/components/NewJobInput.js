import React from 'react';

import Input from './Input';

import styles from 'stylesdir/components/NewJobInput.scss';
import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const NewJobInput = ({title,inputclass,boxclass,...rest}) => {
	
	return (
		<div className={cn('box',boxclass)}>
			<span className={cn('title')}>
				{title}
			</span>
			<Input className={inputclass} type="text" {...rest} />
			
		</div>
		);
	
}

export default NewJobInput;