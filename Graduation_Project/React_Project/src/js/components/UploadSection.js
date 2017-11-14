import React from 'react';

import styles from 'stylesdir/components/UploadSection.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);




const UploadSection = ({id,onFileChange,onDesChange}) => {
	return (
		<div className={ cn('upload-section') }>
			<form encType="multipart/form-data" >
				<input type="file" name={id} onChange={onFileChange}/>
				<input type="text" name={id} placeholder="description" onChange={onDesChange} />
			</form>
			
		</div>
		);
}

export default UploadSection;