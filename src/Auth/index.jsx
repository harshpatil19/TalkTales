//Check if user is logged in
export const isLoggedIn=()=>{
    let response=localStorage.getItem("response");
    if(response != null){
        return true;
    }else{
        return false;
    }
};
//If no then doLogin
export const doLogin=(response,next)=>{
    localStorage.setItem("response",JSON.stringify(response));
    next();
}
//and doLogout to remove the user
export const doLogout = (next) => {
    localStorage.removeItem("response"); // Correct way to remove item
    next(); // Execute callback function after logout
};



//get current loggedin user
export const getCurrentUserDetail = () => {
    if (isLoggedIn()) {
        let response = localStorage.getItem("response");
        if (response) {
            try {
                return JSON.parse(response); // ✅ No `.user` if data is stored directly
            } catch (error) {
                console.error("Error parsing user data:", error);
                return undefined;
            }
        }
    }
    return undefined;
};
