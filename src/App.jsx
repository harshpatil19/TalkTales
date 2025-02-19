import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Services from "./pages/Services";
import About from "./pages/About";
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UserDashboard from "./pages/user-routes/UserDashboard";
import PrivateRoute from "./components/PrivateRoute";
import ProfileInfo from "./pages/user-routes/ProfileInfo";
import PostPage from "./pages/PostPage";
function App() {
  return (
    <>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} /> {/* Default route */}
        <Route path="/home" element={<Home />} />
        <Route path="/about" element={<About />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/services" element={<Services />} />
        <Route path="/post/:postId" element={<PostPage />} />
       
        <Route path="/user" element={<PrivateRoute />}>
        <Route path="dashboard" element={<UserDashboard />} />
        <Route path="profileInfo" element={<ProfileInfo />} />
        </Route> 


      </Routes>
    </BrowserRouter>
     <ToastContainer position="top-center" autoClose={3000} /> 
     </>
  );
}

export default App;
