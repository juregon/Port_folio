import React from 'react';

import styles from 'stylesdir/components/NewTable.scss';
import classNames from 'classnames/bind';

const cn = classNames.bind(styles);

const NewTable = ({title,children,btnClick,btnText,toggleUploadDialog,firstBtn}) => {
	return (
		<div className={cn('page')}>
			<div className={cn('table-header')}>
				<div className={cn('title-text')}>
					{title}
				</div>
				<div className={cn('upload-btn',{'disable':firstBtn})} onClick={toggleUploadDialog}>
					UPLOAD
				</div>
				<div className={cn('kind-btn')} onClick={btnClick}>
					{btnText}
				</div>
			</div>
			
			<div className={cn('border')}>
				{children}
			</div>
			
		</div>
		);
}

export default NewTable;