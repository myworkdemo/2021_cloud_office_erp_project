import React, { Component } from "react";
import axios from "axios";
import { IoIosAddCircle, IoIosRemoveCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import {
  Tooltip,
  Zoom,
  RadioGroup,
  FormControlLabel,
  Radio
} from "@material-ui/core";
import { Label, Input } from "reactstrap";
import { NotificationManager } from "react-notifications";
import axiosInstance from "../../../../../util/axiosInstance";

export default class PRMaterialInfo extends Component {
  constructor(props) {
    super(props);

    window.pr_meterial_info = this;
  }

  state = {
    newPRMaterialInfoData: {
      prMaterialInfoId_temp: [],
      srNo_temp: [],
      materialType_temp: [],
      materialSubType_temp: [],
      materialName_temp: [],
      description_temp: [],
      accountCode_temp: [],
      umo_temp: [],
      requiredQty_temp: [],
      stock_temp: [],
      deliveryRequirement_temp: [],
      actualCostPerUnit_temp: [],
      actualTotal_temp: [],
      sellingPricePerUnit_temp: [],
      prRemark_temp: [],

      purchaseRequisitionInfo: {
        purchaseRequisitionInfoId: ""
      }
    },

    materialTypeList: [],
    materialSubTypeList: [],
    accountCodeList: [],

    materialList: []
  };

  componentWillMount() {
    let incomingMaterialId = sessionStorage.getItem("incomingMaterialId");

    if (incomingMaterialId != null) {
      this.props.delete(this.props.memberPreviousOrgList[0]);
    }

    this.getAllMaterial();
  }

  getAllRecords() {
    let { newPRMaterialInfoData } = this.state;

    this.props.memberPreviousOrgList.map((val, idx) => {
      newPRMaterialInfoData.srNo_temp[idx] = val.srNo;
      newPRMaterialInfoData.materialType_temp[idx] = val.materialType;
    });

    this.setState(newPRMaterialInfoData);
  }

  addPRMaterialInfoData(purchaseRequisitionInfoId) {
    //alert('INDEX : '+this.props.index)
    let { newPRMaterialInfoData } = this.state;
    newPRMaterialInfoData.purchaseRequisitionInfo.purchaseRequisitionInfoId = purchaseRequisitionInfoId;
    this.setState(newPRMaterialInfoData);

    //alert(JSON.stringify(newPRMaterialInfoData));

    axiosInstance
      .post("/purchase-requisition-material-info/add/", newPRMaterialInfoData)
      .then(response => {
        //console.log(response.data);
        NotificationManager.success(
          "Incoming Material Info Added Successfully!",
          ""
        );
      });
  }

  updatePRMaterialInfoData(incomingMaterialId) {
    //alert('INDEX : '+this.props.index)
    let { newPRMaterialInfoData } = this.state;
    newPRMaterialInfoData.incomingMaterialId.incomingMaterialId = incomingMaterialId;
    this.setState(newPRMaterialInfoData);

    //alert(JSON.stringify(newPRMaterialInfoData));

    axiosInstance
      .put("/purchase-requisition-material-info/update/", newPRMaterialInfoData)
      .then(response => {
        //console.log(response.data);
        NotificationManager.success(
          "Incoming Material Info Updated Successfully!",
          ""
        );
      });

    sessionStorage.removeItem("incomingMaterialId");
  }

  calculateMaterialTotalAmount(index, requiredQty, actualTotal) {
    let { newPRMaterialInfoData } = this.state;
    //alert('calculateMaterialTotalAmount : '+requiredQty+" : "+actualTotal)
    let totalAmount = 0;
    if (
      newPRMaterialInfoData.requiredQty_temp[index] != undefined &&
      newPRMaterialInfoData.actualTotal_temp[index] != undefined
    ) {
      totalAmount =
        newPRMaterialInfoData.requiredQty_temp[index] *
        newPRMaterialInfoData.actualTotal_temp[index];
    } else if (requiredQty != undefined && actualTotal != undefined) {
      totalAmount = requiredQty * actualTotal;
    }

    newPRMaterialInfoData.deliveryRequirement_temp[index] = totalAmount;
    this.setState(newPRMaterialInfoData);
  }

  calculateMaterialBalanceQty(index, requiredQty, stock) {
    let { newPRMaterialInfoData } = this.state;
    //alert('calculateMaterialTotalAmount : '+requiredQty+" : "+requiredQty)
    //  let stock = 0;
    if (
      newPRMaterialInfoData.actualTotal_temp[index] != undefined &&
      newPRMaterialInfoData.requiredQty_temp[index] != undefined
    ) {
      stock =
        newPRMaterialInfoData.actualTotal_temp[index] -
        newPRMaterialInfoData.requiredQty_temp[index];
    } else if (requiredQty != undefined && requiredQty != undefined) {
      stock = requiredQty - requiredQty;
    }

    newPRMaterialInfoData.stock_temp[index] = stock;
    this.setState(newPRMaterialInfoData);
  }

  getAllMaterial() {
    let materialList_temp = [];
    axiosInstance.get("/material-qc-details/getAll").then(response => {
      response.data.map((val, idx) => {
        materialList_temp.push({
          materialType: val.materialType,
          materialSubType: val.materialSubType,
          accountCode: val.accountCode
        });
      });

      //alert("getAllMaterial() : "+JSON.stringify(materialList_temp));
      this.setState({ materialList: materialList_temp });
    });
  }

  removeRow(data, index) {
    let { newPRMaterialInfoData } = this.state;

    //   console.log('##VALUE : '+JSON.stringify(val.prMaterialInfoId));
    newPRMaterialInfoData.prMaterialInfoId_temp.splice(index, 1);

    newPRMaterialInfoData.srNo_temp.splice(index, 1);
    newPRMaterialInfoData.materialType_temp.splice(index, 1);
    newPRMaterialInfoData.materialSubType_temp.splice(index, 1);
    newPRMaterialInfoData.accountCode_temp.splice(index, 1);
    newPRMaterialInfoData.materialName_temp.splice(index, 1);
    newPRMaterialInfoData.description_temp.splice(index, 1);
    newPRMaterialInfoData.requiredQty_temp.splice(index, 1);
    newPRMaterialInfoData.stock_temp.splice(index, 1);
    newPRMaterialInfoData.deliveryRequirement_temp.splice(index, 1);
    newPRMaterialInfoData.actualCostPerUnit_temp.splice(index, 1);
    newPRMaterialInfoData.actualTotal_temp.splice(index, 1);
    newPRMaterialInfoData.sellingPricePerUnit_temp.splice(index, 1);
    newPRMaterialInfoData.prRemark_temp.splice(index, 1);

    this.setState(newPRMaterialInfoData);
    this.props.delete(data);
  }

  findMaterial(materialType, idx) {
    let { newPRMaterialInfoData } = this.state;
    var data = this.state.materialList.filter(
      item => item.materialType == materialType
    );

    //alert("findMaterial() : " + data[0].materialSubType);
    if (data.length > 0) {
      newPRMaterialInfoData.materialSubType_temp[idx] = data[0].materialSubType;
      newPRMaterialInfoData.accountCode_temp[idx] = data[0].accountCode;
    } else {
      newPRMaterialInfoData.materialSubType_temp[idx] = "";
      newPRMaterialInfoData.accountCode_temp[idx] = "";
    }

    this.setState({ newPRMaterialInfoData });
  }

  render() {
    let { newPRMaterialInfoData } = this.state;

    let materialTypeList = [],
      materialSubTypeList = [],
      accountCodeList = [];

    /*const materialTypeList = this.state.materialTypeList.map(val => {
      return <option value={val.materialType}> {val.materialType} </option>;
    });

    const materialSubTypeList = this.state.materialSubTypeList.map(val => {
      return (
        <option value={val.materialSubType}> {val.materialSubType} </option>
      );
    });

    const accountCodeList = this.state.accountCodeList.map(val => {
      return <option value={val.accountCode}> {val.accountCode} </option>;
    });
    */

    const materialList = this.state.materialList.map(val => {
      return (
        <option value={val.meterialQcDetailsId}> {val.materialType} </option>
      );
    });

    return this.props.memberPreviousOrgList.map((val, idx) => {
      let prMaterialInfoId = `prMaterialInfoId-${idx}`,
        srNo = `srNo-${idx}`,
        materialType = `materialType-${idx}`,
        materialSubType = `materialSubType-${idx}`,
        accountCode = `accountCode-${idx}`,
        materialName = `materialName-${idx}`,
        description = `description-${idx}`,
        umo = `umo-${idx}`,
        requiredQty = `requiredQty-${idx}`,
        stock = `stock-${idx}`,
        deliveryRequirement = `deliveryRequirement-${idx}`,
        actualCostPerUnit = `actualCostPerUnit-${idx}`,
        actualTotal = `actualTotal-${idx}`,
        sellingPricePerUnit = `sellingPricePerUnit-${idx}`,
        prRemark = `prRemark-${idx}`;

      materialTypeList = val.materialTypeList.map(val => {
        return <option value={val.materialType}> {val.materialType} </option>;
      });

      {
        newPRMaterialInfoData.prMaterialInfoId_temp[idx] = val.prMaterialInfoId;
        newPRMaterialInfoData.srNo_temp[idx] = val.srNo;
        newPRMaterialInfoData.materialType_temp[idx] = val.materialType;
        //newPRMaterialInfoData.materialSubType_temp[idx] = val.materialSubType;
        //newPRMaterialInfoData.accountCode_temp[idx] = val.accountCode;
        newPRMaterialInfoData.materialName_temp[idx] = val.materialName;
        newPRMaterialInfoData.description_temp[idx] = val.description;
        newPRMaterialInfoData.requiredQty_temp[idx] = val.requiredQty;
        newPRMaterialInfoData.stock_temp[idx] = val.stock;
        newPRMaterialInfoData.deliveryRequirement_temp[idx] =
          val.deliveryRequirement;
        newPRMaterialInfoData.actualCostPerUnit_temp[idx] =
          val.actualCostPerUnit;
        newPRMaterialInfoData.actualTotal_temp[idx] = val.actualTotal;
        newPRMaterialInfoData.sellingPricePerUnit_temp[idx] =
          val.sellingPricePerUnit;
        newPRMaterialInfoData.prRemark_temp[idx] = val.prRemark;
      }

      //alert(JSON.stringify(materialTypeList));

      return (
        <tr key={val.index}>
          <td>
            <div className="form-group">
              <Input
                type="text"
                name="srNo"
                data-id={idx}
                id={srNo}
                className="form-control"
                value={val.srNo}
                onChange={e => {
                  newPRMaterialInfoData.srNo_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="select"
                name="materialType"
                id="materialType"
                value={this.state.newPRMaterialInfoData.materialType}
                onChange={e => {
                  newPRMaterialInfoData.materialType = e.target.value;
                  this.setState(newPRMaterialInfoData);
                  this.findMaterial(e.target.value, idx);
                }}
              >
                <option value="">-select-</option>
                {materialList}
              </Input>
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="materialSubType"
                id={materialSubType}
                data-id={idx}
                className="form-control"
                value={
                  this.state.newPRMaterialInfoData.materialSubType_temp[idx]
                }
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="accountCode"
                id={accountCode}
                data-id={idx}
                className="form-control"
                value={this.state.newPRMaterialInfoData.accountCode_temp[idx]}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="textarea"
                name="materialName"
                id={materialName}
                data-id={idx}
                className="form-control"
                value={val.materialName}
                onChange={e => {
                  newPRMaterialInfoData.materialName_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="textarea"
                name="description"
                id={description}
                data-id={idx}
                className="form-control"
                value={val.description}
                onChange={e => {
                  newPRMaterialInfoData.description_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="umo"
                id={umo}
                data-id={idx}
                className="form-control"
                value={val.umo}
                onChange={e => {
                  newPRMaterialInfoData.umo_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="requiredQty"
                id={requiredQty}
                data-id={idx}
                className="form-control"
                value={val.requiredQty}
                onChange={e => {
                  newPRMaterialInfoData.requiredQty_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                  this.calculateMaterialTotalAmount(
                    idx,
                    e.target.value,
                    val.actualTotal
                  );
                  this.calculateMaterialBalanceQty(
                    idx,
                    val.requiredQty,
                    e.target.value
                  );
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="stock"
                id={stock}
                data-id={idx}
                className="form-control"
                onChange={e => {
                  newPRMaterialInfoData.stock_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="deliveryRequirement"
                id={deliveryRequirement}
                data-id={idx}
                className="form-control"
                value={
                  this.state.newPRMaterialInfoData.deliveryRequirement_temp[
                    idx
                  ] != undefined
                    ? this.state.newPRMaterialInfoData.deliveryRequirement_temp[
                        idx
                      ]
                    : val.deliveryRequirement
                }
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="actualCostPerUnit"
                id={actualCostPerUnit}
                data-id={idx}
                className="form-control"
                value={val.actualCostPerUnit}
                onChange={e => {
                  newPRMaterialInfoData.actualCostPerUnit_temp[idx] =
                    e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="actualTotal"
                id={actualTotal}
                data-id={idx}
                className="form-control"
                value={val.actualTotal}
                onChange={e => {
                  newPRMaterialInfoData.actualTotal_temp[idx] = e.target.value;
                  this.setState(newPRMaterialInfoData);
                  this.calculateMaterialBalanceQty(
                    idx,
                    e.target.value,
                    val.requiredQty
                  );
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="sellingPricePerUnit"
                id={sellingPricePerUnit}
                data-id={idx}
                className="form-control"
                value={val.sellingPricePerUnit}
                onChange={e => {
                  newPRMaterialInfoData.sellingPricePerUnit_temp[idx] =
                    e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <RadioGroup
                row
                aria-label="prRemark"
                name="prRemark"
                onChange={e => {
                  newPRMaterialInfoData.prRemark = e.target.value;
                  this.setState(newPRMaterialInfoData);
                }}
                value={newPRMaterialInfoData.prRemark}
              >
                <FormControlLabel
                  value="Approve"
                  control={<Radio color="primary" />}
                  label="Approve"
                />
                <FormControlLabel
                  value="Reject"
                  control={<Radio color="danger" />}
                  label="Reject"
                />
              </RadioGroup>
            </div>
          </td>

          <td>
            {
              <div className="form-group">
                <MDBTooltip sm placement="top" TransitionComponent={Zoom}>
                  <MDBBtn
                    color="danger"
                    onClick={() => this.removeRow(val, idx)}
                  >
                    <IoIosRemoveCircle
                      size="25px"
                      style={{ verticalAlign: "middle" }}
                    />
                  </MDBBtn>
                  <div>Remove this row</div>
                </MDBTooltip>
              </div>
            }
          </td>
        </tr>
      );
    });
  }
}
//export default MemberPreviousOrgList
