import React from 'react';

import styles from 'stylesdir/components/NewTableRow.scss';
import classNames from 'classnames/bind';

const cn = classNames.bind(styles);


const NewTableRow = ({title,header,name,date,status,description}) => {
	return (
		<div className={cn('row',{'header':header},{'not-header':!header})}>
			<div className={cn('text-1')}>
				{name}
			</div>
			<div className={cn('text-2')} >
				{date}
			</div>
			<div className={cn('text-3')} >
				{status}
			</div>
			<div className={cn('text-4')} >
				{description}
			</div>
		</div>
		);
}

export default NewTableRow;