/**
 * Sidebar Content
 */
import React, { Component } from 'react';
import List from '@material-ui/core/List';
import ListSubheader from '@material-ui/core/ListSubheader';
import { withRouter } from 'react-router-dom';
import { connect } from 'react-redux';

import IntlMessages from 'Util/IntlMessages';

import NavMenuItem from './NavMenuItem';

// redux actions
import { onToggleMenu } from 'Actions';

class SidebarContent extends Component {

constructor(){
    super();
}

    toggleMenu(menu, stateCategory) {
        let data = {
            menu,
            stateCategory
        }
        this.props.onToggleMenu(data);
    }

    render() {
        const { sidebarMenus } = this.props.sidebar;
        return (
            <div className="rct-sidebar-nav">
                <nav className="navigation">
 
                    <List
                        className="rct-mainMenu p-0 m-0 list-unstyled"
                        subheader={<ListSubheader className="side-title" component="li">
                            <IntlMessages id="sidebar.masters" /></ListSubheader>}
                    >
                        {sidebarMenus.category1.map((menu, key) => (
                            <NavMenuItem
                                menu={menu}
                                key={key}
                                onToggleMenu={() => this.toggleMenu(menu, 'category1')}
                            />
                        ))}

                       
                        {sidebarMenus.category2.map((menu, key) => (
                            <NavMenuItem
                                menu={menu}
                                key={key}
                                onToggleMenu={() => this.toggleMenu(menu, 'category2')}
                            />
                        ))}

                    </List>

                 {/*   <List
                        className="rct-mainMenu p-0 m-0 list-unstyled"
                        subheader={<ListSubheader className="side-title" component="li">
                        <IntlMessages id="sidebar.masters" /></ListSubheader>}
                    >
                        {sidebarMenus.category5.map((menu, key) => (
                            <NavMenuItem
                                menu={menu}
                                key={key}
                                onToggleMenu={() => this.toggleMenu(menu, 'category5')}
                            />
                        ))}
                    </List>

                        */}

                </nav>
            </div>
        );
    }
}

// map state to props
const mapStateToProps = ({ sidebar }) => {
    return { sidebar };
};

export default withRouter(connect(mapStateToProps, {
    onToggleMenu
})(SidebarContent));
