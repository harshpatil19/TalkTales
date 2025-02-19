//Check if user is logged in
export const isLoggedIn = () => {
    const response = localStorage.getItem("response");
    return response !== null;
  }
//If no then doLogin
export const doLogin = (response, next) => {
    if (response.user) {
      const user = { ...response.user, id: response.user.userid }; // Rename userid to id
      localStorage.setItem("response", JSON.stringify(user));
      next();
    } else {
      console.error("Invalid login response:", response);
    }
  };
  
  
//and doLogout to remove the user
export const doLogout = (next) => {
    localStorage.removeItem("response"); // Correctly remove item
    next(); // Execute callback function after logout
  };



// Get current logged-in user
export const getCurrentUserDetail = () => {
    if (isLoggedIn()) {
      const response = localStorage.getItem("response");
      if (response && response !== "undefined") {
        try {
          const user = JSON.parse(response);
          if (user && (user.id || user.userid)) {
            return { ...user, id: user.id || user.userid }; // Ensure id exists
          } else {
            console.log("User data does not have a valid id.");
            return undefined;
          }
        } catch (error) {
          console.error("Error parsing user data:", error);
          return undefined;
        }
      } else {
        console.log("No valid user data in localStorage.");
        return undefined;
      }
    }
    return undefined;
  };
  


