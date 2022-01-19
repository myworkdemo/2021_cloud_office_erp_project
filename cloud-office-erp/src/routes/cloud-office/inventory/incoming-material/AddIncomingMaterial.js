import React,{ Component } from "react"
import axios from 'axios';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { Tooltip, Zoom, RadioGroup, FormControlLabel, Radio, Checkbox, Grid, TextField } from "@material-ui/core";
import { IoIosAddCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../util/axiosInstance";
import { Label, Input } from 'reactstrap';
import IncomingMaterialInfo from "./component/IncomingMaterialInfo";
import { CustomInput } from 'reactstrap';
import { Typeahead } from 'react-bootstrap-typeahead'; // ES2015

class AddIncomingMaterial extends Component {

    state = {

      newIncomingMaterialData: {

         //incomingMaterialId: '',
        
         incomingRegisterCode: '',
         incomingRegisterDate: '',
         workName: '',
         supplierName: '',
         mrnNo: '',
         suplierInvoiceNo: '',
         suplierInvoiceDate: '',
         departmentName: '',
         fileAttachment: '',

         invoiceAttachment: [],
         otherAttachment: [],

         vendor:{
            vendorId: ''
         }
     },
   
     vendorName: '',
     materialName: '',

     options: [],
     vendorList: [],
     selected: [],
   
     materialList: [],
     departmentList: [],
   
     memberPreviousOrgList: [{ index: Math.random(), incomingMaterialInfoId: "", srNo: "", materialType: "", materialSubType: "", materialName: "", 
     description: "", accountCode: "", uom: "", prQty: "", poQty: "", balanceQty: "", deliveredQty: "", unitRate: "", totalLandingCost: "" }],

     invoiceAttachmentFile: [],
     otherAttachmentFile: [],

      operationType: 'ADD',
    }

    componentWillMount()
    {
        if(sessionStorage.getItem('incomingMaterialId')!=null){
              this.getIncomingMaterialById(sessionStorage.getItem('incomingMaterialId'));
        }

        this.getAllVendors();
        this.getAllDepartments();
    }

    handleChangeRadio = (e, key) => {
		this.setState({ [key]: e.target.value });
	}

    handleChange = (e) => {
      
        if (["srNo", "incomingMaterialInfoId", ,"materialType", "materialSubType", "materialName", "description", "accountCode", 
        "uom", "prQty", "poQty", "balanceQty", "deliveredQty", "unitRate", "totalLandingCost"].includes(e.target.name)) {
            let memberPreviousOrgList = [...this.state.memberPreviousOrgList]
            memberPreviousOrgList[e.target.dataset.id][e.target.name] = e.target.value;
        } else {
            this.setState({ [e.target.name]: e.target.value })
        }
    }
    
    addNewRow = (e) => {
         //alert('#addNewRow()')
        this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index: Math.random(), srNo: "", materialType: "", materialSubType: "", materialName: "", 
               description: "", accountCode: "", uom: "", prQty: "", poQty: "", balanceQty: "", deliveredQty: "", unitRate: "", totalLandingCost: "" }],
        }));

       // window.incoming_material_info.addPN();
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

    addIncomingMaterialData()
    {
      //alert('IncomingMaterialData() : '+JSON.stringify(this.state.invoiceAttachmentFile))

       var postData = JSON.stringify(this.state.newIncomingMaterialData);

       const data = new FormData();
       data.append('invoiceAttachmentFile', this.state.invoiceAttachmentFile);
       data.append('otherAttachmentFile', this.state.otherAttachmentFile);
       data.append('incomingMaterial', postData);

        axiosInstance.post('/incoming-material/add/', data).then((response) => {
          //console.log(response.data);
          //alert(JSON.stringify(response.data))
         window.incoming_material_info.addIncomingMaterialInfoData(response.data.incomingMaterialId);
          NotificationManager.success('Incoming Material Saved Successfully!', '');
       });

       //window.member_previous_orgList.IncomingMaterialData();
    }

    updateIncomingMaterialData(){
//alert('updateIncomingMaterialData()');
      let { newIncomingMaterialData } = this.state;

      newIncomingMaterialData.invoiceAttachment = [];
      this.setState(newIncomingMaterialData);

      var postData = JSON.stringify(newIncomingMaterialData);

      const data = new FormData();
      data.append('invoiceAttachmentFile', this.state.invoiceAttachmentFile);
       data.append('otherAttachmentFile', this.state.otherAttachmentFile);
      data.append('IncomingMaterial', postData);

       axiosInstance.put('/incoming-material/update/', data).then((response) => {
         //console.log(response.data);
         //alert(JSON.stringify(response.data))
         window.incoming_material_info.updateIncomingMaterialInfoData(response.data.incomingMaterialId);
         NotificationManager.success('Incoming Material Updated Successfully!', '');
      });

    }

    onInvoiceAttachment = (e) => {

    //alert('onFileChangeHandler() : '+e.target.files[0]);
    this.setState({invoiceAttachmentFile : e.target.files[0]});
    }

    onOtherAttachment = (e) => {

      //alert('onFileChangeHandler() : '+e.target.files[0]);
      this.setState({otherAttachmentFile : e.target.files[0]});
      }

    getIncomingMaterialById(incomingMaterialId){
//alert('getIncomingMaterialById() : '+incomingMaterialId);
      this.setState({operationType: 'UPDATE'});

      let {newIncomingMaterialData} = this.state;
      axiosInstance.get('/incoming-material/getById/'+ incomingMaterialId).then((response) => {

         console.log('#getIncomingMaterialById() : '+JSON.stringify(response.data));
         newIncomingMaterialData = response.data;
         this.setState({newIncomingMaterialData});

         const option = this.state.options.find(e => e.vendorName === newIncomingMaterialData.supplierName);
       
         this.setState({
           selected: option ? [option] : []
         })

         this.getIncomingMaterialMaterialInfoByIncomingMaterialId(incomingMaterialId);
      });

      //sessionStorage.removeItem('incomingMaterialId');
    }

    getIncomingMaterialMaterialInfoByIncomingMaterialId(incomingMaterialId){

      let incomingMaterialInfoId, srNo, materialType, materialSubType, materialName, description, accountCode, uom, prQty, poQty, 
      balanceQty, deliveredQty, unitRate, totalLandingCost;

      axiosInstance.get('/incoming-material-info/getById/'+incomingMaterialId).then((response) => {
    //alert(JSON.stringify(response.data));
         response.data.map((val, index) => {
            //console.log('### AMOUNT : '+val.amount)
            index = Math.random(),
            incomingMaterialInfoId = val.incomingMaterialInfoId,
            srNo = val.srNo,
            materialType = val.materialType,
            materialSubType = val.materialSubType,
           
            materialName = val.materialName,
            description = val.description,
            accountCode = val.accountCode,

            uom = val.uom,
            prQty = val.prQty,
            poQty = val.poQty,
           
            deliveredQty = val.deliveredQty,
            unitRate = val.unitRate,
           
            totalLandingCost = val.totalLandingCost,
            
           
            
           // IncomingMaterial = val.IncomingMaterial.incomingMaterialId,
 
            this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index, incomingMaterialInfoId, srNo, materialType, materialSubType, 
               materialName, description, accountCode, uom, prQty, poQty, balanceQty, deliveredQty, unitRate, totalLandingCost }],
        }));
 
         });

      });

    }

    getAllVendors(){
     
      let options_temp = [];
       axiosInstance.get('/vendor/getAll').then((response) => {
   
          this.setState({vendorList: response.data});

        /*  response.data.map(val => {

            options_temp.push({
               vendorName: val.vendorName,
               vendorId: val.vendorId
             });

          });
          this.setState({options: options_temp});
          */
       });
   }

   getAllDepartments(){
     
       axiosInstance.get('/department/getAll').then((response) => {
   
          this.setState({departmentList: response.data});
       });
   }

    render() {
        let { memberPreviousOrgList } = this.state;//let { notes, date, description, memberPreviousOrgList } = this.state
        
        let { newIncomingMaterialData } = this.state;

        const vendorList = this.state.vendorList.map((val) =>{
         return(
            <option value={val.vendorId}> {val.vendorName} </option>
          )
      });

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
                           
   <RctCollapsibleCard customClasses="border border-info" heading="Add Incoming Material">


   <RctCollapsibleCard customClasses="border border-info" heading="Incoming Details">

<form noValidate autoComplete="off">
         
         <div className="row">
      
         <div className="col-sm-12 col-md-12 col-xl-12">
      
  <div className="row">
  <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="incomingRegisterCode">Incoming Register Code</Label>
         
         <Input type="text" name="incomingRegisterCode" id="incomingRegisterCode" value={this.state.newIncomingMaterialData.incomingRegisterCode}
         onChange={(e) => {
            newIncomingMaterialData.incomingRegisterCode = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="incomingRegisterDate">Incoming Register Date</Label>
         <Input type="date" id="incomingRegisterDate" name="incomingRegisterDate" value={this.state.newIncomingMaterialData.incomingRegisterDate} 
         onChange={(e) => {
            newIncomingMaterialData.incomingRegisterDate = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />
      </div>
      </div>
      
      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="workName">Work Name</Label>

         <Input type="textarea" name="workName" id="workName" value={this.state.newIncomingMaterialData.workName} 
          onChange={(e) => {
            newIncomingMaterialData.workName = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />

      </div>
      </div>
      
      </div>{/*row end*/}
      
      {/*--------------------------------------------------------------------------------*/}

      <div className="row">

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">

      <Label for="supplierName">Supplier Name</Label>

      <Input type="select" name="supplierName" id="supplierName" value={this.state.newIncomingMaterialData.supplierName} 
         onChange={(e) => {
            newIncomingMaterialData.supplierName = e.target.value;
            this.setState(newIncomingMaterialData); 
            }}>
              <option value=''>-select-</option>
                   {vendorList}
      </Input>

     {/* <Typeahead
          id="basic-typeahead-single"
          labelKey="vendorName"
          onChange={
            (e) => {
               //alert(e[0])
               newIncomingMaterialData.supplierName = e[0]===undefined? "": e[0].vendorName;
               newIncomingMaterialData.vendor.vendorId = e[0]===undefined? "": e[0].vendorId;
               this.setState(newIncomingMaterialData);
            }
          }
          options={this.state.options}
          placeholder="Select a supplier..."
          selected={this.state.selected}
        />
         */}

         {/*
         <Label for="supplierName">Supplier Name</Label>
         <Input type="text" name="supplierName" id="supplierName" value={this.state.newIncomingMaterialData.supplierName} 
         onChange={(e) => {
            newIncomingMaterialData.supplierName = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />
      */}
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="mrnNo">MRN No.</Label>
         <Input type="text" id="mrnNo" name="mrnNo" value={this.state.newIncomingMaterialData.mrnNo} 
         onChange={(e) => {
            newIncomingMaterialData.mrnNo = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="suplierInvoiceNo">Suplier Invoice No.</Label>
         <Input type="text" name="suplierInvoiceNo" id="suplierInvoiceNo" value={this.state.newIncomingMaterialData.suplierInvoiceNo} 
         onChange={(e) => {
            newIncomingMaterialData.suplierInvoiceNo = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="suplierInvoiceDate">Suplier Invoice Date</Label>
         <Input type="date" id="suplierInvoiceDate" name="suplierInvoiceDate" value={this.state.newIncomingMaterialData.suplierInvoiceDate} 
         onChange={(e) => {
            newIncomingMaterialData.suplierInvoiceDate = e.target.value;
            this.setState(newIncomingMaterialData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="departmentName">Department Name</Label>
         <Input type="select" name="departmentName" id="departmentName" value={this.state.newIncomingMaterialData.departmentName} 
         onChange={(e) => {
            newIncomingMaterialData.departmentName = e.target.value;
            this.setState(newIncomingMaterialData); 
            }}>
              <option value=''>-select-</option>
                   {departmentList}
                  </Input>
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="fileAttachment">File Attachment</Label>
         
         <RadioGroup row aria-label="fileAttachment" name="fileAttachment2" 
                    onChange={(e) => {
                     newIncomingMaterialData.fileAttachment = e.target.value;
                       this.setState(newIncomingMaterialData);
                    }} value={newIncomingMaterialData.fileAttachment} >
                      <FormControlLabel value="Import" control={<Radio color="primary" />} label="Import" />
                      <FormControlLabel value="Local" control={<Radio color="primary" />} label="Local" />
                    </RadioGroup>

      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="invoiceAttachment">Invoice Attachment</Label>

         <CustomInput type="file" id="invoiceAttachment" label={(newIncomingMaterialData.invoiceAttachment!='')?'File is selected':'No file choosen'} onChange={this.onInvoiceAttachment} />
      
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="otherAttachment">If any Other Attachment</Label>

         <CustomInput type="file" id="otherAttachment" label={(newIncomingMaterialData.otherAttachment!='')?'File is selected':'No file choosen'} onChange={this.onOtherAttachment} />
      
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}
    
         </div>
         </div>
      
         </form>

</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------------*/}

<RctCollapsibleCard customClasses="border border-info" heading="Incoming Material Info">
   
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
                                                
                                                <th className="required" >Material Name</th>
                                                <th className="required" >Description</th>
                                                <th className="required" >Account Code</th>
                                                <th className="required" >UOM</th>
                                                <th className="required" >PR Qty.</th>
                                                <th className="required" >PO Qty.</th>
                                               
                                                <th className="required" >Balance Qty.</th>
                                                <th className="required" >Delivered Qty.</th>
                                                
                                                <th className="required" >Unit Rate2</th>
                                                <th className="required" >Total Landing Cost</th>
                                                
                                                <th className="required">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <IncomingMaterialInfo add={this.addNewRow} delete={this.clickOnDelete.bind(this)} memberPreviousOrgList={memberPreviousOrgList} />
                                        </tbody>   
                                    </table>
                     </div>               
</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------*/}

<Grid  container direction="row" justify="center" alignItems="center">
  <Grid item>
       <MDBBtn color="success" onClick={() => this.addIncomingMaterialData()} hidden={ (this.state.operationType=='ADD')?false:true }>SUBMIT</MDBBtn> 
       <MDBBtn color="success" onClick={() => this.updateIncomingMaterialData()} hidden={ (this.state.operationType=='UPDATE')?false:true }>UPDATE</MDBBtn> 
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
export default AddIncomingMaterial;