import React, { Component } from "react";
import axios from 'axios';
import { IoIosAddCircle, IoIosRemoveCircle } from 'react-icons/io';
import { MDBBtn, MDBTooltip } from "mdbreact";
import { Tooltip, Zoom } from "@material-ui/core";
import { Label, Input } from 'reactstrap';
import { NotificationManager, NotificationContainer } from "react-notifications";
import axiosInstance from "../../../../../util/axiosInstance";

export default class MaterialInformation extends Component{

constructor(props)
{
   super(props);

   window.material_information = this;
}

state = {

      newPurchaseOrderMaterialInfoData: {

      purchaseOrderMaterialInfoId_temp: [],  
      srNo_temp: [],
      materialType_temp: [],
      materialSubType_temp: [],
      accountCode_temp: [],
      materialName_temp: [],
      description_temp: [],
      umo_temp: [],
      prQty_temp: [],
      requiredDate_temp: [],
      poQty_temp: [],
      balanceQty_temp: [],
      unitPrice_temp: [],
      amount_temp: [],
      remark_temp: [],
      
      purchaseOrder: {
          purchaseOrderId: '',
      },
  },

};

componentWillMount(){
  let purchaseOrderId = sessionStorage.getItem('purchaseOrderId');

   if(purchaseOrderId != null){
      this.props.delete(this.props.memberPreviousOrgList[0]);
    }
}


getAllRecords(){

  let {newPurchaseOrderMaterialInfoData} = this.state;

  this.props.memberPreviousOrgList.map((val, idx) => {
    newPurchaseOrderMaterialInfoData.srNo_temp[idx] = val.srNo;
    newPurchaseOrderMaterialInfoData.materialType_temp[idx] = val.materialType
  });

  this.setState(newPurchaseOrderMaterialInfoData);
}

addPurchaseOrderMaterialInfoData(purchaseOrderId){
  //alert('INDEX : '+this.props.index)
    let {newPurchaseOrderMaterialInfoData} = this.state;
    newPurchaseOrderMaterialInfoData.purchaseOrder.purchaseOrderId = purchaseOrderId;
    this.setState(newPurchaseOrderMaterialInfoData);

    //alert(JSON.stringify(newPurchaseOrderMaterialInfoData));
    
    axiosInstance.post('/purchase-order-material-info/add/', newPurchaseOrderMaterialInfoData).then((response) => {
      //console.log(response.data);
      NotificationManager.success('Purchase Order MaterialInfo Added Successfully!', '');
   });
}

updatePurchaseOrderMaterialInfoData(purchaseOrderId){
  //alert('INDEX : '+this.props.index)
    let {newPurchaseOrderMaterialInfoData} = this.state;
    newPurchaseOrderMaterialInfoData.purchaseOrder.purchaseOrderId = purchaseOrderId;
    this.setState(newPurchaseOrderMaterialInfoData);

    //alert(JSON.stringify(newPurchaseOrderMaterialInfoData));
    
    axiosInstance.put('/purchase-order-material-info/update/', newPurchaseOrderMaterialInfoData).then((response) => {
      //console.log(response.data);
      NotificationManager.success('Purchase Order MaterialInfo Updated Successfully!', '');
   });

   sessionStorage.removeItem('purchaseOrderId');
}
    
    calculateMaterialTotalAmount(index, poQty, unitPrice){
    
      let {newPurchaseOrderMaterialInfoData} = this.state;
      //alert('calculateMaterialTotalAmount : '+poQty+" : "+unitPrice)
      let totalAmount = 0;
      if(newPurchaseOrderMaterialInfoData.poQty_temp[index]!=undefined && newPurchaseOrderMaterialInfoData.unitPrice_temp[index]!=undefined){
        totalAmount = newPurchaseOrderMaterialInfoData.poQty_temp[index] * newPurchaseOrderMaterialInfoData.unitPrice_temp[index];
      }else if(poQty != undefined && unitPrice != undefined){
        totalAmount = poQty * unitPrice;
      }

      newPurchaseOrderMaterialInfoData.amount_temp[index] = totalAmount;
      this.setState(newPurchaseOrderMaterialInfoData);
    }

    calculateMaterialBalanceQty(index, prQty, poQty){
    
      let {newPurchaseOrderMaterialInfoData} = this.state;
      //alert('calculateMaterialTotalAmount : '+poQty+" : "+poQty)
      let balanceQty = 0;
      if(newPurchaseOrderMaterialInfoData.prQty_temp[index]!=undefined && newPurchaseOrderMaterialInfoData.poQty_temp[index]!=undefined){
        balanceQty = newPurchaseOrderMaterialInfoData.prQty_temp[index] - newPurchaseOrderMaterialInfoData.poQty_temp[index];
      }else if(prQty != undefined && poQty != undefined){
        balanceQty = prQty - poQty;
      }

      newPurchaseOrderMaterialInfoData.balanceQty_temp[index] = balanceQty;
      this.setState(newPurchaseOrderMaterialInfoData);
    }

    changeTextFeildProperty(e){
      alert('changeTextFeildProperty() : '+e.target.name)
    }

    removeRow(data, index){

      let { newPurchaseOrderMaterialInfoData } = this.state;

    //   console.log('##VALUE : '+JSON.stringify(val.meterialQcDetailsId));
          newPurchaseOrderMaterialInfoData.purchaseOrderMaterialInfoId_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.srNo_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.materialType_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.materialSubType_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.accountCode_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.materialName_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.description_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.umo_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.prQty_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.requiredDate_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.poQty_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.balanceQty_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.unitPrice_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.amount_temp.splice(index, 1);
          newPurchaseOrderMaterialInfoData.remark_temp.splice(index, 1);

      this.setState(newPurchaseOrderMaterialInfoData);
      this.props.delete(data);

    }

  render(){

    let { newPurchaseOrderMaterialInfoData } = this.state;

  return (

  this.props.memberPreviousOrgList.map((val, idx) => {

      let purchaseOrderMaterialInfoId = `purchaseOrderMaterialInfoId-${idx}`,
      srNo = `srNo-${idx}`,
      materialType = `materialType-${idx}`,
      materialSubType = `materialSubType-${idx}`,
      accountCode = `accountCode-${idx}`,
      materialName = `materialName-${idx}`,
      description = `description-${idx}`,
      umo = `umo-${idx}`,
      prQty = `prQty-${idx}`,
      requiredDate = `requiredDate-${idx}`,
      poQty = `poQty-${idx}`,
      balanceQty = `balanceQty-${idx}`,
      unitPrice = `unitPrice-${idx}`,
      amount = `amount-${idx}`,
      remark = `remark-${idx}`

      {
        newPurchaseOrderMaterialInfoData.purchaseOrderMaterialInfoId_temp = val.purchaseOrderMaterialInfoId;
        newPurchaseOrderMaterialInfoData.srNo_temp = val.srNo;
        newPurchaseOrderMaterialInfoData.materialType_temp = val.materialType;
        newPurchaseOrderMaterialInfoData.materialSubType_temp = val.materialSubType;
        newPurchaseOrderMaterialInfoData.accountCode_temp = val.accountCode;
        newPurchaseOrderMaterialInfoData.materialName_temp = val.materialName;
        newPurchaseOrderMaterialInfoData.description_temp = val.description;
        newPurchaseOrderMaterialInfoData.umo_temp = val.umo;
        newPurchaseOrderMaterialInfoData.prQty_temp = val.prQty;
        newPurchaseOrderMaterialInfoData.requiredDate_temp = val.requiredDate;
        newPurchaseOrderMaterialInfoData.poQty_temp = val.poQty;
        newPurchaseOrderMaterialInfoData.balanceQty_temp = val.balanceQty;
        newPurchaseOrderMaterialInfoData.unitPrice_temp = val.unitPrice;
        newPurchaseOrderMaterialInfoData.amount_temp = val.amount;
        newPurchaseOrderMaterialInfoData.remark_temp = val.remark;
      }

      return (
      
        <tr key={val.index}>
          <td>
          <div className="form-group">
            <Input type="text" name="srNo" data-id={idx} id={srNo} className="form-control" 
            value={val.srNo} 
           onChange={(e) => {
             newPurchaseOrderMaterialInfoData.srNo_temp[idx] = e.target.value;
             this.setState(newPurchaseOrderMaterialInfoData);
           }}
           onMouseEnter={(e) => this.changeTextFeildProperty(e)}
            />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="materialType" id={materialType} data-id={idx} className="form-control " 
             value={val.materialType} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.materialType_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
             </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="materialSubType" id={materialSubType} data-id={idx} className="form-control"
             value={val.materialSubType} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.materialSubType_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
             </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="accountCode" id={accountCode} data-id={idx} className="form-control" 
             value={val.accountCode} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.accountCode_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="textarea" name="materialName" id={materialName} data-id={idx} className="form-control" 
             value={val.materialName} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.materialName_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="textarea" name="description" id={description} data-id={idx} className="form-control" 
             value={val.description} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.description_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="umo" id={umo} data-id={idx} className="form-control" 
             value={val.umo} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.umo_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="prQty" id={prQty} data-id={idx} className="form-control" 
             value={val.prQty} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.prQty_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
              this.calculateMaterialBalanceQty(idx, e.target.value, val.poQty);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="requiredDate" id={requiredDate} data-id={idx} className="form-control" 
             value={val.requiredDate} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.requiredDate_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="poQty" id={poQty} data-id={idx} className="form-control" 
             value={val.poQty} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.poQty_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
              this.calculateMaterialTotalAmount(idx, e.target.value, val.unitPrice);
              this.calculateMaterialBalanceQty(idx, val.prQty, e.target.value)
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="balanceQty" id={balanceQty} data-id={idx} className="form-control" 
             value={(newPurchaseOrderMaterialInfoData.balanceQty_temp[idx] != undefined)? newPurchaseOrderMaterialInfoData.balanceQty_temp[idx] : val.balanceQty} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.balanceQty_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="unitPrice" id={unitPrice} data-id={idx} className="form-control" 
             value={val.unitPrice} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.unitPrice_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
              this.calculateMaterialTotalAmount(idx, val.poQty, e.target.value);
            }} />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="amount" id={amount} data-id={idx} className="form-control" 
             value={ 
               (this.state.newPurchaseOrderMaterialInfoData.amount_temp[idx]!=undefined)? this.state.newPurchaseOrderMaterialInfoData.amount_temp[idx] : val.amount 
              } 
            />
            </div>
          </td>

          <td>
          <div className="form-group">
            <Input type="text" name="remark" id={remark} data-id={idx} className="form-control" 
             value={val.remark} 
             onChange={(e) => {
              newPurchaseOrderMaterialInfoData.remark_temp[idx] = e.target.value;
              this.setState(newPurchaseOrderMaterialInfoData);
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