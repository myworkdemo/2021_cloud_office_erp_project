import React,{ Component } from "react"
import axios from 'axios';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { Tooltip, Zoom, RadioGroup, FormControlLabel, Radio, Checkbox, Grid } from "@material-ui/core";
import { IoIosAddCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../util/axiosInstance";
import { Label, Input } from 'reactstrap';
import MaterialInformation from "./component/MaterialInformation";
import { CustomInput } from 'reactstrap';

class AddPurchaseOrderData extends Component {

    state = {

      newPurchaseOrderData: {

         //purchaseOrderId: '',
         prNo: '',
         workName: '',
         faxNo: '',
         poCode: '',
         poDate: '',
         suplierName: '',
         suplierEmailId: '',
         suplierQuotationNo: '',
         suplierQuotationDate: '',
         refrenceNo: '',
         subject: '',
         cpApprove: '',
         storeInchargeApproval: '',
         sendForApproval: 'No',
         prBy: '',
         poAttachment: [],
         hideWorkNameOnPdf: '',

         gstinNo: '',
         accountNo: '',
         bankName: '',
         ifscCode: '',
         branch: '',
     },
   
     vendorName: '',
     materialName: '',
   
     materialList: [],
     vendorList: [],
   
     memberPreviousOrgList: [{ index: Math.random(), srNo: "", materialType: "", materialSubType: "", accountCode: "", 
      materialName: "", description: "", umo: "", prQty: "", requiredDate: "", poQty: "", balanceQty: "", unitPrice: "", amount: "", remark: "", purchaseOrder: "" }],

      selectedFile: [],

      operationType: 'ADD',
    }

    componentWillMount()
    {
        if(sessionStorage.getItem('purchaseOrderId')!=null){
              this.getPurchaseOrderById(sessionStorage.getItem('purchaseOrderId'));
        }

        this.getAllVendors();
        this.getAllMaterial();
    }

    handleChangeRadio = (e, key) => {
		this.setState({ [key]: e.target.value });
	}

    handleChange = (e) => {
      
        if (["srNo", "materialType", "materialSubType", "accountCode", 
        "materialName", "description", "umo", "prQty", "requiredDate", "poQty", "balanceQty", 
        "unitPrice", "amount", "remark", "purchaseOrder"].includes(e.target.name)) {
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

       // window.material_information.addPN();
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
        this.setState({
            memberPreviousOrgList: this.state.memberPreviousOrgList.filter(r => r !== record)
        });
    }

    addPurchaseOrderData()
    {
      // alert('addPurchaseOrderData() : '+this.state.selectedFile)

       var postData = JSON.stringify(this.state.newPurchaseOrderData);

       const data = new FormData();
       data.append('file', this.state.selectedFile);
       data.append('purchaseOrder', postData);

        axiosInstance.post('/purchase-order/add/', data).then((response) => {
          //console.log(response.data);
          //alert(JSON.stringify(response.data))
          window.material_information.addPurchaseOrderMaterialInfoData(response.data.purchaseOrderId);
          NotificationManager.success('Purhase Order Saved Successfully!', '');
       });

       //window.member_previous_orgList.addPurchaseOrderData();
    }

    updatePurchaseOrderData(){

      let { newPurchaseOrderData } = this.state;

      newPurchaseOrderData.poAttachment = [];
      this.setState(newPurchaseOrderData);

      var postData = JSON.stringify(newPurchaseOrderData);

      const data = new FormData();
      data.append('file', this.state.selectedFile);
      data.append('purchaseOrder', postData);

       axiosInstance.put('/purchase-order/update/', data).then((response) => {
         //console.log(response.data);
         //alert(JSON.stringify(response.data))
         window.material_information.updatePurchaseOrderMaterialInfoData(response.data.purchaseOrderId);
         NotificationManager.success('Purhase Order Updated Successfully!', '');
      });

    }

    onFileChangeHandler = (e) => {

    //alert('onFileChangeHandler() : '+e.target.files[0]);
    this.setState({selectedFile : e.target.files[0]});
    }

    getPurchaseOrderById(purchaseOrderId){

      this.setState({operationType: 'UPDATE'});

      let {newPurchaseOrderData} = this.state;
      axiosInstance.get('/purchase-order/getById/'+ purchaseOrderId).then((response) => {

         //console.log('#getPurchaseOrderById() : '+JSON.stringify(response.data));
         newPurchaseOrderData = response.data;
         this.setState({newPurchaseOrderData});

         this.getPurchaseOrderMaterialInfoByPurchaseOrderId(purchaseOrderId);
      });

      //sessionStorage.removeItem('purchaseOrderId');
    }

    getPurchaseOrderMaterialInfoByPurchaseOrderId(purchaseOrderId){

      let srNo, materialType, materialSubType, accountCode, materialName, description, umo, prQty, requiredDate, poQty, balanceQty,
        unitPrice,
        amount,
        remark, purchaseOrder;

      axiosInstance.get('/purchase-order-material-info/getById/'+purchaseOrderId).then((response) => {
     
         response.data.map((val, index) => {
            //console.log('### AMOUNT : '+val.amount)
            index = Math.random();
            srNo = val.srNo;
            materialType = val.materialType;
            materialSubType = val.materialSubType;
            accountCode = val.accountCode;
            materialName = val.materialName;
            description = val.description;
            umo = val.umo;
            prQty = val.prQty;
            requiredDate = val.requiredDate;
            poQty = val.poQty;
            balanceQty = val.balanceQty;
            unitPrice = val.unitPrice;
            amount = val.amount;
            remark = val.remark;
            
            purchaseOrder = val.purchaseOrder.purchaseOrderId;
 
            this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index, srNo, materialType, materialSubType, accountCode, 
               materialName, description, umo, prQty, requiredDate, poQty, balanceQty, unitPrice, amount, remark, purchaseOrder }],
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

   findVendorEmailId(vendorId){

      let { newPurchaseOrderData } = this.state;
      var data = this.state.vendorList.filter(item => item.vendorId == vendorId);
      newPurchaseOrderData.suplierEmailId = data[0].emailId;
      this.setState(newPurchaseOrderData);
   }

    render() {
        let { memberPreviousOrgList } = this.state//let { notes, date, description, memberPreviousOrgList } = this.state
        
        let { newPurchaseOrderData } = this.state;

        const vendorList = this.state.vendorList.map((val) =>{
         return(
            <option value={val.vendorId}> {val.vendorName} </option>
          )
      });

        return (
            <div className="formelements-wrapper">
                <NotificationContainer/>

                <form onSubmit={this.handleSubmit} onChange={this.handleChange} >
                    <div className="row" style={{ marginTop: 5 }}>
                       
                    <div className="col-sm-12 col-md-12 col-xl-12">
                           
   <RctCollapsibleCard customClasses="border border-info" heading="Add Purchase Order">


   <RctCollapsibleCard customClasses="border border-info" heading="PO Details">

<form noValidate autoComplete="off">
         
         <div className="row">
      
         <div className="col-sm-12 col-md-12 col-xl-12">
      
  <div className="row">
  <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="prNo">PR No.</Label>
         
         <Input type="text" name="prNo" id="prNo" value={this.state.newPurchaseOrderData.prNo}
         onChange={(e) => {
            newPurchaseOrderData.prNo = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />

      </div>
      </div>
      
      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="workName">Work Name</Label>

         <Input type="textarea" name="workName" id="workName" value={this.state.newPurchaseOrderData.workName} 
          onChange={(e) => {
            newPurchaseOrderData.workName = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />

      </div>
      </div>
      
      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="faxNo">Fax No.</Label>
         <Input type="number" name="faxNo" id="faxNo" value={this.state.newPurchaseOrderData.faxNo} 
         onChange={(e) => {
            newPurchaseOrderData.faxNo = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>
      
      </div>{/*row end*/}
      
      {/*--------------------------------------------------------------------------------*/}

      <div className="row">

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="poCode">PO Code</Label>
         <Input type="text" name="poCode" id="poCode" value={this.state.newPurchaseOrderData.poCode} 
         onChange={(e) => {
            newPurchaseOrderData.poCode = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="poDate">PO Date</Label>
         <Input type="date" id="poDate" name="poDate" value={this.state.newPurchaseOrderData.poDate} 
         onChange={(e) => {
            newPurchaseOrderData.poDate = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="suplierName">Suplier Name</Label>
         <Input type="select" name="suplierName" id="suplierName" value={this.state.newPurchaseOrderData.suplierName} 
         onChange={(e) => {
            
            newPurchaseOrderData.suplierName = e.target.value;
            this.setState(newPurchaseOrderData); 
             this.findVendorEmailId(e.target.value);
            }}>
              <option value=''>-select-</option>
                   {vendorList}
                  </Input>
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="suplierEmailId">Suplier Email-Id</Label>
         <Input type="text" id="suplierEmailId" name="suplierEmailId" value={this.state.newPurchaseOrderData.suplierEmailId} 
         onChange={(e) => {
            newPurchaseOrderData.suplierEmailId = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="suplierQuotationNo">Suplier Quotation No.</Label>
         <Input type="number" id="suplierQuotationNo" name="suplierQuotationNo" value={this.state.newPurchaseOrderData.suplierQuotationNo} 
         onChange={(e) => {
            newPurchaseOrderData.suplierQuotationNo = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="suplierQuotationDate">Suplier Quotation Date</Label>
         <Input type="date" id="suplierQuotationDate" name="suplierQuotationDate" value={this.state.newPurchaseOrderData.suplierQuotationDate} 
         onChange={(e) => {
            newPurchaseOrderData.suplierQuotationDate = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="refrenceNo">Refrence No.</Label>
         <Input type="text" name="refrenceNo" id="refrenceNo" value={this.state.newPurchaseOrderData.refrenceNo} 
         onChange={(e) => {
            newPurchaseOrderData.refrenceNo = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="subject">Subject</Label>
         <Input type="text" id="subject" name="subject" value={this.state.newPurchaseOrderData.subject} 
         onChange={(e) => {
            newPurchaseOrderData.subject = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="cpApprove">CP Approve</Label>
         
         <RadioGroup row aria-label="cpApprove" name="cpApprove2" 
                    onChange={(e) => {
                     newPurchaseOrderData.cpApprove = e.target.value;
                       this.setState(newPurchaseOrderData);
                    }} value={newPurchaseOrderData.cpApprove} >
                      <FormControlLabel value="Approve" control={<Radio color="primary" />} label="Approve" />
                      <FormControlLabel value="Reject" control={<Radio color="primary" />} label="Reject" />
                    </RadioGroup>
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="storeInchargeApproval">Store Incharge Approval</Label>
         
         <RadioGroup row aria-label="storeInchargeApproval" name="storeInchargeApproval"
                    onChange={(e) => {
                     newPurchaseOrderData.storeInchargeApproval = e.target.value;
                     this.setState(newPurchaseOrderData);
                    }} value={newPurchaseOrderData.storeInchargeApproval}>
                      <FormControlLabel value="Approve" control={<Radio color="primary" />} label="Approve" />
                      <FormControlLabel value="Reject" control={<Radio color="primary" />} label="Reject" />
                    </RadioGroup>
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

<div className="row">

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="sendForApproval">Send for Approval</Label>
         
         <Checkbox value={newPurchaseOrderData.sendForApproval} checked={(newPurchaseOrderData.sendForApproval=='Yes')?true:false}  
         onChange={(e) => {

            newPurchaseOrderData.sendForApproval = (e.target.value=='Yes')? 'No' : 'Yes';
            this.setState(newPurchaseOrderData);
         }} />

      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="prBy">PR By</Label>
         <Input type="textarea" id="prBy" name="prBy" value={this.state.newPurchaseOrderData.prBy} 
          onChange={(e) => {
            newPurchaseOrderData.prBy = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="poAttachment">PO Attachment</Label>

         <CustomInput type="file" id="docOriginalName" label={(newPurchaseOrderData.poAttachment!='')?'File is selected':'No file choosen'} onChange={this.onFileChangeHandler} />
      
      </div>
      </div>

      <div className="col-sm-3 col-md-3 col-xl-3">
      <div className="form-group">
         <Label for="hideWorkNameOnPdf">Hide work name on pdf</Label>
         
         <RadioGroup row aria-label="hideWorkNameOnPdf" name="hideWorkNameOnPdf" 
                     onChange={(e) => {
                        newPurchaseOrderData.hideWorkNameOnPdf = e.target.value;
                        this.setState(newPurchaseOrderData);
                     }} value={newPurchaseOrderData.hideWorkNameOnPdf}>
                      <FormControlLabel value="Yes" control={<Radio color="primary" />} label="Yes" />
                      <FormControlLabel value="No" control={<Radio color="primary" />} label="No" />
                    </RadioGroup>
      </div>
      </div>

      </div>{/*row end*/}
    
         </div>
         </div>
      
         </form>

</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------------*/}

<RctCollapsibleCard customClasses="border border-info" heading="Material Information">
   
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
                                                <th className="required" >PR Qty.</th>
                                                <th className="required" >Required Date</th>
                                                <th className="required" >PO Qty.</th>
                                                <th className="required" >Balance Qty.</th>
                                                <th className="required" >Unit Price</th>
                                                <th className="required" >Amount</th>
                                                <th className="required" >Remark</th>
                                                <th className="required">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <MaterialInformation add={this.addNewRow} delete={this.clickOnDelete.bind(this)} memberPreviousOrgList={memberPreviousOrgList} />
                                        </tbody>   
                                    </table>
                     </div>               
</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------*/}

<RctCollapsibleCard customClasses="border border-info"  heading="Suplier Commercial Details">

  <form noValidate autoComplete="off">
           
           <div className="row">
        
           <div className="col-sm-12 col-md-12 col-xl-12">
        
    <div className="row">
    <div className="col-sm-4 col-md-4 col-xl-4">
        <div className="form-group">
           <Label for="gstinNo">GSTIN No.</Label>
           
           <Input type="number" name="gstinNo" id="gstinNo" value={this.state.newPurchaseOrderData.gstinNo} 
            onChange={(e) => {
               newPurchaseOrderData.gstinNo = e.target.value;
               this.setState(newPurchaseOrderData);
            }}
           />

        </div>
        </div>
        
        <div className="col-sm-4 col-md-4 col-xl-4">
        <div className="form-group">
           <Label for="accountNo">Account No.</Label>

           <Input type="number" name="accountNo" id="accountNo" value={this.state.newPurchaseOrderData.accountNo} 
            onChange={(e) => {
               newPurchaseOrderData.accountNo = e.target.value;
               this.setState(newPurchaseOrderData);
            }}
           />
           
        </div>
        </div>
        
        <div className="col-sm-4 col-md-4 col-xl-4">
        <div className="form-group">
           <Label for="bankName">Bank Name</Label>

           <Input type="text" name="bankName" id="bankName" value={this.state.newPurchaseOrderData.bankName} 
           onChange={(e) => {
            newPurchaseOrderData.bankName = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
           />

        </div>
        </div>
        
        </div>{/*row end*/}
        
        {/*--------------------------------------------------------------------------------*/}

        <div className="row">

        <div className="col-sm-4 col-md-4 col-xl-4">
        <div className="form-group">
           <Label for="ifscCode">IFSC Code</Label>

           <Input type="number" name="ifscCode" id="ifscCode" value={this.state.newPurchaseOrderData.ifscCode} 
           onChange={(e) => {
            newPurchaseOrderData.ifscCode = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
           />

        </div>
        </div>

        <div className="col-sm-4 col-md-4 col-xl-4">
        <div className="form-group">
           <Label for="branch">Branch</Label>
           <Input type="text" id="branch" name="branch" value={this.state.newPurchaseOrderData.branch} 
           onChange={(e) => {
            newPurchaseOrderData.branch = e.target.value;
            this.setState(newPurchaseOrderData);
         }}
           />
        </div>
        </div>

        </div>{/*row end*/}
      
           </div>
           </div>
        
           </form>
                  
</RctCollapsibleCard>

<Grid  container direction="row" justify="center" alignItems="center">
  <Grid item>
       <MDBBtn color="success" onClick={() => this.addPurchaseOrderData()} hidden={ (this.state.operationType=='ADD')?false:true }>SUBMIT</MDBBtn> 
       <MDBBtn color="success" onClick={() => this.updatePurchaseOrderData()} hidden={ (this.state.operationType=='UPDATE')?false:true }>UPDATE</MDBBtn> 
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
export default AddPurchaseOrderData;