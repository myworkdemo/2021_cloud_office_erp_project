import React,{ Component } from "react"
import axios from 'axios';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { Tooltip, Zoom, RadioGroup, FormControlLabel, Radio, Checkbox, Grid } from "@material-ui/core";
import { IoIosAddCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../util/axiosInstance";
import { Label, Input } from 'reactstrap';
import PRMaterialInfo from "./component/PRMaterialInfo";
import { CustomInput } from 'reactstrap';

class AddPurchaseRequisitionInfo extends Component {

    state = {

      newPurchaseRequisitionInfoData: {

         //purchaseRequisitionInfoId: '',
        
         prDate: '',
         prNo: '',
         departmentName: '',
         customerPoNo: '',
         workName: '',
         customerPoDate: '',
         document: [],
         storeInchargeApproval: '',
         sendForApproval: '',

         inchargeRemark: '',
         equipmentDetails: '',
         deliveryDate: '',
     },
   
     vendorName: '',
     materialName: '',
   
     materialList: [],
     departmentList: [],

     materialTypeList: [],
     materialSubTypeList: [],
     accountCodeList: [],
   
     memberPreviousOrgList: [{ index: Math.random(), meterialQcDetailsId: "", srNo: "", materialType: "", materialTypeList: [], materialSubType: "", materialSubTypeList: [], materialName: "", 
     description: "", accountCode: "", accountCodeList: [], uom: "", requiredQty: "", stock: "", deliveryRequirement: "", actualCostPerUnit: "", actualTotal: "", sellingPricePerUnit: "", prRemark: "" }],

     documentFile: [],

      operationType: 'ADD',
    }

    componentWillMount()
    {
        if(sessionStorage.getItem('purchaseRequisitionInfoId')!=null){
              this.getPurchaseRequisitionInfoById(sessionStorage.getItem('purchaseRequisitionInfoId'));
        }

        this.getAllDepartments();
        this.getAllMaterial();
        //this.getAll();
    }

    handleChangeRadio = (e, key) => {
		this.setState({ [key]: e.target.value });
	}

    handleChange = (e) => {
      
        if (["srNo", "meterialQcDetailsId", ,"materialType", "materialTypeList,", "materialSubType", "materialSubTypeList", "materialName", "description", "accountCode", 
       "accountCodeList", "uom", "requiredQty", "stock", "deliveryRequirement", "actualCostPerUnit", "actualTotal", "sellingPricePerUnit", "prRemark"].includes(e.target.name)) {
            let memberPreviousOrgList = [...this.state.memberPreviousOrgList]
            memberPreviousOrgList[e.target.dataset.id][e.target.name] = e.target.value;
        } else {
            this.setState({ [e.target.name]: e.target.value })
        }
    }
    
    addNewRow = (e) => {
         //alert('#addNewRow()')
         
        this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index: Math.random(), srNo: "", materialType: "", materialTypeList: [], materialSubType: "", materialSubTypeList: [], 
            materialName: "", description: "", accountCode: "", accountCodeList: [], uom: "", requiredQty: "", stock: "", deliveryRequirement: "", actualCostPerUnit: "", actualTotal: "", sellingPricePerUnit: "", prRemark: "" }],
        }));

       // window.pr_meterial_info.addPN();
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

    addPurchaseRequisitionInfoData()
    {
      //alert('PurchaseRequisitionInfoData() : '+JSON.stringify(this.state.documentFile))

       var postData = JSON.stringify(this.state.newPurchaseRequisitionInfoData);

       const data = new FormData();
       data.append('documentFile', this.state.documentFile);
       data.append('purchaseRequisitionInfo', postData);

        axiosInstance.post('/purchase-requisition-info/add/', data).then((response) => {
          //console.log(response.data);
          //alert(JSON.stringify(response.data))
         window.pr_meterial_info.addPRMaterialInfoData(response.data.purchaseRequisitionInfoId);
          NotificationManager.success('Purchase Requisition Info Saved Successfully!', '');
       });

       //window.member_previous_orgList.PurchaseRequisitionInfoData();
    }

    updatePurchaseRequisitionInfoData(){
//alert('updatePurchaseRequisitionInfoData()');
      let { newPurchaseRequisitionInfoData } = this.state;

      newPurchaseRequisitionInfoData.document = [];
      this.setState(newPurchaseRequisitionInfoData);

      var postData = JSON.stringify(newPurchaseRequisitionInfoData);

      const data = new FormData();
      data.append('documentFile', this.state.documentFile);
      data.append('purchaseRequisitionInfo', postData);

       axiosInstance.put('/purchase-requisition-info/update/', data).then((response) => {
         //console.log(response.data);
         //alert(JSON.stringify(response.data))
         window.pr_meterial_info.updatePurchaseRequisitionInfoInfoData(response.data.purchaseRequisitionInfoId);
         NotificationManager.success('Purchase Requisition Info Updated Successfully!', '');
      });

    }

    onInvoiceAttachment = (e) => {

    //alert('onFileChangeHandler() : '+e.target.files[0]);
    this.setState({documentFile : e.target.files[0]});
    }

    getPurchaseRequisitionInfoById(purchaseRequisitionInfoId){
//alert('getPurchaseRequisitionInfoById() : '+purchaseRequisitionInfoId);
      this.setState({operationType: 'UPDATE'});

      let {newPurchaseRequisitionInfoData} = this.state;
      axiosInstance.get('/purchase-requisition-info/getById/'+ purchaseRequisitionInfoId).then((response) => {

         console.log('#getPurchaseRequisitionInfoById() : '+JSON.stringify(response.data));
         newPurchaseRequisitionInfoData = response.data;
         this.setState({newPurchaseRequisitionInfoData});

         this.getMaterialPurchaseRequisitionInfoByPurchaseRequisitionInfoId(purchaseRequisitionInfoId);
      });

      //sessionStorage.removeItem('purchaseRequisitionInfoId');
    }

    getMaterialPurchaseRequisitionInfoByPurchaseRequisitionInfoId(purchaseRequisitionId){

      let srNo, materialType, materialTypeList = [], materialSubType, materialSubTypeList = [], materialName, description, accountCode,
      accountCodeList = [], uom, requiredQty, stock, deliveryRequirement, actualCostPerUnit, actualTotal, sellingPricePerUnit, prRemark;
 
      axiosInstance.get('/purchase-requisition-info/getById/'+purchaseRequisitionId).then((response) => {
     //alert(JSON.stringify(response.data));
         response.data.map((val, index) => {
            //console.log('### AMOUNT : '+val.amount)
            index = Math.random();
            meterialQcDetailsId = val.meterialQcDetailsId;
            srNo = val.srNo;
            materialType = val.materialType;
            materialSubType = val.materialSubType;
           
            materialName = val.materialName;
            description = val.description;
            accountCode = val.accountCode;

            uom = val.uom;
            requiredQty = val.requiredQty;
            stock = val.stock;
           
            actualCostPerUnit = val.actualCostPerUnit;
            actualTotal = val.actualTotal;
            approvedQty = val.approvedQty;
            sellingPricePerUnit = val.sellingPricePerUnit;
            
            prRemark = val.prRemark;
            
            purchaseRequisitionInfo = val.purchaseRequisitionInfo.purchaseRequisitionId;

         
            materialTypeList = this.state.materialTypeList;

 
            this.setState((prevState) => ({
            memberPreviousOrgList: [...prevState.memberPreviousOrgList, { index, meterialQcDetailsId, srNo, materialType, materialTypeList, materialSubType, materialSubTypeList,
               materialName, description, accountCode, accountCodeList, uom, requiredQty, stock, deliveryRequirement, actualCostPerUnit, actualTotal, sellingPricePerUnit, prRemark }],
        }));
 
         });

      });

    }

    getAllDepartments(){
      let departmentList = this.state.departmentList;
       axiosInstance.get('/department/getAll').then((response) => {
   
          this.setState({departmentList: response.data});
       });
   }

   getAll(){
      let materialTypeList_temp = [];
      axiosInstance.get("/incoming-material-info/getAll").then(response => {

        
            response.data.map((val, idx) => {
              
               materialTypeList_temp.push({materialType: val.materialType});

              // materialTypeList.push();
               //this.setState(materialTypeList.push());
            });

            this.setState({materialTypeList: materialTypeList_temp});
      });

      //alert('getAll() : '+materialTypeList_temp)
   }

   getAllMaterial(){
      let materialList_temp = [];
       axiosInstance.get('/material-qc-details/getAll').then((response) => {
   
        response.data.map((val,idx) => {
         materialList_temp.push({
           'materialType': val.materialType,
           'materialSubType': val.materialSubType,
           'accountCode': val.accountCode
         });

        });

        //alert("getAllMaterial() : "+JSON.stringify(materialList_temp));
        this.setState({materialList: materialList_temp});
         
       });
   }


    render() {
        let { memberPreviousOrgList } = this.state;//let { notes, date, description, memberPreviousOrgList } = this.state
        
        let { newPurchaseRequisitionInfoData } = this.state;

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
                           
   <RctCollapsibleCard customClasses="border border-info" heading="Add Purchase Requisition Info">


   <RctCollapsibleCard customClasses="border border-info" heading="Purchase Requisition Info">

<form noValidate autoComplete="off">
         
         <div className="row">
      
         <div className="col-sm-12 col-md-12 col-xl-12">
      
  <div className="row">
  <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="prDate">PR Date</Label>
         
         <Input type="date" name="prDate" id="prDate" value={this.state.newPurchaseRequisitionInfoData.prDate}
         onChange={(e) => {
            newPurchaseRequisitionInfoData.prDate = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="prNo">PR No.</Label>
         <Input type="text" id="prNo" name="prNo" value={this.state.newPurchaseRequisitionInfoData.prNo} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.prNo = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>
      
      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
        <Label for="departmentName">Department Name</Label>
         <Input type="select" name="departmentName" id="departmentName" value={this.state.newPurchaseRequisitionInfoData.departmentName} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.departmentName = e.target.value;
            this.setState(newPurchaseRequisitionInfoData); 
             this.findVendorEmailId(e.target.value);
            }}>
              <option value=''>-select-</option>
                   {departmentList}
         </Input>

      </div>
      </div>
      
      </div>{/*row end*/}
      
      {/*--------------------------------------------------------------------------------*/}

      <div className="row">

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="customerPoNo">Customer PO No.</Label>
         <Input type="text" name="customerPoNo" id="customerPoNo" value={this.state.newPurchaseRequisitionInfoData.customerPoNo} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.customerPoNo = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="workName">Work Name</Label>
         <Input type="textarea" id="workName" name="workName" value={this.state.newPurchaseRequisitionInfoData.workName} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.workName = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="customerPoDate">Customer PO Date</Label>
         <Input type="date" name="customerPoDate" id="customerPoDate" value={this.state.newPurchaseRequisitionInfoData.customerPoDate} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.customerPoDate = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
      <Label for="document">Document</Label>

       <CustomInput type="file" id="document" label={(newPurchaseRequisitionInfoData.document!='')?'File is selected':'No file choosen'} onChange={this.onInvoiceAttachment} />

      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
      <Label for="sendForApproval">Store Incharge Approval</Label>
         
         <RadioGroup row aria-label="sendForApproval" name="sendForApproval2" 
                    onChange={(e) => {
                     newPurchaseRequisitionInfoData.sendForApproval = e.target.value;
                       this.setState(newPurchaseRequisitionInfoData);
                    }} value={newPurchaseRequisitionInfoData.sendForApproval} >
                      <FormControlLabel value="Approve" control={<Radio color="primary" />} label="Approve" />
                      <FormControlLabel value="Reject" control={<Radio color="primary" />} label="Reject" />
                    </RadioGroup>
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="sendForApproval">Send for Approval</Label>
         
         <RadioGroup row aria-label="sendForApproval" name="sendForApproval2" 
                    onChange={(e) => {
                     newPurchaseRequisitionInfoData.sendForApproval = e.target.value;
                       this.setState(newPurchaseRequisitionInfoData);
                    }} value={newPurchaseRequisitionInfoData.sendForApproval} >
                      <FormControlLabel value="Yes" control={<Radio color="primary" />} label="Yes" />
                      <FormControlLabel value="No" control={<Radio color="primary" />} label="No" />
                    </RadioGroup>

      </div>
      </div>

      </div>{/*row end*/}
    
     {/*--------------------------------------------------------------------------------*/}

     <div className="row">

     <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="document">Incharge Remark</Label>
         <Input type="textarea" name="inchargeRemark" id="inchargeRemark" value={this.state.newPurchaseRequisitionInfoData.inchargeRemark} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.inchargeRemark = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="otherAttachment">Equipment Details</Label>
         <Input type="textarea" name="equipmentDetails" id="equipmentDetails" value={this.state.newPurchaseRequisitionInfoData.equipmentDetails} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.equipmentDetails = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
         }}
         />
      </div>
      </div>

      <div className="col-sm-4 col-md-4 col-xl-4">
      <div className="form-group">
         <Label for="otherAttachment">Delivery Date</Label>
         <Input type="date" name="deliveryDate" id="deliveryDate" value={this.state.newPurchaseRequisitionInfoData.deliveryDate} 
         onChange={(e) => {
            newPurchaseRequisitionInfoData.deliveryDate = e.target.value;
            this.setState(newPurchaseRequisitionInfoData);
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

{/*---------------------------------------------------------------------------------------------------------*/}

<RctCollapsibleCard customClasses="border border-info" heading="Material Info">
   
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
                                                <th className="required" >Required Qty.</th>
                                                <th className="required" >Stock</th>
                                               
                                                <th className="required" >Delivery Requirement</th>
                                                <th className="required" >Actual Cost Per Unit</th>
                                                <th className="required" >Actual Total</th>
                                                <th className="required" >Selling Price Per Unit(INR)</th>
                                                
                                                <th className="required">PR Remark</th>
                                                <th className="required">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <PRMaterialInfo add={this.addNewRow} delete={this.clickOnDelete.bind(this)} memberPreviousOrgList={memberPreviousOrgList} />
                                        </tbody>   
                                    </table>
                     </div>               
</RctCollapsibleCard>

{/*---------------------------------------------------------------------------------------------------*/}

<Grid  container direction="row" justify="center" alignItems="center">
  <Grid item>
       <MDBBtn color="success" onClick={() => this.addPurchaseRequisitionInfoData()} hidden={ (this.state.operationType=='ADD')?false:true }>SUBMIT</MDBBtn> 
       <MDBBtn color="success" onClick={() => this.updatePurchaseRequisitionInfoData()} hidden={ (this.state.operationType=='UPDATE')?false:true }>UPDATE</MDBBtn> 
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
export default AddPurchaseRequisitionInfo;