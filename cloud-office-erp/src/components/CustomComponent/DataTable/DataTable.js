import React, { Component } from 'react';
import { MDBDataTable } from 'mdbreact';
import RctCollapsibleCard from 'Components/RctCollapsibleCard/RctCollapsibleCard';

import '@fortawesome/fontawesome-free/css/all.min.css';
import 'bootstrap-css-only/css/bootstrap.min.css'; 
import 'mdbreact/dist/css/mdb.css';
import { Button } from 'semantic-ui-react';
import { Paper } from '@material-ui/core';

 const DataTable = (props) =>{

const data = {
    columns: props.columns,
    rows: props.rows
  };
 
  return (
    
<div>

<Paper elevation={3}>

<RctCollapsibleCard customClasses="border border-info">

    <MDBDataTable 
      //striped
      bordered
      small
      data={data}
      noBottomColumns={true}
    />

</RctCollapsibleCard>
</Paper>
</div>
  );
}

export default DataTable;