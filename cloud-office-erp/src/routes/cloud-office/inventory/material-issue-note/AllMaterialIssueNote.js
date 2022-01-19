import React,{ Component } from "react";
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { IoIosAddCircle, IoIosCloseCircle, IoIosSearch, IoIosArrowUp, IoIosArrowDown } from 'react-icons/io';
import { FaEdit, FaTrash, FaDownload } from 'react-icons/fa';
import DataTable from "../../../../components/CustomComponent/DataTable/DataTable";
import { MDBBtn, MDBModal, MDBModalBody, MDBModalFooter, MDBCollapse } from "mdbreact";
import axiosInstance from "../../../../util/axiosInstance";
import { NotificationManager, NotificationContainer } from "react-notifications";
import jsPDF from 'jspdf';
import 'jspdf-autotable'
import { Button, Grid, Accordion, AccordionSummary, Typography, AccordionDetails, IconButton, Paper, Card, CardHeader } from "@material-ui/core";
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import { makeStyles } from '@material-ui/core/styles';
import { Label, Input } from 'reactstrap';

export default class AllMaterialIssueNote extends Component{

  constructor(props){
    super(props);

    window.user_role = this;
  }

state = {

    materialIssueNoteList: [],
    materialIssueNoteList_temp: [],

    userRoles: [],

     newMaterialIssueNote: {
	     materialIssueNoteId:'',
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
         'MIN No',
         'Work Name',
         'Department Name',
         'Date',
      ],

      suplierName_temp: '',
      suplierEmailId_temp: '',
      prNo_temp: '',
      poDate_temp: '',

      showSearch: true,

      collapseID: "",

    materialIssueNote: {
       minNo: '',
       workName: '',
       minDate: '',
       departmentName: '',

       startDate: '',
       endDate: ''
      },

      departmentList: [],

      errorMsgShow: true
}

componentWillMount()
{
    sessionStorage.removeItem('materialIssueNoteId');

    this.getAllMaterialIssueNotes();
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

getAllMaterialIssueNotes()
{
  let list = [];
 
    axiosInstance.get('/material-issue-note/getAll').then((response) => {

      response.data.map((val, index) => {

       list.push({
         index: (index+1),
         //srNo: val.srNo,
         minNo: val.minNo,
         workName: val.workName,
         minDate: val.minDate,
         departmentName: val.departmentName,
         action: [
          <IconButton aria-label="cyan" style={{color: '#4285F4'}} size="small" onClick={this.onClick.bind(this, val.materialIssueNoteId)}> <FaEdit /> </IconButton>,
          <IconButton aria-label="delete" style={{color: '#fb3640'}} size="small" disabled={this.state.resourcePermission.delete} onClick={this.toggle(val.materialIssueNoteId)}> <FaTrash /> </IconButton>,
          <IconButton aria-label="cyan" style={{color: '#fdca40'}} size="small" onClick={this.downloadReport.bind(this, val.materialIssueNoteId)}> <FaDownload /> </IconButton>,
                ]
       });

      })

      this.setState({materialIssueNoteList : list});
      
    });

    //this.updateResourcePermission();
}

deleteMaterialIssueNote(materialIssueNoteId)
{
    axiosInstance.delete('/material-issue-note/delete/'+materialIssueNoteId).then((response) => {
     
    this.getAllMaterialIssueNotes();
    NotificationManager.success('Material Issue Note Deleted Successfully!', '');

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

downloadReport = (materialIssueNoteId) => {

  let list_temp = [];
  let suplierName_temp = '';
  let suplierEmailId_temp = '';
  let prNo_temp = '';
  let poDate_temp = '';

  ///material-issue-note/getById/'+materialIssueNoteId
  axiosInstance.get('/material-issue-note/getAll').then((response) => {
    //alert(JSON.stringify(response.data))
    if(response.data != ''){

      response.data.map((val, idx) => {

        list_temp.push([
          (idx+1),
          val.minNo,
          val.workName,
          val.departmentName,
          val.minDate,
        ]);

      });

   /*  suplierName_temp = response.data[0].materialIssueNote.suplierName;
     suplierEmailId_temp = response.data[0].materialIssueNote.suplierEmailId;
     prNo_temp = response.data[0].materialIssueNote.prNo;
     poDate_temp = response.data[0].materialIssueNote.poDate;
  */
      this.setState({materialIssueNoteList_temp : list_temp});
   /*   this.setState({suplierName_temp : suplierName_temp});
      this.setState({suplierEmailId_temp : suplierEmailId_temp});
      this.setState({prNo_temp : prNo_temp});
      this.setState({poDate_temp : poDate_temp});
    */
      this.generatePdf();

    }

 }); 
}

getAllMaterialIssueNotesBySearch(){
  
  let list = [];

  if(this.state.materialIssueNote.departmentName !='' || this.state.materialIssueNote.workName !='' ||
     this.state.materialIssueNote.startDate !='' || this.state.materialIssueNote.endDate !=''){

  this.setState({errorMsgShow: true});

  axiosInstance.post('/material-issue-note/getBySearchValues/', this.state.materialIssueNote).then((response) => {

    response.data.map((val, index) => {

      list.push({
        index: (index+1),
         minNo: val.minNo,
         workName: val.workName,
         minDate: val.minDate,
         departmentName: val.departmentName,
         action: [
          <IconButton aria-label="cyan" style={{color: '#4285F4'}} size="small" onClick={this.onClick.bind(this, val.materialIssueNoteId)}> <FaEdit /> </IconButton>,
          <IconButton aria-label="delete" style={{color: '#fb3640'}} size="small" disabled={this.state.resourcePermission.delete} onClick={this.toggle(val.materialIssueNoteId)}> <FaTrash /> </IconButton>,
          <IconButton aria-label="cyan" style={{color: '#fdca40'}} size="small" onClick={this.downloadReport.bind(this, val.materialIssueNoteId)}> <FaDownload /> </IconButton>,
                ]
      });

     })

     this.setState({materialIssueNoteList : list});

  });
}else{
  this.setState({errorMsgShow: false});
}

}

clearSearchFilter(){

  this.setState({errorMsgShow: true});

  this.setState({materialIssueNote: { materialType: '', workName: '', startDate: '', endDate: '' }});
  this.getAllMaterialIssueNotes(); 
  
}

generatePdf = () =>{
       
  var doc = new jsPDF('p','pt');

  doc.setFontSize(13);
  doc.setFont('Arial');
  //doc.text("Financial Year", 20, 35);
  doc.text("Material Issue Note Report", 240, 35);
  //doc.text("Stores Copy", 480, 35);

  doc.setLineWidth(0.5);
  doc.rect(10, 20, 575, 20);

  doc.setLineWidth(0.5);
  doc.rect(10, 20, 575, 805);

  //doc.setFontSize(15);
/*  doc.setTextColor(0, 0, 0);
  doc.text("Material Qc No: ", 20, 65);

 doc.setTextColor(0, 0, 160);
  doc.text(this.state.prNo_temp, 130, 65);

  doc.setTextColor(0, 0, 0);
  doc.text("Date: ", 480, 65);

  doc.setTextColor(0, 0, 160);
  doc.text(this.state.poDate_temp, 515, 65);

  doc.setTextColor(0, 0, 0);
  doc.text("To,", 20, 90);
  doc.setLineWidth(0.5);
  doc.setLineDash([10, 1, 1, 2], 1);

  doc.setTextColor(0, 0, 160);
  doc.text(this.state.suplierName_temp, 45, 100);
  doc.line(35, 100, 180, 100);
*/
  //doc.text(this.state.suplierEmailId_temp, 45, 115);
  //doc.line(35, 120, 180, 120);

  doc.setTextColor(0, 0, 0);

  //doc.text("Requisition No:", 230, 65);
  //doc.text("087557890", 330, 65);

  //doc.setFontSize(12);

/*  doc.text("Signature of Department Officer", 40, 700);
  doc.text("Signature of Chief Officer", 400, 760);

  doc.setLineDash([10, 0, 0, 0], 0);
*/

  doc.autoTable({
      head: [this.state.headers],
body: this.state.materialIssueNoteList_temp,
startY: 80,
theme: 'grid'   
});

window.open(doc.output('bloburl'), '_blank');

  //doc.save('a.pdf');
}

toggle = nr => () => {

  let {newMaterialIssueNote} = this.state;
  newMaterialIssueNote.materialIssueNoteId = nr;
  this.setState(newMaterialIssueNote);

  this.setState({
    modal: !this.state.modal
  });
}

onClick = (materialIssueNoteId) => {
  if(materialIssueNoteId != undefined && materialIssueNoteId != 0){
    sessionStorage.setItem('materialIssueNoteId', materialIssueNoteId);
  }
    this.props.history.push("/app/cloud-office/inventory/material-issue-note/add-material-issue-note");
}

getAllDepartments(){
  // let departmentList = this.state.departmentList;
    axiosInstance.get('/department/getAll').then((response) => {

       this.setState({departmentList: response.data});
    });
}

showSearch = () =>{

   this.setState({showSearch: !this.state.showSearch});
   this.setState({errorMsgShow: true});

   this.getAllDepartments();
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
    label: 'MIN No',
    field: 'minNo',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Work Name',
    field: 'workName',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Department Name',
    field: 'departmentName',
    sort: 'asc',
    width: 270
  },
  {
    label: 'Date',
    field: 'minDate',
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


let {materialIssueNote} = this.state;

const departmentList = this.state.departmentList.map((val) =>{
  return(
     <option value={val.deptName}> {val.deptName} </option>
   )
});

        return(

<div className="formelements-wrapper">
  <RctCollapsibleCard heading="All Material Issue Notes">
   
<Grid justify="space-between" container spacing={24}>
    <Grid item> 
    <Button variant="contained" className="btn-primary text-white mr-10 mb-10" size="small" onClick={()=>this.onClick(0)}>
      <IoIosAddCircle size="25px" style={{verticalAlign:"middle"}} /> 
      Add Material Issue Note
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
         <Grid item sm={4}> <Label for="materialType">Department Name</Label> 
         <Input type="select" name="departmentName" id="departmentName" value={this.state.newMaterialIssueNote.departmentName}
                   onChange={(e) => {
                     newMaterialIssueNote.departmentName = e.target.value;
                     this.setState(newMaterialIssueNote);
                   }}>
                  <option value=''>-select-</option>
                   {departmentList}
          </Input>
         </Grid> 
         <Grid item sm={4}> <Label for="workName">Work Name</Label> <Input 
         value={materialIssueNote.workName}
         onChange={ (e) => {
           materialIssueNote.workName = e.target.value
           this.setState(materialIssueNote);
          }}
         /> </Grid>
      </Grid>

      <Grid container justify='center' spacing={2}>
         <Grid item sm={4}> <Label for="startDate">Start Date</Label> <Input type="date" value={materialIssueNote.startDate}
          onChange={ (e) => {
            materialIssueNote.startDate = e.target.value
            this.setState(materialIssueNote);
           }} 
           /> </Grid>
         <Grid item sm={4}> <Label for="endDate">End Date</Label> <Input type="date" value={materialIssueNote.endDate}
         onChange={ (e) => {
           materialIssueNote.endDate = e.target.value
           this.setState(materialIssueNote);
          }} 
         /> </Grid>
      </Grid>

      <Grid container justify='center' alignContent='center' spacing={2}>
      <Grid item > <Button variant="contained" className="btn-primary text-white mr-10 mb-10" onClick={() => this.getAllMaterialIssueNotesBySearch()}>Serach</Button> </Grid>
      <Grid item > <Button variant="contained" className="btn-default text-white mr-10 mb-10" onClick={() => this.clearSearchFilter()}>Clear</Button> </Grid>
      </Grid>
</Paper>
        </AccordionDetails>
      </Accordion>
</div>

{ /*-------------------------------------------------------------------------------------------------------*/ }

     <DataTable name="UserRole" columns={columns} rows={this.state.materialIssueNoteList} />

     <NotificationContainer />

<MDBModal isOpen={this.state.modal} toggle={this.toggle(0)} size="sm" centered>
  <MDBModalBody className="text-center">
   Are sure you want to delete this record?
  </MDBModalBody>
  <MDBModalFooter className="text-center">
    <MDBBtn color="danger" size="sm" onClick={() => this.deleteMaterialIssueNote(this.state.newMaterialIssueNote.materialIssueNoteId)}>Yes</MDBBtn>
    <MDBBtn color="blue-grey" size="sm" onClick={this.toggle(0)}>No</MDBBtn>
  </MDBModalFooter>
</MDBModal>
    
</RctCollapsibleCard>
     </div>

        )
    }
}


//ReactDOM.render(<CustomTreeView/>, document.getElementById('root'));