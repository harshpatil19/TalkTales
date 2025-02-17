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
} from "reactstrap";

const Login = () => {

  const [LoginDetail,setLoginDetail]=useState({
    username:'',
    password:''

  })

  const handleChange=(event,field)=>{
let actualValue=event.target.value
setLoginDetail({ ...LoginDetail,[field]:actualValue

})
  }
  return (
    <Base>
      <Container>
        <Card>
          <CardHeader>
          <h3 className="text-center">Login</h3>
          </CardHeader>
            <Form className="p-4">
              <FormGroup>
                <Label for="exampleEmail">
                  Email
                </Label>
                <Input
                  id="exampleEmail"
                  name="email"
                  placeholder="Email"
                  type="email"
                  value={LoginDetail.username}
                  onChange={(e)=> handleChange(e,'username')}
                />
              </FormGroup>{" "}
              <FormGroup>
                <Label for="examplePassword">
                  Password
                </Label>
                <Input
                  id="examplePassword"
                  name="password"
                  placeholder="Password"
                  type="password"
                  value={LoginDetail.password}
                  onChange={(e)=> handleChange(e,'password')}
                />
              </FormGroup>{" "}
              <Container className="text-center">
              <Button outline color="success" size="sm" >Submit</Button>
               <Button outline color="danger" type="reset" className="ms-2" size="sm">Reset</Button>
              </Container>
            </Form>
        </Card>
      </Container>
    </Base>
  );
};

export default Login;
