import React, { Component } from "react";
import axios from 'axios';
import { IoIosAddCircle, IoIosRemoveCircle } from 'react-icons/io';
import { MDBBtn, MDBTooltip } from "mdbreact";
import { Tooltip, Zoom } from "@material-ui/core";
import { Label, Input } from 'reactstrap';
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../../util/axiosInstance";

export default class MaterialIssueNote extends Component{

constructor(props)
{
   super(props);

   window.material_qc_details = this;
}

state = {

      newMaterialQcDetailsData: {

      meterialQcDetailsId_temp: [],
      srNo_temp: [],
      materialType_temp: [],
      materialSubType_temp: [],
      accountCode_temp: [],
      materialName_temp: [],
      description_temp: [],
      umo_temp: [],

      unitPrice_temp: [],
      deliveredQty_temp: [],
      approvedQty_temp: [],
      rejectedQty_temp: [],
      balanceQty_temp: [],
      remark_temp: [],
      
      materialQc: {
        materialQcId: '',
      },
  },

};

componentWillMount(){
  let materialQcId = sessionStorage.getItem('materialQcId');

   if(materialQcId != null){
      this.props.delete(this.props.memberPreviousOrgList[0]);
    }
}


getAllRecords(){

  let {newMaterialQcDetailsData} = this.state;

  this.props.memberPreviousOrgList.map((val, idx) => {
    newMaterialQcDetailsData.srNo_temp[idx] = val.srNo;
    newMaterialQcDetailsData.materialType_temp[idx] = val.materialType
  });

  this.setState(newMaterialQcDetailsData);
}

addMaterialQcDetailsData(materialQcId){
  //alert('INDEX : '+this.props.index)
    let {newMaterialQcDetailsData} = this.state;
    newMaterialQcDetailsData.materialQc.materialQcId = materialQcId;
    this.setState(newMaterialQcDetailsData);

    //alert(JSON.stringify(newMaterialQcDetailsData));
    
    axiosInstance.post('/material-qc-details/add/', newMaterialQcDetailsData).then((response) => {
      //console.log(response.data);
      NotificationManager.success('Material QC Details Added Successfully!', '');
   });
}

updateMaterialQcDetailsData(materialQcId){
  //alert('INDEX : '+this.props.index)
    let {newMaterialQcDetailsData} = this.state;
    newMaterialQcDetailsData.materialQc.materialQcId = materialQcId;
    this.setState(newMaterialQcDetailsData);

    //alert(JSON.stringify(newMaterialQcDetailsData));
    
    axiosInstance.put('/material-qc-details/update/', newMaterialQcDetailsData).then((response) => {
      //console.log(response.data);
      NotificationManager.success('Material QC Details Updated Successfully!', '');
   });

   sessionStorage.removeItem('materialQcId');
}
    
    calculateMaterialTotalAmount(index, approvedQty, unitPrice){
    
      let {newMaterialQcDetailsData} = this.state;
      //alert('calculateMaterialTotalAmount : '+approvedQty+" : "+unitPrice)
      let totalAmount = 0;
      if(newMaterialQcDetailsData.approvedQty_temp[index]!=undefined && newMaterialQcDetailsData.unitPrice_temp[index]!=undefined){
        totalAmount = newMaterialQcDetailsData.approvedQty_temp[index] * newMaterialQcDetailsData.unitPrice_temp[index];
      }else if(approvedQty != undefined && unitPrice != undefined){
        totalAmount = approvedQty * unitPrice;
      }

      newMaterialQcDetailsData.balanceQty_temp[index] = totalAmount;
      this.setState(newMaterialQcDetailsData);
    }

    calculateMaterialBalanceQty(index, prQty, approvedQty){
    
      let {newMaterialQcDetailsData} = this.state;
      //alert('calculateMaterialTotalAmount : '+approvedQty+" : "+approvedQty)
      let rejectedQty = 0;
      if(newMaterialQcDetailsData.unitPrice_temp[index]!=undefined && newMaterialQcDetailsData.approvedQty_temp[index]!=undefined){
        rejectedQty = newMaterialQcDetailsData.unitPrice_temp[index] - newMaterialQcDetailsData.approvedQty_temp[index];
      }else if(prQty != undefined && approvedQty != undefined){
        rejectedQty = prQty - approvedQty;
      }

      newMaterialQcDetailsData.rejectedQty_temp[index] = rejectedQty;
      this.setState(newMaterialQcDetailsData);
    }

    removeRow(data, index){

      let { newMaterialQcDetailsData } = this.state;

    //   console.log('##VALUE : '+JSON.stringify(val.meterialQcDetailsId));
         newMaterialQcDetailsData.meterialQcDetailsId_temp.splice(index, 1);

         newMaterialQcDetailsData.srNo_temp.splice(index, 1);
         newMaterialQcDetailsData.materialType_temp.splice(index, 1)
         newMaterialQcDetailsData.materialSubType_temp.splice(index, 1);
         newMaterialQcDetailsData.accountCode_temp.splice(index, 1);
         newMaterialQcDetailsData.materialName_temp.splice(index, 1);
         newMaterialQcDetailsData.description_temp.splice(index, 1);
         newMaterialQcDetailsData.unitPrice_temp.splice(index, 1);
         newMaterialQcDetailsData.deliveredQty_temp.splice(index, 1);
         newMaterialQcDetailsData.approvedQty_temp.splice(index, 1);
         newMaterialQcDetailsData.rejectedQty_temp.splice(index, 1);
         newMaterialQcDetailsData.balanceQty_temp.splice(index, 1);
         newMaterialQcDetailsData.remark_temp.splice(index, 1);

      this.setState(newMaterialQcDetailsData);
      this.props.delete(data);

    }

  render(){

    let { newMaterialQcDetailsData } = this.state;

  return (

  this.props.memberPreviousOrgList.map((val, idx) => {

      let meterialQcDetailsId = `meterialQcDetailsId-${idx}`, 
      srNo = `srNo-${idx}`,
      materialType = `materialType-${idx}`,
      materialSubType = `materialSubType-${idx}`,
      accountCode = `accountCode-${idx}`,
      materialName = `materialName-${idx}`,
      description = `description-${idx}`,
      umo = `umo-${idx}`,
      unitPrice = `unitPrice-${idx}`,
      deliveredQty = `deliveredQty-${idx}`,
      approvedQty = `approvedQty-${idx}`,
      rejectedQty = `rejectedQty-${idx}`,
      balanceQty = `balanceQty-${idx}`,
      remark = `remark-${idx}`

      {
          newMaterialQcDetailsData.meterialQcDetailsId_temp[idx] = val.meterialQcDetailsId;
          newMaterialQcDetailsData.srNo_temp[idx] = val.srNo;
          newMaterialQcDetailsData.materialType_temp[idx] = val.materialType;
          newMaterialQcDetailsData.materialSubType_temp[idx] = val.materialSubType;
          newMaterialQcDetailsData.accountCode_temp[idx] = val.accountCode;
          newMaterialQcDetailsData.materialName_temp[idx] = val.materialName;
          newMaterialQcDetailsData.description_temp[idx] = val.description;
          newMaterialQcDetailsData.unitPrice_temp[idx] = val.unitPrice;
          newMaterialQcDetailsData.deliveredQty_temp[idx] = val.deliveredQty;
          newMaterialQcDetailsData.approvedQty_temp[idx] = val.approvedQty;
          newMaterialQcDetailsData.rejectedQty_temp[idx] = val.rejectedQty;
          newMaterialQcDetailsData.balanceQty_temp[idx] = val.balanceQty;
          newMaterialQcDetailsData.remark_temp[idx] = val.remark;
      }
  

      return (
      
        <tr key={val.index}>
          <td>
          <div className="form-group">
            <Input type="text" name="srNo" data-id={idx} id={srNo} className="form-control" 
            value={val.srNo} 
           onChange={(e) => {
             newMaterialQcDetailsData.srNo_temp[idx] = e.target.value;
             this.setState(newMaterialQcDetailsData);
           }}
            />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="materialType" id={materialType} data-id={idx} className="form-control " 
             value={val.materialType} 
             onChange={(e) => {
              newMaterialQcDetailsData.materialType_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
             </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="materialSubType" id={materialSubType} data-id={idx} className="form-control"
             value={val.materialSubType} 
             onChange={(e) => {
              newMaterialQcDetailsData.materialSubType_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
             </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="accountCode" id={accountCode} data-id={idx} className="form-control" 
             value={val.accountCode} 
             onChange={(e) => {
              newMaterialQcDetailsData.accountCode_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="textarea" name="materialName" id={materialName} data-id={idx} className="form-control" 
             value={val.materialName} 
             onChange={(e) => {
              newMaterialQcDetailsData.materialName_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="textarea" name="description" id={description} data-id={idx} className="form-control" 
             value={val.description} 
             onChange={(e) => {
              newMaterialQcDetailsData.description_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="umo" id={umo} data-id={idx} className="form-control" 
             value={val.umo} 
             onChange={(e) => {
              newMaterialQcDetailsData.umo_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="unitPrice" id={unitPrice} data-id={idx} className="form-control" 
             value={val.unitPrice} 
             onChange={(e) => {
              newMaterialQcDetailsData.unitPrice_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
              this.calculateMaterialBalanceQty(idx, e.target.value, val.approvedQty);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="deliveredQty" id={deliveredQty} data-id={idx} className="form-control" 
             value={val.deliveredQty} 
             onChange={(e) => {
              newMaterialQcDetailsData.deliveredQty_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="approvedQty" id={approvedQty} data-id={idx} className="form-control" 
             value={val.approvedQty} 
             onChange={(e) => {
              newMaterialQcDetailsData.approvedQty_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
              this.calculateMaterialTotalAmount(idx, e.target.value, val.unitPrice);
              this.calculateMaterialBalanceQty(idx, val.prQty, e.target.value)
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="rejectedQty" id={rejectedQty} data-id={idx} className="form-control" 
             value={(newMaterialQcDetailsData.rejectedQty_temp[idx] != undefined)? newMaterialQcDetailsData.rejectedQty_temp[idx] : val.rejectedQty} 
             onChange={(e) => {
              newMaterialQcDetailsData.rejectedQty_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="balanceQty" id={balanceQty} data-id={idx} className="form-control" 
             value={ 
               (this.state.newMaterialQcDetailsData.balanceQty_temp[idx]!=undefined)? this.state.newMaterialQcDetailsData.balanceQty_temp[idx] : val.balanceQty 
              } 
            />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="remark" id={remark} data-id={idx} className="form-control" 
             value={val.remark} 
             onChange={(e) => {
              newMaterialQcDetailsData.remark_temp[idx] = e.target.value;
              this.setState(newMaterialQcDetailsData);
            }} />
            </div>
          </td>

          <td>
            {
              <div className="form-group">
               <MDBTooltip sm placement="top" TransitionComponent={Zoom}>
               <MDBBtn color="danger" onClick={(() =>  this.removeRow(val, idx) )}>
                  <IoIosRemoveCircle size="25px" style={{verticalAlign:"middle"}} />
               </MDBBtn>
                <div>Remove this row</div>
              </MDBTooltip>  
              </div>
            }
          </td>
        </tr >
      )
    })
  )
}
}
//export default MemberPreviousOrgList