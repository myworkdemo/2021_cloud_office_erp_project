import React,{ Component } from "react"
import axios from 'axios';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { Tooltip, Zoom, RadioGroup, FormControlLabel, Radio, Checkbox, Grid } from "@material-ui/core";
import { IoIosAddCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../util/axiosInstance";
import { Label, Input } from 'reactstrap';
import MaterialQcDetails from "./component/MaterialQcDetails";
import { CustomInput } from 'reactstrap';

class AddMaterialQc extends Component {

    state = {

      newMaterialQcData: {

         //materialQcId: '',
         qcCode: '',
         qcDate: '',
         workName: '',
         irCode: '',
         irDate: '',
         poCode: '',

         suplierInvoiceNo: '',
         suplierName: '',
         departmentName: '',

         sendForApproval: 'No',
         storeInchargeApproval: '',
         qcAttachment: [],
     },
   
     vendorName: '',
     materialName: '',
   
     materialList: [],
     vendorList: [],
   
     memberPreviousOrgList: [{ index: Math.random(), meterialQcDetailsId: "", srNo: "", materialType: "", materialSubType: "", accountCode: "", 
      materialName: "", description: "", uom: "", unitPrice: "",  deliveredQty: "",  approvedQty: "",  rejectedQty: "",  balanceQty: "", amount: "", remark: "", materialQc: "" }],

      selectedFile: [],

      operationType: 'ADD',
    }

    componentWillMount()
    {
        if(sessionStorage.getItem('materialQcId')!=null){
              this.getMaterialQcById(sessionStorage.getItem('materialQcId'));
        }

        this.getAllVendors();
    }

    handleChangeRadio = (e, key) => {
		this.setState({ [key]: e.target.value });
	}

    handleChange = (e) => {
      
        if (["srNo", "meterialQcDetailsId", "materialType", "materialSubType", "accountCode", 
        "materialName", "description", "uom", "unitPrice", "deliveredQty", "approvedQty", "rejectedQty", "balanceQty", 
        "remark", "materialQc"].includes(e.target.name)) {
            let memberPreviousOrgList = [...this.state.memberPreviousOrgList]
            memberPreviousOrgList[e.target.dataset.id][e.target.name] = e.target.value;
        } else {
            this.setState({ [e.target.name]: e.target.value })
        }
    }
    
    addNewRow = (e) => {
         //alert('#addNewRow()')
        this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index: Math.random(), companyNames: "", startDate: "", endDate: "", description: "", ctc: "" }],
        }));

       // window.material_qc_details.addPN();
    }

    deteteRow = (index) => {
        this.setState({
            memberPreviousOrgList: this.state.memberPreviousOrgList.filter((s, sindex) => index !== sindex),
        });
        // const taskList1 = [...this.state.memberPreviousOrgList];
        // taskList1.splice(index, 1);
        // this.setState({ memberPreviousOrgList: taskList1 });
    }
    
    handleSubmit = (e) => {
        e.preventDefault();
        if(this.state.date==='' || this.state.description==='')
        {
            NotificationManager.warning("Please Fill up Required Field . Please check Task and Date Field");
            return false;
        }
        for(var i=0;i<this.state.memberPreviousOrgList.length;i++)
        {
                if(this.state.memberPreviousOrgList[i].companyNames==='' || this.state.memberPreviousOrgList[i].startDate==='')
                {
                    NotificationManager.warning("Please Fill up Required Field.Please Check Project name And Task Field");
                    return false;
                }
        }

    }

    clickOnDelete(record) {
      // alert('clickOnDelete() : '+record.remark);
        this.setState({
            memberPreviousOrgList: this.state.memberPreviousOrgList.filter(r => r !== record)
        });
    }

    addMaterialQcData()
    {
      //alert('addMaterialQcData() : '+JSON.stringify(this.state.selectedFile))

       var postData = JSON.stringify(this.state.newMaterialQcData);

       const data = new FormData();
       data.append('file', this.state.selectedFile);
       data.append('materialQc', postData);

        axiosInstance.post('/material-qc/add/', data).then((response) => {
          //console.log(response.data);
          //alert(JSON.stringify(response.data))
          window.material_qc_details.addMaterialQcDetailsData(response.data.materialQcId);
          NotificationManager.success('Material QC Saved Successfully!', '');
       });

       //window.member_previous_orgList.addMaterialQcData();
    }

    updateMaterialQcData(){
//alert('updateMaterialQcData()');
      let { newMaterialQcData } = this.state;

      newMaterialQcData.qcAttachment = [];
      this.setState(newMaterialQcData);

      var postData = JSON.stringify(newMaterialQcData);

      const data = new FormData();
      data.append('file', this.state.selectedFile);
      data.append('materialQc', postData);

       axiosInstance.put('/material-qc/update/', data).then((response) => {
         //console.log(response.data);
         //alert(JSON.stringify(response.data))
         window.material_qc_details.updateMaterialQcDetailsData(response.data.materialQcId);
         NotificationManager.success('Purhase Order Updated Successfully!', '');
      });

    }

    onFileChangeHandler = (e) => {

    //alert('onFileChangeHandler() : '+e.target.files[0]);
    this.setState({selectedFile : e.target.files[0]});
    }

    getMaterialQcById(materialQcId){
//alert('getMaterialQcById() : '+materialQcId);
      this.setState({operationType: 'UPDATE'});

      let {newMaterialQcData} = this.state;
      axiosInstance.get('/material-qc/getById/'+ materialQcId).then((response) => {

         console.log('#getMaterialQcById() : '+JSON.stringify(response.data));
         newMaterialQcData = response.data;
         this.setState({newMaterialQcData});

         this.getMaterialQcMaterialInfoByMaterialQcId(materialQcId);
      });

      //sessionStorage.removeItem('materialQcId');
    }

    getMaterialQcMaterialInfoByMaterialQcId(materialQcId){

      let srNo, meterialQcDetailsId, materialType, materialSubType, accountCode, materialName, description, uom, unitPrice, deliveredQty, approvedQty, rejectedQty, balanceQty,
        remark, materialQc;

      axiosInstance.get('/material-qc-details/getById/'+materialQcId).then((response) => {
     //alert(JSON.stringify(response.data));
         response.data.map((val, index) => {
            //console.log('### AMOUNT : '+val.amount)
            index = Math.random();
            meterialQcDetailsId = val.meterialQcDetailsId;
            srNo = val.srNo;
            materialType = val.materialType;
            materialSubType = val.materialSubType;
            accountCode = val.accountCode;
            materialName = val.materialName;
            description = val.description;
            uom = val.uom;
            unitPrice = val.unitPrice;
            deliveredQty = val.deliveredQty;
            approvedQty = val.approvedQty;
            rejectedQty = val.rejectedQty;
            balanceQty = val.balanceQty;
            
            remark = val.remark;
            
            materialQc = val.materialQc.materialQcId;
 
            this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index, meterialQcDetailsId, srNo, materialType, materialSubType, accountCode, 
               materialName, description, deliveredQty, uom, unitPrice, approvedQty, rejectedQty, balanceQty, remark, materialQc }],
        }));
 
         });

      });

    }

    getAllVendors(){
      let vendorList = this.state.vendorList;
       axiosInstance.get('/vendor/getAll').then((response) => {
   
          this.setState({vendorList: response.data});
       });
   }


    render() {
        let { memberPreviousOrgList } = this.state;//let { notes, date, description, memberPreviousOrgList } = this.state
        
        let { newMaterialQcData } = this.state;

        const vendorList = this.state.vendorList.map((val) =>{
         return(
            <option value={val.vendorName}> {val.vendorName} </option>
          )
      });

        return (
            <div className="formelements-wrapper">
                <NotificationContainer/>

                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 5 }}>
                       
                    <div className="col-sm-12 col-md-12 col-xl-12">
                           
   <RctCollapsibleCard customClasses="border border-info" heading="Add Material QC">


   <RctCollapsibleCard customClasses="border border-info" heading="PO Details">

<form noValidate autoComplete="off">
         
         <div className="row">
      
         <div className="col-sm-12 col-md-12 col-xl-12">
      
  <div className="row">
  <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="qcCode">QC Code</Label>
         
         <Input type="text" name="qcCode" id="qcCode" value={this.state.newMaterialQcData.qcCode}
         onChange={(e) => {
            newMaterialQcData.qcCode = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="qcDate">QC Date</Label>
         <Input type="date" id="qcDate" name="qcDate" value={this.state.newMaterialQcData.qcDate} 
         onChange={(e) => {
            newMaterialQcData.qcDate = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>
      
      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="workName">Work Name</Label>

         <Input type="textarea" name="workName" id="workName" value={this.state.newMaterialQcData.workName} 
          onChange={(e) => {
            newMaterialQcData.workName = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />

      </div>
      </div>
      
      </div>{/*row end*/}
      
      {/*--------------------------------------------------------------------------------*/}

      <div className="row">

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="irCode">IR Code</Label>
         <Input type="number" name="irCode" id="irCode" value={this.state.newMaterialQcData.irCode} 
         onChange={(e) => {
            newMaterialQcData.irCode = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="irDate">IR Date</Label>
         <Input type="date" id="irDate" name="irDate" value={this.state.newMaterialQcData.irDate} 
         onChange={(e) => {
            newMaterialQcData.irDate = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="poCode">PO Code</Label>
         <Input type="text" name="poCode" id="poCode" value={this.state.newMaterialQcData.poCode} 
         onChange={(e) => {
            newMaterialQcData.poCode = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="suplierInvoiceNo">Suplier Invoice No.</Label>
         <Input type="number" id="suplierInvoiceNo" name="suplierInvoiceNo" value={this.state.newMaterialQcData.suplierInvoiceNo} 
         onChange={(e) => {
            newMaterialQcData.suplierInvoiceNo = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="suplierName">Suplier Name</Label>
         <Input type="select" name="suplierName" id="suplierName" value={this.state.newMaterialQcData.suplierName} 
         onChange={(e) => {
            newMaterialQcData.suplierName = e.target.value;
            this.setState(newMaterialQcData); 
             this.findVendorEmailId(e.target.value);
            }}>
              <option value=''>-select-</option>
                   {vendorList}
                  </Input>
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="departmentName">Department Name</Label>
         <Input type="text" id="departmentName" name="departmentName" value={this.state.newMaterialQcData.departmentName} 
         onChange={(e) => {
            newMaterialQcData.departmentName = e.target.value;
            this.setState(newMaterialQcData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="sendForApproval">Send for Approval</Label>
         
         <Checkbox value={newMaterialQcData.sendForApproval} checked={(newMaterialQcData.sendForApproval=='Yes')?true:false}  
         onChange={(e) => {

            newMaterialQcData.sendForApproval = (e.target.value=='Yes')? 'No' : 'Yes';
            this.setState(newMaterialQcData);
         }} />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="storeInvoiceApproval">Store Incharge Approval</Label>
         
         <Checkbox value={newMaterialQcData.storeInvoiceApproval} checked={(newMaterialQcData.storeInvoiceApproval=='Yes')?true:false}  
         onChange={(e) => {

            newMaterialQcData.storeInvoiceApproval = (e.target.value=='Yes')? 'No' : 'Yes';
            this.setState(newMaterialQcData);
         }} />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="qcAttachment">QC Attachment</Label>

         <CustomInput type="file" id="qcAttachment" label={(newMaterialQcData.qcAttachment!='')?'File is selected':'No file choosen'} onChange={this.onFileChangeHandler} />
      
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}
    
         </div>
         </div>
      
         </form>

</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------------*/}

<RctCollapsibleCard customClasses="border border-info" heading="Material QC Details">
   
   <div className="row">
   <div className="col-sm-11 col-md-11 col-xl-11"></div>

   <div className="col-sm-1 col-md-1 col-xl-1">
   <MDBTooltip sm placement="top" TransitionComponent={Zoom}>  
     <MDBBtn color="primary" onClick={this.addNewRow}> 
        <IoIosAddCircle size="20px" style={{verticalAlign:"middle"}} /> 
     </MDBBtn>
     <div>Add new row</div>
    </MDBTooltip>  
   </div>
</div>    

<div className="scrollbar scrollbar-primary">
      
                                    <table className="table">
                                        <thead>
                                            <tr>
                                                <th className="required" >Sr No.</th>
                                                <th className="required" >Material Type</th>
                                                <th className="required" >Material SubType</th>
                                                <th className="required" >Account Code</th>
                                                <th className="required" >Material Name</th>
                                                <th className="required" >Description</th>
                                                <th className="required" >UOM</th>
                                                <th className="required" >Unit Price</th>
                                                <th className="required" >Delivered Qty.</th>
                                                <th className="required" >Approved Qty.</th>
                                                <th className="required" >Rejected Qty.</th>
                                                <th className="required" >Balance Qty.</th>
                                                <th className="required" >Remark</th>
                                                <th className="required">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <MaterialQcDetails add={this.addNewRow} delete={this.clickOnDelete.bind(this)} memberPreviousOrgList={memberPreviousOrgList} />
                                        </tbody>   
                                    </table>
                     </div>               
</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------*/}

<Grid  container direction="row" justify="center" alignItems="center">
  <Grid item>
       <MDBBtn color="success" onClick={() => this.addMaterialQcData()} hidden={ (this.state.operationType=='ADD')?false:true }>SUBMIT</MDBBtn> 
       <MDBBtn color="success" onClick={() => this.updateMaterialQcData()} hidden={ (this.state.operationType=='UPDATE')?false:true }>UPDATE</MDBBtn> 
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
export default AddMaterialQc;