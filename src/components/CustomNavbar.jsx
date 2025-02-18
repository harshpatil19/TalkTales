import React, { useEffect, useState } from "react";
import { NavLink as ReactLink, useNavigate } from "react-router-dom";
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
} from "reactstrap";
import { getCurrentUserDetail, isLoggedIn, doLogout } from "../Auth";

const CustomNavbar = () => {
  const [isOpen, setIsOpen] = useState(false);
  const [login, setLogin] = useState(isLoggedIn()); // Initialize with actual login state
  const [user, setUser] = useState(getCurrentUserDetail()); // Initialize with user details
  const navigate = useNavigate();

  useEffect(() => {
    setLogin(isLoggedIn());
    setUser(getCurrentUserDetail());
  }, [login]);

  const toggle = () => setIsOpen(!isOpen);

  // Handle Logout
  const handleLogout = () => {
    doLogout(() => {
      setLogin(false);
      setUser(undefined);
      navigate("/home"); // Redirect after logout
    });
  };

  return (
    <Navbar color="dark" dark expand="md" className="fixed-top">
      <NavbarBrand tag={ReactLink} to="/">
        TalkTales
      </NavbarBrand>
      <NavbarToggler onClick={toggle} />
      <Collapse isOpen={isOpen} navbar>
        <Nav className="me-auto" navbar>
          <NavItem>
            <NavLink tag={ReactLink} to="/home">
              New Feed
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={ReactLink} to="/about">
              About
            </NavLink>
          </NavItem>
          <NavItem>
            <NavLink tag={ReactLink} to="/services">
              Services
            </NavLink>
          </NavItem>

          <UncontrolledDropdown nav inNavbar>
            <DropdownToggle nav caret>
              More
            </DropdownToggle>
            <DropdownMenu end>
              <DropdownItem>Contact us</DropdownItem>
              <DropdownItem>Facebook</DropdownItem>
              <DropdownItem divider />
              <DropdownItem>Instagram</DropdownItem>
              <DropdownItem>LinkedIn</DropdownItem>
              <DropdownItem>Youtube</DropdownItem>
            </DropdownMenu>
          </UncontrolledDropdown>
        </Nav>

        {/* Authenticated User Menu */}
        <Nav navbar>
          {login ? (
            <>
              <NavItem>
                <NavLink tag={ReactLink} to="/user/profileInfo" style={{ cursor: "pointer" }}>Profile Info</NavLink>
              </NavItem>
              
              <NavItem>
                <NavLink>{user?.email}</NavLink> {/* Avoids undefined error */}
              </NavItem>

              <NavItem>
                <NavLink onClick={handleLogout} style={{ cursor: "pointer" }}>
                  Logout
                </NavLink>
              </NavItem>
            </>
          ) : (
            <>
              <NavItem>
                <NavLink tag={ReactLink} to="/login">
                  Login
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink tag={ReactLink} to="/signup">
                  Signup
                </NavLink>
              </NavItem>
            </>
          )}
        </Nav>
      </Collapse>
    </Navbar>
  );
};

export default CustomNavbar;
