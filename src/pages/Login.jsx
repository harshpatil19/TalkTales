import React from "react";
import Base from "../components/Base";
import {
  Container,
  Card,
  CardHeader,
  Form,
  FormGroup,
  Label,
  Input,
  Button,
  Row,
  Col,
  Toast,
} from "reactstrap";
import { useState } from "react";
import { toast } from "react-toastify";
import { loginUser } from "../Services/user-service";
import { doLogin } from "../Auth";
import { useNavigate } from "react-router-dom";

const Login = () => {

const navigate=useNavigate()



  const [LoginDetail, setLoginDetail] = useState({
    email: "",
    password: "",
  });

  const handleChange = (event, field) => {
    let actualValue = event.target.value;
    setLoginDetail({ ...LoginDetail, [field]: actualValue });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(LoginDetail);
    //validation
    if (LoginDetail.email.trim() === "" || LoginDetail.password.trim() === "") {
      toast.error("Email or Password cannot be empty");
      return;
    }

    //submit data to server
    loginUser(LoginDetail)
      .then((response) => {
        console.log("User login successfully:", response);
        //save data to localstorage
        doLogin(response,()=>{
          console.log("login detail is saved to local storage")
          navigate("/user/dashboard")
        })

      })
      .catch((error) => {
        console.log("Error occurred:", error); // Check if error is logged
        
        // Check if error.response is available
        if (error.response) {
          console.log("Error response:", error.response); // Log error response for debugging
          // Handle specific error statuses like 401 and 404
          if (error.response.status === 401 || error.response.status === 404) {
            toast.error(error.response.data.message || "Unauthorized access.");
          } else {
            toast.error("Something went wrong..!!!");
          }
        } else {
          // Handle other types of errors (e.g., network issues)
          console.log("No response from server, network error?");
          toast.error("Network error or something went wrong.");
        }
      });
  };

  const handleReset = () => {
    setLoginDetail({
      email: "",
      password: "",
    });
  };

  return (
    <Base>
      <Container>
        <Card>
          <CardHeader>
            <h3 className="text-center">Login</h3>
          </CardHeader>
          <Form className="p-4" onSubmit={handleFormSubmit}>
            <FormGroup>
              <Label for="exampleEmail">Email</Label>
              <Input
                id="exampleEmail"
                name="email"
                placeholder="Email"
                type="email"
                value={LoginDetail.email}
                onChange={(e) => handleChange(e, "email")}
              />
            </FormGroup>{" "}
            <FormGroup>
              <Label for="examplePassword">Password</Label>
              <Input
                id="examplePassword"
                name="password"
                placeholder="Password"
                type="password"
                value={LoginDetail.password}
                onChange={(e) => handleChange(e, "password")}
              />
            </FormGroup>{" "}
            <Container className="text-center">
              <Button outline color="success" size="sm">
                Submit
              </Button>
              <Button
                outline
                color="danger"
                type="reset"
                className="ms-2"
                size="sm"
                onClick={handleReset}
              >
                Reset
              </Button>
            </Container>
          </Form>
        </Card>
      </Container>
    </Base>
  );
};

export default Login;
