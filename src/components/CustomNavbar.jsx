import React, { useState } from "react";
import { NavLink as ReactLink} from "react-router-dom";
import {
  Collapse,
  Navbar,
  NavbarToggler,
  NavbarBrand,
  Nav,
  NavItem,
  NavLink,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  NavbarText,
  Container,
} from "reactstrap";

const CustomNavbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen(!isOpen);

  return (
    <Navbar color="dark" dark expand="md" className="fixed-top">
        <NavbarBrand tag={ReactLink} to="/">TalkTales</NavbarBrand>
        <NavbarToggler onClick={toggle} />
        <Collapse isOpen={isOpen} navbar>
          <Nav className="me-auto" navbar>
            <NavItem>
              <NavLink tag={ReactLink} to="/home">Home</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/login">Login</NavLink>
            </NavItem>
            <NavItem>
              <NavLink tag={ReactLink} to="/signup">Signup</NavLink>
            </NavItem>
            <UncontrolledDropdown nav inNavbar>
              <DropdownToggle nav caret>
                Options
              </DropdownToggle>
              <DropdownMenu end>
                <DropdownItem tag={ReactLink} to="/Services">Services</DropdownItem>
                <DropdownItem>Contact us </DropdownItem>
                <DropdownItem divider />
                <DropdownItem>Youtube</DropdownItem>
              </DropdownMenu>
            </UncontrolledDropdown>
          </Nav>
          <NavbarText tag={ReactLink} to="https://www.youtube.com/"> Youtube </NavbarText>
        </Collapse>
    </Navbar>
  );
};

export default CustomNavbar;
