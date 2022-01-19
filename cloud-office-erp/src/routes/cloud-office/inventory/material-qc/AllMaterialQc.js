import React,{ Component } from "react";
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { IoIosAddCircle, IoIosCloseCircle, IoIosSearch, IoIosArrowUp, IoIosArrowDown } from 'react-icons/io';
import { FaEdit, FaTrash, FaDownload } from 'react-icons/fa';
import DataTable from "../../../../components/CustomComponent/DataTable/DataTable";
import { MDBBtn, MDBModal, MDBModalBody, MDBModalFooter, MDBCollapse } from "mdbreact";
import axiosInstance from "../../../../util/axiosInstance";
import { NotificationManager, NotificationContainer } from "react-notifications";
import jsPDF from 'jspdf';
import 'jspdf-autotable';
import { Button, Grid, Accordion, AccordionSummary, Typography, AccordionDetails, IconButton, Paper, Card, CardHeader } from "@material-ui/core";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { makeStyles } from '@material-ui/core/styles';
import { Label, Input } from 'reactstrap';

export default class AllMaterialQc extends Component{

  constructor(props){
    super(props);

    window.user_role = this;
  }

state = {

    materialQcList: [],
    materialQcList_temp: [],

    userRoles: [],

     newMaterialQc: {
	     materialQcId:'',
    },

    editUserRoleData: {
     userRoleId:'',
     userRole:'',
	   reportToLevel:'',
    },
    newUserRoleModel: false,
    editUserRoleModel: false,

    options: [], // multiSelectOptions
    multiSelectedOptions: [],

    selectedValue: [],

    resourcePermission: {
      add: false,
      update: false,
      delete: false,
    },

    modal: false,

    customTreeView: window.custom_tree_view,

    headers: [
         'Sr. No',
         'Material Type',
         'Material Sub Type',
         'Material Name',
         'Material Description',
         'Account Code',
         'Available Qty',
         'rate'
      ],

      suplierName_temp: '',
      suplierEmailId_temp: '',
      materialQcNo_temp: '',
      materialQcDate_temp: '',

      showSearch: true,

      collapseID: "",

    materialQcMaterialInfo: {
        materialType: '',
        materialSubType: '',
        accountCode: '',
        materialName: ''
      },

      errorMsgShow: true
}

componentWillMount()
{
    sessionStorage.removeItem('materialQcId');

    this.getAllMaterialQcs();
    this.updateResourcePermission();
}

updateResourcePermission(){

  var USER_ROLE = {};

  if (typeof sessionStorage.USER_ROLE !== "undefined") {
    USER_ROLE = JSON.parse(sessionStorage.USER_ROLE);
  }

  let resourcePermission = this.state.resourcePermission;

  resourcePermission.add = (USER_ROLE[0].addPermission === 'Y')? false : true;
  resourcePermission.update = (USER_ROLE[0].modifyPermission === 'Y')? false : true;
  resourcePermission.delete = (USER_ROLE[0].deletePermission === 'Y')? false : true;
  
  this.setState(resourcePermission);

  console.log('#USER_ROLE : ResourceName = '+JSON.stringify(USER_ROLE[0].resourceName))
  console.log('#USER_ROLE : Add = '+JSON.stringify(USER_ROLE[0].addPermission))
  console.log('#USER_ROLE : Update = '+JSON.stringify(USER_ROLE[0].modifyPermission))
  console.log('#USER_ROLE : Delete = '+JSON.stringify(USER_ROLE[0].deletePermission))

  //this.getAllUserRoles();
}

getAllMaterialQcs()
{
  let list = [];
 
    axiosInstance.get('/material-qc-details/getAll').then((response) => {

      response.data.map((val, index) => {

       list.push({
         index: (index+1),
         materialType: val.materialType,
         materialSubType: val.materialSubType,
         materialName: val.materialName,
         description: val.description,
         accountCode: val.accountCode,
         balanceQty: val.balanceQty,
         unitPrice: val.unitPrice,
         action: [
          <IconButton aria-label="cyan" style={{color: '#4285F4'}} size="small" onClick={this.onClick.bind(this, val.materialQc.materialQcId)}> <FaEdit /> </IconButton>,
          <IconButton aria-label="delete" style={{color: '#fb3640'}} size="small" disabled={this.state.resourcePermission.delete} onClick={this.toggle(val.materialQc.materialQcId)}> <FaTrash /> </IconButton>,
          <IconButton aria-label="cyan" style={{color: '#fdca40'}} size="small" onClick={this.downloadReport.bind(this, val.materialQc.materialQcId)}> <FaDownload /> </IconButton>,
                ]
       });

      })

      this.setState({materialQcList : list});
      
    });

    //this.updateResourcePermission();
}

deleteMaterialQc(materialQcId)
{
    axiosInstance.delete('/material-qc-details/delete/'+materialQcId).then((response) => {
     
    this.getAllMaterialQcs();
    NotificationManager.success('Material Qc Details Deleted Successfully!', '');

    });

    this.setState({ modal: !this.state.modal });
}

onSelect = (selectedList, selectedItem) =>{
  
  let list = [];

  selectedList.map(val => {
    console.log('selectedList : '+val.name);
    list.push(val.name);
  });

  this.setState({multiSelectedOptions: list});

}

onRemove = (selectedList, selectedItem) =>{

  let list = [];

  selectedList.map(val => {
    console.log('selectedList : '+val.name);
    list.push(val.name);
  });

  this.setState({multiSelectedOptions: list});

}

getPermission(){
  //alert('getPermission() : ')
  axiosInstance.get('/access-permission/getAllUserPermissionByUserRoleId/'+sessionStorage.getItem('USER_ROLE_ID')).then((response) => {
     sessionStorage.setItem('USER_ROLE', JSON.stringify(response.data));
     this.updateResourcePermission();
  });
}

downloadReport = (materialQcId) => {

  let list_temp = [];
  let suplierName_temp = '';
  let suplierEmailId_temp = '';
  let materialQcNo_temp = '';
  let materialQcDate_temp = '';

  axiosInstance.get('/material-qc-details/getById/'+materialQcId).then((response) => {
    console.log(JSON.stringify(response.data))
    if(response.data != ''){

      response.data.map(val => {

        list_temp.push([
          val.srNo,
        
          val.materialType,
          val.materialSubType,
          val.materialName,
          val.description,
          val.accountCode,
          val.balanceQty,
          val.unitPrice,
        ]);

      });

     suplierName_temp = response.data[0].materialQc.suplierName;
    // suplierEmailId_temp = response.data[0].materialQc.suplierEmailId;
     materialQcNo_temp = response.data[0].materialQc.qcCode;
     materialQcDate_temp = response.data[0].materialQc.qcDate;
     

      this.setState({materialQcList_temp : list_temp});
      this.setState({suplierName_temp : suplierName_temp});
     // this.setState({suplierEmailId_temp : suplierEmailId_temp});
      this.setState({materialQcNo_temp : materialQcNo_temp});
      this.setState({materialQcDate_temp : materialQcDate_temp});
      

      this.generatePdf();

    }

 }); 
}

getAllMaterialQcsBySearch(){
  
  let list = [];

  if(this.state.materialQcMaterialInfo.materialType!='' || this.state.materialQcMaterialInfo.materialSubType!='' ||
     this.state.materialQcMaterialInfo.accountCode!='' || this.state.materialQcMaterialInfo.materialName!=''){

  this.setState({errorMsgShow: true});

  axiosInstance.post('/material-qc-details/getBySearchValues/', this.state.materialQcMaterialInfo).then((response) => {

    response.data.map((val, index) => {

      list.push({
        index: (index+1),
        materialType: val.materialType,
        materialSubType: val.materialSubType,
        materialName: val.materialName,
        description: val.description,
        accountCode: val.accountCode,
        balanceQty: val.balanceQty,
        unitPrice: val.unitPrice,
        action: [
          <IconButton aria-label="cyan" style={{color: '#4285F4'}} size="small" onClick={this.onClick.bind(this, val.materialQc.materialQcId)}> <FaEdit /> </IconButton>,
          <IconButton aria-label="delete" style={{color: '#fb3640'}} size="small" disabled={this.state.resourcePermission.delete} onClick={this.toggle(val.materialQc.materialQcId)}> <FaTrash /> </IconButton>,
          <IconButton aria-label="cyan" style={{color: '#fdca40'}} size="small" onClick={this.downloadReport.bind(this, val.materialQc.materialQcId)}> <FaDownload /> </IconButton>,
               ]
      });

     })

     this.setState({materialQcList : list});

  });
}else{
  this.setState({errorMsgShow: false});
}

}

clearSearchFilter(){

  this.setState({errorMsgShow: true});

  this.setState({materialQcMaterialInfo: { materialType: '', materialSubType: '', accountCode: '', materialName: '' }});
  this.getAllMaterialQcs(); 
  
}

generatePdf = () =>{
       
  var doc = new jsPDF('p','pt');

  doc.setFontSize(13);
  doc.setFont('Arial');
  //doc.text("Financial Year", 20, 35);
  doc.text("Material QC Report", 240, 35);
  //doc.text("Stores Copy", 480, 35);

  doc.setLineWidth(0.5);
  doc.rect(10, 20, 575, 20);

  doc.setLineWidth(0.5);
  doc.rect(10, 20, 575, 805);

  //doc.setFontSize(15);
  doc.setTextColor(0, 0, 0);
  doc.text("Material Qc No: ", 20, 65);

  doc.setTextColor(0, 0, 160);
  doc.text(this.state.materialQcNo_temp, 110, 65);

  doc.setTextColor(0, 0, 0);
  doc.text("Date: ", 480, 65);

  doc.setTextColor(0, 0, 160);
  doc.text(this.state.materialQcDate_temp, 515, 65);

  doc.setTextColor(0, 0, 0);
  doc.text("To,", 20, 90);
  doc.setLineWidth(0.5);
  doc.setLineDash([10, 1, 1, 2], 1);

  doc.setTextColor(0, 0, 160);
  doc.text(this.state.suplierName_temp, 45, 100);
  //doc.line(35, 100, 180, 100);

  //doc.text(this.state.suplierEmailId_temp, 45, 115);
  //doc.line(35, 120, 180, 120);

  doc.setTextColor(0, 0, 0);

  //doc.text("Requisition No:", 230, 65);
  //doc.text("087557890", 330, 65);

  //doc.setFontSize(12);
  doc.text("Signature of Department Officer", 40, 700);
  doc.text("Signature of Chief Officer", 400, 760);

  doc.setLineDash([10, 0, 0, 0], 0);
  
  doc.autoTable({
      head: [this.state.headers],
body: this.state.materialQcList_temp,
startY: 150,
theme: 'grid'   
});

window.open(doc.output('bloburl'), '_blank');

  //doc.save('a.pdf');
}

toggle = nr => () => {

  let {newMaterialQc} = this.state;
  newMaterialQc.materialQcId = nr;
  this.setState(newMaterialQc);

  this.setState({
    modal: !this.state.modal
  });
}

onClick = (materialQcId) => {
  if(materialQcId != undefined && materialQcId != 0){
    sessionStorage.setItem('materialQcId', materialQcId);
  }
    this.props.history.push("/app/cloud-office/inventory/material-qc/add-material-qc");
}

showSearch = () =>{

   this.setState({showSearch: !this.state.showSearch});
   this.setState({errorMsgShow: true});
}

toggleCollapse = collapseID => () => {
  this.setState(prevState => ({
    collapseID: prevState.collapseID !== collapseID ? collapseID : ""
  }));
}

 render(){
 //alert('USER ROLE')
const columns = [
  {
    label: 'Sr No',
    field: 'index',
    sort: 'asc',
    width: 50
  },
  {
    label: 'Material Type',
    field: 'materialType',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Material SubType',
    field: 'materialSubType',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Material Name',
    field: 'materialName',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Material Description',
    field: 'description',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Account Code',
    field: 'accountCode',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Available Qty',
    field: 'balanceQty',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Rate',
    field: 'unitPrice',
    sort: 'asc',
    width: 270
  },
  {
    label: <span style={{paddingRight: '20px'}}>Action</span>,
    field: 'action',
    sort: 'asc',
    width: 270
  },
];


const useStyles = makeStyles((theme) => ({
  root: {
    width: '100%',
    paddingBottom: '10px'
  },
  heading: {
    fontSize: theme.typography.pxToRem(15),
    flexBasis: '33.33%',
    flexShrink: 0,
  },
  secondaryHeading: {
    fontSize: theme.typography.pxToRem(15),
    color: theme.palette.text.secondary,
  },
}));


let {materialQcMaterialInfo} = this.state;

        return(

<div className="formelements-wrapper">
  <RctCollapsibleCard heading="All Material Qc">
   
<Grid justify="space-between" container spacing={24}>
    <Grid item> 
    <Button variant="contained" className="btn-primary text-white mr-10 mb-10" size="small" onClick={()=>this.onClick(0)}>
      <IoIosAddCircle size="25px" style={{verticalAlign:"middle"}} /> 
      Add Material Qc
    </Button>
    </Grid>

    <Grid item>
    <Button variant="contained" className="btn-primary text-white mr-10 mb-10" size="small" onClick={this.showSearch}>
      <IoIosSearch size="25px" style={{verticalAlign:"middle"}} /> 
      { (this.state.showSearch)? 'Show Search Filters':'Hide Search Filters' }
    </Button> 
    </Grid>
  </Grid>

{ /*-------------------------------------------------------------------------------------------------------*/ }

<div style={{padding: '10px'}} hidden={this.state.showSearch}>
      <Accordion elevation={3}>
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1bh-content"
          id="panel1bh-header"
        >
          <Grid container spacing={2}>
            <Grid item sm={2}> <Typography>Serach Filter</Typography> </Grid>
            <Grid item sm={2}> <Typography style={{color: 'red'}} hidden={this.state.errorMsgShow}>*All fields are empty!</Typography> </Grid>
          </Grid>
          

        </AccordionSummary>
        <AccordionDetails>
    <Paper elevation={0} style={{width: '100%', height: '100%'}}>

        <Grid container justify='center' spacing={2}>
         <Grid item sm={4}> <Label for="materialType">Material Type</Label> <Input value={materialQcMaterialInfo.materialType}
          onChange={ (e) => {
            materialQcMaterialInfo.materialType = e.target.value
            this.setState(materialQcMaterialInfo);
           }}
         /> </Grid> 
         <Grid item sm={4}> <Label for="materialSubType">Material SubType</Label> <Input 
         value={materialQcMaterialInfo.materialSubType}
         onChange={ (e) => {
           materialQcMaterialInfo.materialSubType = e.target.value
           this.setState(materialQcMaterialInfo);
          }}
         /> </Grid>
      </Grid>

      <Grid container justify='center' spacing={2}>
         <Grid item sm={4}> <Label for="accountCode">Account Code</Label> <Input value={materialQcMaterialInfo.accountCode}
          onChange={ (e) => {
            materialQcMaterialInfo.accountCode = e.target.value
            this.setState(materialQcMaterialInfo);
           }} 
           /> </Grid>
         <Grid item sm={4}> <Label for="materialName">Account Name</Label> <Input value={materialQcMaterialInfo.materialName}
         onChange={ (e) => {
           materialQcMaterialInfo.materialName = e.target.value
           this.setState(materialQcMaterialInfo);
          }} 
         /> </Grid>
      </Grid>

      <Grid container justify='center' alignContent='center' spacing={2}>
      <Grid item > <Button variant="contained" className="btn-primary text-white mr-10 mb-10" onClick={() => this.getAllMaterialQcsBySearch()}>Serach</Button> </Grid>
      <Grid item > <Button variant="contained" className="btn-default text-white mr-10 mb-10" onClick={() => this.clearSearchFilter()}>Clear</Button> </Grid>
      </Grid>
</Paper>
        </AccordionDetails>
      </Accordion>
</div>

{ /*-------------------------------------------------------------------------------------------------------*/ }

     <DataTable name="UserRole" columns={columns} rows={this.state.materialQcList} />

     <NotificationContainer />

<MDBModal isOpen={this.state.modal} toggle={this.toggle(0)} size="sm" centered>
  <MDBModalBody className="text-center">
   Are sure you want to delete this record?
  </MDBModalBody>
  <MDBModalFooter className="text-center">
    <MDBBtn color="danger" size="sm" onClick={() => this.deleteMaterialQc(this.state.newMaterialQc.materialQcId)}>Yes</MDBBtn>
    <MDBBtn color="blue-grey" size="sm" onClick={this.toggle(0)}>No</MDBBtn>
  </MDBModalFooter>
</MDBModal>
    
</RctCollapsibleCard>
     </div>

        )
    }
}


//ReactDOM.render(<CustomTreeView/>, document.getElementById('root'));