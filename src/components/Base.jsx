import React from "react";
import CustomNavbar from "./CustomNavbar";
import { Container } from "reactstrap";
import { ToastContainer } from "react-toastify";

const Base = ({ title = " ", children }) => {
  return (
    <div>
      <CustomNavbar />
      <ToastContainer /> 
      <Container>
        <h1>{title}</h1>
        {children}
        </Container>
      <footer className="text-center">This is footer</footer>
    </div>
  );
};

export default Base;
