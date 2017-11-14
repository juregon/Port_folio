import React from 'react';
import { Link } from 'react-router-dom';

import styles from 'stylesdir/components/NavItem.scss';

import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

const NavItem = ({children, to}) => {
	if(to) {
		return (
			<Link className={ cn('nav-item') } to={to}>{children}</Link>
			);
	}
	return (
		<div className={ cn('nav-item') }>
			{children}
		</div>
		);
}

export default NavItem;