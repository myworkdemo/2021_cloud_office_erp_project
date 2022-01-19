import React, { Component } from "react";
import axios from "axios";
import { IoIosAddCircle, IoIosRemoveCircle } from "react-icons/io";
import { MDBBtn, MDBTooltip } from "mdbreact";
import { Tooltip, Zoom } from "@material-ui/core";
import { Label, Input } from "reactstrap";
import axiosInstance from "../../../../../../util/axiosInstance";
import {
  NotificationManager,
  NotificationContainer
} from "react-notifications";

export default class MemberPreviousOrgList extends Component {
  constructor() {
    super();

    window.member_previous_orgList = this;
  }

  state = {
    temp_companyNames: [],
    temp_startDate: [],
    temp_endDate: [],
    temp_description: [],
    temp_ctc: [],

    newMemberEmploymentDetailsData: {
      memberEmploymentId_temp: [],

      companyNames_temp: [],
      startDate_temp: [],
      endDate_temp: [],
      description_temp: [],
      ctc_temp: [],

      memberDetails: {
        memberEmploymentId: null
      }
    }
  };

  componentWillMount() {
    this.getAllMemberDetails();

    //this.addPN();
    //this.props.add();

    this.props.delete(this.props.memberPreviousOrgList[0]);
    //alert('OrgList : '+this.props.memberPreviousOrgList.length);
  }

  addNewMemberDetails() {
    let { newMemberEmploymentDetailsData } = this.state;

    let memberDetailsId = sessionStorage.getItem("USER_EDIT_ID");
    newMemberEmploymentDetailsData.memberDetails.memberDetailsId = memberDetailsId;
    this.setState(newMemberEmploymentDetailsData);

    console.log(
      this.state.newMemberEmploymentDetailsData.memberDetails.memberDetailsId
    );

    axiosInstance
      .post("/member-employment-details/add/", newMemberEmploymentDetailsData)
      .then(response => {
        //console.log(response.data);
        NotificationManager.success(
          "Pre-Organizational Details Added Successfully!",
          ""
        );
      });
  }

  getAllMemberDetails() {
    axiosInstance
      .get("/member-details/getById/" + sessionStorage.getItem("USER_EDIT_ID"))
      .then(response => {
        this.setState({
          newMemberDetailsData: response.data
        });
      });
  }

  addPN = () => {
    console.log("#addPN() : " + this.props.memberPreviousOrgList.length);
    // alert('#size: '+this.props.memberPreviousOrgList.length)
    this.props.memberPreviousOrgList.map((val, index) => {
      if (!!!val.companyNames) {
        //alert("# IS NULL...");
        this.getMemberEmploymentList();
      }

      //alert("Val : "+val.companyNames)

      /*    this.setState({
        temp_companyNames: this.state.temp_companyNames.concat(
          val.companyNames
        ),
        temp_startDate: this.state.temp_startDate.concat(val.startDate),
        temp_endDate: this.state.temp_endDate.concat(val.endDate),
        temp_description: this.state.temp_description.concat(val.description),
        temp_ctc: this.state.temp_ctc.concat(val.ctc)
      });
*/
    });
  };

  getMemberEmploymentList() {
    axiosInstance
      .get(
        "/member-details/getMemberEmploymentDetailsList/byMemberId/" +
          sessionStorage.getItem("USER_EDIT_ID")
      )
      .then(response => {
        let index, companyNames, startDate, endDate, description;
        //alert('OrgList : '+response.data);
        // let memberPreviousOrgList = [...prevState.memberPreviousOrgList, { index: Math.random(), companyNames: "", startDate: "", endDate: "", description: "" }];

        response.data.map((val, idx) => {
          //alert(val.companyNames)
          //console.log(val.companyNames !== undefined+" : "+idx+" is null")
          console.log(val.companyNames !== undefined);
          if (
            val.companyNames !== null ||
            val.companyNames !== "" ||
            val.companyNames !== undefined
          ) {
            this.setState({
              temp_companyNames: this.state.temp_companyNames.concat(
                val.companyNames
              ),
              temp_startDate: this.state.temp_startDate.concat(val.startDate),
              temp_endDate: this.state.temp_endDate.concat(val.endDate),
              temp_description: this.state.temp_description.concat(
                val.description
              ),
              temp_ctc: this.state.temp_ctc.concat(val.ctc)
            });
          }
        });

        //this.props.add();
        console.log(this.state.temp_companyNames.length);
      });
  }

  removeRow(data, index) {
    let { newMemberEmploymentDetailsData } = this.state;

    //   console.log('##VALUE : '+JSON.stringify(val.incomingMaterialInfoId));
    newMemberEmploymentDetailsData.memberEmploymentId_temp.splice(index, 1);

    newMemberEmploymentDetailsData.companyNames_temp.splice(index, 1);
    newMemberEmploymentDetailsData.startDate_temp.splice(index, 1);
    newMemberEmploymentDetailsData.endDate_temp.splice(index, 1);
    newMemberEmploymentDetailsData.description_temp.splice(index, 1);
    newMemberEmploymentDetailsData.ctc_temp.splice(index, 1);

    this.setState(newMemberEmploymentDetailsData);
    this.props.delete(data);
  }

  render() {
    let { newMemberEmploymentDetailsData } = this.state;

    return this.props.memberPreviousOrgList.map((val, idx) => {
      let memberEmploymentId = `memberEmploymentId-${idx}`,
        companyNames = `companyNames-${idx}`,
        startDate = `startDate-${idx}`,
        endDate = `endDate-${idx}`,
        description = `description-${idx}`,
        ctc = `ctc-${idx}`;

      {
        newMemberEmploymentDetailsData.memberEmploymentId_temp[idx] =
          val.memberEmploymentId;

        newMemberEmploymentDetailsData.companyNames_temp[idx] =
          val.companyNames;
        newMemberEmploymentDetailsData.startDate_temp[idx] = val.startDate;
        newMemberEmploymentDetailsData.endDate_temp[idx] = val.endDate;
        newMemberEmploymentDetailsData.description_temp[idx] = val.description;
        newMemberEmploymentDetailsData.ctc_temp[idx] = val.ctc;
      }

      return (
        <tr key={val.index}>
          <td>
            <div className="form-group">
              <Input
                type="text"
                name="memberEmploymentId"
                data-id={idx}
                id={memberEmploymentId}
                className="form-control"
                value={val.memberEmploymentId}
                readOnly
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="companyNames"
                data-id={idx}
                id={companyNames}
                className="form-control"
                value={val.companyNames}
                onChange={e => {
                  newMemberEmploymentDetailsData.companyNames_temp[idx] =
                    e.target.value;
                  this.setState(newMemberEmploymentDetailsData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="date"
                name="startDate"
                id={startDate}
                data-id={idx}
                className="form-control "
                value={val.startDate}
                onChange={e => {
                  newMemberEmploymentDetailsData.startDate_temp[idx] =
                    e.target.value;
                  this.setState(newMemberEmploymentDetailsData);
                }}
              />
            </div>
          </td>
          <td>
            <div className="form-group">
              <Input
                type="date"
                name="endDate"
                id={endDate}
                data-id={idx}
                className="form-control"
                value={val.endDate}
                onChange={e => {
                  newMemberEmploymentDetailsData.endDate_temp[idx] =
                    e.target.value;
                  this.setState(newMemberEmploymentDetailsData);
                }}
              />
            </div>
          </td>
          <td>
            <div className="form-group">
              <Input
                type="text"
                name="description"
                id={description}
                data-id={idx}
                className="form-control"
                value={val.description}
                onChange={e => {
                  newMemberEmploymentDetailsData.description_temp[idx] =
                    e.target.value;
                  this.setState(newMemberEmploymentDetailsData);
                }}
              />
            </div>
          </td>

          <td>
            <div className="form-group">
              <Input
                type="text"
                name="ctc"
                id={ctc}
                data-id={idx}
                className="form-control"
                value={val.ctc}
                onChange={e => {
                  newMemberEmploymentDetailsData.ctc_temp[idx] = e.target.value;
                  this.setState(newMemberEmploymentDetailsData);
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
