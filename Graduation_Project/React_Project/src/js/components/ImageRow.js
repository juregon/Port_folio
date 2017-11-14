import React from 'react';

import styles from 'stylesdir/components/ImageRow.scss';
import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const ImageRow = ({className,name,onChangeInput}) => {
	
	const obj = { target: {name: 'imagename', value: name}};

	return (
		<div className={cn('box',className)} onClick={()=>onChangeInput(obj)}>
			{name}
		</div>
		);
	
}

export default ImageRow;