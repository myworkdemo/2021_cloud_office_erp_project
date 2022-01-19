import React,{ Component } from "react";
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';
import { IoIosAddCircle } from 'react-icons/io';
import { FaEdit, FaTrash, FaSearch } from 'react-icons/fa';
import DataTable from "../../../components/CustomComponent/DataTable/DataTable";
import {MDBModal, MDBModalBody, MDBModalFooter } from "mdbreact";
import axiosInstance from "../../../util/axiosInstance";
import { Button, IconButton } from '@material-ui/core';

class Vendor extends Component
{

state = {

    vendors: [],
    newVendorDetailsData: {
        vendorId:'',
 
        vendorName:'',
        website:'',
        emailId:'',
        mobileNo:'',
        contactPersonName:''
     },

     resourcePermission: {
      add: false,
      update: false,
      delete: false,
    },

    modal : false,
}

componentWillMount()
{
    this.getAllVendors();
    this.updateResourcePermission();

    if (sessionStorage.getItem("vendorId")) {
      sessionStorage.setItem("vendorId", null)
    }
}

updateResourcePermission(){
  var USER_ROLE = {};

  if (typeof sessionStorage.USER_ROLE !== "undefined") {
    USER_ROLE = JSON.parse(sessionStorage.USER_ROLE);
  }

  
  let resourcePermission = this.state.resourcePermission;

  resourcePermission.add = (USER_ROLE[3].addPermission === 'Y')? false : true;
  resourcePermission.update = (USER_ROLE[3].modifyPermission === 'Y')? false : true;
  resourcePermission.delete = (USER_ROLE[3].deletePermission === 'Y')? false : true;
  
  this.setState(resourcePermission);

  console.log('#USER_ROLE : ResourceName = '+JSON.stringify(USER_ROLE[3].resourceName))
  console.log('#USER_ROLE : Add = '+JSON.stringify(USER_ROLE[3].addPermission))
  console.log('#USER_ROLE : Update = '+JSON.stringify(USER_ROLE[3].modifyPermission))
  console.log('#USER_ROLE : Delete = '+JSON.stringify(USER_ROLE[3].deletePermission))
}

getAllVendors()
{
    let list = [];

    axiosInstance.get('/vendor/getAll').then((response) => {

      response.data.map(val => {

       list.push({
        vendorName: val.vendorName,
        emailId: val.emailId,
        mobileNo: val.mobileNo,
        contactPersonName: val.contactPersonName,
        website: val.website,
         action: [
                  <IconButton aria-label="edit" style={{color: '#4285F4'}} size="small" onClick={this.editVendor.bind(this, val.vendorId)} disabled={this.state.resourcePermission.update}> <FaEdit /> </IconButton>,
                  <IconButton aria-label="delete" style={{color: '#fb3640'}} size="small" onClick={this.toggle(val.vendorId)} disabled={this.state.resourcePermission.delete}> <FaTrash /> </IconButton>
                ]
       });

      })

      this.setState({vendors : list})

    });
}

deleteVendor(vendorId)
{
    axiosInstance.delete('/vendor/delete/'+vendorId).then((response) => {

        this.getAllVendors();
    });
    
    this.setState({ modal: !this.state.modal });
}

editVendor(vendorId)
{
    sessionStorage.setItem('vendorId', vendorId);
    this.props.history.push("/app/cloud-office/vendor/add-vendors");
}

onClick = () => this.props.history.push("/app/cloud-office/vendor/add-vendors");

toggle = nr => () => {
 
  let {newVendorDetailsData} = this.state;
  newVendorDetailsData.vendorId = nr;
  this.setState(newVendorDetailsData);

  this.setState({
    modal: !this.state.modal
  });
}

render(){

        const columns = [
            {
              label: 'Vendor Name',
              field: 'vendorName',
              sort: 'asc',
              width: 150
            },
            {
              label: 'Email Id',
              field: 'emailId',
              sort: 'asc',
              width: 270
            },
            {
                label: 'Mobile No.',
                field: 'mobileNo',
                sort: 'asc',
                width: 270
              },
              {
                label: 'Contact PersonName',
                field: 'contactPersonName',
                sort: 'asc',
                width: 270
              },
              {
                label: 'Website',
                field: 'website',
                sort: 'asc',
                width: 270
              },
            {
              label: 'Action',
              field: 'action',
              sort: 'asc',
              width: 270
            },
          ];


     return(

<div className="formelements-wrapper">
  <RctCollapsibleCard heading="All Vendors">

    <Button variant="contained" className="btn-primary text-white mr-10 mb-10" onClick={this.onClick} disabled={this.state.resourcePermission.add}> 
      <IoIosAddCircle size="25px" style={{verticalAlign:"middle"}} /> 
      Add Vendor
    </Button>

<DataTable name="Vendor" columns={columns} rows={this.state.vendors} />

<MDBModal isOpen={this.state.modal} toggle={this.toggle(0)} size="sm" centered>
        <MDBModalBody className="text-center">
         Are sure you want to delete this record?
        </MDBModalBody>
        <MDBModalFooter className="text-center">
          <Button variant="contained" className="btn-primary text-white mr-10 mb-10" onClick={() => this.deleteVendor(this.state.newVendorDetailsData.vendorId)}>Yes</Button>
          <Button variant="contained" className="btn-default text-white mr-10 mb-10" onClick={this.toggle(0)}>No</Button>
        </MDBModalFooter>
      </MDBModal>

</RctCollapsibleCard>
     </div>

        )
    }
}

export default Vendor;