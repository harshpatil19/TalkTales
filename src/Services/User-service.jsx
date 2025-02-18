import { myAxios } from "./Helper";

// Function to handle sign-up
export const signUp = (user) => {
  return myAxios.post("/api/users/register", user)
    .then((response) => response.data)
    .catch((error) => console.log("Error during sign-up:", error));
};

// Function to handle login
export const loginUser = (loginDetail) => {
  return myAxios.post("/api/users/login", loginDetail)
    .then((response) => response.data)
    .catch((error) => {
      console.log("Error during login:", error);
    });
};
