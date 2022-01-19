import React, { Component } from "react";
import axios from "axios";
import { IoIosAddCircle, IoIosRemoveCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { Tooltip, Zoom } from "@material-ui/core";
import { Label, Input } from "reactstrap";
import { NotificationManager } from "react-notifications";
import axiosInstance from "../../../../../util/axiosInstance";

export default class IncomingMaterialInfo extends Component {
  constructor(props) {
    super(props);

    window.incoming_material_info = this;
  }

  state = {
    newIncomingMaterialInfoData: {
      incomingMaterialInfoId_temp: [],
      srNo_temp: [],
      materialType_temp: [],
      materialSubType_temp: [],
      materialName_temp: [],
      description_temp: [],
      accountCode_temp: [],
      umo_temp: [],
      prQty_temp: [],
      poQty_temp: [],
      balanceQty_temp: [],
      deliveredQty_temp: [],

      unitRate_temp: [],

      totalLandingCost_temp: [],

      incomingMaterial: {
        incomingMaterialId: ""
      }
    }
  };

  componentWillMount() {
    let incomingMaterialId = sessionStorage.getItem("incomingMaterialId");

    if (incomingMaterialId != null) {
      this.props.delete(this.props.memberPreviousOrgList[0]);
    }
  }

  getAllRecords() {
    let { newIncomingMaterialInfoData } = this.state;

    this.props.memberPreviousOrgList.map((val, idx) => {
      newIncomingMaterialInfoData.srNo_temp[idx] = val.srNo;
      newIncomingMaterialInfoData.materialType_temp[idx] = val.materialType;
    });

    this.setState(newIncomingMaterialInfoData);
  }

  addIncomingMaterialInfoData(incomingMaterialId) {
    //alert('INDEX : '+this.props.index)
    let { newIncomingMaterialInfoData } = this.state;
    alert(
      "newIncomingMaterialInfoData : " +
        JSON.stringify(this.state.newIncomingMaterialInfoData)
    );
    newIncomingMaterialInfoData.incomingMaterial.incomingMaterialId = incomingMaterialId;
    this.setState(newIncomingMaterialInfoData);

    //alert(JSON.stringify(newIncomingMaterialInfoData));

    axiosInstance
      .post("/incoming-material-info/add/", newIncomingMaterialInfoData)
      .then(response => {
        //console.log(response.data);
        NotificationManager.success(
          "Incoming Material Info Added Successfully!",
          ""
        );
      });
  }

  updateIncomingMaterialInfoData(incomingMaterialId) {
    //alert('INDEX : '+this.props.index)
    let { newIncomingMaterialInfoData } = this.state;
    newIncomingMaterialInfoData.incomingMaterial.incomingMaterialId = incomingMaterialId;
    this.setState(newIncomingMaterialInfoData);

    //alert(JSON.stringify(newIncomingMaterialInfoData));

    axiosInstance
      .put("/incoming-material-info/update/", newIncomingMaterialInfoData)
      .then(response => {
        //console.log(response.data);
        NotificationManager.success(
          "Incoming Material Info Updated Successfully!",
          ""
        );
      });

    sessionStorage.removeItem("incomingMaterialId");
  }

  removeRow(data, index) {
    let { newIncomingMaterialInfoData } = this.state;

    //   console.log('##VALUE : '+JSON.stringify(val.incomingMaterialInfoId));
    newIncomingMaterialInfoData.incomingMaterialInfoId_temp.splice(index, 1);

    newIncomingMaterialInfoData.srNo_temp.splice(index, 1);
    newIncomingMaterialInfoData.materialType_temp.splice(index, 1);
    newIncomingMaterialInfoData.materialSubType_temp.splice(index, 1);
    newIncomingMaterialInfoData.accountCode_temp.splice(index, 1);
    newIncomingMaterialInfoData.materialName_temp.splice(index, 1);
    newIncomingMaterialInfoData.description_temp.splice(index, 1);
    newIncomingMaterialInfoData.prQty_temp.splice(index, 1);
    newIncomingMaterialInfoData.poQty_temp.splice(index, 1);
    newIncomingMaterialInfoData.balanceQty_temp.splice(index, 1);
    newIncomingMaterialInfoData.deliveredQty_temp.splice(index, 1);
    newIncomingMaterialInfoData.unitRate_temp.splice(index, 1);
    newIncomingMaterialInfoData.totalLandingCost_temp.splice(index, 1);

    this.setState(newIncomingMaterialInfoData);
    this.props.delete(data);
  }

  render() {
    let { newIncomingMaterialInfoData } = this.state;
    //alert("memberPreviousOrgList : " + this.props.memberPreviousOrgList.length);
    return this.props.memberPreviousOrgList.map((val, idx) => {
      let incomingMaterialInfoId = `incomingMaterialInfoId-${idx}`,
        srNo = `srNo-${idx}`,
        materialType = `materialType-${idx}`,
        materialSubType = `materialSubType-${idx}`,
        accountCode = `accountCode-${idx}`,
        materialName = `materialName-${idx}`,
        description = `description-${idx}`,
        umo = `umo-${idx}`,
        prQty = `prQty-${idx}`,
        poQty = `poQty-${idx}`,
        balanceQty = `balanceQty-${idx}`,
        deliveredQty = `deliveredQty-${idx}`,
        unitRate = `unitRate-${idx}`,
        totalLandingCost = `totalLandingCost-${idx}`;

      {
        newIncomingMaterialInfoData.incomingMaterialInfoId_temp[idx] =
          val.incomingMaterialInfoId;
        newIncomingMaterialInfoData.srNo_temp[idx] = val.srNo;
        newIncomingMaterialInfoData.materialType_temp[idx] = val.materialType;
        newIncomingMaterialInfoData.materialSubType_temp[idx] =
          val.materialSubType;
        newIncomingMaterialInfoData.accountCode_temp[idx] = val.accountCode;
        newIncomingMaterialInfoData.materialName_temp[idx] = val.materialName;
        newIncomingMaterialInfoData.description_temp[idx] = val.description;
        newIncomingMaterialInfoData.prQty_temp[idx] = val.prQty;
        newIncomingMaterialInfoData.poQty_temp[idx] = val.poQty;
        newIncomingMaterialInfoData.balanceQty_temp[idx] = val.balanceQty;
        newIncomingMaterialInfoData.deliveredQty_temp[idx] = val.deliveredQty;
        newIncomingMaterialInfoData.unitRate_temp[idx] = val.unitRate;
        newIncomingMaterialInfoData.totalLandingCost_temp[idx] =
          val.totalLandingCost;
      }

      return (
        <tr key={val.index}>
          <td>
            <div className="form-group">
              <Input
                type="text"
                name="srNo"
                id={srNo}
                data-id={idx}
                className="form-control"
                value={val.srNo}
                onChange={e => {
                  newIncomingMaterialInfoData.srNo_temp[idx] = e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="materialType"
                id={materialType}
                data-id={idx}
                className="form-control "
                value={val.materialType}
                onChange={e => {
                  newIncomingMaterialInfoData.materialType_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
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
                value={val.materialSubType}
                onChange={e => {
                  newIncomingMaterialInfoData.materialSubType_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
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
                value={val.accountCode}
                onChange={e => {
                  newIncomingMaterialInfoData.accountCode_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
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
                  newIncomingMaterialInfoData.materialName_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
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
                  newIncomingMaterialInfoData.description_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
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
                  newIncomingMaterialInfoData.umo_temp[idx] = e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="prQty"
                id={prQty}
                data-id={idx}
                className="form-control"
                value={val.prQty}
                onChange={e => {
                  newIncomingMaterialInfoData.prQty_temp[idx] = e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="poQty"
                id={poQty}
                data-id={idx}
                className="form-control"
                value={
                  newIncomingMaterialInfoData.poQty_temp[idx] != undefined
                    ? newIncomingMaterialInfoData.poQty_temp[idx]
                    : val.poQty
                }
                onChange={e => {
                  newIncomingMaterialInfoData.poQty_temp[idx] = e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="balanceQty"
                id={balanceQty}
                data-id={idx}
                className="form-control"
                value={
                  this.state.newIncomingMaterialInfoData.balanceQty_temp[idx] !=
                  undefined
                    ? this.state.newIncomingMaterialInfoData.balanceQty_temp[
                        idx
                      ]
                    : val.balanceQty
                }
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="deliveredQty"
                id={deliveredQty}
                data-id={idx}
                className="form-control"
                value={val.deliveredQty}
                onChange={e => {
                  newIncomingMaterialInfoData.deliveredQty_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="unitRate"
                id={unitRate}
                data-id={idx}
                className="form-control"
                value={val.unitRate}
                onChange={e => {
                  newIncomingMaterialInfoData.unitRate_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="totalLandingCost"
                id={totalLandingCost}
                data-id={idx}
                className="form-control"
                value={val.totalLandingCost}
                onChange={e => {
                  newIncomingMaterialInfoData.totalLandingCost_temp[idx] =
                    e.target.value;
                  this.setState(newIncomingMaterialInfoData);
                }}
              />
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
