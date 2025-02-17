import React, { useEffect, useState } from "react";
import Base from "../components/base";
import { signUp } from "../Services/user-service";
import { toast } from "react-toastify";
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
  FormFeedback,
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

    if(error.isError){
      toast.error("Form data is invalid");
      setError({...error,isError:false})
      return;
    }
    
    console.log(data);
    signUp(data).then((resp)=>{
      console.log(resp);
      console.log("success log");
      toast.success("User Registered Successfully");

    }).catch((error)=>{
      console.log(error);
      console.log("error log");
      setError({
       errors:error,
       isError:true 
      })
    });
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
                  value={data.name}
                  invalid={ error.errors?.response?.data?.name ? true:false }/>
                  <FormFeedback>
                    {error.errors?.response?.data?.name}
                  </FormFeedback>
                </FormGroup>

                {/* Email Field */}
                <FormGroup>
                  <Label for="email">Enter Email</Label>
                  <Input type="email" id="email" placeholder="Enter email"
                  onChange={(e)=>handleChange(e,'email')} 
                  value={data.email}
                  invalid={ error.errors?.response?.data?.email ? true:false }/>
                  <FormFeedback>
                    {error.errors?.response?.data?.email}
                  </FormFeedback>
                  
                </FormGroup>

                {/* Password Field */}
                <FormGroup>
                  <Label for="password">Enter Password</Label>
                  <Input
                    type="password"
                    id="password"
                    placeholder="Enter password"
                    onChange={(e)=>handleChange(e,'password')}
                    value={data.password} 
                    invalid={ error.errors?.response?.data?.password ? true:false }/>
                  <FormFeedback>
                    {error.errors?.response?.data?.password}
                  </FormFeedback>
                </FormGroup>

                {/* Text Area */}
                <FormGroup>
                  <Label for="about">Text Area</Label>
                  <Input id="about" name="text" type="textarea" 
                  placeholder="Enter text"
                  onChange={(e)=>handleChange(e,'about')}
                  value={data.about}
                  invalid={ error.errors?.response?.data?.about ? true:false }/>
                  <FormFeedback>
                    {error.errors?.response?.data?.about}
                  </FormFeedback>
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
