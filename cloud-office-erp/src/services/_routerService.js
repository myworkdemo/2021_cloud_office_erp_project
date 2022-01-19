// routes
import Widgets from 'Routes/widgets';
import Pages from 'Routes/pages';
import AdvanceUIComponents from 'Routes/advance-ui-components';
import CalendarComponents from 'Routes/calendar';
import ChartsComponents from 'Routes/charts';
import FormElements from 'Routes/forms';
import Users from 'Routes/users';
import Components from 'Routes/components';
import Tables from 'Routes/tables';
import Icons from 'Routes/icons';
import Maps from 'Routes/maps';
import DragAndDrop from 'Routes/drag-drop';
import Editor from 'Routes/editor';
import Ecommerce from 'Routes/ecommerce';
import Dashboard from 'Routes/dashboard';
import Crm from 'Routes/crm';
import ImageCropper from 'Routes/image-cropper';
import VideoPlayer from 'Routes/video-player';
import Dropzone from 'Routes/dropzone';

// async component
import {
   AsyncAboutUsComponent,
   AsyncChatComponent,
   AsyncMailComponent,
   AsyncTodoComponent,
} from 'Components/AsyncComponent/AsyncComponent';
import NewList from '../routes/new-modules';
import Department from '../routes/cloud-office/department';
import MemberDetails from '../routes/cloud-office/member-details';
import UserRole from '../routes/cloud-office/user-role';

import AddMemberDetails from '../routes/cloud-office/member-details/add-member-details';
import Vendor from '../routes/cloud-office/vendor';
import AddVendor from '../routes/cloud-office/vendor/add-vendors';
import Material from '../routes/cloud-office/material';

import InventorComponents from '../routes/cloud-office/inventory';

export default [
   {
      path: 'dashboard',
      component: Dashboard
   },
   {
      path: 'crm',
      component: Crm
   },
   {
      path: 'widgets',
      component: Widgets
   },
   {
      path: 'ecommerce',
      component: Ecommerce
   },
   {
      path: 'icons',
      component: Icons
   },
   {
      path: 'about-us',
      component: AsyncAboutUsComponent
   },
   {
      path: 'pages',
      component: Pages
   },
   {
      path: 'chat',
      component: AsyncChatComponent
   },
   {
      path: 'mail',
      component: AsyncMailComponent
   },
   {
      path: 'todo',
      component: AsyncTodoComponent
   },
   {
      path: 'charts',
      component: ChartsComponents
   },
   {
      path: 'tables',
      component: Tables
   },
   {
      path: 'maps',
      component: Maps
   },
   {
      path: 'users',
      component: Users
   },
   {
      path: 'ui-components',
      component: Components
   },
   {
      path: 'advanced-component',
      component: AdvanceUIComponents
   },
   {
      path: 'drag-andDrop',
      component: DragAndDrop
   },
   {
      path: 'forms',
      component: FormElements
   },
   {
      path: 'editor',
      component: Editor
   },
   {
      path: 'calendar',
      component: CalendarComponents
   },
   {
      path: 'image-cropper',
      component: ImageCropper
   },
   {
      path: 'video-player',
      component: VideoPlayer
   },
   {
      path: 'dropzone',
      component: Dropzone
   },

   {
      path: 'new-modules',
      component: NewList
   },

   {
      path: 'cloud-office/department',
      component: Department
   },

   {
      path: 'cloud-office/member-details/show-member-details',
      component: MemberDetails
   },

   {
      path: 'cloud-office/user-role',
      component: UserRole
   },

   {
      path: 'cloud-office/member-details/add-member-details',
      component: AddMemberDetails
   },

   {
      path: 'cloud-office/vendor/show-vendors',
      component: Vendor
   },
   
   {
      path: 'cloud-office/vendor/add-vendors',
      component: AddVendor
   },

   {
      path: 'cloud-office/material',
      component: Material
   },


   //----------------Inventory---------------------
   {
      path: 'cloud-office/inventory',
      component: InventorComponents
   },
   
]