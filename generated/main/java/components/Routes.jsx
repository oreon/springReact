

import {BrowserRouter as Router, Route, NavLink, Switch, Redirect} from 'react-router-dom';



	import {EmployeeList,EditEmployee, ViewEmployee} from './Employee';

	import {DepartmentList,EditDepartment, ViewDepartment} from './Department';

	import {CustomerList,EditCustomer, ViewCustomer} from './Customer';

	import {CustomerOrderList,EditCustomerOrder, ViewCustomerOrder} from './CustomerOrder';

	import {ProductList,EditProduct, ViewProduct} from './Product';

	import {OrderItemList,EditOrderItem, ViewOrderItem} from './OrderItem';

	import {PaymentMethodList,EditPaymentMethod, ViewPaymentMethod} from './PaymentMethod';

	import {CreditCardList,EditCreditCard, ViewCreditCard} from './CreditCard';

	import {PayPalList,EditPayPal, ViewPayPal} from './PayPal';

	import {AppUserList,EditAppUser, ViewAppUser} from './AppUser';

	import {AppRoleList,EditAppRole, ViewAppRole} from './AppRole';

	import {CaseDefinitionList,EditCaseDefinition, ViewCaseDefinition} from './CaseDefinition';

	import {TaskDefinitionList,EditTaskDefinition, ViewTaskDefinition} from './TaskDefinition';

	import {FieldList,EditField, ViewField} from './Field';

	import {CaseInstanceList,EditCaseInstance, ViewCaseInstance} from './CaseInstance';

	import {TaskInstanceList,EditTaskInstance, ViewTaskInstance} from './TaskInstance';


const CommerceEntitiyLinks = () => (
    <div>
    <h1> commerce </h1>
        <nav>
        	
            <NavLink to="/entities/Employees" activeClassName="active">Employees </NavLink>
        	
            <NavLink to="/entities/Departments" activeClassName="active">Departments </NavLink>
        	
            <NavLink to="/entities/Customers" activeClassName="active">Customers </NavLink>
        	
            <NavLink to="/entities/CustomerOrders" activeClassName="active">CustomerOrders </NavLink>
        	
            <NavLink to="/entities/Products" activeClassName="active">Products </NavLink>
        	
            <NavLink to="/entities/OrderItems" activeClassName="active">OrderItems </NavLink>
        	
            <NavLink to="/entities/PaymentMethods" activeClassName="active">PaymentMethods </NavLink>
        	
            <NavLink to="/entities/CreditCards" activeClassName="active">CreditCards </NavLink>
        	
            <NavLink to="/entities/PayPals" activeClassName="active">PayPals </NavLink>
        	
        </nav>
    </div>
)

const UsersEntitiyLinks = () => (
    <div>
    <h1> users </h1>
        <nav>
        	
            <NavLink to="/entities/AppUsers" activeClassName="active">AppUsers </NavLink>
        	
            <NavLink to="/entities/AppRoles" activeClassName="active">AppRoles </NavLink>
        	
        </nav>
    </div>
)

const WfEntitiyLinks = () => (
    <div>
    <h1> wf </h1>
        <nav>
        	
            <NavLink to="/entities/CaseDefinitions" activeClassName="active">CaseDefinitions </NavLink>
        	
            <NavLink to="/entities/TaskDefinitions" activeClassName="active">TaskDefinitions </NavLink>
        	
            <NavLink to="/entities/Fields" activeClassName="active">Fields </NavLink>
        	
            <NavLink to="/entities/CaseInstances" activeClassName="active">CaseInstances </NavLink>
        	
            <NavLink to="/entities/TaskInstances" activeClassName="active">TaskInstances </NavLink>
        	
        </nav>
    </div>
)



 export  const Home = (props) => (
    <Router>
        <div>
        	
        		CommerceEntitiyLinks
        	
        		UsersEntitiyLinks
        	
        		WfEntitiyLinks
        	
        
            <Switch>
            
           		<Route path="/entities/Employees/edit/:id(\d+)?" component={EditEmployee}/>
           		<Route path="/entities/Employees/view/:id(\d+)?" component={ViewEmployee}/>
        		<Route path="/entities/Employees" component={EmployeeList}/>
           
           		<Route path="/entities/Departments/edit/:id(\d+)?" component={EditDepartment}/>
           		<Route path="/entities/Departments/view/:id(\d+)?" component={ViewDepartment}/>
        		<Route path="/entities/Departments" component={DepartmentList}/>
           
           		<Route path="/entities/Customers/edit/:id(\d+)?" component={EditCustomer}/>
           		<Route path="/entities/Customers/view/:id(\d+)?" component={ViewCustomer}/>
        		<Route path="/entities/Customers" component={CustomerList}/>
           
           		<Route path="/entities/CustomerOrders/edit/:id(\d+)?" component={EditCustomerOrder}/>
           		<Route path="/entities/CustomerOrders/view/:id(\d+)?" component={ViewCustomerOrder}/>
        		<Route path="/entities/CustomerOrders" component={CustomerOrderList}/>
           
           		<Route path="/entities/Products/edit/:id(\d+)?" component={EditProduct}/>
           		<Route path="/entities/Products/view/:id(\d+)?" component={ViewProduct}/>
        		<Route path="/entities/Products" component={ProductList}/>
           
           		<Route path="/entities/OrderItems/edit/:id(\d+)?" component={EditOrderItem}/>
           		<Route path="/entities/OrderItems/view/:id(\d+)?" component={ViewOrderItem}/>
        		<Route path="/entities/OrderItems" component={OrderItemList}/>
           
           		<Route path="/entities/PaymentMethods/edit/:id(\d+)?" component={EditPaymentMethod}/>
           		<Route path="/entities/PaymentMethods/view/:id(\d+)?" component={ViewPaymentMethod}/>
        		<Route path="/entities/PaymentMethods" component={PaymentMethodList}/>
           
           		<Route path="/entities/CreditCards/edit/:id(\d+)?" component={EditCreditCard}/>
           		<Route path="/entities/CreditCards/view/:id(\d+)?" component={ViewCreditCard}/>
        		<Route path="/entities/CreditCards" component={CreditCardList}/>
           
           		<Route path="/entities/PayPals/edit/:id(\d+)?" component={EditPayPal}/>
           		<Route path="/entities/PayPals/view/:id(\d+)?" component={ViewPayPal}/>
        		<Route path="/entities/PayPals" component={PayPalList}/>
           
           		<Route path="/entities/AppUsers/edit/:id(\d+)?" component={EditAppUser}/>
           		<Route path="/entities/AppUsers/view/:id(\d+)?" component={ViewAppUser}/>
        		<Route path="/entities/AppUsers" component={AppUserList}/>
           
           		<Route path="/entities/AppRoles/edit/:id(\d+)?" component={EditAppRole}/>
           		<Route path="/entities/AppRoles/view/:id(\d+)?" component={ViewAppRole}/>
        		<Route path="/entities/AppRoles" component={AppRoleList}/>
           
           		<Route path="/entities/CaseDefinitions/edit/:id(\d+)?" component={EditCaseDefinition}/>
           		<Route path="/entities/CaseDefinitions/view/:id(\d+)?" component={ViewCaseDefinition}/>
        		<Route path="/entities/CaseDefinitions" component={CaseDefinitionList}/>
           
           		<Route path="/entities/TaskDefinitions/edit/:id(\d+)?" component={EditTaskDefinition}/>
           		<Route path="/entities/TaskDefinitions/view/:id(\d+)?" component={ViewTaskDefinition}/>
        		<Route path="/entities/TaskDefinitions" component={TaskDefinitionList}/>
           
           		<Route path="/entities/Fields/edit/:id(\d+)?" component={EditField}/>
           		<Route path="/entities/Fields/view/:id(\d+)?" component={ViewField}/>
        		<Route path="/entities/Fields" component={FieldList}/>
           
           		<Route path="/entities/CaseInstances/edit/:id(\d+)?" component={EditCaseInstance}/>
           		<Route path="/entities/CaseInstances/view/:id(\d+)?" component={ViewCaseInstance}/>
        		<Route path="/entities/CaseInstances" component={CaseInstanceList}/>
           
           		<Route path="/entities/TaskInstances/edit/:id(\d+)?" component={EditTaskInstance}/>
           		<Route path="/entities/TaskInstances/view/:id(\d+)?" component={ViewTaskInstance}/>
        		<Route path="/entities/TaskInstances" component={TaskInstanceList}/>
           
            </Switch>
        </div>
    </Router>
)

  
  