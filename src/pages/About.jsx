import React from "react";
import Base from "../components/Base"
import UserContext from "../Context/userContext";


const About = () => {
    return (

   <UserContext.Consumer>
    {
        (user)=>(
            <Base>
                <h1>this is about page</h1>
                <p>Blog website</p>
                <h1>Welcome user:{user.name}</h1>
            </Base>
        )
    }
    </UserContext.Consumer>
  


    );

};
export default About;