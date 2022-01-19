import React,{ Component } from "react"
import axios from 'axios';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { Tooltip, Zoom, RadioGroup, FormControlLabel, Radio, Checkbox, Grid } from "@material-ui/core";
import { IoIosAddCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../util/axiosInstance";
import { Label, Input } from 'reactstrap';

class AddMaterialIssueNote extends Component {

    state = {

      newMaterialIssueNote: {

         minNo: '',
         workName: '',
         minDate: '',
         departmentName: '',
     },

     departmentList: [],

      operationType: 'ADD',
    }

    componentWillMount()
    {
        if(sessionStorage.getItem('materialIssueNoteId')!=null){
              this.getMaterialIssueNoteById(sessionStorage.getItem('materialIssueNoteId'));
        }

        this.getAllDepartments();
    }
      
    clickOnDelete(record) {
      // alert('clickOnDelete() : '+record.remark);
        this.setState({
            memberPreviousOrgList: this.state.memberPreviousOrgList.filter(r => r !== record)
        });
    }

    addMaterialIssueNote()
    {
      //alert('addMaterialIssueNote() : '+JSON.stringify(this.state.selectedFile))

        axiosInstance.post('/material-issue-note/add/', this.state.newMaterialIssueNote).then((response) => {
        
          NotificationManager.success('Material Issue Note Saved Successfully!', '');
       });

       //window.member_previous_orgList.addMaterialIssueNote();
    }

    updateMaterialIssueNote(){
       axiosInstance.put('/material-issue-note/update/', this.state.newMaterialIssueNote).then((response) => {
         //console.log(response.data);
         //alert(JSON.stringify(response.data))
         NotificationManager.success('Material Issue Note Updated Successfully!', '');
      });

    }

    getMaterialIssueNoteById(materialIssueNoteId){
     //alert('getMaterialIssueNoteById() : '+materialIssueNoteId);
      this.setState({operationType: 'UPDATE'});

      let {newMaterialIssueNote} = this.state;
      axiosInstance.get('/material-issue-note/getById/'+ materialIssueNoteId).then((response) => {

         console.log('#getMaterialIssueNoteById() : '+JSON.stringify(response.data));
         newMaterialIssueNote = response.data;
         this.setState({newMaterialIssueNote});
      });

      //sessionStorage.removeItem('materialIssueNoteId');
    }
   
    getAllDepartments(){
     // let departmentList = this.state.departmentList;
       axiosInstance.get('/department/getAll').then((response) => {
   
          this.setState({departmentList: response.data});
       });
   }

    render() {
       
        let { newMaterialIssueNote } = this.state;

        const departmentList = this.state.departmentList.map((val) =>{
         return(
            <option value={val.deptName}> {val.deptName} </option>
          )
      });

        return (
            <div className="formelements-wrapper">
                <NotificationContainer/>

                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 5 }}>
                       
                    <div className="col-sm-12 col-md-12 col-xl-12">
                           
   <RctCollapsibleCard customClasses="border border-info" heading="Add Material Isuue Note">


   <RctCollapsibleCard customClasses="border border-info" >

<form noValidate autoComplete="off">
         
         <div className="row">
      
         <div className="col-sm-12 col-md-12 col-xl-12">
      
  <div className="row">

  <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="minNo">MIN No.</Label>
         
         <Input type="text" name="minNo" id="minNo" value={this.state.newMaterialIssueNote.minNo}
         onChange={(e) => {
            newMaterialIssueNote.minNo = e.target.value;
            this.setState(newMaterialIssueNote);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="workName">Work Name</Label>

         <Input type="textarea" name="workName" id="workName" value={this.state.newMaterialIssueNote.workName} 
          onChange={(e) => {
            newMaterialIssueNote.workName = e.target.value;
            this.setState(newMaterialIssueNote);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
       <div className="form-group">
       <Label for="departmentName">Department Name</Label>
                  <Input type="select" name="departmentName" id="departmentName" value={this.state.newMaterialIssueNote.departmentName}
                   onChange={(e) => {
                     newMaterialIssueNote.departmentName = e.target.value;
                     this.setState(newMaterialIssueNote);
                   }}>
                  <option value=''>-select-</option>
                   {departmentList}
                  </Input>
       </div>
       </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="minDate">Date</Label>
         <Input type="date" id="minDate" name="minDate" value={this.state.newMaterialIssueNote.minDate} 
         onChange={(e) => {
            newMaterialIssueNote.minDate = e.target.value;
            this.setState(newMaterialIssueNote);
         }}
         />
      </div>
      </div>
      
      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}
    
         </div>
         </div>
      
         </form>

</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------*/}

<Grid  container direction="row" justify="center" alignItems="center">
  <Grid item>
       <MDBBtn color="success" onClick={() => this.addMaterialIssueNote()} hidden={ (this.state.operationType=='ADD')?false:true }>SUBMIT</MDBBtn> 
       <MDBBtn color="success" onClick={() => this.updateMaterialIssueNote()} hidden={ (this.state.operationType=='UPDATE')?false:true }>UPDATE</MDBBtn> 
       <MDBBtn color="blue-grey">CLEAR</MDBBtn>
  </Grid>
</Grid>

                    </RctCollapsibleCard>

                        </div>
                       
                    </div>
                </form>
             
            </div>
        )
    }
}
export default AddMaterialIssueNote;