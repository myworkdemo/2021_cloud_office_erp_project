// sidebar nav links
export default {
  category1: [
    {
      menu_title: "sidebar.masters",
      menu_icon: "zmdi zmdi-file-plus",
      type_multi: null,
      new_item: false,
      child_routes: [
        {
          path: "/app/cloud-office/user-role",
          new_item: false,
          menu_title: "sidebar.masterUserRole"
        },
        {
          path: "/app/cloud-office/department",
          new_item: false,
          menu_title: "sidebar.masterDepartment"
        },
        {
          path: "/app/cloud-office/member-details/show-member-details",
          new_item: false,
          menu_title: "sidebar.masterMemberDetails"
        },
        /*  {
               "path": "/app/cloud-office/member-details/add-member-details",
               "new_item": false,
               "menu_title": "sidebar.masterAddMemberDetails"
            },
            */
        {
          path: "/app/cloud-office/vendor/show-vendors",
          new_item: false,
          menu_title: "sidebar.masterVendor"
        },
        {
          path: "/app/cloud-office/material",
          new_item: false,
          menu_title: "sidebar.masterMaterial"
        }
      ]
    }
  ],

  category2: [
    {
      menu_title: "sidebar.inventory",
      menu_icon: "zmdi zmdi-calendar-note",
      type_multi: null,
      new_item: false,
      child_routes: [
        {
          path:
            "/app/cloud-office/inventory/material-requisition/all-purchase-requisition",
          new_item: false,
          menu_title: "components.purchaseRequisition"
        },
        {
          path: "/app/cloud-office/inventory/PurchaseOrder/all-purchase-orders",
          new_item: false,
          menu_title: "components.allPurchaseOrders"
        },
        {
          path: "/app/cloud-office/inventory/material-qc/all-material-qc",
          new_item: false,
          menu_title: "components.materialQc"
        },
        {
          path:
            "/app/cloud-office/inventory/material-issue-note/all-material-issue-note",
          new_item: false,
          menu_title: "components.materialIssueNote"
        },
        {
          path:
            "/app/cloud-office/inventory/incoming-material/all-incoming-material",
          new_item: false,
          menu_title: "components.incomingMaterial"
        }
      ]
    }
  ]
};
