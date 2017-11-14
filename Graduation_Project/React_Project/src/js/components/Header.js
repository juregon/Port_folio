import React from 'react';

import Logo from './Logo';
import HeaderNav from './HeaderNav';

import styles from 'stylesdir/components/Header.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const Header = () => {
	return (
		<div className={ cn('header') } >
			<div className={ cn('header-content')} >
				<Logo/>
				<div className={ cn('right-side') } >
					<HeaderNav />
				</div>
			</div>
		</div>

		);
};

export default Header;