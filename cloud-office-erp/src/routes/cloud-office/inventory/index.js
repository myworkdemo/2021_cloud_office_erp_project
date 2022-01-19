/**
 * Calendar Routes
 */
import React from "react";
import { Redirect, Route, Switch } from "react-router-dom";
import { Helmet } from "react-helmet";
// async components
import {
  AsyncSelectableComponent,
  AsyncCustomComponent
} from "Components/AsyncComponent/AsyncComponent";
import {
  AsyncAddPurchaseRequisitionInfoComponent,
  AsyncAllMaterialRequisitionComponent,
  AsyncAddPurchaseOrderComponent,
  AsyncAllPurchaseOrdersComponent,
  AsyncAddMaterialQcComponent,
  AsyncAllMaterialQcComponent,
  AsyncAddMaterialIssueNoteComponent,
  AsyncAllMaterialIssueNoteComponent,
  AsyncAddIncomingMaterialComponent,
  AsyncAllIncomingMaterialComponent
} from "../../../components/AsyncComponent/AsyncComponent";

const InventorComponents = ({ match }) => (
  <div className="content-wrapper">
    <Helmet>
      {/* <title>Calendar</title>*/}
      <meta name="description" content="Reactify Calendar" />
    </Helmet>
    <Switch>
      <Redirect
        exact
        from={`${match.url}/`}
        to={`${match.url}/cloud-office/inventory`}
      />

      <Route
        path={`${match.url}/material-requisition/add-purchase-requisition`}
        component={AsyncAddPurchaseRequisitionInfoComponent}
      />
      <Route
        path={`${match.url}/material-requisition/all-purchase-requisition`}
        component={AsyncAllMaterialRequisitionComponent}
      />

      <Route
        path={`${match.url}/PurchaseOrder/add-purchase-order`}
        component={AsyncAddPurchaseOrderComponent}
      />
      <Route
        path={`${match.url}/PurchaseOrder/all-purchase-orders`}
        component={AsyncAllPurchaseOrdersComponent}
      />

      <Route
        path={`${match.url}/material-qc/add-material-qc`}
        component={AsyncAddMaterialQcComponent}
      />
      <Route
        path={`${match.url}/material-qc/all-material-qc`}
        component={AsyncAllMaterialQcComponent}
      />

      <Route
        path={`${match.url}/material-issue-note/add-material-issue-note`}
        component={AsyncAddMaterialIssueNoteComponent}
      />
      <Route
        path={`${match.url}/material-issue-note/all-material-issue-note`}
        component={AsyncAllMaterialIssueNoteComponent}
      />

      <Route
        path={`${match.url}/incoming-material/add-incoming-material`}
        component={AsyncAddIncomingMaterialComponent}
      />
      <Route
        path={`${match.url}/incoming-material/all-incoming-material`}
        component={AsyncAllIncomingMaterialComponent}
      />

      <Route
        path={`${match.url}/selectable`}
        component={AsyncSelectableComponent}
      />
      <Route
        path={`${match.url}/custom-rendering`}
        component={AsyncCustomComponent}
      />
    </Switch>
  </div>
);

export default InventorComponents;
