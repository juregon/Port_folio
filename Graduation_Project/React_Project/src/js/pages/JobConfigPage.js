import React from 'react';

import $ from 'jquery';
import TableHeader from '../components/TableHeader';
import TableBox from '../components/TableBox';
import TableRow from '../components/TableRow';
import TableItem from '../components/TableItem';
import DialogBg from '../components/DialogBg';
import ImageDialog from '../components/ImageDialog';

import Page from './Page';
import ItemListBox from '../components/ItemListBox';
import ItemRow from '../components/ItemRow';

import styles from 'stylesdir/pages/JobConfigPage.scss';
import NewTable from '../components/NewTable';
import NewTableRow from '../components/NewTableRow';


import classNames from 'classnames/bind';
const cn = classNames.bind(styles);

class JobConfigPage extends React.Component {
	constructor() {
		super();
		this.toggleDialog = this.toggleDialog.bind(this);
		this.state = {
			stateman : true,
			isDialogOn: false
		};
	}

	toggleDialog() {
		this.setState(prevState=>({
			isDialogOn:!prevState.isDialogOn
		}));
	}

	toggleBtn() {
		this.setState(prevState=>({
			stateman:!prevState.stateman
		}));
	}

	onFileChange(e) {
		const targetName = e.target.name;
		const targetFile = e.target.files[0];

		this.setState({
			[targetName] : targetFile
		});	
	}

	render() {
		const { datalist, execlist, scriptlist, imagelist, addImage, addImageEdit } = this.props;
		const { isDialogOn } = this.state;

		const ImageTable = imagelist.map((data,i)=>{
		   return ( 	
				<NewTableRow header={true} name={data.Name} date={data.Date} status={data.Status} description={data.Description}/>
		   );
		});
		return (
			<div>
				<DialogBg isDialogOn={isDialogOn}/>
				<ImageDialog imagelist={imagelist} addImage={addImage} addImageEdit={addImageEdit} datalist={datalist} execlist={execlist} scriptlist={scriptlist} isDialogOn={isDialogOn} toggleDialog={this.toggleDialog}/>
				<Page>
					<div className={ cn({'disable':!this.state.stateman})}>	
						<NewTable title={"Image List"} firstBtn={true} btnText="Image Create" btnClick={this.toggleDialog}>
							<NewTableRow header={true} name="Name" date="Date" status="Status" description="Description"/>
							{ImageTable}
						</NewTable>
					</div>
					
				</Page>
			</div>

		);
	}
}

export default JobConfigPage;