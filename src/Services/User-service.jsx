import { myAxios } from "./Helper";

export const signUp=(user)=>{
    return myAxios.post("/api/users/register", user)
    .then((response) => response.data); 

};