import React from 'react';

import FlexBox from './FlexBox';
import NavItem from './NavItem';

const HeaderNav = () => {
	return (
		<FlexBox row>
			<NavItem to="/">
				JOB
			</NavItem>
			<NavItem to="/upload">
				UPLOAD
			</NavItem>
			<NavItem to="/script">
				SCRIPT
			</NavItem>
			<NavItem to="/config">
				IMAGES
			</NavItem>
		</FlexBox>		
		);
}

export default HeaderNav;