import React from 'react';

import TableTitle from './TableTitle';
import UploadSection from './UploadSection';

import styles from 'stylesdir/components/TableHeader.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);


const TableHeader = ({title, id, onFileChange, onDesChange }) => {
	return (
		<div className={ cn('table-header') }>
			<TableTitle>{title}</TableTitle>
			<UploadSection id={id} onFileChange={onFileChange} onDesChange={onDesChange}/>
		</div>
		);
}

export default TableHeader;