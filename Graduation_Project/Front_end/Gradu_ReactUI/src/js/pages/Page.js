import React from 'react';

import FlexBox from '../components/FlexBox';

import styles from 'stylesdir/components/Page.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const Page = ({children}) => {
	return (
		<div className={ cn('page') }>
			<FlexBox center>
				{children}
			</FlexBox>			
		</div>
		);
}

export default Page;