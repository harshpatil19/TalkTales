import React, { useEffect, useState } from "react";
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
} from "reactstrap"; // Import missing components

const Signup = () => {

const [data,setData]=useState({
    name:'',
    email:'',
    password:'',
    about:'',
})
const[error,setError]=useState({
    errors:{},
    isError:false
})
useEffect(()=>{
    console.log(data);
},[data]);

//handle change
const handleChange=(event,property)=>{
  setData({...data,[property]:event.target.value})
 
}
//to set data to blank
const resetData=()=>{
    setData({
     name:'',
    email:'',
    password:'',
    about:'',

    })
}

const submitForm=(event)=>{
    event.preventDefault();
    console.log(data);
}



  return (
    <Base>
      <Container>
        <Row> 
          <Col>
            <Card>
              <CardHeader>
                <h3 className="text-center">Register</h3>
              </CardHeader>
              <Form className="p-4" onSubmit={submitForm}>
                {/* Name Field */}
                <FormGroup>
                  <Label for="name">Enter Name</Label>
                  <Input type="text" id="name" placeholder="Enter here" 
                  onChange={(e)=>handleChange(e,'name')}
                  value={data.name}/>
                </FormGroup>

                {/* Email Field */}
                <FormGroup>
                  <Label for="email">Enter Email</Label>
                  <Input type="email" id="email" placeholder="Enter email"
                  onChange={(e)=>handleChange(e,'email')} 
                  value={data.email}/>
                  
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for="password">Enter Password</Label>
                  <Input
                    type="password"
                    id="password"
                    placeholder="Enter password"
                    onChange={(e)=>handleChange(e,'password')}
                    value={data.password} />
                </FormGroup>

                {/* Text Area */}
                <FormGroup>
                  <Label for="about">Text Area</Label>
                  <Input id="about" name="text" type="textarea" 
                  placeholder="Enter text"
                  onChange={(e)=>handleChange(e,'about')}
                  value={data.about}/>
                </FormGroup>

                {/* Buttons */}
                <Container className="text-center">
                  <Button outline color="success" size="sm">Register</Button>
                  <Button onClick={resetData} outline color="danger" type="reset" className="ms-2" size="sm">
                    Reset
                  </Button>
                </Container>
              </Form>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Signup;
