import React from "react";
import CustomNavbar from "./CustomNavbar";

const Base = ({ title = "Welcome to our Website", children }) => {
  return (
    <div>
      <CustomNavbar />
        <h1>{title}</h1>
        {children}
      <footer>This is footer</footer>
    </div>
  );
};

export default Base;
