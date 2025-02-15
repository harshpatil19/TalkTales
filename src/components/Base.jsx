import React from "react";
import CustomNavbar from "./CustomNavbar";
import { Container } from "reactstrap";

const Base = ({ title = "Welcome to TalkTales", children }) => {
  return (
    <div>
      <CustomNavbar />
      <Container>
        <h1>{title}</h1>
        {children}
        </Container>
      <footer className="text-center">This is footer</footer>
    </div>
  );
};

export default Base;
